package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.*;

import model.ConnectionPool;
import model.userModel;
import view.registView.ItemChangeListener;
import vo.userVO;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;

public class myInfoView extends JPanel implements ActionListener {
   private JTextField tfUserId;
   private JTextField tfUserName;
   private JTextField tfUserTel;
   private JTextField tfJobType;
   private JTextField tfJob;
   private JTextField tfGender;
   private JTextField tfSido;
   private JTextField tfGoo;
   private JTextField tfDong;
   private JTextField tfBirth;

   private JTextField tfCustToken;
   private JTextField tfUserTokenAdd;
   private JTextField tfCustCash;
   private JTextField tfCustTrueContent;

   private JPasswordField passwordField;

   private JButton bCustTokenAdd;
   private JButton bCustTokencontent;
   private JButton bCustcashcontent;
   private JButton bCusttruecontent;
   private JButton bCustUserMod;
   JButton btnNewButton_1_1;

   PreparedStatement ps = null;
   ResultSet rs = null;
   userModel model = null;

   static Connection con = null;
   String loc_sido_no = null;
   int cnt1 = 0, cnt2 = 0, cnt3 = 0;
   private JPanel panel_1;
   private JPanel panel_3;
   private JPanel panel_4;
   private JPanel panel_5;
   private JPanel panel_6;
   private JPanel panel_7;
   private JPanel panel_8;
   private JPanel panel_9;
   private JPanel panel_10;
   private JPanel panel_11;
   private JPanel panel_12;
   private JTable table;
   private JButton btnNewButton;
   private JButton btnNewButton_1;

   JTable tbuserVote;
   userVoteAddTable tbUserAdd = new userVoteAddTable();

   private JLabel lblNewLabel_3;
   private JTextField textField;
   private JLabel lblNewLabel;
   private JLabel lblNewLabel_1;
   private JLabel lblNewLabel_2;

   void connectDB() throws Exception {
      con = ConnectionPool.getConnection();
   }

   /**
    * Create the panel.
    */
   public myInfoView() {
      addLayout();
      setStyle();
      userVoteList();
      eventProc();

   }

