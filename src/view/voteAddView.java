package view;

import java.util.Calendar;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTree;

import model.ConnectionPool;
import model.userModel;
import model.voteAddModel;
import view.myInfoView.userVoteAddTable;
import view.registView.ItemChangeListener;
import vo.userVO;
import vo.voteOptionVO;
import vo.voteaddVO;

import javax.swing.JOptionPane;

import java.awt.Checkbox;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.SwingConstants;

public class voteAddView extends JPanel implements ActionListener {
	String allToken = null;
	private int rowCount = 0;
	private JTextField tfVoteTitle;
	private JTextField tfVoteCost;
	private JTextField tfVoteCash;
	private JTextField tfVoteOptionContent1;
	private JTextField tfVoteOptionContent2;
	private JTextField tfVoteOptionContent3;
	private JTextField tfVoteOptionContent4;

	JLabel lblNewLabel_6;

	private JComboBox cbJobType;
	private JComboBox cbJob;
	private JComboBox cbCat;
	private JComboBox cbSido;
	private JComboBox cbGoo;
	private JComboBox cbDong;
	private JComboBox cbRangeEtc;

	private JButton btnLocRangeAdd;
	private JButton btnGenderRangeAdd;
	private JButton btnBirthRangeAdd;
	private JButton btnEtcRangeAdd;
	private JButton btnJobRangeAdd;

	private JRadioButton rdbtnTotal;
	private JRadioButton rdbtnMan;
	private JRadioButton rdbtnWomen;

	JTextArea textArea;
	JCheckBox ckbone;
	JCheckBox ckbtwo;
	JCheckBox ckbthree;
	JCheckBox ckbfour;

	static Connection con = null;
	voteAddModel model = null;
	userModel user = null;
	private JButton btnVoteRegist;
	private JButton btnClear;
	PreparedStatement ps = null;
	ResultSet rs = null;
	private JTextField tfStartAge;
	private JTextField tfEndAge;
	int cnt1 = 0, cnt2 = 0, cnt3 = 0;
	String loc_sido_no = null;
	String range_gender1 = "남자";
	String range_gender2 = "여자";
	String range_loc = null;
	String range_etc = null;
	String range_job_type = null;
	int range_start_age = 0;
	int range_end_age = 0;
	private JTable table;
	private String dongNoDefault = null;
	private String dongNo = null;
	private String etcNo = null;
	ArrayList<String> dongList = new ArrayList<String>();
	ArrayList<String> etcList = new ArrayList<String>();

	JTable tbvoteAdd;
	// userVoteAddTable tbUserAdd = new userVoteAddTable();
	DefaultTableModel userVoteAddTable = null;

	private int cost = 0;
	private int genderCount = 0;
	private int locCount = 0;
	private int ageCount = 0;
	private int etcCount = 0;
	private int jobCount = 0;

