package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import main.CashVote;
import model.smsSend;
import model.userModel;

public class storeView extends JPanel implements ActionListener {

	JButton btnTradeCoffee;
	JButton btnTradeAirpot;
	JButton btnTradeMacpro;

	userModel user = null;

	String allCash = null;

	private Image backgroundImage = new ImageIcon(CashVote.class.getResource("/image/store.png")).getImage();

	public storeView() {
		try {
			user = new userModel();
			System.out.println("����DB ���� ����");
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "���� DB���� ����" + e.getMessage());
		}

		setLayout(null);

		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(backgroundImage, 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setBounds(0, 0, 1030, 412);
		add(panel);
		panel.setLayout(null);

		btnTradeCoffee = new JButton("");
		btnTradeCoffee.setBounds(142, 316, 97, 23);
		btnTradeCoffee.setIcon(new ImageIcon(CashVote.class.getResource("/image/storebtn.png")));
		btnTradeCoffee.setBorderPainted(false);
		panel.add(btnTradeCoffee);

		btnTradeAirpot = new JButton("\uAD50\uD658\uD558\uAE30");
		btnTradeAirpot.setBounds(471, 316, 97, 23);
		btnTradeAirpot.setIcon(new ImageIcon(CashVote.class.getResource("/image/storebtn.png")));
		btnTradeAirpot.setBorderPainted(false);
		panel.add(btnTradeAirpot);

		btnTradeMacpro = new JButton("\uAD50\uD658\uD558\uAE30");
		btnTradeMacpro.setBounds(791, 316, 97, 23);
		btnTradeMacpro.setIcon(new ImageIcon(CashVote.class.getResource("/image/storebtn.png")));
		btnTradeMacpro.setBorderPainted(false);
		panel.add(btnTradeMacpro);

		btnTradeCoffee.addActionListener(this);
		btnTradeAirpot.addActionListener(this);
		btnTradeMacpro.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnTradeCoffee) {
			String cost = "50000";
			try {
				allCash = user.getAllCash();
			} catch (Exception e1) {
			}

			if (Integer.parseInt(allCash) - Integer.parseInt(cost) >= 0) {
				try {
					user.useCash(userModel.loginId, cost, "Ŀ�� ��ȯ");
					new smsSend().coffeeSend();
					JOptionPane.showMessageDialog(null, "Ŀ�� ��ȯ ����!");
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Ŀ�� ��ȯ ����!" + e2.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(null, "���� ĳ�� ����");
			}
		}

		if (o == btnTradeAirpot) {
			String cost = "500000";
			try {
				allCash = user.getAllCash();
			} catch (Exception e1) {
			}

			if (Integer.parseInt(allCash) - Integer.parseInt(cost) >= 0) {
				try {
					user.useCash(userModel.loginId, cost, "������ ��ȯ");
					new smsSend().airpotSend();
					JOptionPane.showMessageDialog(null, "������ ��ȯ ����!");
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "������ ��ȯ ����!" + e2.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(null, "���� ĳ�� ����");
			}
		}

		if (o == btnTradeMacpro) {
			String cost = "7500000";
			try {
				allCash = user.getAllCash();
			} catch (Exception e1) {
			}

			if (Integer.parseInt(allCash) - Integer.parseInt(cost) >= 0) {
				try {
					user.useCash(userModel.loginId, cost, "�ƺ� ��ȯ");
					new smsSend().macproSend();
					JOptionPane.showMessageDialog(null, "�ƺ� ��ȯ ����!");
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "�ƺ� ��ȯ ����!" + e2.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(null, "���� ĳ�� ����");
			}
		}
	}
}