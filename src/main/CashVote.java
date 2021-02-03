package main;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import view.*;
import javax.swing.JButton;

public class CashVote extends JFrame {

	public CashVote() {
		voteView vote;
		voteAddView voteAdd;
		voteResultView voteResult;
		myInfoView myInfo;
		storeView store;
//		loginView login;
		// ������ ȭ���� �����ϴ� Ŭ���� ��ü ����
		vote = new voteView();
		voteAdd = new voteAddView();
		voteResult = new voteResultView();
		myInfo = new myInfoView();
		store = new storeView();
		
		JTabbedPane pane = new JTabbedPane();
		pane.setFont(loginView.customFont);
		pane.addTab("��ǥ", vote);
		vote.setLayout(null);
		pane.addTab("���ǹ� ���", voteAdd);
		pane.addTab("��ǥ���", voteResult);
		pane.addTab("�� ����", myInfo);
		pane.addTab("��ǰ ��ȯ", store);

		pane.setSelectedIndex(0);

		getContentPane().add("Center", pane);
		setTitle("CashVote");
		setSize(1050, 480);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
