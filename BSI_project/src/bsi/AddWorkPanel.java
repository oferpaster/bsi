package bsi;

import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddWorkPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textCilentName;
	private JTextField textBatteryID;
	private JTextField textBatteryAmper;
	private JTextField textBatteryVolte;
	private JButton btnAddWork;
	private JButton btnAddWorkReturn;
	private JDateChooser dateChooser;

	public AddWorkPanel() {
		super();
		initialize();
		Listners();

	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(this,
					"setLookAndFeel error: " + e.getMessage(),
					"setLookAndFeel ERRORE", JOptionPane.ERROR_MESSAGE);
		}
		setLayout(null);
		setBackground(SystemColor.activeCaption);
		this.setSize(675, 465);

		JLabel lblAddWork = new JLabel("Add new work");
		lblAddWork.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblAddWork.setBounds(247, 24, 181, 42);
		add(lblAddWork);

		JLabel lblClientName = new JLabel("Client name:");
		lblClientName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblClientName.setBounds(37, 101, 115, 22);
		add(lblClientName);

		JLabel lblBatteryID = new JLabel("Battery ID:");
		lblBatteryID.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBatteryID.setBounds(37, 134, 115, 22);
		add(lblBatteryID);

		JLabel lblBatteryAmper = new JLabel("Battery amper:");
		lblBatteryAmper.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBatteryAmper.setBounds(37, 167, 136, 22);
		add(lblBatteryAmper);

		JLabel lblBattryVolte = new JLabel("Battery volte:");
		lblBattryVolte.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBattryVolte.setBounds(37, 200, 136, 22);
		add(lblBattryVolte);

		textCilentName = new JTextField();
		textCilentName.setBounds(194, 105, 287, 20);
		add(textCilentName);
		textCilentName.setColumns(10);

		textBatteryID = new JTextField();
		textBatteryID.setBounds(194, 138, 287, 20);
		add(textBatteryID);
		textBatteryID.setColumns(10);

		textBatteryAmper = new JTextField();
		textBatteryAmper.setBounds(194, 171, 287, 20);
		add(textBatteryAmper);
		textBatteryAmper.setColumns(10);

		textBatteryVolte = new JTextField();
		textBatteryVolte.setBounds(194, 204, 287, 20);
		add(textBatteryVolte);
		textBatteryVolte.setColumns(10);

		btnAddWork = new JButton("Add Work");
		btnAddWork.setBounds(274, 279, 127, 69);
		add(btnAddWork);

		btnAddWorkReturn = new JButton("Return");
		btnAddWorkReturn.setBounds(10, 388, 108, 36);
		add(btnAddWorkReturn);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDate.setBounds(37, 233, 136, 22);
		add(lblDate);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(194, 235, 115, 20);
		add(dateChooser);
	}

	private void Listners() {
		btnAddWork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat(
						"dd/MM/yyyy");
				// String formattedDate = fmt.format(dateChooser.getDate());

				/*
				 * String[] msg = { "INSERT", textCilentName.getText(),
				 * textBatteryID.getText(), textBatteryAmper.getText(),
				 * textBatteryVolte.getText(), dateChooser.getDate().toString(),
				 * formattedDate };
				 */
				MySqlConnection con = new MySqlConnection();
				String[] checkForClientId = {
						"SELECT client.idclient FROM bsi_db.client WHERE ? = ?;",
						"client.name", textCilentName.getText() };
				con.update(con.getConn(), checkForClientId);
				Object result = getSqlRslt(con);
				if (result.equals("No Result")) {
					MySqlConnection con1 = new MySqlConnection();
					String[] msg = { "SELECT COUNT(client.idclient) AS 'numberOfId' FROM bsi_db.client " };
					con1.update(con1.getConn(), msg);
					result = getSqlRslt(con1);
					System.out.println("number of clients:" +result.toString());
				}
				System.out.println("Fuck off!");

			}
		});
	}

	public JButton getBtnAddWork() {
		return btnAddWork;
	}

	public JButton getBtnReturn() {
		return btnAddWorkReturn;
	}

	public Object getSqlRslt(MySqlConnection con) {
		Object result = null;
		while ((result = con.getResult()) == null) {
		}
		return result;
	}
}
