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

	// ������ ȣ���ϸ� DB Ŀ��Ʈ
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

	// ���� ��Ʈ�� ���� ���� ��ǥ ��� ������ �����ϱ� (JDBC �����ͼ�)
	public PieDataset JDBC_Pie_dataset(int voteSerial) {

		// JFreeChart ��Ű���� JDBC �����ͼ� Ŭ���� �̿�
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

	//�Ź����� ��Ʈ�� ���� �����ͼ� ����
	public CategoryDataset JDBC_Spi_dataset(int voteSerial) {
		
		JDBCCategoryDataset data = new JDBCCategoryDataset(con);
		
		// [���ɴ�], [1��������], [2��], [3��], [4��] �÷� �� ���� ���� ȣ��
		String sql = "with aging as "
					+ "(SELECT user_id, trunc( months_between( sysdate, user_birth )/ 12 ) age "
					+ "FROM user_info) "
				+ "SELECT "
				+ "(case when age < 20 then '10��' "
				+ "when a.age >= 20 and a.age < 30 then '20��' "
				+ "when a.age >= 30 and a.age < 40 then '30��' "
				+ "when a.age >= 40 and a.age < 50 then '40��' "
				+ "when a.age >= 50 and a.age < 60 then '50��' "
				+ "else '60��' end) ���ɴ�, "
				+ "sum(decode(r.result_choice, 1,1,0)) as \"1��\", "
				+ "sum(decode(r.result_choice, 2,1,0)) as \"2��\", "
				+ "sum(decode(r.result_choice, 3,1,0)) as \"3��\", "
				+ "sum(decode(r.result_choice, 4,1,0)) as \"4��\" "
				+ "FROM result_info r, aging a "
				+ "where r.vote_no = "
				+ voteSerial
				+ " and r.vote_count = 1 "
				+ "and a.user_id = r.user_id "
				+ "GROUP BY "
					+ "(case when age < 20 then '10��' "
					+ "when a.age >= 20 and a.age < 30 then '20��' "
					+ "when a.age >= 30 and a.age < 40 then '30��' "
					+ "when a.age >= 40 and a.age < 50 then '40��' "
					+ "when a.age >= 50 and a.age < 60 then '50��' "
					+ "else '60��' end) "
				+ "order by ���ɴ�";
		
		try {
			data.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	//���� ���� �׷����� ���� �����ͼ� ����
	public CategoryDataset JDBC_Bar_dataset(int voteSerial) {
		JDBCCategoryDataset data = new JDBCCategoryDataset(con);
		
		//[���������׳���] [����] [����]
		String sql = "select \r\n" + 
				"o.vote_option_content ����������,\r\n" + 
				"(select count(r.result_choice) from result_info r, user_info u \r\n" + 
				"    where r.user_id = u.user_id \r\n" + 
				"    and i.vote_no = r.vote_no\r\n" + 
				"    and r.vote_count=1 \r\n" + 
				"    and o.vote_option_choice = r.result_choice \r\n" + 
				"    and u.gender_no=2) ����,\r\n" + 
				"(select count(r.result_choice) from result_info r, user_info u \r\n" + 
				"    where r.user_id = u.user_id \r\n" + 
				"    and i.vote_no = r.vote_no\r\n" + 
				"    and r.vote_count=1 \r\n" + 
				"    and o.vote_option_choice = r.result_choice \r\n" + 
				"    and u.gender_no=1) ����    \r\n" + 
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
