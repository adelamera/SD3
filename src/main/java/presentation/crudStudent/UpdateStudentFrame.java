package presentation.crudStudent;

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
import javax.swing.JTextField;
import client.HttpStudent;

public class UpdateStudentFrame {

	public static void updateStudent() {

		final JFrame principalFrame = new JFrame("UPDATE");
		principalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		principalFrame.setSize(600, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 600);
		panel.setBackground(new Color(251, 252, 194));

		final JTextField id = new JTextField("Insert id");
		id.setBounds(50, 0, 250, 50);
		panel.add(id);

		final JTextField email = new JTextField("Insert email");
		email.setBounds(50, 70, 250, 50);
		panel.add(email);

		final JTextField name = new JTextField("Insert name");
		name.setBounds(50, 140, 250, 50);
		panel.add(name);

		final JTextField username = new JTextField("Insert username");
		username.setBounds(50, 210, 250, 50);
		panel.add(username);

		final JPasswordField password = new JPasswordField();
		password.setBounds(50, 280, 250, 50);
		panel.add(password);

		final JTextField group = new JTextField("Insert group number");
		group.setBounds(50, 350, 250, 50);
		panel.add(group);

		final JTextField hobby = new JTextField("Insert hobby");
		hobby.setBounds(50, 420, 250, 50);
		panel.add(hobby);

		JButton update = new JButton("UPDATE");
		update.setBounds(50, 490, 250, 50);
		update.setBackground(new Color(251, 252, 194));
		update.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(update);

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

		username.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				username.setText("");
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

		id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id.setText("");
			}
		});

		update.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String message = HttpStudent.updateStudent(Long.valueOf(id.getText()), name.getText(), email.getText(),
						username.getText(), String.valueOf(password.getPassword()), Integer.parseInt(group.getText()),
						hobby.getText());
				JOptionPane.showMessageDialog(principalFrame, message);

			}

		});

		principalFrame.add(panel);
		principalFrame.setVisible(true);
	}

}
