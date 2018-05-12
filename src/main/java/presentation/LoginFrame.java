package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import client.HttpStudent;
import client.HttpUser;

public class LoginFrame {

	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
			}
		}
		login();
	}

	public static void login() {

		final JFrame principalFrame = new JFrame("WELCOME");
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principalFrame.setSize(600, 800);

		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(0, 0, 600, 800);
		panel1.setBackground(new Color(251, 252, 194));

		final JTextField username = new JTextField("Insert username");
		username.setBounds(50, 100, 250, 50);
		panel1.add(username);

		final JPasswordField password = new JPasswordField();
		password.setBounds(50, 200, 250, 50);
		panel1.add(password);

		JButton login = new JButton("LOGIN");
		login.setBounds(50, 300, 250, 50);
		login.setBackground(new Color(251, 252, 194));
		login.setFont(new Font("Dialog", Font.BOLD, 18));
		panel1.add(login);

		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0, 0, 600, 800);
		panel2.setBackground(new Color(227, 230, 232));

		final JTextField oldUsername = new JTextField("Insert token");
		oldUsername.setBounds(50, 20, 250, 50);
		panel2.add(oldUsername);

		final JTextField email = new JTextField("Insert email");
		email.setBounds(50, 100, 250, 50);
		panel2.add(email);

		final JTextField name = new JTextField("Insert name");
		name.setBounds(50, 180, 250, 50);
		panel2.add(name);

		final JTextField newUsername = new JTextField("Insert username");
		newUsername.setBounds(50, 260, 250, 50);
		panel2.add(newUsername);

		final JPasswordField newPassword = new JPasswordField();
		newPassword.setBounds(50, 340, 250, 50);
		panel2.add(newPassword);

		final JTextField group = new JTextField("Insert group number");
		group.setBounds(50, 420, 250, 50);
		panel2.add(group);

		final JTextField hobby = new JTextField("Insert hobby");
		hobby.setBounds(50, 500, 250, 50);
		panel2.add(hobby);

		JButton register = new JButton("REGISTER");
		register.setBounds(50, 600, 250, 50);
		register.setBackground(new Color(251, 252, 194));
		register.setFont(new Font("Dialog", Font.BOLD, 18));
		panel2.add(register);

		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 18));
		tabbedPane.addTab("LOGIN", panel1);
		tabbedPane.addTab("REGISTER", panel2);

		username.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				username.setText("");
			}
		});

		name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				name.setText("");
			}
		});

		email.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				email.setText("");
			}
		});

		newUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newUsername.setText("");
			}
		});

		group.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				group.setText("");
			}
		});

		hobby.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hobby.setText("");
			}
		});

		oldUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				oldUsername.setText("");
			}
		});

		login.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String type = HttpUser.login(username.getText(), String.valueOf(password.getPassword()));
				if (type.equals("student")) {
					Long id = HttpStudent.getId(username.getText(), String.valueOf(password.getPassword()));
					StudentScene.studentFrame(id);
				} else if (type.equals("teacher")) {
					TeacherScene.teacherFrame();
				} else {
					JOptionPane.showMessageDialog(principalFrame, "Invalid user", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}

		});

		register.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String message = HttpStudent.register(oldUsername.getText(), name.getText(), email.getText(),
						newUsername.getText(), String.valueOf(newPassword.getPassword()),
						Integer.parseInt(group.getText()), hobby.getText());
				JOptionPane.showMessageDialog(principalFrame, message);

			}

		});

		principalFrame.add(tabbedPane);
		principalFrame.setVisible(true);

	}

}
