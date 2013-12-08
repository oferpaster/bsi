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
	private JTextField textFieldRefurbished;
	private JTextField txtScrap;
	private JTextField textField;

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
		lblClientName.setBounds(103, 101, 115, 22);
		add(lblClientName);

		JLabel lblBatteryID = new JLabel("Battery ID:");
		lblBatteryID.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBatteryID.setBounds(103, 134, 115, 22);
		add(lblBatteryID);

		JLabel lblBatteryAmper = new JLabel("Battery amper:");
		lblBatteryAmper.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBatteryAmper.setBounds(103, 167, 136, 22);
		add(lblBatteryAmper);

		JLabel lblBattryVolte = new JLabel("Battery volte:");
		lblBattryVolte.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBattryVolte.setBounds(103, 200, 136, 22);
		add(lblBattryVolte);

		textCilentName = new JTextField();
		textCilentName.setBounds(260, 105, 287, 20);
		add(textCilentName);
		textCilentName.setColumns(10);

		textBatteryID = new JTextField();
		textBatteryID.setEditable(false);
		textBatteryID.setBounds(260, 138, 136, 20);
		add(textBatteryID);
		textBatteryID.setColumns(10);

		textBatteryAmper = new JTextField();
		textBatteryAmper.setEditable(false);
		textBatteryAmper.setBounds(260, 171, 287, 20);
		add(textBatteryAmper);
		textBatteryAmper.setColumns(10);

		textBatteryVolte = new JTextField();
		textBatteryVolte.setEditable(false);
		textBatteryVolte.setBounds(260, 204, 287, 20);
		add(textBatteryVolte);
		textBatteryVolte.setColumns(10);

		btnAddWork = new JButton("Add Work");
		btnAddWork.setBounds(418, 382, 129, 49);
		add(btnAddWork);

		btnAddWorkReturn = new JButton("Return");
		btnAddWorkReturn.setBounds(10, 388, 108, 36);
		add(btnAddWorkReturn);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDate.setBounds(103, 332, 136, 22);
		add(lblDate);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(260, 334, 115, 20);
		add(dateChooser);
		
		JLabel lblRefurbished = new JLabel("Refurbished:");
		lblRefurbished.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRefurbished.setBounds(103, 266, 136, 22);
		add(lblRefurbished);
		
		textFieldRefurbished = new JTextField();
		textFieldRefurbished.setBounds(260, 270, 287, 20);
		add(textFieldRefurbished);
		textFieldRefurbished.setColumns(10);
		
		JLabel lblScrap = new JLabel("Scrap:");
		lblScrap.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblScrap.setBounds(103, 299, 136, 22);
		add(lblScrap);
		
		txtScrap = new JTextField();
		txtScrap.setBounds(260, 301, 287, 20);
		add(txtScrap);
		txtScrap.setColumns(10);
		
		JButton btnSelectBattery = new JButton("Select battery");
		btnSelectBattery.setBounds(405, 137, 142, 23);
		add(btnSelectBattery);
		
		JLabel lblCells = new JLabel("Cells:");
		lblCells.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCells.setBounds(103, 233, 136, 22);
		add(lblCells);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(260, 237, 287, 20);
		add(textField);
		textField.setColumns(10);
	}

	private void Listners() {
		btnAddWork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("dd/MM/yyyy");
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