	UtilDateModel model_reg;
	UtilDateModel model_end;
	private JLabel lblNewLabel_12;

	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}

	public voteAddView() {
		try {
			model = new voteAddModel();
			System.out.println("유저DB 연결 성공");
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "유저 DB연결 실패" + e.getMessage());
		}

		try {
			user = new userModel();
			System.out.println("유저DB 연결 성공");
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "유저 DB연결 실패" + e.getMessage());
		}
		setLayout(null);

		btnVoteRegist = new JButton("투표 등록");
		btnVoteRegist.setBounds(917, 370, 97, 23);
		add(btnVoteRegist);

		btnClear = new JButton("초기화");
		btnClear.setBounds(451, 370, 97, 23);
		add(btnClear);

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
		panel.setBounds(12, 10, 427, 396);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("시작일");
		lblNewLabel_2.setBounds(12, 300, 57, 23);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_4 = new JLabel("등록비용");
		lblNewLabel_4.setBounds(12, 332, 57, 21);
		panel.add(lblNewLabel_4);

		tfVoteCost = new JTextField();
		tfVoteCost.setEditable(false);
		tfVoteCost.setBounds(81, 332, 122, 21);
		panel.add(tfVoteCost);
		tfVoteCost.setColumns(10);
		tfVoteCost.setText(Integer.toString(cost));

		JLabel lblNewLabel_10 = new JLabel("카테고리");
		lblNewLabel_10.setBounds(12, 363, 57, 23);
		panel.add(lblNewLabel_10);

		cbCat = new JComboBox();
		cbCat.setBounds(81, 363, 122, 23);
		panel.add(cbCat);

		JLabel lblNewLabel_8 = new JLabel("획득 캐쉬");
		lblNewLabel_8.setBounds(216, 332, 57, 21);
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_3 = new JLabel("종료일");
		lblNewLabel_3.setBounds(215, 300, 57, 23);
		panel.add(lblNewLabel_3);

		tfVoteCash = new JTextField();
		tfVoteCash.setEditable(false);
		tfVoteCash.setBounds(285, 332, 122, 21);
		panel.add(tfVoteCash);
		tfVoteCash.setColumns(10);
		tfVoteCash.setText("500");

		tfVoteOptionContent4 = new JTextField();
		tfVoteOptionContent4.setBounds(155, 269, 253, 21);
		panel.add(tfVoteOptionContent4);
		tfVoteOptionContent4.setColumns(10);

		tfVoteOptionContent3 = new JTextField();
		tfVoteOptionContent3.setBounds(155, 231, 253, 21);
		panel.add(tfVoteOptionContent3);
		tfVoteOptionContent3.setColumns(10);

		tfVoteOptionContent2 = new JTextField();
		tfVoteOptionContent2.setBounds(155, 193, 253, 21);
		panel.add(tfVoteOptionContent2);
		tfVoteOptionContent2.setColumns(10);

		tfVoteOptionContent1 = new JTextField();
		tfVoteOptionContent1.setBounds(155, 155, 253, 21);
		panel.add(tfVoteOptionContent1);
		tfVoteOptionContent1.setColumns(10);

		ckbone = new JCheckBox("1.");
		ckbone.setBounds(93, 155, 50, 23);
		panel.add(ckbone);

		ckbtwo = new JCheckBox("2.");
		ckbtwo.setBounds(93, 193, 50, 23);
		panel.add(ckbtwo);

		ckbthree = new JCheckBox("3.");
		ckbthree.setBounds(93, 231, 50, 23);
		panel.add(ckbthree);

		ckbfour = new JCheckBox("4.");
		ckbfour.setBounds(93, 269, 50, 23);
		panel.add(ckbfour);

		JLabel lblNewLabel_9 = new JLabel("보기");
		lblNewLabel_9.setBounds(12, 159, 57, 15);
		panel.add(lblNewLabel_9);

		JLabel lblNewLabel_1 = new JLabel("내용");
		lblNewLabel_1.setBounds(12, 74, 57, 15);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("투표 제목");
		lblNewLabel.setBounds(12, 46, 57, 15);
		panel.add(lblNewLabel);

		tfVoteTitle = new JTextField();
		tfVoteTitle.setBounds(93, 43, 315, 21);
		panel.add(tfVoteTitle);
		tfVoteTitle.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(12, 10, 132, 23);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_11 = new JLabel("투표등록");
		lblNewLabel_11.setForeground(Color.WHITE);
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setBounds(0, 1, 132, 23);
		panel_2.add(lblNewLabel_11);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(93, 74, 315, 70);
		panel.add(textArea);

//TODO 달력 입력기 생성------------------------------------
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		Calendar today = Calendar.getInstance();

		// 1. 시작일 입력 영역 판넬에 추가
		model_reg = new UtilDateModel();
		model_reg.setDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE));
		model_reg.setSelected(true);
		JDatePanelImpl datePanel_reg = new JDatePanelImpl(model_reg, p);

		JDatePickerImpl datePicker_reg = new JDatePickerImpl(datePanel_reg, new DateLabelFormatter());
		datePicker_reg.getJFormattedTextField().setFont(new Font("돋움체", Font.PLAIN, 12));
		datePicker_reg.setBounds(81, 300, 122, 25);

		panel.add(datePicker_reg);

		// 2. 종료일 입력 영역 판넬에 추가
		model_end = new UtilDateModel();
		model_end.setDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE) + 5);
		model_end.setSelected(true);
		JDatePanelImpl datePanel_end = new JDatePanelImpl(model_end, p);

		JDatePickerImpl datePicker_end = new JDatePickerImpl(datePanel_end, new DateLabelFormatter());
		datePicker_end.getJFormattedTextField().setFont(new Font("돋움체", Font.PLAIN, 12));
		datePicker_end.setBounds(285, 300, 122, 25);

		panel.add(datePicker_end);
