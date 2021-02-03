package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import main.CashVote;
import model.ConnectionPool;
import model.userModel;
import model.voteModel;
import vo.voteVO;

import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class voteView extends JPanel implements ActionListener {

   private Image backgroundImage = new ImageIcon(CashVote.class.getResource("/image/CashVoteEvent.png")).getImage();
   private JButton btnNext;
   private JButton btnReport;
   private JTextField tfVoteno;
   private JTextField tfTitle;
   private JTextField tfVoteContent;
   private JTextField tfToken;
   private JTextField tfVoteCat;
   private JRadioButton rdbtnOption1;
   private JRadioButton rdbtnOption2;
   private JRadioButton rdbtnOption3;
   private JRadioButton rdbtnOption4;
   PreparedStatement ps = null;
   ResultSet rs = null;
   static Connection con = null;
   voteModel model = null;
   voteVO vo = null;
   private JLabel lblNewLabel;

   /**
    * Create the panel.
    */

   void connectDB() throws Exception {
      con = ConnectionPool.getConnection();
   }

   public voteView() {

	   
      try {
         model = new voteModel();
         System.out.println("유저DB 연결 성공");
      } catch (Exception e) {
         JOptionPane.showConfirmDialog(null, "유저 DB연결 실패" + e.getMessage());
      }

      setLayout(null);

      btnReport = new JButton("\uC2E0\uACE0");
      btnReport.setBounds(584, 365, 77, 23);
      add(btnReport);
      btnReport.addActionListener(this);

      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
      layeredPane.setBounds(12, 10, 661, 391);
      add(layeredPane);

      tfVoteContent = new JTextField();
      tfVoteContent.setBounds(12, 102, 637, 98);
      layeredPane.add(tfVoteContent);
      tfVoteContent.setEditable(false);
      tfVoteContent.setFont(new Font("굴림", Font.BOLD, 12));
      tfVoteContent.setColumns(10);

      JLabel lbTitle = new JLabel("TITLE");
      lbTitle.setBounds(13, 74, 31, 15);
      layeredPane.add(lbTitle);
      lbTitle.setHorizontalAlignment(SwingConstants.LEFT);
      lbTitle.setFont(new Font("굴림", Font.PLAIN, 10));

      tfTitle = new JTextField();
      tfTitle.setBounds(82, 71, 366, 21);
      layeredPane.add(tfTitle);
      tfTitle.setEditable(false);
      tfTitle.setFont(new Font("굴림", Font.BOLD, 12));
      tfTitle.setColumns(10);

      tfVoteno = new JTextField();
      tfVoteno.setBounds(82, 42, 72, 21);
      layeredPane.add(tfVoteno);
      tfVoteno.setEditable(false);
      tfVoteno.setFont(new Font("굴림", Font.BOLD, 12));
      tfVoteno.setColumns(10);

      JLabel lbVoteNo = new JLabel("VOTE_NO");
      lbVoteNo.setBounds(13, 46, 57, 15);
      layeredPane.add(lbVoteNo);
      lbVoteNo.setFont(new Font("굴림", Font.PLAIN, 9));

      JLabel iconToken = new JLabel("");
      iconToken.setBounds(526, 46, 20, 21);
      layeredPane.add(iconToken);
      iconToken.setIcon(new ImageIcon(CashVote.class.getResource("/image/cash.png")));

      tfToken = new JTextField();
      tfToken.setBounds(556, 46, 93, 21);
      layeredPane.add(tfToken);
      tfToken.setEditable(false);
      tfToken.setFont(new Font("굴림", Font.BOLD, 12));
      tfToken.setHorizontalAlignment(SwingConstants.CENTER);
      tfToken.setColumns(10);

      tfVoteCat = new JTextField();
      tfVoteCat.setBounds(576, 74, 73, 21);
      layeredPane.add(tfVoteCat);
      tfVoteCat.setEditable(false);
      tfVoteCat.setFont(new Font("굴림", Font.BOLD, 12));
      tfVoteCat.setColumns(10);

      JLabel lbVoteCat = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
      lbVoteCat.setBounds(526, 78, 48, 15);
      layeredPane.add(lbVoteCat);
      lbVoteCat.setFont(new Font("굴림", Font.PLAIN, 10));

      JPanel panel = new JPanel();
      panel.setBackground(Color.DARK_GRAY);
      panel.setBounds(12, 10, 132, 23);
      layeredPane.add(panel);
      panel.setLayout(null);
      
      lblNewLabel = new JLabel("투표하기");
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setForeground(Color.WHITE);
      lblNewLabel.setBounds(0, 1, 132, 23);
      panel.add(lblNewLabel);

//      JLabel lblNewLabel = new JLabel("투표하기");
//      lblNewLabel.setForeground(Color.WHITE);
//      panel.add(lblNewLabel);

      btnNext = new JButton("");
      btnNext.setBounds(612, 260, 37, 36);
      layeredPane.add(btnNext);
      btnNext.setIcon(new ImageIcon(CashVote.class.getResource("/image/vote.png")));
      btnNext.addActionListener(this);

      JPanel panel_1 = new JPanel() {
         public void paintComponent(Graphics g) {
            g.drawImage(backgroundImage, 0, 0, null);
            setOpaque(false);
            super.paintComponent(g);
         }
      };
      panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
      panel_1.setBounds(685, 10, 331, 391);
      add(panel_1);

//      JRadioButton rdbtnNewRadioButton = new JRadioButton();
//      rdbtnNewRadioButton.setBounds(12, 185, 500, 23);
//      add(rdbtnNewRadioButton);
//      
//      JRadioButton rdbtnNewRadioButton_1 = new JRadioButton();
//      rdbtnNewRadioButton_1.setBounds(12, 245, 500, 23);
//      add(rdbtnNewRadioButton_1);
//      
//      JRadioButton rdbtnNewRadioButton_2 = new JRadioButton();
//      rdbtnNewRadioButton_2.setBounds(12, 305, 500, 23);
//      add(rdbtnNewRadioButton_2);
//      
//      JRadioButton rdbtnNewRadioButton_3 = new JRadioButton();
//      rdbtnNewRadioButton_3.setBounds(12, 365, 500, 23);
//      add(rdbtnNewRadioButton_3);

      try {
         voting();
         option();

      } catch (Exception e) {
         // TODO: handle exception
      }

   }

   public void voting() {
	   
      try {
         vo = model.randomVote();
      } catch (Exception e) {
         // TODO Auto-generated catch block
//         e.printStackTrace();
      }
      tfVoteno.setFont(loginView.customFont);
      tfTitle.setFont(loginView.customFont);
      tfVoteContent.setFont(loginView.customFont);
      tfToken.setFont(loginView.customFont);
      tfVoteCat.setFont(loginView.customFont);
      
      tfVoteno.setText(String.valueOf(vo.getVote_No()));
      tfTitle.setText(vo.getVote_Title());
      tfVoteContent.setText(vo.getVote_Content());
      tfToken.setText(String.valueOf(vo.getvote_Cash_Amount()));
      tfVoteCat.setText(vo.getVote_Cat_Name());
   }

   public void option() {

      try {
         ArrayList list = model.selectVote(tfVoteno.getText());
         ButtonGroup group = new ButtonGroup();
         if (list.size() == 2) {
            rdbtnOption1 = new JRadioButton();
            rdbtnOption1.setBounds(20, 220, 500, 23);
            add(rdbtnOption1);

            rdbtnOption2 = new JRadioButton();
            rdbtnOption2.setBounds(20, 260, 500, 23);
            add(rdbtnOption2);

            group.add(rdbtnOption1);
            group.add(rdbtnOption2);

            this.add(rdbtnOption1);
            this.add(rdbtnOption2);

            rdbtnOption1.setText(list.get(0).toString());
            rdbtnOption2.setText(list.get(1).toString());

            rdbtnOption1.setVisible(true);
            rdbtnOption2.setVisible(true);

         } else if (list.size() == 3) {
            rdbtnOption1 = new JRadioButton();
            rdbtnOption1.setBounds(20, 220, 500, 23);
            add(rdbtnOption1);

            rdbtnOption2 = new JRadioButton();
            rdbtnOption2.setBounds(20, 260, 500, 23);
            add(rdbtnOption2);

            rdbtnOption3 = new JRadioButton();
            rdbtnOption3.setBounds(20, 300, 500, 23);
            add(rdbtnOption3);

            group.add(rdbtnOption1);
            group.add(rdbtnOption2);
            group.add(rdbtnOption3);

            this.add(rdbtnOption1);
            this.add(rdbtnOption2);
            this.add(rdbtnOption3);

            rdbtnOption1.setText(list.get(0).toString());
            rdbtnOption2.setText(list.get(1).toString());
            rdbtnOption3.setText(list.get(2).toString());

            rdbtnOption1.setVisible(true);
            rdbtnOption2.setVisible(true);
            rdbtnOption3.setVisible(true);

         } else if (list.size() == 4) {
            rdbtnOption1 = new JRadioButton();
            rdbtnOption1.setBounds(20, 220, 500, 23);
            add(rdbtnOption1);

            rdbtnOption2 = new JRadioButton();
            rdbtnOption2.setBounds(20, 260, 500, 23);
            add(rdbtnOption2);

            rdbtnOption3 = new JRadioButton();
            rdbtnOption3.setBounds(20, 300, 500, 23);
            add(rdbtnOption3);

            rdbtnOption4 = new JRadioButton();
            rdbtnOption4.setBounds(20, 340, 500, 23);
            add(rdbtnOption4);

            group.add(rdbtnOption1);
            group.add(rdbtnOption2);
            group.add(rdbtnOption3);
            group.add(rdbtnOption4);

            this.add(rdbtnOption1);
            this.add(rdbtnOption2);
            this.add(rdbtnOption3);
            this.add(rdbtnOption4);

            rdbtnOption1.setText(list.get(0).toString());
            rdbtnOption2.setText(list.get(1).toString());
            rdbtnOption3.setText(list.get(2).toString());
            rdbtnOption4.setText(list.get(3).toString());

            rdbtnOption1.setVisible(true);
            rdbtnOption2.setVisible(true);
            rdbtnOption3.setVisible(true);
            rdbtnOption4.setVisible(true);
         }

      } catch (Exception e) {
         // TODO Auto-generated catch block
//         e.printStackTrace();
      }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      Object o = e.getSource();
      if (o == btnReport) {
         Window parentWindow = SwingUtilities.windowForComponent(btnReport);
         reportView dialog = new reportView(vo);
         dialog.setLocationRelativeTo(btnReport);
         dialog.setModal(true);
         dialog.setVisible(true);
      } else if (o == btnNext) {
         try {
            int saveNum = 0;
            ArrayList list = model.selectVote(tfVoteno.getText());
            ButtonGroup group = new ButtonGroup();

            if (list.size() == 2) {
               if (rdbtnOption1.isSelected() == false && rdbtnOption2.isSelected() == false) {
                  JOptionPane.showMessageDialog(null, "투표를 해주세요");
               } else if (rdbtnOption1.isSelected()) {
                  try {
                     connectDB();
                     String voteno = tfVoteno.getText();

                     String sqlUserReward = "insert into user_cash(cash_no, cash_date, cash_amount, cash_content, user_id) values (user_cashAdd_sq.nextval, sysdate, (select vote_cash_amount from vote_info where vote_no ='"
                           + voteno + "'),'투표리워드','" + userModel.loginId + "')";
                     ps = con.prepareStatement(sqlUserReward);
                     ps.executeUpdate();
                     ps.close();

                     JOptionPane.showMessageDialog(null, "캐시 적립 완료!");

                     saveNum = model.selectedVoteNo(rdbtnOption1.getText());
                    
                     
                     int count = 0;
                     System.out.println(
                           count + " " + saveNum + " " + tfVoteno.getText() + " " + userModel.loginId);
                     model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     count = model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     model.insertVoteInfo(count, saveNum, tfVoteno.getText(), userModel.loginId);
                     model.trueScore(voteno, saveNum);
                     
                     Clear();
                     voting();
                     option();
                     
                  } catch (Exception e2) {
//                     e2.printStackTrace();
                  }
               } else if (rdbtnOption2.isSelected()) {
                  try {
                     connectDB();
                     String voteno = tfVoteno.getText();

                     String sqlUserReward = "insert into user_cash(cash_no, cash_date, cash_amount, cash_content, user_id) values (user_cashAdd_sq.nextval, sysdate, (select vote_cash_amount from vote_info where vote_no ='"
                           + voteno + "'),'투표리워드','" + userModel.loginId + "')";
                     ps = con.prepareStatement(sqlUserReward);
                     ps.executeUpdate();
                     ps.close();

                     JOptionPane.showMessageDialog(null, "캐시 적립 완료!");

                     saveNum = model.selectedVoteNo(rdbtnOption2.getText());
                    
                     int count = 0;
                     System.out.println(
                           count + " " + saveNum + " " + tfVoteno.getText() + " " + userModel.loginId);
                     model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     count = model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     model.insertVoteInfo(count, saveNum, tfVoteno.getText(), userModel.loginId);
                     model.trueScore(voteno, saveNum);
                     
                     Clear();
                     voting();
                     option();
                     
                  } catch (Exception e2) {
//                     e2.printStackTrace();
                  }

               }
            }
            if (list.size() == 3) {
               if (rdbtnOption1.isSelected() == false && rdbtnOption2.isSelected() == false
                     && rdbtnOption3.isSelected() == false) {
                  JOptionPane.showMessageDialog(null, "투표를 해주세요");
               } else if (rdbtnOption1.isSelected()) {
                  try {
                     connectDB();
                     String voteno = tfVoteno.getText();

                     String sqlUserReward = "insert into user_cash(cash_no, cash_date, cash_amount, cash_content, user_id) values (user_cashAdd_sq.nextval, sysdate, (select vote_cash_amount from vote_info where vote_no ='"
                           + voteno + "'),'투표리워드','" + userModel.loginId + "')";
                     ps = con.prepareStatement(sqlUserReward);
                     ps.executeUpdate();
                     ps.close();

                     JOptionPane.showMessageDialog(null, "캐시 적립 완료!");

                     saveNum = model.selectedVoteNo(rdbtnOption1.getText());
                    
                     
                     int count = 0;
                     System.out.println(
                           count + " " + saveNum + " " + tfVoteno.getText() + " " + userModel.loginId);
                     model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     count = model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     model.insertVoteInfo(count, saveNum, tfVoteno.getText(), userModel.loginId);
                     model.trueScore(voteno, saveNum);
                     
                     Clear();
                     voting();
                     option();
                     
                  } catch (Exception e2) {
//                     e2.printStackTrace();
                  }

               } else if (rdbtnOption2.isSelected()) {
                  try {
                     connectDB();
                     String voteno = tfVoteno.getText();

                     String sqlUserReward = "insert into user_cash(cash_no, cash_date, cash_amount, cash_content, user_id) values (user_cashAdd_sq.nextval, sysdate, (select vote_cash_amount from vote_info where vote_no ='"
                           + voteno + "'),'투표리워드','" + userModel.loginId + "')";
                     ps = con.prepareStatement(sqlUserReward);
                     ps.executeUpdate();
                     ps.close();

                     JOptionPane.showMessageDialog(null, "캐시 적립 완료!");

                     saveNum = model.selectedVoteNo(rdbtnOption2.getText());
                     
                     
                     int count = 0;
                     System.out.println(
                           count + " " + saveNum + " " + tfVoteno.getText() + " " + userModel.loginId);
                     model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     count = model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     model.insertVoteInfo(count, saveNum, tfVoteno.getText(), userModel.loginId);
                     model.trueScore(voteno, saveNum);
                     
                     Clear();
                     voting();
                     option();
                     
                  } catch (Exception e2) {
//                     e2.printStackTrace();
                  }

               } else if (rdbtnOption3.isSelected()) {
                  try {
                     connectDB();
                     String voteno = tfVoteno.getText();

                     String sqlUserReward = "insert into user_cash(cash_no, cash_date, cash_amount, cash_content, user_id) values (user_cashAdd_sq.nextval, sysdate, (select vote_cash_amount from vote_info where vote_no ='"
                           + voteno + "'),'투표리워드','" + userModel.loginId + "')";
                     ps = con.prepareStatement(sqlUserReward);
                     ps.executeUpdate();
                     ps.close();
                     
                     JOptionPane.showMessageDialog(null, "캐시 적립 완료!");
                     
                     saveNum = model.selectedVoteNo(rdbtnOption3.getText());
                     
                     
                     int count = 0;
                     System.out.println(
                           count + " " + saveNum + " " + tfVoteno.getText() + " " + userModel.loginId);
                     model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     count = model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     model.insertVoteInfo(count, saveNum, tfVoteno.getText(), userModel.loginId);
                     model.trueScore(voteno, saveNum);
                     
                     Clear();
                     voting();
                     option();
                     
                  } catch (Exception e2) {
//                     e2.printStackTrace();
                  }

               }
            }
            if (list.size() == 4) {
               if (rdbtnOption1.isSelected() == false && rdbtnOption2.isSelected() == false
                     && rdbtnOption3.isSelected() == false && rdbtnOption4.isSelected() == false) {
                  JOptionPane.showMessageDialog(null, "투표를 해주세요");
               } else if (rdbtnOption1.isSelected()) {
                  try {
                     connectDB();
                     String voteno = tfVoteno.getText();

                     String sqlUserReward = "insert into user_cash(cash_no, cash_date, cash_amount, cash_content, user_id) values (user_cashAdd_sq.nextval, sysdate, (select vote_cash_amount from vote_info where vote_no ='"
                           + voteno + "'),'투표리워드','" + userModel.loginId + "')";
                     ps = con.prepareStatement(sqlUserReward);
                     ps.executeUpdate();
                     ps.close();

                     JOptionPane.showMessageDialog(null, "캐시 적립 완료!");

                     saveNum = model.selectedVoteNo(rdbtnOption1.getText());
                     
                     
                     int count = 0;
                     System.out.println(
                           count + " " + saveNum + " " + tfVoteno.getText() + " " + userModel.loginId);
                     model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     count = model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     model.insertVoteInfo(count, saveNum, tfVoteno.getText(), userModel.loginId);
                     model.trueScore(voteno, saveNum);
                     
                     Clear();
                     voting();
                     option();
                     
                  } catch (Exception e2) {
//                     e2.printStackTrace();
                  }

               } else if (rdbtnOption2.isSelected()) {
                  try {
                     connectDB();
                     String voteno = tfVoteno.getText();

                     String sqlUserReward = "insert into user_cash(cash_no, cash_date, cash_amount, cash_content, user_id) values (user_cashAdd_sq.nextval, sysdate, (select vote_cash_amount from vote_info where vote_no ='"
                           + voteno + "'),'투표리워드','" + userModel.loginId + "')";
                     ps = con.prepareStatement(sqlUserReward);
                     ps.executeUpdate();
                     ps.close();

                     JOptionPane.showMessageDialog(null, "캐시 적립 완료!");

                     saveNum = model.selectedVoteNo(rdbtnOption2.getText());
                    
                     
                     int count = 0;
                     System.out.println(
                           count + " " + saveNum + " " + tfVoteno.getText() + " " + userModel.loginId);
                     model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     count = model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     model.insertVoteInfo(count, saveNum, tfVoteno.getText(), userModel.loginId);
                     model.trueScore(voteno, saveNum);
                     
                     Clear();
                     voting();
                     option();
                     
                  } catch (Exception e2) {
//                     e2.printStackTrace();
                  }

               } else if (rdbtnOption3.isSelected()) {
                  try {
                     connectDB();
                     String voteno = tfVoteno.getText();

                     String sqlUserReward = "insert into user_cash(cash_no, cash_date, cash_amount, cash_content, user_id) values (user_cashAdd_sq.nextval, sysdate, (select vote_cash_amount from vote_info where vote_no ='"
                           + voteno + "'),'투표리워드','" + userModel.loginId + "')";
                     ps = con.prepareStatement(sqlUserReward);
                     ps.executeUpdate();
                     ps.close();

                     JOptionPane.showMessageDialog(null, "캐시 적립 완료!");

                     saveNum = model.selectedVoteNo(rdbtnOption3.getText());
                    
                     
                     int count = 0;
                     System.out.println(
                           count + " " + saveNum + " " + tfVoteno.getText() + " " + userModel.loginId);
                     model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     count = model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     model.insertVoteInfo(count, saveNum, tfVoteno.getText(), userModel.loginId);
                     model.trueScore(voteno, saveNum);
                     
                     Clear();
                     voting();
                     option();
                     
                  } catch (Exception e2) {
//                     e2.printStackTrace();
                  }

               } else if (rdbtnOption4.isSelected()) {
                  try {
                     connectDB();
                     String voteno = tfVoteno.getText();
                     // 캐시충전 메소드
                     // 시퀀스 생성
                     // create sequence user_cashAdd_sq
                     // start with 101
                     // INCREMENT BY 1 MAXVALUE 99999 MINVALUE 1 NOCYCLE;
                     String sqlUserReward = "insert into user_cash(cash_no, cash_date, cash_amount, cash_content, user_id) values (user_cashAdd_sq.nextval, sysdate, (select vote_cash_amount from vote_info where vote_no ='"
                           + voteno + "'),'투표리워드','" + userModel.loginId + "')";
                     ps = con.prepareStatement(sqlUserReward);
                     ps.executeUpdate();
                     ps.close();

                     JOptionPane.showMessageDialog(null, "캐시 적립 완료!");

                     saveNum = model.selectedVoteNo(rdbtnOption4.getText());
                     
                     
                     int count = 0;
                     System.out.println(
                           count + " " + saveNum + " " + tfVoteno.getText() + " " + userModel.loginId);
                     model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     count = model.sendVoteInfo(count, tfVoteno.getText(), userModel.loginId);
                     model.insertVoteInfo(count, saveNum, tfVoteno.getText(), userModel.loginId);
                     model.trueScore(voteno, saveNum);
                     
                     Clear();
                     voting();
                     option();
                     
                  } catch (Exception e2) {
//                     e2.printStackTrace();
                  }

               }
            }

         } catch (Exception e2) {
//            e2.printStackTrace();
         }
      }

   }

   public void Clear() {
      try {
         ArrayList list = model.selectVote(tfVoteno.getText());

         if (list.size() == 2) {
            rdbtnOption1.setVisible(false);
//          rdbtnOption1.remove(rdbtnOption1);
            rdbtnOption2.setVisible(false);
         } else if (list.size() == 3) {
            rdbtnOption1.setVisible(false);
            rdbtnOption2.setVisible(false);
            rdbtnOption3.setVisible(false);
         } else if (list.size() == 4) {
            rdbtnOption1.setVisible(false);
            rdbtnOption2.setVisible(false);
            rdbtnOption3.setVisible(false);
            rdbtnOption4.setVisible(false);
         }
         tfVoteno.setText(null);
         tfTitle.setText(null);
         tfVoteContent.setText(null);
         tfToken.setText(null);
         tfVoteCat.setText(null);

      } catch (SQLException e) {
         // TODO Auto-generated catch block
//         e.printStackTrace();
      }

   }
}