package model;

import java.sql.*;
import java.util.ArrayList;

import vo.userVO;

public class userModel {
	public static String loginId;

	Connection con;
	// constructor

	public userModel() throws Exception {
		connectDB();
	}

	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}

	// 회원가입 메소드
	public void regist(userVO user) throws Exception {

		String sql = "insert into user_info(user_id, user_pw, user_name, user_tel, job_no, gender_no, loc_dong_no, user_birth)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		String id = user.getUserId();

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, user.getUserId());
		ps.setString(2, user.getUserPw());
		ps.setString(3, user.getUserName());
		ps.setString(4, user.getUserTel());
		ps.setString(5, user.getUserJob());
		ps.setString(6, user.getUserGender());
		ps.setString(7, user.getUserDong());
		ps.setString(8, user.getUserBirth());

		ps.executeUpdate();
		ps.close();

		newTruescore(id);
	}

	// 신뢰도 기본값 입력 메소드
	// 시퀀스 생성
	// create sequence user_truescore_sq
	// start with 1
	// INCREMENT BY 1 MAXVALUE 10000 MINVALUE 1 NOCYCLE;
	private void newTruescore(String id) throws SQLException {
		String sql = "insert into user_truescore(truescore_no, truescore_date, truescore_amount, truescore_content, user_id)"
				+ "values (user_truescore_sq.nextval, sysdate, 100, '기본값', ?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, id);

		ps.executeUpdate();
		ps.close();
	}

	// 로그인 메소드
	public void login(String id, String pw) throws Exception {
		userVO user = new userVO();

		String sql = "select * from user_info where user_id=? and user_pw=?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, pw);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			user.setUserId(id);
			user.setUserPw(pw);
			user.setUserName("user_name");
			user.setUserTel("user_tel");
			user.setUserJob("user_job");
			user.setUserGender("user_gender");
			user.setUserDong("user_dong");
			user.setUserBirth("user_birth");
			loginId = id;
			System.out.println("성공");
		} else
			throw new Exception();
		rs.close();
		ps.close();
	}

	// 회원정보 수정 메소드
	public void update(userVO user) throws Exception {

		String sql = "update user_info set user_pw = ?,user_tel = ?, job_no=?, gender_no=? ,loc_dong_no=? ,user_birth=?"
				+ "where user_id= '" + userModel.loginId + "'";

//      "insert into user_info(user_id, user_pw, user_name, user_tel, job_no, gender_no, loc_dong_no, user_birth)"
//      + "values (?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, user.getUserPw());
		ps.setString(2, user.getUserTel());
		ps.setString(3, user.getUserJob());
		ps.setString(4, user.getUserGender());
		ps.setString(5, user.getUserDong());
		ps.setString(6, user.getUserBirth());

		ps.executeUpdate();
		ps.close();
	}

	public void call(userVO user) throws Exception {
		String sql = "select * from user_info where user_id = '" + userModel.loginId + "'";
	}

	// 토큰충전 메소드
	// 시퀀스 생성
	// create sequence no_token_add
	// start with 1
	// INCREMENT BY 1 MAXVALUE 10000 MINVALUE 1 NOCYCLE;
	public void tokenadd(userVO user) throws Exception {
		String sql = "insert into user_token(TOKEN_NO,TOKEN_DATE,TOKEN_AMOUNT,TOKEN_CONTENT,USER_ID)"
				+ "values(no_token_add.nextval, sysdate, ?,'토큰충전', '" + userModel.loginId + "')";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, user.getUsertoken());

		ps.executeUpdate();
		ps.close();
	}

	// 토큰총합 메소드
	public void token(userVO user) throws Exception {
		String sql = "select sum(token_amount) from user_token where user_id = '" + userModel.loginId + "'";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.executeUpdate();
		ps.close();

	}

	// 토큰 내역
	public ArrayList searchToken() throws Exception {
		String sql = "select * from user_token where user_id = '" + userModel.loginId + "' order by token_date desc";

		Statement ps = con.createStatement();

		// 5. 전송하기
		ResultSet rs = ps.executeQuery(sql);
		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("token_date"));
			temp.add(rs.getString("token_amount"));
			temp.add(rs.getString("token_content"));
			list.add(temp);
		}

		// 6. 닫기
		rs.close();
		ps.close();

		return list;

	}

	public ArrayList searchCash() throws SQLException {
		String sql = "select * from user_cash where user_id = '" + userModel.loginId + "' order by cash_date desc";

		Statement ps = con.createStatement();

		// 5. 전송하기
		ResultSet rs = ps.executeQuery(sql);
		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("cash_date"));
			temp.add(rs.getString("cash_amount"));
			temp.add(rs.getString("cash_content"));
			list.add(temp);
		}

		// 6. 닫기
		rs.close();
		ps.close();

		return list;
	}

	public ArrayList searchTruescore() throws SQLException {
		String sql = "select * from user_truescore where user_id = '" + userModel.loginId
				+ "' order by truescore_date desc";

		Statement ps = con.createStatement();

		// 5. 전송하기
		ResultSet rs = ps.executeQuery(sql);
		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("truescore_date"));
			temp.add(rs.getString("truescore_amount"));
			temp.add(rs.getString("truescore_content"));
			list.add(temp);
		}

		// 6. 닫기
		rs.close();
		ps.close();

		return list;
	}

