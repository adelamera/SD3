package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.HttpAssignment;
import client.HttpAttendence;
import client.HttpLab;
import client.HttpSubmission;
import model.Assignment;
import model.Attendence;
import model.Lab;
import model.Submission;

public class StudentScene {

	private static Long assignmentId;

	public static void studentFrame(final Long studentId) {
		final JFrame principalFrame = new JFrame("STUDENT");
		principalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		principalFrame.setSize(1000, 600);

		// list panel
		final JPanel listScrollPane = new JPanel();
		listScrollPane.setLayout(null);
		listScrollPane.setBounds(0, 0, 1000, 600);
		listScrollPane.setBackground(new Color(251, 252, 194));

		// simple panel
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 600);
		panel.setBackground(new Color(227, 230, 232));

		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		menu.setFont(new Font("Arial", Font.BOLD, 20));
		JMenuItem i1 = new JMenuItem("Submit assignment");
		i1.setFont(new Font("Arial", Font.BOLD, 16));
		JMenuItem i2 = new JMenuItem("Filter list");
		i2.setFont(new Font("Arial", Font.BOLD, 16));
		JMenuItem i3 = new JMenuItem("See attendence");
		i3.setFont(new Font("Arial", Font.BOLD, 16));
		JMenuItem i4 = new JMenuItem("See submissions");
		i4.setFont(new Font("Arial", Font.BOLD, 16));
		menu.add(i1);
		menu.add(i2);
		menu.add(i3);
		menu.add(i4);
		mb.add(menu);
		principalFrame.setJMenuBar(mb);

		// list all labs
		final List<Lab> allLabs = HttpLab.getAllLabs();
		String[] dataL = new String[allLabs.size()];
		for (int i = 0; i < allLabs.size(); i++) {
			Lab lab = allLabs.get(i);
			dataL[i] = "Laboratory number: " + lab.getLaboratoryNr() + ", title: " + lab.getTitle() + ", date: "
					+ lab.getDate();
		}
		final JList<String> listLabs = new JList<String>(dataL);
		listLabs.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listLabs.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listLabs.setFont(new Font("Arial", Font.BOLD, 16));
		JScrollPane listScroller = new JScrollPane(listLabs);
		listScroller.setBounds(100, 5, 300, 200);
		listScrollPane.add(listScroller);

		// link
		final JTextField link = new JTextField("Insert link");
		link.setBounds(100, 100, 250, 50);
		panel.add(link);
		link.setVisible(false);

		// remark
		final JTextField remark = new JTextField("Insert remark");
		remark.setBounds(100, 200, 250, 50);
		panel.add(remark);
		remark.setVisible(false);

		// submit
		final JButton submit = new JButton("SUBMIT ASSIGNMENT");
		submit.setBounds(100, 300, 250, 50);
		submit.setBackground(new Color(251, 252, 194));
		panel.add(submit);
		submit.setVisible(false);

		// keyword
		final JTextField keyword = new JTextField("Insert keyword");
		keyword.setBounds(100, 100, 250, 50);
		keyword.setVisible(false);
		panel.add(keyword);
		keyword.setVisible(false);

		// filter
		final JButton filter = new JButton("FILTER LIST");
		filter.setBounds(100, 200, 250, 50);
		filter.setBackground(new Color(251, 252, 194));
		panel.add(filter);
		filter.setVisible(false);

		final JTextArea attendences = new JTextArea();
		attendences.setBounds(100, 100, 250, 200);
		panel.add(attendences);
		attendences.setVisible(false);

