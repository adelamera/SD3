package presentation.grade;

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
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import client.HttpAssignment;
import client.HttpSubmission;
import model.Assignment;

public class GetGradesFrame {

	public static void getGrades() {
		JFrame principalFrame = new JFrame("GET");
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

		final List<Assignment> allAssignments = HttpAssignment.getAllAssignments();
		String[] data = new String[allAssignments.size()];
		for (int i = 0; i < allAssignments.size(); i++) {
			Assignment assignment = allAssignments.get(i);
			data[i] = "Assignment: " + assignment.getName() + ", deadline: " + assignment.getDeadline()
					+ ", description: " + assignment.getDescription();
		}
		final JList<String> listAssignment = new JList<String>(data);
		listAssignment.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listAssignment.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listAssignment.setFont(new Font("Arial", Font.BOLD, 16));
		JScrollPane listScroller = new JScrollPane(listAssignment);
		listScroller.setBounds(5, 100, 280, 250);
		listScrollPane.add(listScroller);

		final JTextArea grades = new JTextArea();
		grades.setBounds(10, 100, 200, 200);
		grades.setVisible(false);
		grades.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(grades);

		JButton get = new JButton("GET");
		get.setBounds(10, 400, 250, 50);
		get.setBackground(new Color(251, 252, 194));
		get.setFont(new Font("Dialog", Font.BOLD, 18));
		listScrollPane.add(get);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, panel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(350);
		principalFrame.add(splitPane);

		get.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Long assignmentId = HttpAssignment
						.getAssignmentByName(allAssignments.get(listAssignment.getSelectedIndex()).getName());
				grades.setText(HttpSubmission.getAllGrades(assignmentId));
				grades.setVisible(true);
			}

		});

		principalFrame.setVisible(true);
	}

}
