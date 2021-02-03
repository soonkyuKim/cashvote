package model;

import java.sql.*;

import vo.voteOptionVO;
import vo.voteaddVO;

public class voteAddModel {

	Connection con;

	public voteAddModel() throws Exception {
		connectDB();
	}

	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}

	// 투표등록 메소드
	// 시퀀스 생성
	// create sequence no_vote_add
	// start with 500
	// INCREMENT BY 1 MAXVALUE 10000 MINVALUE 1 NOCYCLE;
	public void voteAdd(voteaddVO vote) throws Exception {
		String voteaddsql = "insert into vote_info(vote_no,vote_title,vote_content,vote_reg_start,"
				+ "vote_reg_end,vote_cost,vote_cash_amount,vote_cat_no,user_id)"
				+ "values(no_vote_add.nextval,?,?,?,?,?,500,?,'" + userModel.loginId + "')";

		PreparedStatement ps = con.prepareStatement(voteaddsql);

		ps.setString(1, vote.getVoteTitle());
		ps.setString(2, vote.getVoteContent());
		ps.setString(3, vote.getVoteStart());
		ps.setString(4, vote.getVoteEnd());
		ps.setString(5, vote.getVoteCost());
		ps.setString(6, vote.getVoteCatNo());

		ps.executeUpdate();
		ps.close();

	}

	// 발의문 선택지 등록 메소드
	// 시퀀스 생성
	// create sequence vote_option_sq
	// start with 10100041
	// INCREMENT BY 1 MAXVALUE 99999999 MINVALUE 1 NOCYCLE;
	public void voteOptionAdd(voteOptionVO vo) throws Exception {
		String sqlVoteOptionAdd = "insert into vote_option(vote_option_choice, vote_option_content, vote_no, vote_option_no)"
				+ "values(?, ?, ?, vote_option_sq.nextval)";

		PreparedStatement ps = con.prepareStatement(sqlVoteOptionAdd);

		ps.setString(1, vo.getOptionChoice());
		ps.setString(2, vo.getOptionContent());
		ps.setString(3, vo.getVoteNo());

		ps.executeUpdate();
		ps.close();
	}

	// 성별 범위 등록 메소드
	// 시퀀스 생성
	// create sequence range_gender_sq
	// start with 10005002
	// INCREMENT BY 1 MAXVALUE 99999999 MINVALUE 1 NOCYCLE;
	public void voteGenderRangeAdd(String vote_no, String range_gender) throws SQLException {
		String sqlRangeGenderAdd = "insert into range_gender(range_gender_no, vote_no, gender_no)"
				+ "values(range_gender_sq.nextval, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sqlRangeGenderAdd);

		ps.setString(1, vote_no);
		ps.setString(2, range_gender);

		ps.executeUpdate();
		ps.close();
	}

	// 지역 범위 등록 메소드
	// 시퀀스 생성
	// create sequence range_loc_sq
	// start with 10001018
	// INCREMENT BY 1 MAXVALUE 99999999 MINVALUE 1 NOCYCLE;
	public void voteLocRangeAdd(String vote_no, String range_loc_no) throws SQLException {
		String sqlRangeLocAdd = "insert into range_loc(range_loc_no, loc_dong_no, vote_no)"
				+ "values(range_loc_sq.nextval, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sqlRangeLocAdd);

		ps.setString(1, range_loc_no);
		ps.setString(2, vote_no);

		ps.executeUpdate();
		ps.close();
	}

	// 기타조건 범위 등록 메소드
	// 시퀀스 생성
	// create sequence range_etc_sq
	// start with 10002005
	// INCREMENT BY 1 MAXVALUE 99999999 MINVALUE 1 NOCYCLE;
	public void voteEtcRangeAdd(String vote_no, String range_etc_no) throws SQLException {
		String sqlRangeEtcAdd = "insert into range_etc(range_etc_no,vote_no,etc_no)"
				+ "values(range_etc_sq.nextval,?,?)";

		PreparedStatement ps = con.prepareStatement(sqlRangeEtcAdd);

		ps.setString(1, vote_no);
		ps.setString(2, range_etc_no);

		ps.executeUpdate();
		ps.close();
	}

	// 직업조건 범위 등록 메소드
	// 시퀀스 생성
	// create sequence range_job_sq
	// start with 10003003
	// INCREMENT BY 1 MAXVALUE 99999999 MINVALUE 1 NOCYCLE;
	public void voteJobTypeRangeAdd(String vote_no, String range_job_type_no) throws SQLException {
		int i = 0;

		String sqlSearchJob = "select * from cat_job where job_type_no=?";

		PreparedStatement ps = con.prepareStatement(sqlSearchJob);
		ps.setString(1, range_job_type_no);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getString("job_no") + " " + i);
			voteJobRangeAdd(vote_no, rs.getString("job_no"));
			i++;
		}

		rs.close();
		ps.close();
	}

	public void voteJobRangeAdd(String vote_no, String range_job_no) throws SQLException {
		String sqlRangeJobAdd = "insert into range_job(range_job_no,vote_no,job_no)"
				+ "values(range_job_sq.nextval, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sqlRangeJobAdd);

		ps.setString(1, vote_no);
		ps.setString(2, range_job_no);

		ps.executeUpdate();
		ps.close();
	}

	// 나이조건 범위 등록 메소드
	// 시퀀스 생성
	// create sequence range_birth_sq
	// start with 10004003
	// INCREMENT BY 1 MAXVALUE 99999999 MINVALUE 1 NOCYCLE;
	public void voteAgeRangeAdd(String voteNo, int range_start_age, int range_end_age) throws SQLException {
		java.util.Calendar cal = java.util.Calendar.getInstance();

		int year = cal.get(cal.YEAR);
		int startYear = year - range_start_age + 1;
		int endYear = year - range_end_age + 1;
		int repeat = startYear - endYear + 1;
		System.out.println("repeat : " + repeat);

		for (int i = 0; i < repeat; i++) {
			voteAgeAdd(voteNo, startYear);
			startYear--;
		}
	}

	public void voteAgeAdd(String voteNo, int birth) throws SQLException {
		String sqlRangeAgeAdd = "insert into range_birth(range_birth_no, vote_no, range_birth_year)"
				+ "values(range_birth_sq.nextval, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sqlRangeAgeAdd);

		ps.setString(1, voteNo);
		ps.setInt(2, birth);

		ps.executeUpdate();
		ps.close();
	}
	
	// 나이조건 범위 등록 메소드
	// 시퀀스 생성
	// create sequence user_token_sq;
	// start with 100
	// INCREMENT BY 1 MAXVALUE 99999999 MINVALUE 1 NOCYCLE;
	public void useToken(String id, String Cost) throws SQLException {
		String sqlUseToken = "insert into user_token(token_no, token_date, token_amount, token_content, user_id)"
				+ "values(no_token_add.nextval, sysdate, ?*(-1), '토큰사용', ?)";

		PreparedStatement ps = con.prepareStatement(sqlUseToken);

		ps.setString(1, Cost);
		ps.setString(2, id);

		ps.executeUpdate();
		ps.close();
	}

}
