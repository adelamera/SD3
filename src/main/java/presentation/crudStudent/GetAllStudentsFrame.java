package presentation.crudStudent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import client.HttpStudent;
import model.Student;

public class GetAllStudentsFrame {

	public static void getAllStudents() {

		JFrame principalFrame = new JFrame("GET ALL");
		principalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		principalFrame.setSize(600, 600);

		final JPanel listScrollPane = new JPanel();
		listScrollPane.setLayout(null);
		listScrollPane.setBounds(0, 0, 600, 600);
		listScrollPane.setBackground(new Color(251, 252, 194));

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 600);
		panel.setBackground(new Color(227, 230, 232));

		JButton get = new JButton("GET");
		get.setBounds(10, 200, 250, 50);
		get.setBackground(new Color(251, 252, 194));
		get.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(get);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, panel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(300);
		principalFrame.add(splitPane);

		get.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final List<Student> allStudents = HttpStudent.getAllStudents();
				String[] data = new String[allStudents.size()];
				for (int i = 0; i < allStudents.size(); i++) {
					Student student = allStudents.get(i);
					data[i] = "Student: " + student.getName() + ", email: " + student.getEmail() + ", username: "
							+ student.getUsername() + ", group: " + student.getEmail() + ", hobby: " + student.getHobby();
				}
				final JList<String> listStudents = new JList<String>(data);
				listStudents.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				listStudents.setLayoutOrientation(JList.HORIZONTAL_WRAP);
				listStudents.setFont(new Font("Arial", Font.BOLD, 16));
				JScrollPane listScroller = new JScrollPane(listStudents);
				listScroller.setBounds(5, 100, 280, 250);
				listScrollPane.add(listScroller);

			}
		});

		principalFrame.setVisible(true);
	}

}
