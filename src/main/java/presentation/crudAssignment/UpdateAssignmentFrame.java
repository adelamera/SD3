package presentation.crudAssignment;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import client.HttpAssignment;

public class UpdateAssignmentFrame {

	public static void updateAssignment() {
		final JFrame principalFrame = new JFrame("UPDATE");
		principalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		principalFrame.setSize(600, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 600);
		panel.setBackground(new Color(251, 252, 194));

		final JTextField id = new JTextField("Insert id");
		id.setBounds(50, 50, 250, 50);
		panel.add(id);

		final JDateChooser chooser = new JDateChooser();
		chooser.setLocale(Locale.ENGLISH);
		chooser.setBounds(50, 150, 250, 50);
		panel.add(chooser);

		final JTextField name = new JTextField("Insert name");
		name.setBounds(50, 250, 250, 50);
		panel.add(name);

		final JTextArea description = new JTextArea("Insert description");
		description.setBounds(50, 350, 250, 100);
		panel.add(description);

		JButton update = new JButton("UPDATE");
		update.setBounds(50, 470, 250, 50);
		update.setBackground(new Color(251, 252, 194));
		update.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(update);

		id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id.setText("");
			}
		});

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

		update.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(chooser.getDate());
				String message = HttpAssignment.updateAssignment(Long.valueOf(id.getText()), name.getText(),
						Date.valueOf(date), description.getText());
				JOptionPane.showMessageDialog(principalFrame, message);

			}
		});

		principalFrame.add(panel);
		principalFrame.setVisible(true);
	}

}
