package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import main.CashVote;
import model.userModel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class loginView extends JFrame implements ActionListener {
	public static Font customFont = null;
	JScrollPane scrollPane;
	private Image backgroundImage = new ImageIcon(CashVote.class.getResource("/image/login.png")).getImage();
	public JTextField tfId;
	private JButton btnRegist;
	private JButton btnLogin;
	PreparedStatement ps = null;
	ResultSet rs = null;
	userModel model = null;

	static loginView frame = null;
	private JPasswordField tfPw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			// 1. 자바 내장 룩앤필

			// UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						frame = new loginView();
						frame.setVisible(true);
					} catch (Exception e) {
//                  e.printStackTrace();
					}
				}
			});
			// 2. quaqua.jar : Quaqua Look and Feel

//            UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
//
//            JFrame.setDefaultLookAndFeelDecorated(true);

			// 3. liquidlnf.jar : Liquid Look and Feel

			// UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");

			// 4. idw-gpl.jar : InfoNode Look and Feel

			// UIManager.setLookAndFeel("net.infonode.gui.laf.InfoNodeLookAndFeel");

			// 5. JTattoo.jar : JTattoo Look and Feel

			// UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");

			// UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");

		} catch (Exception e) {
		}

	}

	/**
	 * Create the frame.
	 */
	public loginView() {
		try {
			UIDefaults swingComponentDefaultTable = UIManager.getDefaults();
			Enumeration<Object> allDefaultKey = swingComponentDefaultTable.keys();
			while (allDefaultKey.hasMoreElements()) {
				String defaultKey = allDefaultKey.nextElement().toString();
				if (defaultKey.indexOf("font") != -1) {
					Font newDefaultFont = new Font("/font/NanumSquare_acL.ttf", Font.PLAIN, 12);
					UIManager.put(defaultKey, newDefaultFont);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try {
//			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("/font/NanumSquare_acEB.ttf"));
//			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//			ge.registerFont(customFont);
//		} catch (IOException | FontFormatException e2) {
//			// Handle exception
//		}

		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(backgroundImage, 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		setTitle("로그인");
		setResizable(false);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			model = new userModel();
			System.out.println("유저DB 연결 성공");
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "DB연결 실패" + e.getMessage());
		}

		JLabel lblId = new JLabel("\uC544\uC774\uB514");
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setBounds(125, 272, 50, 15);
		lblId.setFont(customFont);

		JLabel lblPw = new JLabel("\uD328\uC2A4\uC6CC\uB4DC");
		lblPw.setHorizontalAlignment(SwingConstants.LEFT);
		lblPw.setBounds(125, 300, 50, 15);
		lblPw.setFont(customFont);

		tfId = new JTextField();
		tfId.setBounds(185, 269, 147, 21);
		tfId.setColumns(10);

		btnRegist = new JButton("");
		btnRegist.setBounds(150, 325, 88, 26);
		btnRegist.setIcon(new ImageIcon(CashVote.class.getResource("/image/login_p.png")));
		// btnRegist.setBorderPainted(false);

		btnLogin = new JButton("");
		btnLogin.setBounds(247, 325, 88, 26);
		btnLogin.setIcon(new ImageIcon(CashVote.class.getResource("/image/login_l.png")));
		// btnLogin.setBorderPainted(false);

		tfPw = new JPasswordField();
		tfPw.setBounds(185, 297, 147, 21);

		background.setLayout(null);

		background.add(lblId);
		background.add(lblPw);
		background.add(tfId);
		background.add(tfPw);
		background.add(btnRegist);
		background.add(btnLogin);

		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);

		btnLogin.addActionListener(this);
		btnRegist.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		// 로그인
		if (o == btnLogin) {
			String id = tfId.getText();
			String pw = tfPw.getText();

			try {
				model.login(id, pw);
				JOptionPane.showMessageDialog(null, "로그인 성공");
				frame.dispose();
				CashVote cash = new CashVote();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "로그인 실패 : " + e1.getMessage());
//            e1.printStackTrace();
			}
		}

		if (o == btnRegist) {
			Window parentWindow = SwingUtilities.windowForComponent(btnRegist);
			registView dialog = new registView();
			dialog.setLocationRelativeTo(btnRegist);
			dialog.setModal(true);
			dialog.setVisible(true);
		}
	}
}