   // 초기화
   public void addLayout() {
      try {
         model = new userModel();
         System.out.println("유저DB 연결 성공");
      } catch (Exception e) {
         JOptionPane.showConfirmDialog(null, "유저 DB연결 실패" + e.getMessage());
      }

      setLayout(null);

      bCustUserMod = new JButton("회원정보수정");
      bCustUserMod.setForeground(Color.WHITE);
      bCustUserMod.setBackground(Color.DARK_GRAY);
      bCustUserMod.setBounds(442, 378, 116, 23);
      add(bCustUserMod);
      bCustUserMod.addActionListener(this);

      JPanel panel = new JPanel();
      panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
      panel.setBounds(12, 10, 446, 358);
      add(panel);
      panel.setLayout(null);

      JPanel panel_2 = new JPanel();
      panel_2.setBackground(Color.DARK_GRAY);
      panel_2.setBounds(12, 10, 132, 23);
      panel.add(panel_2);
      panel_2.setLayout(null);
      
      lblNewLabel = new JLabel("회원정보");
      lblNewLabel.setForeground(Color.WHITE);
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setBounds(0, 1, 132, 23);
      panel_2.add(lblNewLabel);

      JLabel lblUserId = new JLabel("아이디");
      lblUserId.setBounds(12, 54, 60, 15);
      panel.add(lblUserId);

      tfUserId = new JTextField();
      tfUserId.setBounds(78, 51, 150, 21);
      panel.add(tfUserId);
      tfUserId.setColumns(10);

      panel_3 = new JPanel();
      panel_3.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
      panel_3.setBounds(12, 79, 422, 2);
      panel.add(panel_3);

      JLabel lblUserPw = new JLabel("패스워드");
      lblUserPw.setBounds(12, 91, 60, 15);
      panel.add(lblUserPw);

      passwordField = new JPasswordField();
      passwordField.setBounds(78, 88, 150, 21);
      panel.add(passwordField);

      panel_4 = new JPanel();
      panel_4.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
      panel_4.setBounds(12, 116, 422, 2);
      panel.add(panel_4);

      JLabel lblUserName = new JLabel("이름");
      lblUserName.setBounds(12, 128, 60, 15);
      panel.add(lblUserName);

      tfUserName = new JTextField();
      tfUserName.setBounds(78, 125, 150, 21);
      panel.add(tfUserName);
      tfUserName.setColumns(10);

      panel_5 = new JPanel();
      panel_5.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
      panel_5.setBounds(12, 153, 422, 2);
      panel.add(panel_5);

      JLabel lblUserTel = new JLabel("전화번호");
      lblUserTel.setBounds(12, 166, 60, 15);
      panel.add(lblUserTel);

      tfUserTel = new JTextField();
      tfUserTel.setBounds(78, 163, 150, 21);
      panel.add(tfUserTel);
      tfUserTel.setColumns(10);

      panel_6 = new JPanel();
      panel_6.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
      panel_6.setBounds(12, 192, 422, 2);
      panel.add(panel_6);

      JLabel lblUserJob = new JLabel("직업");
      lblUserJob.setBounds(12, 205, 60, 15);
      panel.add(lblUserJob);

      tfJobType = new JTextField();
      tfJobType.setBounds(78, 202, 125, 21);
      panel.add(tfJobType);
      tfJobType.setColumns(10);

      tfJob = new JTextField();
      tfJob.setBounds(206, 202, 125, 21);
      panel.add(tfJob);
      tfJob.setColumns(10);

      panel_7 = new JPanel();
      panel_7.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
      panel_7.setBounds(12, 230, 422, 2);
      panel.add(panel_7);

      JLabel lblUserGender = new JLabel("성별");
      lblUserGender.setBounds(12, 241, 60, 15);
      panel.add(lblUserGender);

      tfGender = new JTextField();
      tfGender.setBounds(78, 238, 79, 21);
      panel.add(tfGender);
      tfGender.setColumns(10);

      panel_8 = new JPanel();
      panel_8.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
      panel_8.setBounds(12, 266, 422, 2);
      panel.add(panel_8);

      tfSido = new JTextField();
      tfSido.setBounds(78, 275, 79, 21);
      panel.add(tfSido);
      tfSido.setColumns(10);

      tfGoo = new JTextField();
      tfGoo.setBounds(164, 275, 79, 21);
      panel.add(tfGoo);
      tfGoo.setColumns(10);

      tfDong = new JTextField();
      tfDong.setBounds(252, 275, 79, 21);
      panel.add(tfDong);
      tfDong.setColumns(10);

      JLabel lblUserDong = new JLabel("지역");
      lblUserDong.setBounds(12, 278, 60, 15);
      panel.add(lblUserDong);

      panel_9 = new JPanel();
      panel_9.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
      panel_9.setBounds(12, 303, 422, 2);
      panel.add(panel_9);

      JLabel lblUserBirth = new JLabel("생일");
      lblUserBirth.setBounds(12, 313, 60, 15);
      panel.add(lblUserBirth);

      tfBirth = new JTextField();
      tfBirth.setBounds(78, 310, 150, 21);
      panel.add(tfBirth);
      tfBirth.setColumns(10);

      panel_1 = new JPanel();
      panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
      panel_1.setBounds(470, 10, 543, 169);
      add(panel_1);
      panel_1.setLayout(null);

      panel_10 = new JPanel();
      panel_10.setBackground(Color.DARK_GRAY);
      panel_10.setBounds(12, 10, 132, 23);
      panel_1.add(panel_10);
      panel_10.setLayout(null);
      
      lblNewLabel_1 = new JLabel("사용내역");
      lblNewLabel_1.setForeground(Color.WHITE);
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1.setBounds(0, 1, 132, 23);
      panel_10.add(lblNewLabel_1);

      // ---------------------------------
      // 토큰,캐시,신뢰도
      JLabel lbUserToken = new JLabel("토큰");
      lbUserToken.setBounds(12, 54, 57, 15);
      panel_1.add(lbUserToken);

      tfCustToken = new JTextField();
      tfCustToken.setBounds(78, 51, 116, 21);
      panel_1.add(tfCustToken);
      tfCustToken.setColumns(10);

      bCustTokencontent = new JButton("토큰내역");
      bCustTokencontent.setBounds(204, 50, 97, 23);
      panel_1.add(bCustTokencontent);

      tfUserTokenAdd = new JTextField();
      tfUserTokenAdd.setBounds(306, 51, 116, 21);
      panel_1.add(tfUserTokenAdd);
      tfUserTokenAdd.setColumns(10);

      bCustTokenAdd = new JButton("토큰충전");
      bCustTokenAdd.setBounds(429, 50, 97, 23);
      panel_1.add(bCustTokenAdd);

      panel_11 = new JPanel();
      panel_11.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
      panel_11.setBounds(12, 79, 514, 2);
      panel_1.add(panel_11);

      JLabel lbUserCash = new JLabel("캐시");
      lbUserCash.setBounds(12, 90, 57, 15);
      panel_1.add(lbUserCash);

      tfCustCash = new JTextField();
      tfCustCash.setBounds(78, 87, 116, 21);
      panel_1.add(tfCustCash);
      tfCustCash.setColumns(10);

      bCustcashcontent = new JButton("캐시내역");
      bCustcashcontent.setBounds(204, 86, 97, 23);
      panel_1.add(bCustcashcontent);

      panel_12 = new JPanel();
      panel_12.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.LIGHT_GRAY));
      panel_12.setBounds(12, 115, 514, 2);
      panel_1.add(panel_12);

      JLabel lbUserTrue = new JLabel("신뢰도");
      lbUserTrue.setBounds(12, 128, 57, 15);
      panel_1.add(lbUserTrue);

      tfCustTrueContent = new JTextField();
      tfCustTrueContent.setBounds(78, 125, 116, 21);
      panel_1.add(tfCustTrueContent);
      tfCustTrueContent.setColumns(10);

      bCusttruecontent = new JButton("신뢰도내역");
      bCusttruecontent.setBounds(204, 124, 97, 23);
      panel_1.add(bCusttruecontent);

      bCustTokenAdd.addActionListener(this);
      bCustTokencontent.addActionListener(this);
      bCustcashcontent.addActionListener(this);
      bCusttruecontent.addActionListener(this);

      JPanel panel_13 = new JPanel();
      panel_13.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
      panel_13.setBounds(470, 189, 543, 179);
      add(panel_13);
      panel_13.setLayout(null);

      JPanel panel_14 = new JPanel();
      panel_14.setBackground(Color.DARK_GRAY);
      panel_14.setBounds(12, 10, 132, 23);
      panel_13.add(panel_14);
      panel_14.setLayout(null);
      
      lblNewLabel_2 = new JLabel("등록투표");
      lblNewLabel_2.setForeground(Color.WHITE);
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2.setBounds(0, 1, 132, 23);
      panel_14.add(lblNewLabel_2);

      tbuserVote = new JTable(tbUserAdd);
      JScrollPane scrollPane = new JScrollPane(tbuserVote);
      scrollPane.setBounds(12, 43, 519, 101);
      panel_13.add(scrollPane);

      table = new JTable();
      table.setBounds(12, 43, 519, 94);
      panel_13.add(table);

      btnNewButton = new JButton("투표종료");
      btnNewButton.setBounds(225, 150, 97, 23);
      panel_13.add(btnNewButton);

      btnNewButton_1 = new JButton("투표삭제");
      btnNewButton_1.setBounds(330, 150, 97, 23);
      panel_13.add(btnNewButton_1);

      lblNewLabel_3 = new JLabel("투표번호");
      lblNewLabel_3.setBounds(71, 154, 68, 15);
      panel_13.add(lblNewLabel_3);

      textField = new JTextField();
      textField.setBounds(129, 151, 89, 21);
      panel_13.add(textField);

      btnNewButton_1_1 = new JButton("새로고침");
      btnNewButton_1_1.setBounds(434, 150, 97, 23);
      panel_13.add(btnNewButton_1_1);

      btnNewButton_1.addActionListener(this);
      btnNewButton.addActionListener(this);
      btnNewButton_1_1.addActionListener(this);

      try {
         connectDB();
         // user_id 값 가져오기
//         String sqlCatJobType = "select user_id from user_info where user_id = 'cash'";
         String sqlCatJobType = "select user_id from user_info where user_id = '" + userModel.loginId + "'";
         ps = con.prepareStatement(sqlCatJobType);
         rs = ps.executeQuery();
         while (rs.next()) {
            tfUserId.setText(rs.getString("user_id"));
         }
         rs.close();
         ps.close();
      } catch (Exception e) {
//         e.printStackTrace();
      }
      try {
         // user_pw 값 가져오기
//         String sqlCatJobType = "select user_pw from user_info where user_id = 'cash'";
         String sqlCatJobType = "select user_pw from user_info where user_id = '" + userModel.loginId + "'";
         ps = con.prepareStatement(sqlCatJobType);
         rs = ps.executeQuery();
         while (rs.next()) {
            passwordField.setText(rs.getString("user_pw"));
         }
         rs.close();
         ps.close();
      } catch (Exception e) {
//         e.printStackTrace();
      }
      try {
         // user_name 값 가져오기
//         String sqlCatJobType = "select user_name from user_info where user_id = 'cash'";
         String sqlCatJobType = "select user_name from user_info where user_id = '" + userModel.loginId + "'";
         ps = con.prepareStatement(sqlCatJobType);
         rs = ps.executeQuery();
         while (rs.next()) {
            tfUserName.setText(rs.getString("user_name"));
         }
         rs.close();
         ps.close();
      } catch (Exception e) {
//         e.printStackTrace();
      }
      try {
         // user_tel 값 가져오기
//         String sqlCatJobType = "select user_tel from user_info where user_id = 'cash'";
         String sqlCatJobType = "select user_tel from user_info where user_id = '" + userModel.loginId + "'";
         ps = con.prepareStatement(sqlCatJobType);
         rs = ps.executeQuery();
         while (rs.next()) {
            tfUserTel.setText(rs.getString("user_tel"));
         }
         rs.close();
         ps.close();
      } catch (Exception e) {
//         e.printStackTrace();
      }
      try {
         // user_job_type 값 가져오기
//         String sqlCatJobType = "select cjt.job_type_name from user_info u, cat_job cj, cat_job_type cjt where u.job_no = cj.job_no and cj.job_type_no = cjt.job_type_no and user_id = 'cash'";
         String sqlCatJobType = "select cjt.job_type_name from user_info u, cat_job cj, cat_job_type cjt where u.job_no = cj.job_no and cj.job_type_no = cjt.job_type_no and user_id = '"
               + userModel.loginId + "'";
         // String sqlUserID = "select job_no from user_info where user_id ='cash'";
         ps = con.prepareStatement(sqlCatJobType);
         rs = ps.executeQuery();
         while (rs.next()) {
            tfJobType.setText(rs.getString("job_type_name"));
         }
         rs.close();
         ps.close();
      } catch (Exception e3) {
//         e3.printStackTrace();
//         System.out.println("aaaa");
      }
      try {
         // user_job 값 가져오기
//         String sqlCatJobType = "select cj.job_name from user_info u, cat_job cj, cat_job_type cjt where u.job_no = cj.job_no and cj.job_type_no = cjt.job_type_no and user_id = 'cash'";
         String sqlCatJobType = "select cj.job_name from user_info u, cat_job cj, cat_job_type cjt where u.job_no = cj.job_no and cj.job_type_no = cjt.job_type_no and user_id = '"
               + userModel.loginId + "'";
         // String sqlUserID = "select job_no from user_info where user_id ='cash'";
         ps = con.prepareStatement(sqlCatJobType);
         rs = ps.executeQuery();
         while (rs.next()) {
            tfJob.setText(rs.getString("job_name"));
         }
         rs.close();
         ps.close();
      } catch (Exception e3) {
//         e3.printStackTrace();
//         System.out.println("aaaa");
      }
      try {
         // gender_no 값 가져오기
//         String sqlCatGender = "select cg.gender_name from user_info u, cat_gender cg where u.gender_no = cg.gender_no and u.user_id = 'cash'";
         String sqlCatGender = "select cg.gender_name from user_info u, cat_gender cg where u.gender_no = cg.gender_no and u.user_id = '"
               + userModel.loginId + "'";
         ps = con.prepareStatement(sqlCatGender);
         rs = ps.executeQuery();
         while (rs.next()) {
            tfGender.setText(rs.getString("gender_name"));
         }
         rs.close();
         ps.close();
      } catch (Exception e) {
//         e.printStackTrace();
      }

      try {
         // user_sido 값 가져오기
//         String sqlCatGender = "select cls.loc_sido_name from user_info u, cat_loc_dong cld, cat_loc_goo clg, cat_loc_sido cls where u.loc_dong_no = cld.loc_dong_no and clg.loc_goo_no = cld.loc_goo_no and clg.loc_sido_no = cls.loc_sido_no and u.user_id ='cash'";
         String sqlCatGender = "select cls.loc_sido_name from user_info u, cat_loc_dong cld, cat_loc_goo clg, cat_loc_sido cls where u.loc_dong_no = cld.loc_dong_no and clg.loc_goo_no = cld.loc_goo_no and clg.loc_sido_no = cls.loc_sido_no and u.user_id ='"
               + userModel.loginId + "'";
         ps = con.prepareStatement(sqlCatGender);
         rs = ps.executeQuery();
         while (rs.next()) {
            tfSido.setText(rs.getString("loc_sido_name"));
         }
         rs.close();
         ps.close();
      } catch (Exception e) {
//         e.printStackTrace();
      }

      try {
         // user_goo 값 가져오기
//         String sqlCatGender = "select clg.loc_goo_name from user_info u, cat_loc_dong cld, cat_loc_goo clg, cat_loc_sido cls where u.loc_dong_no = cld.loc_dong_no and clg.loc_goo_no = cld.loc_goo_no and clg.loc_sido_no = cls.loc_sido_no and u.user_id ='cash'";
         String sqlCatGender = "select clg.loc_goo_name from user_info u, cat_loc_dong cld, cat_loc_goo clg, cat_loc_sido cls where u.loc_dong_no = cld.loc_dong_no and clg.loc_goo_no = cld.loc_goo_no and clg.loc_sido_no = cls.loc_sido_no and u.user_id ='"
               + userModel.loginId + "'";
         ps = con.prepareStatement(sqlCatGender);
         rs = ps.executeQuery();
         while (rs.next()) {
            tfGoo.setText(rs.getString("loc_goo_name"));
         }
         rs.close();
         ps.close();
      } catch (Exception e) {
//         e.printStackTrace();
      }

      try {
         // user_dong 값 가져오기
//         String sqlUserDong = "select cld.loc_dong_name from user_info u, cat_loc_dong cld, cat_loc_goo clg, cat_loc_sido cls where u.loc_dong_no = cld.loc_dong_no and clg.loc_goo_no = cld.loc_goo_no and clg.loc_sido_no = cls.loc_sido_no and u.user_id ='cash'";
         String sqlUserDong = "select cld.loc_dong_name from user_info u, cat_loc_dong cld, cat_loc_goo clg, cat_loc_sido cls where u.loc_dong_no = cld.loc_dong_no and clg.loc_goo_no = cld.loc_goo_no and clg.loc_sido_no = cls.loc_sido_no and u.user_id ='"
               + userModel.loginId + "'";
         ps = con.prepareStatement(sqlUserDong);
         rs = ps.executeQuery();
         while (rs.next()) {
            tfDong.setText(rs.getString("loc_dong_name"));
         }
         rs.close();
         ps.close();
      } catch (Exception e) {
//         e.printStackTrace();
      }

      try {
         // user_birth 값 가져오기
//         String sqlUserBirth = "select user_birth from user_info where user_id = 'cash'";
         // String sqlUserBirth = "select user_birth from user_info where user_id = '" +
         // userModel.loginId + "'";
         String sqlUserBirth = "select to_char(user_birth, 'YYYY/MM/DD') user_birth from user_info where user_id = '"
               + userModel.loginId + "'";
         ps = con.prepareStatement(sqlUserBirth);
         rs = ps.executeQuery();
         while (rs.next()) {
            tfBirth.setText(rs.getString("user_birth"));
         }
         rs.close();
         ps.close();
      } catch (Exception e) {
//         e.printStackTrace();
      }
      try {
         // user_token 값 가져오기
//         String sqlUserToken = "select sum(token_amount) token_add from user_token where user_id = 'cash'";
         String sqlUserToken = "select sum(token_amount) token_add from user_token where user_id = '"
               + userModel.loginId + "'";
         ps = con.prepareStatement(sqlUserToken);
         rs = ps.executeQuery();
         while (rs.next()) {
            tfCustToken.setText(rs.getString("token_add"));
         }
         rs.close();
         ps.close();
      } catch (Exception e) {
//         e.printStackTrace();
      }
      try {
         // user_cash 값 가져오기
//         String sqlUserCash = "select sum(cash_amount) cash_add from user_cash where user_id = 'cash'";
         String sqlUserCash = "select sum(cash_amount) cash_add from user_cash where user_id = '" + userModel.loginId
               + "'";
         ps = con.prepareStatement(sqlUserCash);
         rs = ps.executeQuery();
         while (rs.next()) {
            tfCustCash.setText(rs.getString("cash_add"));
         }
         rs.close();
         ps.close();
      } catch (Exception e) {
//         e.printStackTrace();
      }
      try {
         // user_truescore 값 가져오기
//         String sqlUserCash = "select sum(cash_amount) cash_add from user_cash where user_id = 'cash'";
         String sqlUserTruescore = "select sum(truescore_amount) truescore_add from user_truescore where user_id = '"
               + userModel.loginId + "'";
         ps = con.prepareStatement(sqlUserTruescore);
         rs = ps.executeQuery();
         while (rs.next()) {
            tfCustTrueContent.setText(rs.getString("truescore_add"));
         }
         rs.close();
         ps.close();
      } catch (Exception e) {
//         e.printStackTrace();
      }
   }

