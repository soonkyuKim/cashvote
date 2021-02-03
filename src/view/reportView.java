package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import model.ConnectionPool;
import model.reportModel;
import model.userModel;
import vo.reportVO;
import vo.userVO;
import vo.voteVO;

import javax.swing.JTextArea;
import javax.swing.JButton;

public class reportView extends JDialog implements ActionListener {
	private JButton btnReport;
	private JTextField tfUserId;
	private JTextField tfVoteTitle;
	private JComboBox cbReportCat;
	private JTextArea taReportContent;
	voteVO vote = null;
	static Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	reportModel model = null;

	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}

	public reportView(voteVO vo) {
		try {
			model = new reportModel();
			System.out.println("신고DB 연결 성공");
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "신고 DB연결 실패" + e.getMessage());
		}

		getVoteVO(vo);
		setTitle("신고");
		setSize(350, 270);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC2E0\uACE0\uC790");
		lblNewLabel.setBounds(12, 10, 57, 15);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uBC1C\uC758\uBB38");
		lblNewLabel_1.setBounds(12, 40, 57, 15);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\uB0B4\uC6A9");
		lblNewLabel_2.setBounds(12, 70, 57, 15);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		lblNewLabel_3.setBounds(12, 166, 57, 15);
		getContentPane().add(lblNewLabel_3);

		cbReportCat = new JComboBox();
		cbReportCat.setBounds(81, 163, 241, 23);
		getContentPane().add(cbReportCat);

		tfUserId = new JTextField();
		tfUserId.setEditable(false);
		tfUserId.setBounds(81, 7, 101, 21);
		getContentPane().add(tfUserId);
		tfUserId.setColumns(10);

		tfVoteTitle = new JTextField();
		tfVoteTitle.setEditable(false);
		tfVoteTitle.setBounds(81, 37, 241, 21);
		getContentPane().add(tfVoteTitle);
		tfVoteTitle.setColumns(10);

		taReportContent = new JTextArea();
		taReportContent.setLineWrap(true);
		taReportContent.setBounds(81, 67, 241, 86);
		getContentPane().add(taReportContent);

		tfUserId.setText(userModel.loginId);
		tfVoteTitle.setText(vote.getVote_Title());

		btnReport = new JButton("\uC2E0\uACE0");
		btnReport.setBounds(125, 198, 97, 23);
		getContentPane().add(btnReport);
		btnReport.addActionListener(this);

		try {
			connectDB();

			String sqlReportCat = "select * from report_cat";
			ps = con.prepareStatement(sqlReportCat);
			rs = ps.executeQuery();
			while (rs.next()) {
				cbReportCat.addItem(rs.getString("report_cat_name"));
			}
			rs.close();
			ps.close();

		} catch (Exception e) {
//			e.printStackTrace();
		}
	}

	public void getVoteVO(voteVO vo) {
		this.vote = vo;
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object o = ev.getSource();

		// 회원가입
		if (o == btnReport) {
			String voteTitle = tfVoteTitle.getText();
			String content = taReportContent.getText();
			String reportCatName = String.valueOf(cbReportCat.getSelectedItem());
			String id = tfUserId.getText();

			int voteTitleNo = getVoteNo(voteTitle);
			int reportCatNo = getReportCatNo(reportCatName);

			System.out.println(voteTitleNo + " " + content + " " + reportCatNo + " " + id);

			reportVO vo = new reportVO(voteTitleNo, content, reportCatNo, id);
			try {
				model.report(vo);
				JOptionPane.showMessageDialog(null, "신고 성공");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "신고 실패 : " + e.getMessage());
//				e.printStackTrace();
			}
		}
	}

	public int getVoteNo(String voteTitle) {
		// 해당 발의문 이름의 vote_no 구하기
		String sqlVoteNo = "select vote_no from vote_info where vote_title=?";
		int vote_no = 0;
		try {
			ps = con.prepareStatement(sqlVoteNo);
			ps.setString(1, voteTitle);
			rs = ps.executeQuery();
			if (rs.next()) {
				vote_no = Integer.parseInt(rs.getString("vote_no"));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}

		return vote_no;
	}

	public int getReportCatNo(String reportCat) {
		// 해당 신고 카테고리 이름의 report_cat_no 구하기
		String sqlGenderNo = "select report_cat_no from report_cat where report_cat_name=?";
		int report_cat_no = 0;
		try {
			ps = con.prepareStatement(sqlGenderNo);
			ps.setString(1, reportCat);
			rs = ps.executeQuery();
			if (rs.next()) {
				report_cat_no = Integer.parseInt(rs.getString("report_cat_no"));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}

		return report_cat_no;
	}
}
