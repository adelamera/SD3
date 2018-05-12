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

public class DeleteStudentFrame {

	public static void deleteStudent() {

		final JFrame principalFrame = new JFrame("DELETE");
		principalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		principalFrame.setSize(600, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 600);
		panel.setBackground(new Color(251, 252, 194));

		final JTextField id = new JTextField("Insert id");
		id.setBounds(50, 50, 250, 50);
		panel.add(id);

		JButton delete = new JButton("DELETE");
		delete.setBounds(50, 200, 250, 50);
		delete.setBackground(new Color(251, 252, 194));
		delete.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(delete);
		

		id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id.setText("");
			}
		});

		delete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				
				String message = HttpStudent.deleteStudent(Long.valueOf(id.getText()));
				JOptionPane.showMessageDialog(principalFrame, message);

			}

		});
		
		principalFrame.add(panel);
		principalFrame.setVisible(true);

	}

}
