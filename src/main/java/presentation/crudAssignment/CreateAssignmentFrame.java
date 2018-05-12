package presentation.crudAssignment;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import client.HttpAssignment;
import client.HttpStudent;
import model.Assignment;
import model.Student;
import utils.SendEmail;

public class CreateAssignmentFrame {

	public static void createAssignment(final Long laboratoryId) {

		final JFrame principalFrame = new JFrame("CREATE");
		principalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		principalFrame.setSize(600, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 600);
		panel.setBackground(new Color(251, 252, 194));

		final JDateChooser chooser = new JDateChooser();
		chooser.setLocale(Locale.ENGLISH);
		chooser.setBounds(50, 30, 250, 50);
		panel.add(chooser);

		final JTextField name = new JTextField("Insert name");
		name.setBounds(50, 100, 250, 50);
		panel.add(name);

		final JTextArea description = new JTextArea("Insert description");
		description.setBounds(50, 170, 250, 100);
		panel.add(description);

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

		description.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				description.setText("");
			}
		});

		create.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(chooser.getDate());
				Assignment assignment = new Assignment(name.getText(), Date.valueOf(date), description.getText());
				String message = HttpAssignment.saveAssignment(laboratoryId, name.getText(), Date.valueOf(date), description.getText());
				List<Student> students = HttpStudent.getAllStudents();
				JOptionPane.showMessageDialog(principalFrame, message);
				for (int i = 0; i < students.size(); i++) {
					SendEmail.sendEmail(students.get(i).getEmail(), "new assignment", assignment.toString());
				}			

			}
		});

		principalFrame.add(panel);
		principalFrame.setVisible(true);

	}

}
