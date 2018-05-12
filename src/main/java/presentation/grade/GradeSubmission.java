package presentation.grade;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.HttpStudent;
import client.HttpSubmission;
import model.Student;
import utils.SendEmail;

public class GradeSubmission {

	public static void gradeSubmission() {

		final JFrame principalFrame = new JFrame("GRADE");
		principalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		principalFrame.setSize(600, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 600);
		panel.setBackground(new Color(251, 252, 194));

		final JTextField id = new JTextField("Insert submission id");
		id.setBounds(50, 50, 250, 50);
		panel.add(id);

		String[] numbers = new String[10];
		for (int i = 1; i <= 10; i++) {
			numbers[i - 1] = String.valueOf(i);
		}
		final JComboBox<String> grades = new JComboBox<String>(numbers);
		grades.setBounds(50, 200, 250, 50);
		panel.add(grades);

		JButton update = new JButton("GRADE");
		update.setBounds(50, 450, 250, 50);
		update.setBackground(new Color(251, 252, 194));
		update.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(update);

		id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id.setText("");
			}
		});

		update.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String grade = grades.getSelectedItem().toString();
				String message = HttpSubmission.gradeSubmission(Long.valueOf(id.getText()),
						Integer.valueOf(grade));
				JOptionPane.showMessageDialog(principalFrame, message);
				Long studentId = HttpSubmission.getStudentId(Long.valueOf(id.getText()));
				Student student = HttpStudent.getStudent(studentId);
				SendEmail.sendEmail(student.getEmail(), "grade",
						"Your grade for submission " + id.getText() + " is: " + String.valueOf(grade));
			}

		});

		principalFrame.add(panel);
		principalFrame.setVisible(true);

	}

}