//--------------------달력입력기 끝-------------------------

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
		panel_1.setBounds(451, 10, 565, 347);
		add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.DARK_GRAY);
		panel_2_1.setBounds(12, 10, 132, 23);
		panel_1.add(panel_2_1);
		panel_2_1.setLayout(null);

		lblNewLabel_12 = new JLabel("배포범위");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setForeground(Color.WHITE);
		lblNewLabel_12.setBounds(0, 1, 132, 23);
		panel_2_1.add(lblNewLabel_12);

		JLabel lblNewLabel_7_1_2 = new JLabel("기타 조건");
		lblNewLabel_7_1_2.setBounds(12, 138, 57, 23);
		panel_1.add(lblNewLabel_7_1_2);

		cbRangeEtc = new JComboBox();
		cbRangeEtc.setBounds(81, 138, 100, 23);
		panel_1.add(cbRangeEtc);

		JLabel lblNewLabel_7_1 = new JLabel("직종");
		lblNewLabel_7_1.setBounds(12, 171, 57, 23);
		panel_1.add(lblNewLabel_7_1);

		JLabel lblNewLabel_7 = new JLabel("지역");
		lblNewLabel_7.setBounds(12, 72, 34, 23);
		panel_1.add(lblNewLabel_7);

		lblNewLabel_6 = new JLabel("성별");
		lblNewLabel_6.setBounds(12, 43, 34, 15);
		panel_1.add(lblNewLabel_6);

		cbSido = new JComboBox();
		cbSido.setBounds(81, 72, 100, 23);
		panel_1.add(cbSido);

		cbJobType = new JComboBox();
		cbJobType.setBounds(81, 171, 100, 23);
		panel_1.add(cbJobType);

		cbGoo = new JComboBox();
		cbGoo.setBounds(193, 72, 100, 23);
		panel_1.add(cbGoo);

		JLabel lblNewLabel_7_1_1 = new JLabel("나이대");
		lblNewLabel_7_1_1.setBounds(12, 105, 46, 23);
		panel_1.add(lblNewLabel_7_1_1);

		tfStartAge = new JTextField();
		tfStartAge.setBounds(81, 105, 100, 21);
		panel_1.add(tfStartAge);
		tfStartAge.setColumns(10);

		tfEndAge = new JTextField();
		tfEndAge.setBounds(210, 105, 100, 21);
		panel_1.add(tfEndAge);
		tfEndAge.setColumns(10);

		cbDong = new JComboBox();
		cbDong.setBounds(305, 72, 100, 23);
		panel_1.add(cbDong);

		JLabel lblNewLabel_5 = new JLabel("~");
		lblNewLabel_5.setBounds(190, 108, 15, 15);
		panel_1.add(lblNewLabel_5);

		btnLocRangeAdd = new JButton("지역 추가");
		btnLocRangeAdd.setBounds(458, 72, 95, 23);
		panel_1.add(btnLocRangeAdd);

		btnBirthRangeAdd = new JButton("나이 추가");
		btnBirthRangeAdd.setBounds(458, 104, 95, 23);
		panel_1.add(btnBirthRangeAdd);

		btnGenderRangeAdd = new JButton("성별 추가");
		btnGenderRangeAdd.setBounds(458, 39, 95, 23);
		panel_1.add(btnGenderRangeAdd);

		btnEtcRangeAdd = new JButton("기타 추가");
		btnEtcRangeAdd.setBounds(458, 138, 95, 23);
		panel_1.add(btnEtcRangeAdd);

		btnJobRangeAdd = new JButton("직종 추가");
		btnJobRangeAdd.setBounds(458, 171, 95, 23);
		panel_1.add(btnJobRangeAdd);

		rdbtnTotal = new JRadioButton("\uC804\uCCB4");
		rdbtnTotal.setBounds(81, 39, 63, 23);
		panel_1.add(rdbtnTotal);

		rdbtnMan = new JRadioButton("\uB0A8\uC790");
		rdbtnMan.setBounds(148, 39, 63, 23);
		panel_1.add(rdbtnMan);

		rdbtnWomen = new JRadioButton("\uC5EC\uC790");
		rdbtnWomen.setBounds(215, 39, 63, 23);
		panel_1.add(rdbtnWomen);

		ButtonGroup bgGender = new ButtonGroup();
		bgGender.add(rdbtnTotal);
		bgGender.add(rdbtnMan);
		bgGender.add(rdbtnWomen);

		String[] title = { "구분", "내용", "등록비용" };
		userVoteAddTable = new DefaultTableModel(title, 0);

		tbvoteAdd = new JTable(userVoteAddTable);
		JScrollPane scrollPane = new JScrollPane(tbvoteAdd);
		scrollPane.setBounds(12, 204, 541, 133);
		panel_1.add(scrollPane);

		table = new JTable();
		table.setBounds(12, 205, 541, 132);
		panel_1.add(table);

		cbSido.addItemListener(new ItemChangeListener());
		cbGoo.addItemListener(new ItemChangeListener());
		cbDong.addItemListener(new ItemChangeListener());

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
			// vote_cat 값 가져오기
			if (userModel.loginId.equals("cash")) {
				String sqlCatLocSido = "select * from vote_cat";
				ps = con.prepareStatement(sqlCatLocSido);
				rs = ps.executeQuery();
				while (rs.next()) {
					cbCat.addItem(rs.getString("vote_cat_name"));
				}
			} else {
				String sqlCatLocSido = "select * from vote_cat where not vote_cat_no = 1";
				ps = con.prepareStatement(sqlCatLocSido);
				rs = ps.executeQuery();
				while (rs.next()) {
					cbCat.addItem(rs.getString("vote_cat_name"));
				}
			}
		} catch (Exception e2) {
//			e2.printStackTrace();
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
			// 기타조건 값 가져오기
			String sqlCatLocSido = "select * from cat_etc";
			ps = con.prepareStatement(sqlCatLocSido);
			rs = ps.executeQuery();
			while (rs.next()) {
				cbRangeEtc.addItem(rs.getString("etc_name"));
			}
		} catch (Exception e2) {
//			e2.printStackTrace();
		}

		dongNoDefault = defaultLocNo();
		range_start_age = 1;
		range_end_age = 70;
		System.out.println("start : " + range_start_age);
		System.out.println("end : " + range_end_age);

		btnClear.addActionListener(this);
		btnVoteRegist.addActionListener(this);
		rdbtnTotal.addActionListener(this);
		rdbtnMan.addActionListener(this);
		rdbtnWomen.addActionListener(this);
		btnBirthRangeAdd.addActionListener(this);
		btnGenderRangeAdd.addActionListener(this);
		btnLocRangeAdd.addActionListener(this);
		btnEtcRangeAdd.addActionListener(this);
		btnJobRangeAdd.addActionListener(this);

	}

	public void actionPerformed(ActionEvent ev) {
		Object o = ev.getSource();
		if (o == btnClear) {
			try {
				layoutClear();
				JOptionPane.showMessageDialog(null, "초기화 성공!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "초기화 실패!" + e.getMessage());
			}
		}
		if (o == btnVoteRegist) {
			try {
				allToken = user.getAllToken();
			} catch (Exception e9) {
//				e9.printStackTrace();
			}
			if (Integer.parseInt(allToken) - cost >= 0) {
//		         String votecost = tfVoteCost.getText();
				//
//				         if (votecost == null || votecost.equals("")) {
//				            JOptionPane.showMessageDialog(null, "1원이상 입력해 주세요");
				//
//				         }
//				         int to = Integer.parseInt(votecost);
//				         if (to < 1) {
				//
//				            JOptionPane.showMessageDialog(null, "1원이상 입력해 주세요");
//				         } else {
				String title = tfVoteTitle.getText();
				String Content = textArea.getText();
//TODO 투표 등록기간을 달력 선택기 필드 값으로 변경하기-------------------				
				String start =
//						tfVoteRegDate.getText();
						model_reg.getYear() + "/" + (model_reg.getMonth() + 1) + "/" + model_reg.getDay();
				System.out.println(start);
				String end =
//						tfVoteEndDate.getText();
						model_end.getYear() + "/" + (model_end.getMonth() + 1) + "/" + model_end.getDay();

//-----------------변경 끝--------------------------------------------------
				String cost = tfVoteCost.getText();
				String catno = String.valueOf(cbCat.getSelectedItem());

				String votecatno = getVoteCatNo(catno);

				String genderNo1 = getGenderNo(range_gender1);
				String jobTypeNo = getJobTypeNo(range_job_type);

				voteaddVO vo = null;
				String voteNo = null;
				try {
					if (title.equals("") || Content.equals("") || start.equals("") || end.equals("")
							|| cost.equals("")) {
						throw new Exception();
					}

					vo = new voteaddVO(title, Content, cost, start, end, votecatno);
					model.voteAdd(vo);

					voteNo = getVoteNo(vo.getVoteTitle());

					try {
						model.voteGenderRangeAdd(voteNo, genderNo1);
						if (range_gender2 != null) { // 에러
							String genderNo2 = getGenderNo(range_gender2);
							model.voteGenderRangeAdd(voteNo, genderNo2);
						}
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(null, "성별범위 지정 실패!" + e2.getMessage());
					}

					try {
						if (dongList.size() == 0)
							model.voteLocRangeAdd(voteNo, dongNoDefault);
						else {
							for (int i = 0; i < dongList.size(); i++) {
								model.voteLocRangeAdd(voteNo, dongList.get(i));
							}
						}
					} catch (SQLException e3) {
						JOptionPane.showMessageDialog(null, "지역범위 지정 실패!" + e3.getMessage());
					}

					try {
						if (etcNo != null) { // 에러
							for (int i = 0; i < etcList.size(); i++) {
								model.voteEtcRangeAdd(voteNo, etcList.get(i));
							}
						}
					} catch (SQLException e4) {
						JOptionPane.showMessageDialog(null, "기타범위 지정 실패!" + e4.getMessage());
					}

					try {
						if (jobTypeNo != null) { // 에러
							model.voteJobTypeRangeAdd(voteNo, jobTypeNo);
						}
					} catch (SQLException e5) {
						JOptionPane.showMessageDialog(null, "직업범위 지정 실패!" + e5.getMessage());
					}

					try {
						model.voteAgeRangeAdd(voteNo, range_start_age, range_end_age);
					} catch (SQLException e6) {
						JOptionPane.showMessageDialog(null, "나이범위 지정 실패!" + e6.getMessage());
					}

					try {
						model.useToken(userModel.loginId, cost);
					} catch (SQLException e7) {
						JOptionPane.showMessageDialog(null, "토큰 사용 실패!" + e7.getMessage());
					} catch (Exception e8) {
//						e8.printStackTrace();
					}

					JOptionPane.showMessageDialog(null, "투표등록 성공!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "투표등록 실패!" + e1.getMessage());
				}

				System.out.println(voteNo);

				voteOptionVO option = null;
				String optionOne = "";
				String optionTwo = "";
				String optionThree = "";
				String optionFour = "";

				if (ckbone.isSelected()) {
					optionOne += tfVoteOptionContent1.getText();
				}
				if (ckbtwo.isSelected()) {
					optionTwo += tfVoteOptionContent2.getText();
				}
				if (ckbthree.isSelected()) {
					optionThree += tfVoteOptionContent3.getText();
				}
				if (ckbfour.isSelected()) {
					optionFour += tfVoteOptionContent4.getText();
				}

				System.out.println(optionOne + "\n" + optionTwo + "\n" + optionThree + "\n" + optionFour);

				if (!optionOne.equals("")) {

					try {
						option = new voteOptionVO("1", optionOne, voteNo);
						model.voteOptionAdd(option);
//									JOptionPane.showMessageDialog(null, "선택지 1번 등록 성공!");
					} catch (Exception e) {
//									JOptionPane.showMessageDialog(null, "선택지 1번 등록 실패!" + e.getMessage());
					}
				}

				if (!optionTwo.equals("")) {
					option = new voteOptionVO("2", optionTwo, voteNo);

					try {
						model.voteOptionAdd(option);
//									JOptionPane.showMessageDialog(null, "선택지 2번 등록 성공!");
					} catch (Exception e) {
//									JOptionPane.showMessageDialog(null, "선택지 2번 등록 실패!" + e.getMessage());
					}
				}

				if (!optionThree.equals("")) {
					option = new voteOptionVO("3", optionThree, voteNo);

					try {
						model.voteOptionAdd(option);
//									JOptionPane.showMessageDialog(null, "선택지 3번 등록 성공!");
					} catch (Exception e) {
//									JOptionPane.showMessageDialog(null, "선택지 3번 등록 실패!" + e.getMessage());
					}
				}

				if (!optionFour.equals("")) {
					option = new voteOptionVO("4", optionFour, voteNo);

					try {
						model.voteOptionAdd(option);
//									JOptionPane.showMessageDialog(null, "선택지 4번 등록 성공!");
					} catch (Exception e) {
//									JOptionPane.showMessageDialog(null, "선택지 4번 등록 실패!" + e.getMessage());
					}
				}
				layoutClear();
			} else {
				JOptionPane.showMessageDialog(null, "보유 토큰 부족");
			}
		}

		if (o == btnGenderRangeAdd) {

			if (genderCount == 0) {
				cost += 500;
				genderCount++;
			}
			if (rdbtnTotal.isSelected()) {
				System.out.println("전체");
				range_gender1 = "남자";
				range_gender2 = "여자";
			}
			if (rdbtnMan.isSelected()) {
				System.out.println("남자");
				range_gender1 = "남자";
				range_gender2 = null;
			}
			if (rdbtnWomen.isSelected()) {
				System.out.println("여자");
				range_gender1 = "여자";
				range_gender2 = null;
			}

			String[] rows = new String[3];
			rows[0] = "성별";

			if (rdbtnTotal.isSelected())
				rows[1] = "전체";
			if (rdbtnMan.isSelected())
				rows[1] = "남자";
			if (rdbtnWomen.isSelected())
				rows[1] = "여자";

			rows[2] = "500토큰";
			userVoteAddTable.addRow(rows);
			System.out.println(Arrays.toString(rows));
			tfVoteCost.setText(Integer.toString(cost));
		}

		if (o == btnLocRangeAdd) {
//			if (locCount == 0) {
			cost += 500;
//				locCount++;
//			}

			tfVoteCost.setText(Integer.toString(cost));

			range_loc = String.valueOf(cbDong.getSelectedItem());

			dongNo = getDongNo(range_loc);

			dongList.add(dongNo);

			String[] rows = new String[3];
			rows[0] = "지역";
			rows[1] = range_loc;
			rows[2] = "500토큰";

			userVoteAddTable.addRow(rows);

			System.out.println(dongNo);
		}

		if (o == btnEtcRangeAdd) {
//			if (etcCount == 0) {
			cost += 500;
//				etcCount++;
//			}

			tfVoteCost.setText(Integer.toString(cost));

			range_etc = String.valueOf(cbRangeEtc.getSelectedItem());

			etcNo = getEtcNo(range_etc);

			etcList.add(etcNo);

			String[] rows = new String[3];
			rows[0] = "기타조건";
			rows[1] = range_etc;
			rows[2] = "500토큰";

			userVoteAddTable.addRow(rows);
		}

		if (o == btnJobRangeAdd) {
			if (jobCount == 0) {
				cost += 500;
				jobCount++;
			}

			tfVoteCost.setText(Integer.toString(cost));

			range_job_type = String.valueOf(cbJobType.getSelectedItem());

			String[] rows = new String[3];
			rows[0] = "직업";
			rows[1] = range_job_type;
			rows[2] = "500토큰";

			userVoteAddTable.addRow(rows);
		}

		if (o == btnBirthRangeAdd) {
			if (ageCount == 0) {
				cost += 500;
				ageCount++;
			}

			tfVoteCost.setText(Integer.toString(cost));

			range_start_age = Integer.parseInt(tfStartAge.getText());
			range_end_age = Integer.parseInt(tfEndAge.getText());
			// System.out.println(range_start_age + " " + range_end_age);
			String range = Integer.toString(range_start_age) + " " + Integer.toString(range_end_age);

			String[] rows = new String[3];
			rows[0] = "나이대";
			rows[1] = range;
			rows[2] = "500토큰";

			userVoteAddTable.addRow(rows);
		}

	}
//   }

	public void layoutClear() {
		tfVoteTitle.setText(null);
		textArea.setText(null);
		tfVoteOptionContent1.setText(null);
		tfVoteOptionContent2.setText(null);
		tfVoteOptionContent3.setText(null);
		tfVoteOptionContent4.setText(null);
		tfVoteCost.setText("0");
		tfStartAge.setText(null);
		tfEndAge.setText(null);
		ckbone.setSelected(false);
		ckbtwo.setSelected(false);
		ckbthree.setSelected(false);
		ckbfour.setSelected(false);
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
		}

		return job_no;
	}

	public String getJobTypeNo(String jobType) {
		// 해당 직종 이름의 job_no 구하기
		String sqlJobTypeNo = "select job_type_no from cat_job_type where job_type_name=?";
		String job_type_no = null;
		try {
			ps = con.prepareStatement(sqlJobTypeNo);
			ps.setString(1, jobType);
			rs = ps.executeQuery();
			if (rs.next()) {
				job_type_no = rs.getString("job_type_no");
			}
		} catch (SQLException e) {
		}

		return job_type_no;
	}

	public String getGenderNo(String gender) {
		// 해당 성별 이름의 gender_no 구하기
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
		}

		return dong_no;
	}

	public String getVoteCatNo(String catno) {
		// 해당 카테고리의 cat_no 구하기
		String sqlCatNo = "select vote_cat_no from vote_cat where vote_cat_name=?";
		String cat_no = null;
		try {
			ps = con.prepareStatement(sqlCatNo);
			ps.setString(1, catno);
			rs = ps.executeQuery();
			if (rs.next()) {
				cat_no = rs.getString("vote_cat_no");
			}
		} catch (SQLException e) {
		}

		return cat_no;
	}

	public String getVoteNo(String voteTitle) {
		// 해당 발의문 제목의 vote_no 구하기
		String sqlVoteNo = "select vote_no from vote_info where vote_title=?";
		String vote_no = null;
		try {
			ps = con.prepareStatement(sqlVoteNo);
			ps.setString(1, voteTitle);
			rs = ps.executeQuery();
			if (rs.next()) {
				vote_no = rs.getString("vote_no");
			}
		} catch (SQLException e) {
		}

		return vote_no;
	}

	public String getEtcNo(String etc) {
		// 해당 기타조건이름의 etc_no구하기
		String sqlEtcNo = "select etc_no from cat_etc where etc_name=?";
		String etc_no = null;
		try {
			ps = con.prepareStatement(sqlEtcNo);
			ps.setString(1, etc);
			rs = ps.executeQuery();
			if (rs.next()) {
				etc_no = rs.getString("etc_no");
			}
		} catch (SQLException e) {
		}
		return etc_no;
	}

	public String defaultLocNo() {
		String sqlUserLocNo = "select loc_dong_no from user_info where user_id=?";
		String loc_dong_no = null;
		try {
			ps = con.prepareStatement(sqlUserLocNo);
			ps.setString(1, userModel.loginId);
			rs = ps.executeQuery();
			if (rs.next()) {
				loc_dong_no = rs.getString("loc_dong_no");
			}
		} catch (SQLException e) {
		}
		return loc_dong_no;
	}

	public int defaultAge() {
		java.util.Calendar cal = java.util.Calendar.getInstance();

		int year = cal.get(cal.YEAR);

		String sqlUserBirthNo = "select to_char(user_birth, 'yyyy') birth from user_info where user_id=?";
		int user_birth = 0;
		int user_age = 0;
		try {
			ps = con.prepareStatement(sqlUserBirthNo);
			ps.setString(1, userModel.loginId);
			rs = ps.executeQuery();
			if (rs.next()) {
				user_birth = Integer.parseInt(rs.getString("birth"));
			}
		} catch (SQLException e) {
		}
		user_age = year - user_birth + 1;
		System.out.println("user_birth : " + user_birth);
		System.out.println("user_age : " + user_age);
		return user_age;
	}

	class userVoteAddTable extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] title = { "구분", "내용", "등록비용" };

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
}