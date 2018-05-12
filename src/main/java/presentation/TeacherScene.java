package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;

import client.HttpLab;
import model.Lab;
import presentation.crudAssignment.CreateAssignmentFrame;
import presentation.crudAssignment.DeleteAssignmentFrame;
import presentation.crudAssignment.GetAllAssignmentFrame;
import presentation.crudAssignment.GetAssignmentFrame;
import presentation.crudAssignment.UpdateAssignmentFrame;
import presentation.crudAttendence.CreateAttendenceFrame;
import presentation.crudAttendence.DeleteAttendenceFrame;
import presentation.crudAttendence.GetAllAttendenceFrame;
import presentation.crudAttendence.GetAttendenceFrame;
import presentation.crudAttendence.UpdateAttendenceFrame;
import presentation.crudLab.CreateLabFrame;
import presentation.crudLab.DeleteLabFrame;
import presentation.crudLab.GetAllLabFrame;
import presentation.crudLab.GetLabFrame;
import presentation.crudLab.UpdateLabFrame;
import presentation.crudStudent.CreateStudentFrame;
import presentation.crudStudent.DeleteStudentFrame;
import presentation.crudStudent.GetAllStudentsFrame;
import presentation.crudStudent.GetStudentFrame;
import presentation.crudStudent.UpdateStudentFrame;
import presentation.grade.GetGradesFrame;
import presentation.grade.GradeSubmission;

public class TeacherScene {

	public static void teacherFrame() {
		final JFrame principalFrame = new JFrame("TEACHER");
		principalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		principalFrame.setSize(600, 600);

		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(0, 0, 600, 600);
		panel1.setBackground(new Color(251, 252, 194));

		JCheckBox delete = new JCheckBox("Delete");
		delete.setMnemonic(KeyEvent.VK_C);
		delete.setBounds(100, 250, 100, 100);
		delete.setFont(new Font("Dialog", Font.BOLD, 18));
		delete.setSelected(false);

		JCheckBox update = new JCheckBox("Update");
		update.setMnemonic(KeyEvent.VK_C);
		update.setBounds(100, 200, 100, 100);
		update.setFont(new Font("Dialog", Font.BOLD, 18));
		update.setSelected(false);

		JCheckBox getAll = new JCheckBox("Get all students");
		getAll.setMnemonic(KeyEvent.VK_G);
		getAll.setBounds(100, 150, 200, 100);
		getAll.setFont(new Font("Dialog", Font.BOLD, 18));
		getAll.setSelected(false);

		JCheckBox get = new JCheckBox("Get student");
		get.setMnemonic(KeyEvent.VK_H);
		get.setBounds(100, 100, 150, 100);
		get.setFont(new Font("Dialog", Font.BOLD, 18));
		get.setSelected(false);

		JCheckBox create = new JCheckBox("Create");
		create.setMnemonic(KeyEvent.VK_T);
		create.setBounds(100, 50, 100, 100);
		create.setFont(new Font("Dialog", Font.BOLD, 18));
		create.setSelected(false);

		panel1.add(create);
		panel1.add(get);
		panel1.add(getAll);
		panel1.add(update);
		panel1.add(delete);

		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0, 0, 600, 600);
		panel2.setBackground(new Color(227, 230, 232));

		JCheckBox deleteLab = new JCheckBox("Delete");
		deleteLab.setMnemonic(KeyEvent.VK_C);
		deleteLab.setBounds(100, 250, 100, 100);
		deleteLab.setFont(new Font("Dialog", Font.BOLD, 18));
		deleteLab.setSelected(false);

		JCheckBox updateLab = new JCheckBox("Update");
		updateLab.setMnemonic(KeyEvent.VK_C);
		updateLab.setBounds(100, 200, 100, 100);
		updateLab.setFont(new Font("Dialog", Font.BOLD, 18));
		updateLab.setSelected(false);

		JCheckBox getAllLabs = new JCheckBox("Get all labs");
		getAllLabs.setMnemonic(KeyEvent.VK_G);
		getAllLabs.setBounds(100, 150, 200, 100);
		getAllLabs.setFont(new Font("Dialog", Font.BOLD, 18));
		getAllLabs.setSelected(false);

		JCheckBox getLab = new JCheckBox("Get laboratory");
		getLab.setMnemonic(KeyEvent.VK_H);
		getLab.setBounds(100, 100, 150, 100);
		getLab.setFont(new Font("Dialog", Font.BOLD, 18));
		getLab.setSelected(false);

