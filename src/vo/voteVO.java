package vo;

public class voteVO {
   
   private int vote_No, vote_Cash_Amount, vote_Cat_No, vote_Option_Choice;
   private String vote_Title, vote_Content, vote_Option_Content, vote_Cat_Name;
   
   public voteVO(int vote_No, int vote_Cash_Amount, int vote_Cat_No, int vote_Option_Choice, String vote_Title,
              String vote_Content, String vote_Option_Content, String vote_Cat_Name) {
      this.vote_No = vote_No;
      this.vote_Cash_Amount = vote_Cash_Amount;
      this.vote_Cat_No = vote_Cat_No;
      this.vote_Option_Choice = vote_Option_Choice;
      this.vote_Title = vote_Title;
      this.vote_Content = vote_Content;
      this.vote_Option_Content = vote_Option_Content;
      this.vote_Cat_Name = vote_Cat_Name;
   }
   
   public voteVO() {
      
   }

   public int getVote_No() {
      return vote_No;
   }

   public void setVote_No(int vote_No) {
      this.vote_No = vote_No;
   }

   public int getvote_Cash_Amount() {
      return vote_Cash_Amount;
   }

   public void setvote_Cash_Amount(int vote_Cash_Amount) {
      this.vote_Cash_Amount = vote_Cash_Amount;
   }

   public int getvote_Cat_No() {
      return vote_Cat_No;
   }

   public void setvote_Cat_No(int vote_Cat_No) {
      this.vote_Cat_No = vote_Cat_No;
   }

   public int getVote_Option_Choice() {
      return vote_Option_Choice;
   }

   public void setVote_Option_Choice(int vote_Option_Choice) {
      this.vote_Option_Choice = vote_Option_Choice;
   }

   public String getVote_Title() {
      return vote_Title;
   }

   public void setVote_Title(String vote_Title) {
      this.vote_Title = vote_Title;
   }

   public String getVote_Content() {
      return vote_Content;
   }

   public void setVote_Content(String vote_Content) {
      this.vote_Content = vote_Content;
   }

   public String getVote_Option_Content() {
      return vote_Option_Content;
   }

   public void setVote_Option_Content(String vote_Option_Content) {
      this.vote_Option_Content = vote_Option_Content;
   }

   public String getVote_Cat_Name() {
      return vote_Cat_Name;
   }

   public void setVote_Cat_Name(String vote_Cat_Name) {
      this.vote_Cat_Name = vote_Cat_Name;
   }
}