// 텍스트필드 편집가능하지 않도록 지정 --------------------------------------
   void setStyle() {
      tfUserId.setEditable(false);
      passwordField.setEditable(false);
      tfUserName.setEditable(false);
      tfUserTel.setEditable(false);
      tfJobType.setEditable(false);
      tfJob.setEditable(false);
      tfGender.setEditable(false);
      tfSido.setEditable(false);
      tfGoo.setEditable(false);
      tfDong.setEditable(false);
      tfBirth.setEditable(false);
      textField.setEditable(false);

      tfCustToken.setEditable(false);
      tfCustCash.setEditable(false);
      tfCustTrueContent.setEditable(false);
   }

   class userVoteAddTable extends AbstractTableModel {
      ArrayList data = new ArrayList();
      String[] title = { "번호", "제목", "등록일(종료까지)", "현재투표인원", "목표투표수" };

      public int getColumnCount() {
         return title.length;
      }

      public int getRowCount() {
         return data.size();
      }

      public Object getValueAt(int row, int col) {
         // 값 하나씩 얻어노는 메소드
         ArrayList temp = (ArrayList) data.get(row);
         // get은 오브젝트형으로 변환해줘서 강제 형변화 필요
         return temp.get(col);
      }

      public String getColumnName(int col) {
         return title[col];
      }
   }

   void userVoteList() {
      try {
         ArrayList list = model.uservotelist();
         tbUserAdd.data = list;
         tbuserVote.setModel(tbUserAdd);
      } catch (Exception e4) {
         JOptionPane.showMessageDialog(null, "추출 실패" + e4.getMessage());
//         e4.printStackTrace(); // 오류 몇번째줄에서 나는지 확인
      }
   }

   public void eventProc() {
      tbuserVote.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            int row = tbuserVote.getSelectedRow();
            int col = 0;
            String voteno = String.valueOf(tbuserVote.getValueAt(row, col));

            textField.setText(voteno);

         }
      });
   }

