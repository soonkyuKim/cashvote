package vo;

public class voteaddVO {
	private String voteTitle, voteContent, voteCost, voteStart, voteEnd, voteCatNo;

	public voteaddVO(String title, String content, String cost, String start, String end, String catno) {
		this.voteTitle = title;
		this.voteContent = content;
		this.voteCost = cost;
		this.voteStart = start;
		this.voteEnd = end;
		this.voteCatNo = catno;
	}

	public String getVoteTitle() {
		return voteTitle;
	}

	public void setVoteTitle(String voteTitle) {
		this.voteTitle = voteTitle;
	}

	public String getVoteContent() {
		return voteContent;
	}

	public void setVoteContent(String voteContent) {
		this.voteContent = voteContent;
	}

	public String getVoteStart() {
		return voteStart;
	}

	public void setVoteStart(String voteStart) {
		this.voteStart = voteStart;
	}

	public String getVoteEnd() {
		return voteEnd;
	}

	public void setVoteEnd(String voteEnd) {
		this.voteEnd = voteEnd;
	}

	public String getVoteCatNo() {
		return voteCatNo;
	}

	public void setVoteCatNo(String voteCatNo) {
		this.voteCatNo = voteCatNo;
	}

	public String getVoteCost() {
		return voteCost;
	}

	public void setVoteCost(String voteCost) {
		this.voteCost = voteCost;
	}
}
