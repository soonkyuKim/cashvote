package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.ConnectionPool;
import model.userModel;
import view.registView.ItemChangeListener;
import vo.userVO;
import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class myInfoModView extends JDialog implements ActionListener{

	private JPanel contentPane;
	private JTextField tfUserId;
	private JTextField tfUserPw;
	private JTextField tfUserName;
	private JTextField tfUserTel;
	private JTextField tfUserBirth;
	
	private JButton btnUpdate;
	
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
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;

	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}
	
	public myInfoModView() {
		addLayout();
		setStyle();
	}
	public void addLayout() {
		// db 연결
		try {
			model = new userModel();
			System.out.println("유저DB 연결 성공");
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "유저 DB연결 실패" + e.getMessage());
		}

		setTitle("회원정보수정");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 476, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUserId = new JLabel("아이디");
		lblUserId.setBounds(12, 13, 57, 15);
		contentPane.add(lblUserId);

		JLabel lblUserPw = new JLabel("패스워드");
		lblUserPw.setBounds(12, 49, 57, 15);
		contentPane.add(lblUserPw);

		JLabel lblUserName = new JLabel("이름");
		lblUserName.setBounds(12, 86, 57, 15);
		contentPane.add(lblUserName);

		JLabel lblUserTel = new JLabel("전화번호");
		lblUserTel.setBounds(12, 122, 57, 15);
		contentPane.add(lblUserTel);

		JLabel lblUserJob = new JLabel("직업");
		lblUserJob.setBounds(12, 160, 57, 15);
		contentPane.add(lblUserJob);

		JLabel lblUserGender = new JLabel("성별");
		lblUserGender.setBounds(12, 201, 57, 15);
		contentPane.add(lblUserGender);

		JLabel lblUserDong = new JLabel("주소");
		lblUserDong.setBounds(12, 242, 57, 15);
		contentPane.add(lblUserDong);

		JLabel lblUserBirth = new JLabel("생일");
		lblUserBirth.setBounds(12, 283, 57, 15);
		contentPane.add(lblUserBirth);

		tfUserId = new JTextField();
		tfUserId.setBounds(76, 10, 116, 21);
		contentPane.add(tfUserId);
		tfUserId.setColumns(10);

		tfUserPw = new JTextField();
		tfUserPw.setBounds(76, 46, 116, 21);
		contentPane.add(tfUserPw);
		tfUserPw.setColumns(10);

		tfUserName = new JTextField();
		tfUserName.setBounds(76, 83, 116, 21);
		contentPane.add(tfUserName);
		tfUserName.setColumns(10);

		tfUserTel = new JTextField();
		tfUserTel.setBounds(76, 119, 116, 21);
		contentPane.add(tfUserTel);
		tfUserTel.setColumns(10);

		btnUpdate = new JButton("저장");
		btnUpdate.setBounds(185, 311, 75, 23);
		contentPane.add(btnUpdate);

		cbGender = new JComboBox();
		cbGender.setBounds(76, 197, 75, 23);
		contentPane.add(cbGender);

		cbJobType = new JComboBox();
		cbJobType.setBounds(76, 156, 162, 23);
		contentPane.add(cbJobType);

		cbJob = new JComboBox();
		cbJob.setBounds(250, 156, 194, 23);
		contentPane.add(cbJob);

		cbSido = new JComboBox();
		cbSido.setBounds(76, 238, 75, 23);
		contentPane.add(cbSido);

		cbGoo = new JComboBox();
		cbGoo.setBounds(163, 238, 75, 23);
		contentPane.add(cbGoo);

		cbDong = new JComboBox();
		cbDong.setBounds(250, 238, 75, 23);
		contentPane.add(cbDong);

		tfUserBirth = new JTextField();
		tfUserBirth.setBounds(76, 280, 116, 21);
		contentPane.add(tfUserBirth);
		tfUserBirth.setColumns(10);
		
		panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(192, 192, 192)));
		panel.setBounds(12, 38, 432, 2);
		contentPane.add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(192, 192, 192)));
		panel_1.setBounds(12, 74, 432, 2);
		contentPane.add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(192, 192, 192)));
		panel_2.setBounds(10, 111, 432, 2);
		contentPane.add(panel_2);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(192, 192, 192)));
		panel_3.setBounds(12, 147, 432, 2);
		contentPane.add(panel_3);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(192, 192, 192)));
		panel_4.setBounds(12, 188, 432, 2);
		contentPane.add(panel_4);
		
		panel_5 = new JPanel();
		panel_5.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(192, 192, 192)));
		panel_5.setBounds(10, 228, 432, 2);
		contentPane.add(panel_5);
		
		panel_6 = new JPanel();
		panel_6.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(192, 192, 192)));
		panel_6.setBounds(10, 270, 432, 2);
		contentPane.add(panel_6);

		cbJobType.addItemListener(new ItemChangeListener());
		cbSido.addItemListener(new ItemChangeListener());
		cbGoo.addItemListener(new ItemChangeListener());
		btnUpdate.addActionListener(this);
		
		try {
			connectDB();
			// user_id 값 가져오기
			String sqlCatJobType = "select user_id from user_info where user_id = '" + userModel.loginId + "'";
			ps = con.prepareStatement(sqlCatJobType);
			rs = ps.executeQuery();
			while (rs.next()) {
				tfUserId.setText(rs.getString("user_id"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		try {
			// user_pw 값 가져오기
			String sqlCatJobType = "select user_pw from user_info where user_id = '" + userModel.loginId + "'";
			ps = con.prepareStatement(sqlCatJobType);
			rs = ps.executeQuery();
			while (rs.next()) {
				tfUserPw.setText(rs.getString("user_pw"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		try {
			// user_name 값 가져오기
			String sqlCatJobType = "select user_name from user_info where user_id = '" + userModel.loginId + "'";
			ps = con.prepareStatement(sqlCatJobType);
			rs = ps.executeQuery();
			while (rs.next()) {
				tfUserName.setText(rs.getString("user_name"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		try {
			// user_tel 값 가져오기
			String sqlCatJobType = "select user_tel from user_info where user_id = '" + userModel.loginId + "'";
			ps = con.prepareStatement(sqlCatJobType);
			rs = ps.executeQuery();
			while (rs.next()) {
				tfUserTel.setText(rs.getString("user_tel"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
//			e.printStackTrace();
		}

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
//			e.printStackTrace();
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
//			e1.printStackTrace();
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
//			e2.printStackTrace();
		}
		try {
			// user_birth 값 가져오기
			String sqlUserBirth = "select to_char(user_birth, 'YYYY/MM/DD') user_birth from user_info where user_id = '" + userModel.loginId + "'";
			ps = con.prepareStatement(sqlUserBirth);
			rs = ps.executeQuery();
			while (rs.next()) {
				tfUserBirth.setText(rs.getString("user_birth"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
//			e.printStackTrace();
		}

	}
	
	void setStyle() {
		// 텍스트필드 편집가능하지 않도록 지정
		tfUserId.setEditable(false);
		tfUserName.setEditable(false);
		
	}

	public void actionPerformed(ActionEvent ev) {
		Object o = ev.getSource();

		// 회원가입
		if (o == btnUpdate) {
			String pw = tfUserPw.getText();
			String tel = tfUserTel.getText();
			String job = String.valueOf(cbJob.getSelectedItem());
			String gender = String.valueOf(cbGender.getSelectedItem());
			String dong = String.valueOf(cbDong.getSelectedItem());
			String birth = tfUserBirth.getText();

			String jobNo = getJobNo(job);
			String genderNo = getGenderNo(gender);
			String dongNo = getDongNo(dong);

			userVO vo = new userVO(pw, tel, jobNo, genderNo, dongNo, birth);
			try {
				model.update(vo);
				JOptionPane.showMessageDialog(null, "회원정보 변경 완료!");
				dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "회원정보 수정 실패! : " + e.getMessage());
//				e.printStackTrace();
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
//					e1.printStackTrace();
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
//					e1.printStackTrace();
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
//					e1.printStackTrace();
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
//			e.printStackTrace();
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
//			e.printStackTrace();
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
//			e.printStackTrace();
		}

		return dong_no;
	}
}
