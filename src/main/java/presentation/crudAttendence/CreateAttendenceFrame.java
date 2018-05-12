package presentation.crudAttendence;

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
import client.HttpAttendence;

public class CreateAttendenceFrame {

	public static void createAttendence(final Long laboratoryId) {
		final JFrame principalFrame = new JFrame("CREATE");
		principalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		principalFrame.setSize(600, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 600);
		panel.setBackground(new Color(251, 252, 194));

		final JTextField id = new JTextField("Insert student id");
		id.setBounds(50, 100, 250, 50);
		panel.add(id);

		JButton create = new JButton("CREATE");
		create.setBounds(50, 200, 250, 50);
		create.setBackground(new Color(251, 252, 194));
		create.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(create);

		id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id.setText("");
			}
		});

		create.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String message = HttpAttendence.saveAttendence(laboratoryId, Long.valueOf(id.getText()));
				JOptionPane.showMessageDialog(principalFrame, message);

			}

		});

		principalFrame.add(panel);
		principalFrame.setVisible(true);

	}

}
