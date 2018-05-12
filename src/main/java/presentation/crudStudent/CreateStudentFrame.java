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
import javax.swing.JTextField;
import client.HttpStudent;
import utils.SendEmail;

public class CreateStudentFrame {

	public static void createStudent() {

		final JFrame principalFrame = new JFrame("CREATE");
		principalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		principalFrame.setSize(600, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 600);
		panel.setBackground(new Color(251, 252, 194));

		final JTextField email = new JTextField("Insert email");
		email.setBounds(50, 100, 250, 50);
		panel.add(email);

		final JTextField name = new JTextField("Insert name");
		name.setBounds(50, 200, 250, 50);
		panel.add(name);

		JButton create = new JButton("CREATE");
		create.setBounds(50, 300, 250, 50);
		create.setBackground(new Color(251, 252, 194));
		create.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(create);

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

		create.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String token = HttpStudent.saveStudent(email.getText(), name.getText());
				String message = SendEmail.sendEmail(email.getText(), "registration token", token);
				JOptionPane.showMessageDialog(principalFrame, message);

			}

		});

		principalFrame.add(panel);
		principalFrame.setVisible(true);
	}

}
