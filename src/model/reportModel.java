package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.reportVO;
import vo.userVO;

public class reportModel {
	Connection con;
	// constructor

	public reportModel() throws Exception {
		connectDB();
	}

	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}

	// 신고 메소드
	// 시퀀스 생성
	// create sequence report_sq
	// start with 1
	// INCREMENT BY 1 MAXVALUE 10000 MINVALUE 1 NOCYCLE;
	public void report(reportVO report) throws Exception {

		String sql = "insert into report_info(report_no, vote_no, report_content, report_cat_no, user_id)"
				+ "values (report_sq.nextval, ?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, report.getVote_no());
		ps.setString(2, report.getReport_content());
		ps.setInt(3, report.getReport_cat_no());
		ps.setString(4, report.getUser_id());

		ps.executeUpdate();
		ps.close();
		
	}

}
