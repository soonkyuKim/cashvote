package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JRadioButton;

import view.voteView;
import vo.userVO;
import vo.voteVO;

public class voteModel {
   Connection con;
   Statement stmt = null;

   // 생성자
   public voteModel() {
      connectDB();
   }

   void connectDB() {
      try {
         con = ConnectionPool.getConnection();
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   // 등록된 발의문 목록 중 랜덤으로 투표 창을 띄워줌

   // 투표창 데이터 받아오기
   public voteVO randomVote() throws Exception {
      voteVO vo = new voteVO();

      String range = "select rg.vote_no from range_gender rg, range_loc rl, range_birth rb, user_info ui "
            + "where rg.vote_no = rl.vote_no and rl.vote_no = rb.vote_no and user_id = '" + userModel.loginId
            + "' and ui.gender_no = rg.gender_no and ui.loc_dong_no = rl.loc_dong_no and to_char(user_birth, 'yyyy') = rb.range_birth_year "
            + "and not exists (select 1 from vote_finish vf where rg.vote_no=vf.vote_no and rl.vote_no=vf.vote_no and rb.vote_no=vf.vote_no)";

      Statement rangestmt = con.createStatement();

      ResultSet rangers = rangestmt.executeQuery(range);

      ArrayList<Integer> list = new ArrayList<Integer>();

      while (rangers.next()) {
         list.add(rangers.getInt("vote_no"));

//         String sql = "select * from " + "(select "
//               + "d.vote_Cat_Name, a.vote_Content, a.vote_Cash_Amount, a.vote_No, a.vote_Title, "
//               + "b.vote_Option_Choice, b.vote_Option_Content " + "from vote_info a, vote_option b, vote_cat d "
//               + "where a.vote_no = b.vote_no " + "and not exists " + "(select 1 from vote_finish c "
//               + "where a.vote_no = c.vote_no) " + "order by dbms_random.value) " + "where rownum = 1";
      }

      Collections.shuffle(list);

      String sql = "select * from vote_info vi, vote_cat vc, vote_option vo where vi.vote_no=vo.vote_no and vi.vote_cat_no=vc.vote_cat_no "
            + "and vi.vote_no="
            + list.get(0);

      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery(sql);

//      vo.setVote_Cat_Name("XXX");
//      vo.setVote_Content("투표없음");
//      vo.setvote_Cash_Amount(0);
//      vo.setVote_No(0000);
//      vo.setVote_Title("님에게 맞는 조건의 투표가 없음");
      System.out.print("list -> "+list.get(0));
      if (rs.next()) {
         vo.setVote_Cat_Name(rs.getString("vote_Cat_Name"));
         vo.setVote_Content(rs.getString("vote_Content"));
         vo.setvote_Cash_Amount(rs.getInt("vote_Cash_Amount"));
         vo.setVote_No(rs.getInt("vote_No"));
         vo.setVote_Option_Choice(rs.getInt("vote_Option_Choice"));
         vo.setVote_Title(rs.getString("vote_Title"));
      }

      rs.close();
      stmt.close();

      return vo;

   }

   public ArrayList selectVote(String vote_no) throws SQLException {
      voteVO vo = new voteVO();
      int vote = Integer.parseInt(vote_no);
      String sql = "Select vote_option_content from vote_option where vote_no = " + vote;

      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      ArrayList<String> list = new ArrayList();
      while (rs.next()) {
         list.add(rs.getString("Vote_Option_Content"));
      }
      rs.close();
      stmt.close();
      return list;
   }

   public int selectedVoteNo(String content) throws SQLException {
      String sql = "select vote_option_choice from vote_option where vote_option_content like '" + content + "'";

      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      int selectNo = 0;
      if (rs.next()) {
         selectNo = rs.getInt("vote_option_choice");

      }
      rs.close();
      stmt.close();

      return selectNo;
   }

   public void insertVoteInfo(int count, int saveNum, String vote_no, String id) throws SQLException {
      String sql2 = "insert into result_info(vote_count, result_choice, vote_no, user_id) " + "values(?, ?, ?, ?) ";
      PreparedStatement ps = con.prepareStatement(sql2);
      ps.setInt(1, count);
      ps.setInt(2, saveNum);
      ps.setString(3, vote_no);
      ps.setString(4, id);

      ps.executeUpdate();

      ps.close();

   }

   public int sendVoteInfo(int count, String vote, String id) throws SQLException {
      int vote_no = Integer.parseInt(vote);

      String sql = "select vote_count from result_info " + "where vote_no = " + vote_no + " and user_id = '" + id
            + "'";

      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery(sql);

      if (rs.next()) {
         rs.last();
         count = rs.getRow() + 1;
         rs.beforeFirst();

      } else {
         count = 1;
      }

      stmt.close();
      rs.close();

      return count;
   }

   public void trueScore(String vote, int saveNum) throws SQLException {
      int vote_no = Integer.parseInt(vote);

      String sql = "select 1 from vote_info where vote_no = " + vote + " and vote_cat_no = 1";
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      if (rs.next()) {
         String mgr = "select a.result_choice, b.result_choice from result_info a, result_info b "
               + "where a.vote_no = b.vote_no and b.vote_no = " + vote
               + " and a.result_choice = b.result_choice and a.result_choice = 1 and b.result_choice = " + saveNum;
         Statement mgrstmt = con.createStatement();
         ResultSet mgrrs = mgrstmt.executeQuery(mgr);

         if (mgrrs.next()) {
            String mgrO = "insert into user_truescore(truescore_no, truescore_date, truescore_amount, truescore_content, user_id) "
                  + "values(user_truescoreAdd_sq.nextval, sysdate, +1, '관리자검사통과', '" + userModel.loginId + "')";
            Statement mgrOstmt = con.createStatement();
            ResultSet mgrOrs = mgrOstmt.executeQuery(mgrO);
         } else {
            String mgrX = "insert into user_truescore(truescore_no, truescore_date, truescore_amount, truescore_content, user_id) "
                  + "values(user_truescoreAdd_sq.nextval, sysdate, -1, '관리자검사의심', '" + userModel.loginId + "')";
            Statement mgrXstmt = con.createStatement();
            ResultSet mgrXrs = mgrXstmt.executeQuery(mgrX);
         }
      } else {
         String sql1 = "select vote_no from result_info where vote_count >= 2 and rownum = 1 and vote_no = " + vote;

         Statement stmt1 = con.createStatement();
         ResultSet rs1 = stmt.executeQuery(sql1);

         if (rs1.next()) {
            String nomgr = "select a.result_choice, b.result_choice from result_info a, result_info b "
                  + "where a.vote_count = 1 and a.vote_no = b.vote_no and b.vote_no = " + vote_no
                  + " and a.user_id = b.user_id and b.user_id = '" + userModel.loginId
                  + "' and a.result_choice = b.result_choice and b.result_choice = " + saveNum;
            Statement nomgrstmt = con.createStatement();
            ResultSet nomgrrs = nomgrstmt.executeQuery(nomgr);

            if (nomgrrs.next()) {
               String nomgrO = "insert into user_truescore(truescore_no, truescore_date, truescore_amount, truescore_content, user_id) "
                     + "values(user_truescoreAdd_sq.nextval, sysdate, +1, '신뢰성검사통과', '" + userModel.loginId
                     + "')";
               Statement nomgrOstmt = con.createStatement();
               ResultSet nomgrOrs = stmt.executeQuery(nomgrO);
            } else {
               String nomgrX = "insert into user_truescore(truescore_no, truescore_date, truescore_amount, truescore_content, user_id) "
                     + "values(user_truescoreAdd_sq.nextval, sysdate, -1, '신뢰성검사의심', '" + userModel.loginId
                     + "')";
               Statement nomgrXstmt = con.createStatement();
               ResultSet nomgrXrs = stmt.executeQuery(nomgrX);
            }

         }

      }
   }
}