//TODO 내 정보 JTable 쿼리

	public ArrayList uservotelist() throws Exception {

		String sql = "select " + "i.vote_no \"번호\", " + "i.vote_title \"제목\", "
				+ "to_char(i.vote_reg_start, 'YYYY/MM/DD')||'(D'||"
				+ "(case when sysdate > i.vote_reg_end then '+'||trunc(sysdate-i.vote_reg_end) else '-'||-trunc(sysdate-i.vote_reg_end) end )||')' \"등록일\", "
				+ "(select count(*) from result_info r where i.vote_no = r.vote_no and r.vote_count=1) \"현투표인원\", "
				+ "(select nvl(sum(sk_count),0) from stats_kosis sk "
				+ "where sk_loc in (select loc_dong_no from range_loc loc where loc.vote_no = i.vote_no) "
				+ "and sk_birth in (select range_birth_year from range_birth bir where bir.vote_no = i.vote_no) "
				+ "and sk_gender in (select gender_no from range_gender gen where gen.vote_no = i.vote_no)) \"모집단\" "
				+ "from vote_info i " + "where i.user_id='" + userModel.loginId + "' "
				+ "and not exists (select 1 from vote_finish f where f.vote_no=i.vote_no)";

		Statement ps = con.createStatement();

		// 5. 전송하기
		ResultSet rs = ps.executeQuery(sql);
		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("번호"));
			temp.add(rs.getString("제목"));
			temp.add(rs.getString("등록일"));

			int vote_num = rs.getInt("현투표인원");
			int sample_size = target_sample_size_95(rs.getInt("모집단"));
			String vote_ratio = "0명(0%)";

			// 현재투표인수 (달성율)
			if (sample_size == 0) {
				vote_ratio = vote_num + "명";
			} else {
				vote_ratio = vote_num + "명 (" + vote_num / sample_size * 100 + "%)";
			}

			temp.add(vote_ratio);

			// 목표 표본 수
			temp.add(sample_size);

			list.add(temp);
		}

		// 6. 닫기
		rs.close();
		ps.close();

		return list;

	}

	// 적절한 표본 크기 출력하는 메서드
	public int target_sample_size_95(int population) {
		int sample_size = 0;
		double z_score = 1.96d;
		double margin_error = 0.05d;

		sample_size = (int) Math.round((Math.pow(z_score, 2) * 0.25 / Math.pow(margin_error, 2))
				/ (1 + ((Math.pow(z_score, 2) * 0.25 / population * Math.pow(margin_error, 2)))));

		return sample_size;
	}

	public String getAllToken() throws Exception {
		String sql = "select sum(token_amount) from user_token where user_id = '" + userModel.loginId + "'";

		Statement ps = con.createStatement();
		ResultSet rs = ps.executeQuery(sql);

		String allToken = null;

		if (rs.next()) {
			allToken = rs.getString("sum(token_amount)");
		}

		rs.close();
		ps.close();

		return allToken;
	}

	public String getAllCash() throws Exception {
		String sql = "select sum(cash_amount) from user_cash where user_id = '" + userModel.loginId + "'";

		Statement ps = con.createStatement();
		ResultSet rs = ps.executeQuery(sql);

		String allCash = null;

		if (rs.next()) {
			allCash = rs.getString("sum(cash_amount)");
		}

		rs.close();
		ps.close();

		return allCash;
	}

	public void useCash(String id, String Cost, String content) throws SQLException {
		String sqlUseCash = "insert into user_cash(cash_no, cash_date, cash_amount, cash_content, user_id)"
				+ "values(user_cashAdd_sq.nextval, sysdate, ?*(-1), ?, ?)";

		PreparedStatement ps = con.prepareStatement(sqlUseCash);

		ps.setString(1, Cost);
		ps.setString(2, content);
		ps.setString(3, id);

		ps.executeUpdate();
		ps.close();
	}
}