		final JTextArea submissions = new JTextArea();
		submissions.setBounds(100, 100, 250, 200);
		panel.add(submissions);
		submissions.setVisible(false);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, listScrollPane);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(450);
		principalFrame.add(splitPane);

		keyword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				keyword.setText("");
			}
		});

		remark.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remark.setText("");
			}
		});

		link.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link.setText("");
			}
		});

		listLabs.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {
				// list all assignments
				Long labId = HttpLab.getByLabNr(allLabs.get(listLabs.getSelectedIndex()).getLaboratoryNr());
				final List<Assignment> allAssignments = HttpAssignment.getAllAssignmentsForLab(labId);
				String[] dataA = new String[allAssignments.size()];
				for (int i = 0; i < allAssignments.size(); i++) {
					Assignment a = allAssignments.get(i);
					dataA[i] = "Assignment: " + a.getName() + ", description: " + a.getDescription() + ", deadline: "
							+ a.getDeadline();
				}
				final JList<String> listAssignments = new JList<String>(dataA);
				listAssignments.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				listAssignments.setLayoutOrientation(JList.HORIZONTAL_WRAP);
				listAssignments.setFont(new Font("Arial", Font.BOLD, 16));
				JScrollPane listScrollerA = new JScrollPane(listAssignments);
				listScrollerA.setBounds(100, 280, 300, 200);
				listScrollPane.add(listScrollerA);

				listAssignments.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent e) {
						assignmentId = HttpAssignment
								.getAssignmentByName(allAssignments.get(listAssignments.getSelectedIndex()).getName());
					}
				});

			}

		});

		filter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				List<Lab> filteredLabs = HttpLab.getByKeyword(keyword.getText());
				String[] data = new String[filteredLabs.size()];
				for (int i = 0; i < filteredLabs.size(); i++) {
					Lab lab = filteredLabs.get(i);
					data[i] = "Laboratory number: " + lab.getLaboratoryNr() + ", title: " + lab.getTitle() + ", date: "
							+ lab.getDate();
				}
				listLabs.setListData(data);

			}

		});

		keyword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				keyword.setText("");
			}
		});

		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String message = HttpSubmission.saveSubmission(studentId, assignmentId, link.getText(),
						remark.getText());
				JOptionPane.showMessageDialog(principalFrame, message);

			}
		});

		i1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				link.setVisible(true);
				remark.setVisible(true);
				submit.setVisible(true);
				keyword.setVisible(false);
				filter.setVisible(false);
				attendences.setVisible(false);
				submissions.setVisible(false);
			}
		});

		i2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				keyword.setVisible(true);
				filter.setVisible(true);
				link.setVisible(false);
				remark.setVisible(false);
				submit.setVisible(false);
				attendences.setVisible(false);
				submissions.setVisible(false);
			}
		});

		i3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				keyword.setVisible(false);
				filter.setVisible(false);
				link.setVisible(false);
				remark.setVisible(false);
				submit.setVisible(false);
				attendences.setVisible(true);
				submissions.setVisible(false);
				attendences.setText("");
				List<Attendence> att = HttpAttendence.getbyStudentId(studentId);
				StringBuilder data = new StringBuilder();
				for (int i = 0; i < att.size(); i++) {
					data.append("Laboratory number: " + String.valueOf(att.get(i).getLaboratoryId())
							+ System.lineSeparator());
				}
				attendences.setFont(new Font("Arial", Font.BOLD, 14));
				attendences.setText(data.toString());

			}
		});

		i4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				keyword.setVisible(false);
				filter.setVisible(false);
				link.setVisible(false);
				remark.setVisible(false);
				submit.setVisible(false);
				attendences.setVisible(false);
				submissions.setVisible(true);
				submissions.setText("");
				List<Submission> sub = HttpSubmission.getAllSubmissionsOfStudent(studentId);
				StringBuilder data = new StringBuilder();
				for (int i = 0; i < sub.size(); i++) {
					data.append("Grade: " + String.valueOf(sub.get(i).getGrade()) + ", link: " + sub.get(i).getLink()
							+ System.lineSeparator());
				}
				submissions.setFont(new Font("Arial", Font.BOLD, 14));
				submissions.setText(data.toString());

			}
		});

		principalFrame.setVisible(true);
	}

}
