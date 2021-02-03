package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import model.ConnectionPool;
import model.userModel;
import view.registView.ItemChangeListener;
import vo.userVO;

public class registView extends JDialog implements ActionListener {

   private JPanel contentPane;
   private JTextField tfUserId;
   private JTextField tfUserPw;
   private JTextField tfUserName;
   private JTextField tfUserTel;
   private JButton btnRegist;
   private JComboBox cbGender;
   private JComboBox cbJobType;
   private JComboBox cbJob;
   private JComboBox cbSido;
   private JComboBox cbGoo;
   private JComboBox cbDong;
   PreparedStatement ps = null;
   ResultSet rs = null;
   static Connection con = null;
   userModel model = null;
   String loc_sido_no = null;
   int cnt1 = 0, cnt2 = 0, cnt3 = 0;

//TODO 생일 입력용 달력   
   UtilDateModel model_birth;
   
   void connectDB() throws Exception {
      con = ConnectionPool.getConnection();
   }
   
   public registView() {
      
      // db 연결
      try {
         model = new userModel();
         System.out.println("유저DB 연결 성공");
      } catch (Exception e) {
         JOptionPane.showConfirmDialog(null, "유저 DB연결 실패" + e.getMessage());
      }

      setTitle("회원가입");
      setResizable(false);
      setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 400, 284);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel lblUserId = new JLabel("\uC544\uC774\uB514");
      lblUserId.setBounds(12, 10, 57, 15);
      contentPane.add(lblUserId);

      JLabel lblUserPw = new JLabel("\uD328\uC2A4\uC6CC\uB4DC");
      lblUserPw.setBounds(12, 35, 57, 15);
      contentPane.add(lblUserPw);

      JLabel lblUserName = new JLabel("\uC774\uB984");
      lblUserName.setBounds(12, 60, 57, 15);
      contentPane.add(lblUserName);

      JLabel lblUserTel = new JLabel("\uC804\uD654\uBC88\uD638");
      lblUserTel.setBounds(12, 85, 57, 15);
      contentPane.add(lblUserTel);

      JLabel lblUserJob = new JLabel("\uC9C1\uC5C5");
      lblUserJob.setBounds(12, 110, 57, 15);
      contentPane.add(lblUserJob);

      JLabel lblUserGender = new JLabel("\uC131\uBCC4");
      lblUserGender.setBounds(12, 135, 57, 15);
      contentPane.add(lblUserGender);

      JLabel lblUserDong = new JLabel("\uC9C0\uC5ED");
      lblUserDong.setBounds(12, 160, 57, 15);
      contentPane.add(lblUserDong);

      JLabel lblUserBirth = new JLabel("\uC0DD\uC77C");
      lblUserBirth.setBounds(12, 185, 57, 15);
      contentPane.add(lblUserBirth);

      tfUserId = new JTextField();
      tfUserId.setBounds(76, 7, 116, 21);
      contentPane.add(tfUserId);
      tfUserId.setColumns(10);

      tfUserPw = new JTextField();
      tfUserPw.setBounds(76, 32, 116, 21);
      contentPane.add(tfUserPw);
      tfUserPw.setColumns(10);

      tfUserName = new JTextField();
      tfUserName.setBounds(76, 57, 116, 21);
      contentPane.add(tfUserName);
      tfUserName.setColumns(10);

      tfUserTel = new JTextField();
      tfUserTel.setBounds(76, 82, 116, 21);
      contentPane.add(tfUserTel);
      tfUserTel.setColumns(10);

      btnRegist = new JButton("\uD68C\uC6D0\uAC00\uC785");
      btnRegist.setBounds(146, 213, 116, 23);
      contentPane.add(btnRegist);

      cbGender = new JComboBox();
      cbGender.setBounds(76, 131, 75, 23);
      contentPane.add(cbGender);

      cbJobType = new JComboBox();
      cbJobType.setBounds(76, 106, 116, 23);
      contentPane.add(cbJobType);

      cbJob = new JComboBox();
      cbJob.setBounds(204, 106, 160, 23);
      contentPane.add(cbJob);

      cbSido = new JComboBox();
      cbSido.setBounds(76, 156, 116, 23);
      contentPane.add(cbSido);

      cbGoo = new JComboBox();
      cbGoo.setBounds(202, 156, 75, 23);
      contentPane.add(cbGoo);

      cbDong = new JComboBox();
      cbDong.setBounds(289, 156, 75, 23);
      contentPane.add(cbDong);

// TODO 달력 입력기 생성------------------------------------
      Properties p = new Properties();
      p.put("text.today", "Today");
      p.put("text.month", "Month");
      p.put("text.year", "Year");

      Calendar today = Calendar.getInstance();

