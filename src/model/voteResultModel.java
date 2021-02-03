package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.jfree.data.general.PieDataset;
import org.jfree.data.jdbc.JDBCPieDataset;

import view.voteResult_PieChart_View;

public class voteResultModel {
   Connection con;
   Statement stmt = null;

   // voteResult_PieChart pie;
   // voteResult_PieChart.Piedata piedata = pie.new Piedata();

   // 생성자
   public voteResultModel() {
      connectDB();
   }

   void connectDB() {
      try {
         con = ConnectionPool.getConnection();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   // 투표 완료 목록 리스트생성 + 이너 클래스로 fire
   public ArrayList voteFINlist() {
      ArrayList list = new ArrayList();

      // 1. sql 쿼리 만들기
      String sql = "select i.vote_no serialNO, " + "c.vote_cat_name votecate, " + "i.vote_title votetitle, "
            + "to_char(i.vote_reg_start,'yyyy/mm/dd') startdate, "
            + "(select count(*) from result_info r where i.vote_no = r.vote_no and vote_count=1 group by r.vote_no) count "
            + "from vote_info i, vote_cat c " + "where " + "user_id ='" + userModel.loginId + "' "
            + "and c.vote_cat_no = i.vote_cat_no " + "and i.vote_no in (select vote_no from vote_finish) "
            + "order by i.vote_reg_start desc";
      // 2. sql 전송 객체 얻어오기
      try {
         // 3. sql 전송하기
         Statement ss = con.createStatement();
         ResultSet rs = ss.executeQuery(sql);

         // 4. 결과값을 받아 ArrayList 변수에 지정
         while (rs.next()) {
            ArrayList temp = new ArrayList();
            temp.add(rs.getInt("serialNO"));
            temp.add(rs.getString("votecate"));
            temp.add(rs.getString("votetitle"));
            temp.add(rs.getString("startdate"));
            temp.add(rs.getInt("count"));
            list.add(temp);
         }
         rs.close();
         ss.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      // 5. sql 닫기
      return list;
   }

   // 테이블에서 선택된 일련번호로 투표 정보, 투표 선택지 반환
   public ArrayList findVoteInfo(int serial) {
      ArrayList list = new ArrayList();

      // 1. sql 쿼리 만들기
      String sql = "select " + "o.vote_option_choice choices, " + "o.vote_option_content opt, "
            + "(select count(*) from result_info r " + "where r.vote_count=1 and r.vote_no = i.vote_no "
            + "and r.result_choice = o.vote_option_choice) cnt " + "from vote_option o, vote_info i " + "where "
            + "o.vote_no = i.vote_no " + "and i.user_id='" + userModel.loginId + "' " + "and i.vote_no = " + serial;
      // 2. sql 전송 객체 얻어오기
      try {
         // 3. sql 전송하기
         Statement ss = con.createStatement();
         ResultSet rs = ss.executeQuery(sql);

         // 4. 결과값을 받아 ArrayList 변수에 지정
         while (rs.next()) {
            ArrayList temp = new ArrayList();

            temp.add(rs.getInt("choices"));
            temp.add(rs.getString("opt"));
            temp.add(rs.getInt("cnt"));
            list.add(temp);
         }
         rs.close();
         ss.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      // 5. sql 닫기
      return list;

   }

   // 테이블에서 선택된 일련번호로 투표 내용 반환(여러줄 가능)
   public String findVoteContent(int voteSerial) {
      String contents = "";
      String sql = "select " + "vote_content cont " + "from vote_info " + "where vote_no =" + voteSerial;
      // 2. sql 전송 객체 얻어오기
      try {
         // 3. sql 전송하기
         Statement ss = con.createStatement();
         ResultSet rs = ss.executeQuery(sql);

         while (rs.next()) {
            contents = rs.getString("cont");
            // System.out.println("발의문 내용은 :"+contents);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return contents;
   }

   // [요약문] 테이블에서 선택된 일련번호로 range 검색해서 모집단 수 산출하는 쿼리
   public int findRangePop(int voteSerial) {
      int population = 0;

      String sql = "select " + "sum(sk_count) pop " + "from stats_kosis " + "where sk_loc in "
            + "(select loc_dong_no from range_loc " + "where vote_no = " + voteSerial + ")" + "and   sk_birth in "
            + "(select range_birth_year from range_birth " + "where vote_no = " + voteSerial + ")"
            + "and   sk_gender in " + "(select gender_no from range_gender " + "where vote_no = " + voteSerial
            + ")";

      try {
         Statement ss = con.createStatement();
         ResultSet rs = ss.executeQuery(sql);

         while (rs.next()) {
            population = rs.getInt("pop");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

      return population;
   }

   // 적절한 표본 크기 출력하는 메서드
   public int target_sample_size_95 (int population) {
      int sample_size=0;
      double z_score = 1.96d;
      double margin_error = 3.0d;
      
      sample_size = (int) Math.round((Math.pow(z_score, 2) * 0.25 / Math.pow(margin_error, 2))
            / (1 + ((Math.pow(z_score, 2) * 0.25 / population * Math.pow(margin_error, 2)))));
      
      return sample_size;
   }
   
   
   // [요약문] 요약문 내용을 작성하는 메서드
   public String Prod_summary(int vote_count , int population , int trust_level) {
      /**
       * default sample_size = 0
       * default sample_count = 1
       * default z_score = 0.1d (유의수준 a = 0.05), (95% 신뢰도)
       * default margin of error = 0.3d
       * default trust_level = 95
       * */
       
      String summ = "";

      double z_score = 1.96d;      //유의수준 0.05, 95% 신뢰도에서의 z값이 기본임
      int sample_size = 0;
      int sample_count = 1;

      double margin_error = 3.0d;   //퍼센트 숫자이므로 3%와 같음

      if (vote_count != 0) {
         sample_count = vote_count;

         switch (trust_level) {
         case 99:
            z_score = 2.575;
            break;
         case 95:
            z_score = 1.96;
            break;
         case 90:
            z_score = 1.645;
            break;
         default:
            z_score = 1.96;
            break;
         }

         // 표본오차한계 계산방법... +/- 3 % 같은 수
         if (sample_count == 1) {
            margin_error = 3.0d;
         } else {
            margin_error = Math.round((z_score * Math.sqrt(0.25 / sample_count)) * 100);
         }

         // 표본크기 계산 방법... 해당 신뢰도 (기본 95%)를 만족하기 위한 샘플의 크기
         sample_size = (int) Math.round((Math.pow(z_score, 2) * 0.25 / Math.pow(margin_error, 2))
               / (1 + ((Math.pow(z_score, 2) * 0.25 / population * Math.pow(margin_error, 2)))));

         summ = "모집단의 크기는 [" + population + "] 명 이며, " + "투표 인원은 [" + vote_count + "] 명.\n " + "신뢰수준 <" + trust_level
               + "%> 에서 " + "오차한계는 <±" + margin_error + ">이다. ";
         return summ;
      } else {
         return "투표 내역이 없어서 요약할 수 없음.";
      }
   }
}