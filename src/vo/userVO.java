package vo;

public class userVO {
	private String userId, userPw, userName, userTel, userJob, userGender, userDong, userBirth, usertoken;

	public userVO(String id, String pw, String name, String tel, String job, String gender, String dong, String birth) {
		this.userId = id;
		this.userPw = pw;
		this.userName = name;
		this.userTel = tel;
		this.userJob = job;
		this.userGender = gender;
		this.userDong = dong;
		this.userBirth = birth;
	}

	public userVO(String pw, String tel, String job, String gender, String dong, String birth) {
		this.userPw = pw;
		this.userTel = tel;
		this.userJob = job;
		this.userGender = gender;
		this.userDong = dong;
		this.userBirth = birth;
	}

	public userVO(String tokenadd) {
		this.usertoken = tokenadd;
	}

	public userVO() {
	}

	public String getUsertoken() {
		return usertoken;
	}

	public void setUsertoken(String usertoken) {
		this.usertoken = usertoken;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserJob() {
		return userJob;
	}

	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserDong() {
		return userDong;
	}

	public void setUserDong(String userDong) {
		this.userDong = userDong;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

}