      // 1. 시작일 입력 영역 판넬에 추가
      model_birth = new UtilDateModel();
      model_birth.setDate(2000, today.get(Calendar.MONTH), today.get(Calendar.DATE));
      model_birth.setSelected(true);
      JDatePanelImpl datePanel_birth = new JDatePanelImpl(model_birth, p);

      JDatePickerImpl datePicker_birth = new JDatePickerImpl(datePanel_birth, new DateLabelFormatter());
      datePicker_birth.getJFormattedTextField().setFont(new Font("돋움체", Font.PLAIN, 12));
      datePicker_birth.setBounds(76, 182, 130, 25);
      
      contentPane.add(datePicker_birth);
      
      
      cbJobType.addItemListener(new ItemChangeListener());
      cbSido.addItemListener(new ItemChangeListener());
      cbGoo.addItemListener(new ItemChangeListener());
      btnRegist.addActionListener(this);

      try {
         connectDB();
         // cat_job_type 값 가져오기
         String sqlCatJobType = "select * from cat_job_type";
         ps = con.prepareStatement(sqlCatJobType);
         rs = ps.executeQuery();
         while (rs.next()) {
            cbJobType.addItem(rs.getString("job_type_name"));
         }
         rs.close();
         ps.close();
      } catch (Exception e) {
      }

      try {
         // cat_gender 값 가져오기
         String sqlCatGender = "select * from cat_gender";
         ps = con.prepareStatement(sqlCatGender);
         rs = ps.executeQuery();
         while (rs.next()) {
            cbGender.addItem(rs.getString("gender_name"));
         }
      } catch (Exception e1) {
//         e1.printStackTrace();
      }