		JCheckBox createLab = new JCheckBox("Create");
		createLab.setMnemonic(KeyEvent.VK_T);
		createLab.setBounds(100, 50, 100, 100);
		createLab.setFont(new Font("Dialog", Font.BOLD, 18));
		createLab.setSelected(false);

		panel2.add(createLab);
		panel2.add(getLab);
		panel2.add(getAllLabs);
		panel2.add(updateLab);
		panel2.add(deleteLab);

		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(0, 0, 600, 600);
		panel3.setBackground(new Color(251, 252, 194));

		final JPanel listScrollPane = new JPanel();
		listScrollPane.setLayout(null);
		listScrollPane.setBounds(0, 0, 600, 600);
		listScrollPane.setBackground(new Color(251, 252, 194));

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel3, listScrollPane);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(350);

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
		final JScrollPane listScroller = new JScrollPane(listLabs);
		listScroller.setBounds(10, 100, 200, 250);
		listScrollPane.add(listScroller);

		final JList<String> listLabs2 = new JList<String>(dataL);
		listLabs2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listLabs2.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listLabs2.setFont(new Font("Arial", Font.BOLD, 16));
		JScrollPane listScroller2 = new JScrollPane(listLabs2);
		listScroller2.setBounds(10, 100, 200, 250);
		listScrollPane.add(listScroller2);

		JCheckBox deleteAtt = new JCheckBox("Delete");
		deleteAtt.setMnemonic(KeyEvent.VK_C);
		deleteAtt.setBounds(100, 250, 100, 100);
		deleteAtt.setFont(new Font("Dialog", Font.BOLD, 18));
		deleteAtt.setSelected(false);

		JCheckBox updateAtt = new JCheckBox("Update");
		updateAtt.setMnemonic(KeyEvent.VK_C);
		updateAtt.setBounds(100, 200, 100, 100);
		updateAtt.setFont(new Font("Dialog", Font.BOLD, 18));
		updateAtt.setSelected(false);

		JCheckBox getAllAtt = new JCheckBox("Get all attendences");
		getAllAtt.setMnemonic(KeyEvent.VK_G);
		getAllAtt.setBounds(100, 150, 200, 100);
		getAllAtt.setFont(new Font("Dialog", Font.BOLD, 18));
		getAllAtt.setSelected(false);

		JCheckBox getAtt = new JCheckBox("Get attendence");
		getAtt.setMnemonic(KeyEvent.VK_H);
		getAtt.setBounds(100, 100, 200, 100);
		getAtt.setFont(new Font("Dialog", Font.BOLD, 18));
		getAtt.setSelected(false);

		JCheckBox createAtt = new JCheckBox("Create");
		createAtt.setMnemonic(KeyEvent.VK_T);
		createAtt.setBounds(100, 50, 100, 100);
		createAtt.setFont(new Font("Dialog", Font.BOLD, 18));
		createAtt.setSelected(false);

		panel3.add(createAtt);
		panel3.add(getAtt);
		panel3.add(getAllAtt);
		panel3.add(updateAtt);
		panel3.add(deleteAtt);

		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBounds(0, 0, 600, 600);
		panel4.setBackground(new Color(227, 230, 232));

		JPanel listScrollPaneA = new JPanel();
		listScrollPaneA.setLayout(null);
		listScrollPaneA.setBounds(0, 0, 600, 600);
		listScrollPaneA.setBackground(new Color(227, 230, 232));
		listScrollPaneA.add(listScroller);

		JSplitPane splitPaneA = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel4, listScrollPaneA);
		splitPaneA.setOneTouchExpandable(true);
		splitPaneA.setDividerLocation(350);

		JCheckBox deleteA = new JCheckBox("Delete");
		deleteA.setMnemonic(KeyEvent.VK_C);
		deleteA.setBounds(100, 250, 100, 100);
		deleteA.setFont(new Font("Dialog", Font.BOLD, 18));
		deleteA.setSelected(false);

		JCheckBox updateA = new JCheckBox("Update");
		updateA.setMnemonic(KeyEvent.VK_C);
		updateA.setBounds(100, 200, 100, 100);
		updateA.setFont(new Font("Dialog", Font.BOLD, 18));
		updateA.setSelected(false);

		JCheckBox getAllA = new JCheckBox("Get all assignments");
		getAllA.setMnemonic(KeyEvent.VK_G);
		getAllA.setBounds(100, 150, 200, 100);
		getAllA.setFont(new Font("Dialog", Font.BOLD, 18));
		getAllA.setSelected(false);

		JCheckBox getA = new JCheckBox("Get assignment");
		getA.setMnemonic(KeyEvent.VK_H);
		getA.setBounds(100, 100, 200, 100);
		getA.setFont(new Font("Dialog", Font.BOLD, 18));
		getA.setSelected(false);

		JCheckBox createA = new JCheckBox("Create");
		createA.setMnemonic(KeyEvent.VK_T);
		createA.setBounds(100, 50, 100, 100);
		createA.setFont(new Font("Dialog", Font.BOLD, 18));
		createA.setSelected(false);

		panel4.add(createA);
		panel4.add(getA);
		panel4.add(getAllA);
		panel4.add(updateA);
		panel4.add(deleteA);

		JPanel panel5 = new JPanel();
		panel5.setLayout(null);
		panel5.setBounds(0, 0, 600, 600);
		panel5.setBackground(new Color(251, 252, 194));

		JCheckBox grade = new JCheckBox("Grade submission");
		grade.setMnemonic(KeyEvent.VK_H);
		grade.setBounds(100, 100, 200, 100);
		grade.setFont(new Font("Dialog", Font.BOLD, 18));
		grade.setSelected(false);

		JCheckBox getGrades = new JCheckBox("Get grades");
		getGrades.setMnemonic(KeyEvent.VK_T);
		getGrades.setBounds(100, 200, 200, 100);
		getGrades.setFont(new Font("Dialog", Font.BOLD, 18));
		getGrades.setSelected(false);

		panel5.add(getGrades);
		panel5.add(grade);

		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 18));
		tabbedPane.addTab("student", panel1);
		tabbedPane.addTab("laboratory", panel2);
		tabbedPane.addTab("attendence", splitPane);
		tabbedPane.addTab("assignment", splitPaneA);
		tabbedPane.addTab("grade", panel5);
		principalFrame.add(tabbedPane);

		get.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				GetStudentFrame.getStudent();

			}

		});

		getAll.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				GetAllStudentsFrame.getAllStudents();

			}

		});

		create.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				CreateStudentFrame.createStudent();

			}

		});

		update.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				UpdateStudentFrame.updateStudent();

			}

		});

		delete.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				DeleteStudentFrame.deleteStudent();

			}

		});

		createLab.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				CreateLabFrame.createLab();
			}

		});

		updateLab.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				UpdateLabFrame.updateLab();

			}

		});

		deleteLab.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				DeleteLabFrame.deleteLab();

			}

		});

		getLab.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				GetLabFrame.getLab();
			}

		});

		getAllLabs.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				GetAllLabFrame.getAllLabs();
			}

		});

		getAllAtt.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {

				GetAllAttendenceFrame.getAllAttendences();
			}

		});

		getAtt.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {

				GetAttendenceFrame.getAttendence();
			}

		});

		createAtt.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				if (listLabs2.getSelectedIndex() < 0) {
					JOptionPane.showMessageDialog(principalFrame, "You must choose a laboratory");
				} else {
					Long id = HttpLab.getByLabNr(allLabs.get(listLabs2.getSelectedIndex()).getLaboratoryNr());
					CreateAttendenceFrame.createAttendence(id);
				}
			}

		});

		updateAtt.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				if (listLabs2.getSelectedIndex() < 0) {
					JOptionPane.showMessageDialog(principalFrame, "You must choose a laboratory");
				} else {
					Long id = HttpLab.getByLabNr(allLabs.get(listLabs2.getSelectedIndex()).getLaboratoryNr());
					UpdateAttendenceFrame.updateAttendence(id);
				}
			}

		});

		deleteAtt.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				DeleteAttendenceFrame.deleteAttendence();
			}

		});

		getA.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				GetAssignmentFrame.getAssignment();
			}

		});

		getAllA.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				GetAllAssignmentFrame.getAllAssignments();
			}

		});

		createA.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				if (listLabs.getSelectedIndex() < 0) {
					JOptionPane.showMessageDialog(principalFrame, "You must choose a laboratory");
				} else {
					Long id = HttpLab.getByLabNr(allLabs.get(listLabs.getSelectedIndex()).getLaboratoryNr());
					CreateAssignmentFrame.createAssignment(id);
				}
			}

		});

		updateA.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				UpdateAssignmentFrame.updateAssignment();

			}

		});

		deleteA.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				DeleteAssignmentFrame.deleteAssignment();

			}

		});

		getGrades.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				GetGradesFrame.getGrades();

			}

		});

		grade.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				GradeSubmission.gradeSubmission();

			}

		});

		principalFrame.setVisible(true);
	}

}
