package vo;

public class reportVO {
	private int report_no, vote_no, report_cat_no;
	private String report_content, user_id;

	public reportVO(int vote_no, String report_content, int report_cat_no, String user_id) {
		this.vote_no = vote_no;
		this.report_content = report_content;
		this.report_cat_no = report_cat_no;
		this.user_id = user_id;
	}

	public int getReport_no() {
		return report_no;
	}

	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}

	public int getVote_no() {
		return vote_no;
	}

	public void setVote_no(int vote_no) {
		this.vote_no = vote_no;
	}

	public int getReport_cat_no() {
		return report_cat_no;
	}

	public void setReport_cat_no(int report_cat_no) {
		this.report_cat_no = report_cat_no;
	}

	public String getReport_content() {
		return report_content;
	}

	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