// 버튼 이벤트 actionPerformed --------------------------------------
   public void actionPerformed(ActionEvent e) {
      // 토큰충전
      Object o = e.getSource();

      if (o == bCustTokenAdd) {
         String tokenadd = tfUserTokenAdd.getText();

         if (tokenadd == null || tokenadd.equals("")) {
            JOptionPane.showMessageDialog(null, "1원이상 입력해 주세요");

         }
         int to = Integer.parseInt(tokenadd);
         if (to < 1) {

            JOptionPane.showMessageDialog(null, "1원이상 입력해 주세요");
         } else {
            userVO vo = new userVO(tokenadd);
            try {
               model.tokenadd(vo);
               JOptionPane.showMessageDialog(null, "토큰충전 성공");
            } catch (Exception e1) {
               JOptionPane.showMessageDialog(null, "토큰충전 실패 : " + e1.getMessage());
//               e1.printStackTrace();
            }
         }

         try {
            // user_token 값 가져오기
//            String sqlUserToken = "select sum(token_amount) token_add from user_token where user_id = 'cash'";
            String sqlUserToken = "select sum(token_amount) token_add from user_token where user_id = '"
                  + userModel.loginId + "'";
            ps = con.prepareStatement(sqlUserToken);
            rs = ps.executeQuery();
            while (rs.next()) {
               tfCustToken.setText(rs.getString("token_add"));
            }
            rs.close();
            ps.close();
         } catch (Exception e2) {
//            e2.printStackTrace();
         }
      }
      if (o == bCustUserMod) {
         Window parentWindow = SwingUtilities.windowForComponent(bCustUserMod);
         myInfoModView dialog = new myInfoModView();
         dialog.setLocationRelativeTo(bCustUserMod);
         dialog.setModal(true);
         dialog.setVisible(true);
      }

      if (o == bCustTokencontent) {
         Window parentWindow = SwingUtilities.windowForComponent(bCustTokencontent);
         tokenHistoryView dialog = new tokenHistoryView();
         dialog.setLocationRelativeTo(bCustTokencontent);
         dialog.setModal(true);
         dialog.setVisible(true);
      }

      if (o == bCustcashcontent) {
         Window parentWindow = SwingUtilities.windowForComponent(bCustcashcontent);
         cashHistoryView dialog = new cashHistoryView();
         dialog.setLocationRelativeTo(bCustcashcontent);
         dialog.setModal(true);
         dialog.setVisible(true);
      }

      if (o == bCusttruecontent) {
         Window parentWindow = SwingUtilities.windowForComponent(bCusttruecontent);
         truescoreHistoryView dialog = new truescoreHistoryView();
         dialog.setLocationRelativeTo(bCusttruecontent);
         dialog.setModal(true);
         dialog.setVisible(true);
      }
      if (o == btnNewButton) {
         String voteno = textField.getText();
         if (voteno == null || voteno.equals("")) {
            JOptionPane.showMessageDialog(null, "종료할 투표를 선택해주세요");
         } else {
            try {
               String sqlFinishVote = "insert into vote_finish(finish_content,finish_date,vote_no) values ('임의종료',sysdate,'"
                     + voteno + "')";
               ps = con.prepareStatement(sqlFinishVote);
               ps.executeUpdate();
               ps.close();

               JOptionPane.showMessageDialog(null, "투표가 임의 종료 되었습니다.");
            } catch (Exception e2) {
//               e2.printStackTrace();
            }
         }

      }
      if (o == btnNewButton_1) {
         String voteno = textField.getText();
         if (voteno == null || voteno.equals("")) {
            JOptionPane.showMessageDialog(null, "삭제할 투표를 선택해주세요");
         } else {
            try {

               String sqlVoteDelete = "delete from vote_option where vote_no ='" + voteno + "'";
               ps = con.prepareStatement(sqlVoteDelete);
               ps.executeUpdate();
               ps.close();

            } catch (Exception e5) {
//               e5.printStackTrace();
            }
            try {

               String sqlVoteDelete = "delete from result_info where vote_no ='" + voteno + "'";
               ps = con.prepareStatement(sqlVoteDelete);
               ps.executeUpdate();
               ps.close();

            } catch (Exception e5) {
//               e5.printStackTrace();
            }

            try {

               String sqlVoteDelete = "delete from range_birth where vote_no ='" + voteno + "'";
               ps = con.prepareStatement(sqlVoteDelete);
               ps.executeUpdate();
               ps.close();
            } catch (Exception e5) {
//               e5.printStackTrace();
            }
            try {

               String sqlVoteDelete = "delete from range_etc where vote_no ='" + voteno + "'";
               ps = con.prepareStatement(sqlVoteDelete);
               ps.executeUpdate();
               ps.close();
            } catch (Exception e5) {
//               e5.printStackTrace();
            }
            try {

               String sqlVoteDelete = "delete from range_gender where vote_no ='" + voteno + "'";
               ps = con.prepareStatement(sqlVoteDelete);
               ps.executeUpdate();
               ps.close();
            } catch (Exception e5) {
//               e5.printStackTrace();
            }
            try {

               String sqlVoteDelete = "delete from range_job where vote_no ='" + voteno + "'";
               ps = con.prepareStatement(sqlVoteDelete);
               ps.executeUpdate();
               ps.close();
            } catch (Exception e5) {
//               e5.printStackTrace();
            }
            try {

               String sqlVoteDelete = "delete from range_loc where vote_no ='" + voteno + "'";
               ps = con.prepareStatement(sqlVoteDelete);
               ps.executeUpdate();
               ps.close();
            } catch (Exception e5) {
//               e5.printStackTrace();
            }

            try {

               String sqlVoteDelete = "delete from report_info where vote_no ='" + voteno + "'";
               ps = con.prepareStatement(sqlVoteDelete);
               ps.executeUpdate();
               ps.close();
            } catch (Exception e5) {
//               e5.printStackTrace();
            }

            try {

               String sqlVoteDelete = "delete from vote_info where vote_no ='" + voteno + "'";
               ps = con.prepareStatement(sqlVoteDelete);
               ps.executeUpdate();
               ps.close();

               JOptionPane.showMessageDialog(null, "삭제되었습니다!");

            } catch (Exception e5) {
//               e5.printStackTrace();
            }
         }
      }
      if (o == btnNewButton_1_1) {
         try {
            ArrayList list = model.uservotelist();
            tbUserAdd.data = list;
            tbuserVote.setModel(tbUserAdd);
            tbUserAdd.fireTableDataChanged();
         } catch (Exception e4) {
            JOptionPane.showMessageDialog(null, "추출 실패" + e4.getMessage());
//            e4.printStackTrace(); // 오류 몇번째줄에서 나는지 확인
         }

         try {
            // user_cash 값 가져오기
//            String sqlUserCash = "select sum(cash_amount) cash_add from user_cash where user_id = 'cash'";
            String sqlUserCash = "select sum(cash_amount) cash_add from user_cash where user_id = '"
                  + userModel.loginId + "'";
            ps = con.prepareStatement(sqlUserCash);
            rs = ps.executeQuery();
            while (rs.next()) {
               tfCustCash.setText(rs.getString("cash_add"));
            }
            rs.close();
            ps.close();
         } catch (Exception e4) {
//            e4.printStackTrace();
         }

         try {
            // user_truescore 값 가져오기
//            String sqlUserCash = "select sum(cash_amount) cash_add from user_cash where user_id = 'cash'";
            String sqlUserTruescore = "select sum(truescore_amount) truescore_add from user_truescore where user_id = '"
                  + userModel.loginId + "'";
            ps = con.prepareStatement(sqlUserTruescore);
            rs = ps.executeQuery();
            while (rs.next()) {
               tfCustTrueContent.setText(rs.getString("truescore_add"));
            }
            rs.close();
            ps.close();
         } catch (Exception e5) {
//            e5.printStackTrace();
         }

      }
   }
}