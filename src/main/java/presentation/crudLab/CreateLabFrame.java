package presentation.crudLab;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import client.HttpLab;

public class CreateLabFrame {

	public static void createLab() {

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

		String[] numbers = new String[14];
		for (int i = 1; i <= 14; i++) {
			numbers[i - 1] = String.valueOf(i);
		}
		final JComboBox<String> number = new JComboBox<String>(numbers);
		number.setBounds(50, 100, 250, 50);
		panel.add(number);

		final JTextField title = new JTextField("Insert title");
		title.setBounds(50, 170, 250, 50);
		panel.add(title);

		final JTextField curricula = new JTextField("Insert curricula");
		curricula.setBounds(50, 240, 250, 50);
		panel.add(curricula);

		final JTextArea description = new JTextArea("Insert description");
		description.setBounds(50, 310, 250, 100);
		panel.add(description);

		JButton create = new JButton("CREATE");
		create.setBounds(50, 450, 250, 50);
		create.setBackground(new Color(251, 252, 194));
		create.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(create);

		title.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				title.setText("");
			}
		});

		curricula.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				curricula.setText("");
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
				String labNr = number.getSelectedItem().toString();
				String message = HttpLab.saveLab(Integer.parseInt(labNr), Date.valueOf(date), title.getText(),
						curricula.getText(), description.getText());
				JOptionPane.showMessageDialog(principalFrame, message);

			}
		});

		principalFrame.add(panel);

		principalFrame.setVisible(true);
	}

}
