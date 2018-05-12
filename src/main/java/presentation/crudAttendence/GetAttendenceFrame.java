package presentation.crudAttendence;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.HttpAttendence;

public class GetAttendenceFrame {

	public static void getAttendence() {
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

		final JTextField id = new JTextField("Insert id");
		id.setBounds(50, 50, 250, 50);
		panel.add(id);

		final JTextArea att = new JTextArea();
		att.setBounds(10, 100, 200, 200);
		att.setVisible(false);
		listScrollPane.add(att);

		JButton get = new JButton("GET");
		get.setBounds(50, 200, 250, 50);
		get.setBackground(new Color(251, 252, 194));
		get.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(get);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, listScrollPane);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(350);
		principalFrame.add(splitPane);

		id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id.setText("");
			}
		});

		get.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				att.setText(HttpAttendence.getAttendence(Long.valueOf(id.getText())).toString());
				att.setVisible(true);
			}

		});

		principalFrame.setVisible(true);

	}

}