      try {
         // cat_loc_sido 값 가져오기
         String sqlCatLocSido = "select * from cat_loc_sido";
         ps = con.prepareStatement(sqlCatLocSido);
         rs = ps.executeQuery();
         while (rs.next()) {
            cbSido.addItem(rs.getString("loc_sido_name"));
         }
      } catch (Exception e2) {
//         e2.printStackTrace();
      }

   }

   public void actionPerformed(ActionEvent ev) {
      Object o = ev.getSource();

      // 회원가입
      if (o == btnRegist) {
         String id = tfUserId.getText();
         String pw = tfUserPw.getText();
         String name = tfUserName.getText();
         String tel = tfUserTel.getText();
         String job = String.valueOf(cbJob.getSelectedItem());
         String gender = String.valueOf(cbGender.getSelectedItem());
         String dong = String.valueOf(cbDong.getSelectedItem());
         String birth = 
//               tfUserBirth.getText();
//TODO 달력 입력기에서 날짜 받아오기
         model_birth.getYear()+"/"+(model_birth.getMonth()+1)+"/"+model_birth.getDay();

         String jobNo = getJobNo(job);
         String genderNo = getGenderNo(gender);
         String dongNo = getDongNo(dong);

         userVO vo = new userVO(id, pw, name, tel, jobNo, genderNo, dongNo, birth);
         try {
            model.regist(vo);
            JOptionPane.showMessageDialog(null, "회원가입 성공");
         } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "회원가입 실패 : " + e.getMessage());
//            e.printStackTrace();
         }
      }
   }

   class ItemChangeListener implements ItemListener {
      @Override
      public void itemStateChanged(ItemEvent e) {
         Object o = e.getSource();
         if (e.getStateChange() == ItemEvent.SELECTED && o == cbJobType) {
            cbJob.removeAllItems();
            try {
               // cat_job_type 목록 가져오기
               if (cnt1 < 1) {
                  String sqlCatJobType = "select * from cat_job_type";
                  ps = con.prepareStatement(sqlCatJobType);
                  rs = ps.executeQuery();
                  while (rs.next()) {
                     cbJobType.addItem(rs.getString("job_type_name"));
                  }
               }

               // 해당 직종 이름의 job_type_no 구하기
               String job_type_name = cbJobType.getSelectedItem().toString();
               String sqlJobTypeNo = "select job_type_no from cat_job_type where job_type_name=?";
               String job_type_no = null;
               ps = con.prepareStatement(sqlJobTypeNo);
               ps.setString(1, job_type_name);
               rs = ps.executeQuery();
               if (rs.next()) {
                  job_type_no = rs.getString("job_type_no");
               }

               // cat_job 목록 가져오기
               String sqlCatJob = "select * from cat_job where job_type_no=?";
               ps = con.prepareStatement(sqlCatJob);
               ps.setString(1, job_type_no);
               rs = ps.executeQuery();
               while (rs.next()) {
                  cbJob.addItem(rs.getString("job_name"));
               }
               rs.close();
               ps.close();

            } catch (Exception e1) {
//               e1.printStackTrace();
            }
         }

         if (e.getStateChange() == ItemEvent.SELECTED && o == cbSido) {
            cbGoo.removeAllItems();
            cbDong.removeAllItems();
            try {
               // cat_loc_sido 목록 가져오기
               if (cnt2 < 1) {
                  String sqlCatLocSido = "select * from cat_loc_sido";
                  ps = con.prepareStatement(sqlCatLocSido);
                  rs = ps.executeQuery();
                  while (rs.next()) {
                     cbSido.addItem(rs.getString("loc_sido_name"));
                  }
               }

               // 해당 시도 이름의 loc_sido_no 구하기
               String loc_sido_name = cbSido.getSelectedItem().toString();
               String sqlLocSidoNo = "select loc_sido_no from cat_loc_sido where loc_sido_name=?";
               ps = con.prepareStatement(sqlLocSidoNo);
               ps.setString(1, loc_sido_name);
               rs = ps.executeQuery();
               if (rs.next()) {
                  loc_sido_no = rs.getString("loc_sido_no");
               }

               // cat_loc_goo 목록 가져오기
               String sqlCatLocGoo = "select * from cat_loc_goo where loc_sido_no=?";
               ps = con.prepareStatement(sqlCatLocGoo);
               ps.setString(1, loc_sido_no);
               rs = ps.executeQuery();
               while (rs.next()) {
                  cbGoo.addItem(rs.getString("loc_goo_name"));
               }
               rs.close();
               ps.close();

            } catch (Exception e1) {
//               e1.printStackTrace();
            }
         }

         if (e.getStateChange() == ItemEvent.SELECTED && o == cbGoo) {
            cbDong.removeAllItems();
            try {
               // cat_loc_goo 목록 가져오기
               String sqlCatLocGoo = "select * from cat_loc_goo where loc_sido_no=?";
               ps = con.prepareStatement(sqlCatLocGoo);
               ps.setString(1, loc_sido_no);
               rs = ps.executeQuery();
               while (rs.next()) {
                  cbGoo.addItem(rs.getString("loc_goo_name"));
               }

               // 해당 구 이름의 loc_goo_no 구하기
               String loc_goo_name = cbGoo.getSelectedItem().toString();
               String sqlLocGooNo = "select * from cat_loc_goo where loc_goo_name=? and loc_goo_no between 25010 and 25050";
               String loc_goo_no = null;
               ps = con.prepareStatement(sqlLocGooNo);
               ps.setString(1, loc_goo_name);
               rs = ps.executeQuery();
               if (rs.next()) {
                  loc_goo_no = rs.getString("loc_goo_no");
               }

               // cat_loc_dong 목록 가져오기
               String sqlCatLocDong = "select * from cat_loc_dong where loc_goo_no=?";
               ps = con.prepareStatement(sqlCatLocDong);
               ps.setString(1, loc_goo_no);
               rs = ps.executeQuery();
               while (rs.next()) {
                  cbDong.addItem(rs.getString("loc_dong_name"));
               }
               rs.close();
               ps.close();

            } catch (Exception e1) {
//               e1.printStackTrace();
            }
         }
      }
   }

   public String getJobNo(String job) {
      // 해당 직업 이름의 job_no 구하기
      String sqlJobNo = "select job_no from cat_job where job_name=?";
      String job_no = null;
      try {
         ps = con.prepareStatement(sqlJobNo);
         ps.setString(1, job);
         rs = ps.executeQuery();
         if (rs.next()) {
            job_no = rs.getString("job_no");
         }
      } catch (SQLException e) {
//         e.printStackTrace();
      }

      return job_no;
   }

   public String getGenderNo(String gender) {
      // 해당 직업 이름의 gender_no 구하기
      String sqlGenderNo = "select gender_no from cat_gender where gender_name=?";
      String gender_no = null;
      try {
         ps = con.prepareStatement(sqlGenderNo);
         ps.setString(1, gender);
         rs = ps.executeQuery();
         if (rs.next()) {
            gender_no = rs.getString("gender_no");
         }
      } catch (SQLException e) {
//         e.printStackTrace();
      }

      return gender_no;
   }

   public String getDongNo(String dong) {
      // 해당 직업 이름의 dong_no 구하기
      String sqlDongNo = "select loc_dong_no from cat_loc_dong where loc_dong_name=?";
      String dong_no = null;
      try {
         ps = con.prepareStatement(sqlDongNo);
         ps.setString(1, dong);
         rs = ps.executeQuery();
         if (rs.next()) {
            dong_no = rs.getString("loc_dong_no");
         }
      } catch (SQLException e) {
//         e.printStackTrace();
      }

      return dong_no;
   }

}