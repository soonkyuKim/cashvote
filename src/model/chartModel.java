package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.data.jdbc.JDBCPieDataset;

public class chartModel {

	Connection con;

	// 생성자 호출하면 DB 커넥트
	public chartModel() {
		connectDB();
	}

	void connectDB() {
		try {
			con = ConnectionPool.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 파이 차트를 위한 최종 투표 결과 데이터 쿼리하기 (JDBC 데이터셋)
	public PieDataset JDBC_Pie_dataset(int voteSerial) {

		// JFreeChart 패키지의 JDBC 데이터셋 클래스 이용
		JDBCPieDataset data = new JDBCPieDataset(con);

		//SQL from RESULT_INFO
		String sql = "select "
				+ "o.vote_option_content opt, "
				+ "(select count(*) from result_info r "
					+ "where r.vote_count=1 and r.vote_no = i.vote_no "
						+ "and r.result_choice = o.vote_option_choice) cnt "
				+ "from vote_option o, vote_info i "
				+ "where i.vote_no = "
				+ voteSerial
				+ "and o.vote_no = i.vote_no "
				+ "order by o.vote_option_choice";
		
		try {
			data.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	//거미줄형 차트를 위한 데이터셋 쿼리
	public CategoryDataset JDBC_Spi_dataset(int voteSerial) {
		
		JDBCCategoryDataset data = new JDBCCategoryDataset(con);
		
		// [연령대], [1번선택지], [2번], [3번], [4번] 컬럼 당 선택 갯수 호출
		String sql = "with aging as "
					+ "(SELECT user_id, trunc( months_between( sysdate, user_birth )/ 12 ) age "
					+ "FROM user_info) "
				+ "SELECT "
				+ "(case when age < 20 then '10대' "
				+ "when a.age >= 20 and a.age < 30 then '20대' "
				+ "when a.age >= 30 and a.age < 40 then '30대' "
				+ "when a.age >= 40 and a.age < 50 then '40대' "
				+ "when a.age >= 50 and a.age < 60 then '50대' "
				+ "else '60대' end) 연령대, "
				+ "sum(decode(r.result_choice, 1,1,0)) as \"1번\", "
				+ "sum(decode(r.result_choice, 2,1,0)) as \"2번\", "
				+ "sum(decode(r.result_choice, 3,1,0)) as \"3번\", "
				+ "sum(decode(r.result_choice, 4,1,0)) as \"4번\" "
				+ "FROM result_info r, aging a "
				+ "where r.vote_no = "
				+ voteSerial
				+ " and r.vote_count = 1 "
				+ "and a.user_id = r.user_id "
				+ "GROUP BY "
					+ "(case when age < 20 then '10대' "
					+ "when a.age >= 20 and a.age < 30 then '20대' "
					+ "when a.age >= 30 and a.age < 40 then '30대' "
					+ "when a.age >= 40 and a.age < 50 then '40대' "
					+ "when a.age >= 50 and a.age < 60 then '50대' "
					+ "else '60대' end) "
				+ "order by 연령대";
		
		try {
			data.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	//누적 막대 그래프를 위한 데이터셋 쿼리
	public CategoryDataset JDBC_Bar_dataset(int voteSerial) {
		JDBCCategoryDataset data = new JDBCCategoryDataset(con);
		
		//[선택지문항내용] [여성] [남성]
		String sql = "select \r\n" + 
				"o.vote_option_content 선택지내용,\r\n" + 
				"(select count(r.result_choice) from result_info r, user_info u \r\n" + 
				"    where r.user_id = u.user_id \r\n" + 
				"    and i.vote_no = r.vote_no\r\n" + 
				"    and r.vote_count=1 \r\n" + 
				"    and o.vote_option_choice = r.result_choice \r\n" + 
				"    and u.gender_no=2) 여성,\r\n" + 
				"(select count(r.result_choice) from result_info r, user_info u \r\n" + 
				"    where r.user_id = u.user_id \r\n" + 
				"    and i.vote_no = r.vote_no\r\n" + 
				"    and r.vote_count=1 \r\n" + 
				"    and o.vote_option_choice = r.result_choice \r\n" + 
				"    and u.gender_no=1) 남성    \r\n" + 
				"from  vote_option o, vote_info i\r\n" + 
				"where i.vote_no = o.vote_no\r\n" + 
				"and i.vote_no = "
				+ voteSerial + 
				"order by o.vote_option_choice";
		
		try {
			data.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

}
