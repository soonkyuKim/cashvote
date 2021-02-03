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
			System.out.println("����DB ���� ����");
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "���� DB���� ����" + e.getMessage());
		}
		
		setTitle("��ū ����");
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
		// ���� ���̺�
		JPanel pEast = new JPanel();

		JPanel pEastCenter = new JPanel();
		pEastCenter.setLayout(new BorderLayout());
		pEastCenter.add("Center", new JScrollPane(tabletoken));

		pEast.setLayout(new BorderLayout());
		pEast.add("Center", pEastCenter);

		// -----------------------------------------
		// ��ü ���̱�
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
			JOptionPane.showMessageDialog(null, "���� ����" + e5.getMessage());
//			e5.printStackTrace(); // ���� ���°�ٿ��� ������ Ȯ��
		}
	}

}

class tokenTableModel extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "��¥", "�ݾ�", "����" };

//=============================================================
// 1. �⺻���� TabelModel  �����
// �Ʒ� �� �Լ��� TabelModel �������̽��� �߻��Լ��ε�
// AbstractTabelModel���� �������� �ʾұ⿡...
// �ݵ�� ����� ���� �ʼ�!!!!

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
// 2. ������ �÷������� ��ȯ�ϱ�
//
//      �⺻������ A, B, C, D ��� �̸����� �÷����� �����ȴ�
	public String getColumnName(int col) {
		return columnNames[col];
	}

}