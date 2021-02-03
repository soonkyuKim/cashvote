package view;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.userModel;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class tokenHistoryView extends JDialog {

	JTable tabletoken;
	userModel model = null;
	tokenTableModel tmToken;
	ArrayList list = null;

	public tokenHistoryView() {
		try {
			model = new userModel();
			System.out.println("유저DB 연결 성공");
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "유저 DB연결 실패" + e.getMessage());
		}
		
		setTitle("토큰 내역");
		setResizable(false);
		setSize(450, 300);
		setLocationRelativeTo(null);

		newObject();
		addLayout();
		choiceTable();
		
	}

	void newObject() {
		tmToken = new tokenTableModel();
		tabletoken = new JTable(tmToken);
	}

	void addLayout() {
		// 내역 테이블
		JPanel pEast = new JPanel();

		JPanel pEastCenter = new JPanel();
		pEastCenter.setLayout(new BorderLayout());
		pEastCenter.add("Center", new JScrollPane(tabletoken));

		pEast.setLayout(new BorderLayout());
		pEast.add("Center", pEastCenter);

		// -----------------------------------------
		// 전체 붙이기
		setLayout(new GridLayout(1, 2));
		add(pEast);
	}

	void choiceTable() {
		try {
			list = model.searchToken();
			tmToken.data = list;
			tabletoken.setModel(tmToken);
//			tmToken.fireTableDataChanged();
		} catch (Exception e5) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "추출 실패" + e5.getMessage());
//			e5.printStackTrace(); // 오류 몇번째줄에서 나는지 확인
		}
	}

}

class tokenTableModel extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "날짜", "금액", "내용" };

//=============================================================
// 1. 기본적인 TabelModel  만들기
// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
// AbstractTabelModel에서 구현되지 않았기에...
// 반드시 사용자 구현 필수!!!!

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

//===============================================================
// 2. 지정된 컬럼명으로 변환하기
//
//      기본적으로 A, B, C, D 라는 이름으로 컬럼명이 지정된다
	public String getColumnName(int col) {
		return columnNames[col];
	}

}