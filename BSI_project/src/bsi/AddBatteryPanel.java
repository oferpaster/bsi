package bsi;

import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
public class AddBatteryPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtBatteryVoltage;
	private JTextField txtClientName;
	private JTextField txtBatteryAmper;
	private JButton btnAdd;
	private JButton btnReturn;

	public AddBatteryPanel() {
		super();
		initialize();
		Listners();
	}

	private void initialize() {
		setBackground(SystemColor.activeCaption);
		this.setSize(390, 290);
		//this.setVisible(true);
		setLayout(null);

		JLabel lblAddNewBattery = new JLabel("Add new battery");
		lblAddNewBattery.setBounds(96, 11, 197, 29);
		lblAddNewBattery.setFont(new Font("Tahoma", Font.BOLD, 24));
		add(lblAddNewBattery);

		JLabel lblClientName = new JLabel("Client name:");
		lblClientName.setBounds(10, 64, 115, 22);
		lblClientName.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblClientName);

		JLabel lblBatteryVoltage = new JLabel("Battery voltage:");
		lblBatteryVoltage.setBounds(10, 97, 144, 22);
		lblBatteryVoltage.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblBatteryVoltage);

		JLabel lblBatteryAmper = new JLabel("Battery amper:");
		lblBatteryAmper.setBounds(10, 130, 136, 22);
		lblBatteryAmper.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblBatteryAmper);

		txtBatteryVoltage = new JTextField();
		txtBatteryVoltage.setBounds(164, 101, 54, 20);
		add(txtBatteryVoltage);
		txtBatteryVoltage.setColumns(10);

		txtClientName = new JTextField();
		txtClientName.setBounds(165, 68, 129, 20);
		add(txtClientName);
		txtClientName.setColumns(10);

		txtBatteryAmper = new JTextField();
		txtBatteryAmper.setBounds(164, 134, 54, 20);
		add(txtBatteryAmper);
		txtBatteryAmper.setColumns(10);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(10, 243, 108, 36);
		add(btnReturn);

		btnAdd = new JButton("Add");

		btnAdd.setBounds(251, 237, 129, 49);
		add(btnAdd);

	}

	private void Listners() {
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MySqlConnection db = new MySqlConnection();

				String[] msg = { "SELECT COUNT(battery.idbattery) AS 'numberOfId' FROM `bsi_db`.`battery`;" };
				db.update(db.getConn(), msg);
				String numberOfidStr = getSqlRslt(db).get(0).toString();
				System.out.println(numberOfidStr);
				Integer numberOfid = Integer.parseInt(numberOfidStr.replaceAll("-", ""));
				numberOfid++;

				db = new MySqlConnection();
				String[] checkForClientId = {
						"SELECT client.idclient FROM bsi_db.client WHERE ? = ?;",
						"client.name", txtClientName.getText() };
				db.update(db.getConn(), checkForClientId);
				Object clientID = getSqlRslt(db).get(0);

				Integer clientNumber = 0;
				if (clientID.equals("No Result")) {
					MySqlConnection con1 = new MySqlConnection();
					Object[] msg2 = { "SELECT COUNT(client.idclient) AS 'numberOfId' FROM bsi_db.client " };
					con1.update(con1.getConn(), msg2);
					String clientNumberStr = getSqlRslt(con1).get(0).toString();
					clientNumber = Integer.parseInt(clientNumberStr);
				} else {
					clientNumber = Integer.parseInt(clientID.toString());
				}
				
				db = new MySqlConnection();
				clientNumber++;
				Object[] addClient = {
						"INSERT INTO `bsi_db`.`client` VALUES (?,?);",
						clientNumber, txtClientName.getText() };
				db.update(db.getConn(), addClient);

				db = new MySqlConnection();
				Object[] addBattery = {
						"INSERT INTO `bsi_db`.`battery` VALUES (?,?,?,?);",
						numberOfid,clientNumber,
						Integer.parseInt(txtBatteryAmper.getText()),
						Integer.parseInt(txtBatteryVoltage.getText()) };
				db.update(db.getConn(), addBattery);



				db = new MySqlConnection();
				Object[] addNewBattery = {
						"INSERT INTO `bsi_db`.`clientbattery`(`batteryid`,`clientid`,`status`) VALUES (?,?,?);",
						numberOfid, clientNumber, 1 };
				db.update(db.getConn(), addNewBattery);
			}

		});
	}

	public ArrayList<Object> getSqlRslt(MySqlConnection con) {
		ArrayList<Object> result = null;
		while ((result = con.getResult()) == null) {
		}
		return result;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}
}
