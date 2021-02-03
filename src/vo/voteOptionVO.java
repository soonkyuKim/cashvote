package vo;

public class voteOptionVO {
	private String optionChoice, optionContent, voteNo, optionNo;

	public voteOptionVO(String optionChoice, String optionContent, String voteNo) {
		this.optionChoice = optionChoice;
		this.optionContent = optionContent;
		this.voteNo = voteNo;
	}

	public String getOptionChoice() {
		return optionChoice;
	}

	public void setOptionChoice(String optionChoice) {
		this.optionChoice = optionChoice;
	}

	public String getOptionContent() {
		return optionContent;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}

	public String getVoteNo() {
		return voteNo;
	}

	public void setVoteNo(String voteNo) {
		this.voteNo = voteNo;
	}

	public String getOptionNo() {
		return optionNo;
	}

	public void setOptionNo(String optionNo) {
		this.optionNo = optionNo;
	}
}