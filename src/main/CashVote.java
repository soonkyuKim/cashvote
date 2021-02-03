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
		// 각각의 화면을 관리하는 클래스 객체 생성
		vote = new voteView();
		voteAdd = new voteAddView();
		voteResult = new voteResultView();
		myInfo = new myInfoView();
		store = new storeView();
		
		JTabbedPane pane = new JTabbedPane();
		pane.setFont(loginView.customFont);
		pane.addTab("투표", vote);
		vote.setLayout(null);
		pane.addTab("발의문 등록", voteAdd);
		pane.addTab("투표결과", voteResult);
		pane.addTab("내 정보", myInfo);
		pane.addTab("상품 교환", store);

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
