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

   // ������
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

   // ��ǥ �Ϸ� ��� ����Ʈ���� + �̳� Ŭ������ fire
   public ArrayList voteFINlist() {
      ArrayList list = new ArrayList();

      // 1. sql ���� �����
      String sql = "select i.vote_no serialNO, " + "c.vote_cat_name votecate, " + "i.vote_title votetitle, "
            + "to_char(i.vote_reg_start,'yyyy/mm/dd') startdate, "
            + "(select count(*) from result_info r where i.vote_no = r.vote_no and vote_count=1 group by r.vote_no) count "
            + "from vote_info i, vote_cat c " + "where " + "user_id ='" + userModel.loginId + "' "
            + "and c.vote_cat_no = i.vote_cat_no " + "and i.vote_no in (select vote_no from vote_finish) "
            + "order by i.vote_reg_start desc";
      // 2. sql ���� ��ü ������
      try {
         // 3. sql �����ϱ�
         Statement ss = con.createStatement();
         ResultSet rs = ss.executeQuery(sql);

         // 4. ������� �޾� ArrayList ������ ����
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
      // 5. sql �ݱ�
      return list;
   }

   // ���̺��� ���õ� �Ϸù�ȣ�� ��ǥ ����, ��ǥ ������ ��ȯ
   public ArrayList findVoteInfo(int serial) {
      ArrayList list = new ArrayList();

      // 1. sql ���� �����
      String sql = "select " + "o.vote_option_choice choices, " + "o.vote_option_content opt, "
            + "(select count(*) from result_info r " + "where r.vote_count=1 and r.vote_no = i.vote_no "
            + "and r.result_choice = o.vote_option_choice) cnt " + "from vote_option o, vote_info i " + "where "
            + "o.vote_no = i.vote_no " + "and i.user_id='" + userModel.loginId + "' " + "and i.vote_no = " + serial;
      // 2. sql ���� ��ü ������
      try {
         // 3. sql �����ϱ�
         Statement ss = con.createStatement();
         ResultSet rs = ss.executeQuery(sql);

         // 4. ������� �޾� ArrayList ������ ����
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
      // 5. sql �ݱ�
      return list;

   }

   // ���̺��� ���õ� �Ϸù�ȣ�� ��ǥ ���� ��ȯ(������ ����)
   public String findVoteContent(int voteSerial) {
      String contents = "";
      String sql = "select " + "vote_content cont " + "from vote_info " + "where vote_no =" + voteSerial;
      // 2. sql ���� ��ü ������
      try {
         // 3. sql �����ϱ�
         Statement ss = con.createStatement();
         ResultSet rs = ss.executeQuery(sql);

         while (rs.next()) {
            contents = rs.getString("cont");
            // System.out.println("���ǹ� ������ :"+contents);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return contents;
   }

   // [��๮] ���̺��� ���õ� �Ϸù�ȣ�� range �˻��ؼ� ������ �� �����ϴ� ����
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

   // ������ ǥ�� ũ�� ����ϴ� �޼���
   public int target_sample_size_95 (int population) {
      int sample_size=0;
      double z_score = 1.96d;
      double margin_error = 3.0d;
      
      sample_size = (int) Math.round((Math.pow(z_score, 2) * 0.25 / Math.pow(margin_error, 2))
            / (1 + ((Math.pow(z_score, 2) * 0.25 / population * Math.pow(margin_error, 2)))));
      
      return sample_size;
   }
   
   
   // [��๮] ��๮ ������ �ۼ��ϴ� �޼���
   public String Prod_summary(int vote_count , int population , int trust_level) {
      /**
       * default sample_size = 0
       * default sample_count = 1
       * default z_score = 0.1d (���Ǽ��� a = 0.05), (95% �ŷڵ�)
       * default margin of error = 0.3d
       * default trust_level = 95
       * */
       
      String summ = "";

      double z_score = 1.96d;      //���Ǽ��� 0.05, 95% �ŷڵ������� z���� �⺻��
      int sample_size = 0;
      int sample_count = 1;

      double margin_error = 3.0d;   //�ۼ�Ʈ �����̹Ƿ� 3%�� ����

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

         // ǥ�������Ѱ� �����... +/- 3 % ���� ��
         if (sample_count == 1) {
            margin_error = 3.0d;
         } else {
            margin_error = Math.round((z_score * Math.sqrt(0.25 / sample_count)) * 100);
         }

         // ǥ��ũ�� ��� ���... �ش� �ŷڵ� (�⺻ 95%)�� �����ϱ� ���� ������ ũ��
         sample_size = (int) Math.round((Math.pow(z_score, 2) * 0.25 / Math.pow(margin_error, 2))
               / (1 + ((Math.pow(z_score, 2) * 0.25 / population * Math.pow(margin_error, 2)))));

         summ = "�������� ũ��� [" + population + "] �� �̸�, " + "��ǥ �ο��� [" + vote_count + "] ��.\n " + "�ŷڼ��� <" + trust_level
               + "%> ���� " + "�����Ѱ�� <��" + margin_error + ">�̴�. ";
         return summ;
      } else {
         return "��ǥ ������ ��� ����� �� ����.";
      }
   }
}