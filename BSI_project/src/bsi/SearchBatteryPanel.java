package bsi;

import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.UIManager;

public class SearchBatteryPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFirstValue;
	private JRadioButton rdbtnBatteryId;
	private JRadioButton rdbtnAmper;
	private JRadioButton rdbtnVolt;
	private JRadioButton rdbtnAmperVolt;
	private JRadioButton rdbtnClientName;
	private JRadioButton rdbtnClientId;
	private ButtonGroup radioGroup;
	private JPanel panelBatteryID;
	private JPanel panelSelection;
	private JLabel lblSecondValue;
	private JTextField textSecondValue;
	private JButton btnSearch;
	private JButton btnReturn;
	private JLabel lblFirstValue;
	private ResultFrame resultFrame;
	private MySqlConnection con;
	private String[] columnNames = { "Battery ID", "Client ID", "Client name",
			"Amper", "Volt", "Start date", "End date", "Refurbished", "scrap",
			"status" };
	private Vector<String> cols;

	public SearchBatteryPanel() {
		super();
		initialize();
		listners();
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

		int len = columnNames.length;
		cols = new Vector<String>(len);
		for (int i = 0; i < len; i++)
			cols.add(columnNames[i]);

		JLabel lblSearchBattery = new JLabel("Search battery");
		lblSearchBattery.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSearchBattery.setBounds(250, 38, 175, 29);
		add(lblSearchBattery);

		panelSelection = new JPanel();
		panelSelection.setBackground(SystemColor.activeCaption);
		panelSelection.setBorder(new TitledBorder(null, "Select",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSelection.setBounds(195, 92, 348, 79);
		add(panelSelection);
		panelSelection.setLayout(null);
		radioGroup = new ButtonGroup();

		rdbtnBatteryId = new JRadioButton("Battery ID", true);
		rdbtnBatteryId.setBounds(16, 16, 89, 23);
		panelSelection.add(rdbtnBatteryId);
		rdbtnBatteryId.setBackground(SystemColor.activeCaption);
		radioGroup.add(rdbtnBatteryId);

		rdbtnAmper = new JRadioButton("Amper", false);
		rdbtnAmper.setBounds(16, 42, 89, 23);
		panelSelection.add(rdbtnAmper);
		rdbtnAmper.setBackground(SystemColor.activeCaption);
		radioGroup.add(rdbtnAmper);

		rdbtnVolt = new JRadioButton("Volt", false);
		rdbtnVolt.setBounds(127, 16, 89, 23);
		panelSelection.add(rdbtnVolt);
		rdbtnVolt.setBackground(SystemColor.activeCaption);
		radioGroup.add(rdbtnVolt);

		rdbtnAmperVolt = new JRadioButton("Amper & Volt", false);
		rdbtnAmperVolt.setBounds(127, 42, 89, 23);
		panelSelection.add(rdbtnAmperVolt);
		rdbtnAmperVolt.setBackground(SystemColor.activeCaption);
		radioGroup.add(rdbtnAmperVolt);

		rdbtnClientId = new JRadioButton("Client ID");
		
		radioGroup.add(rdbtnClientId);
		rdbtnClientId.setBackground(SystemColor.activeCaption);
		rdbtnClientId.setBounds(253, 16, 67, 23);
		panelSelection.add(rdbtnClientId);

		rdbtnClientName = new JRadioButton("Client name");
		
		radioGroup.add(rdbtnClientName);
		rdbtnClientName.setBackground(SystemColor.activeCaption);
		rdbtnClientName.setBounds(253, 42, 81, 23);
		panelSelection.add(rdbtnClientName);

		JLabel lblSearchBy = new JLabel("Search by:");
		lblSearchBy.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSearchBy.setBounds(84, 120, 101, 22);
		add(lblSearchBy);

		panelBatteryID = new JPanel();
		panelBatteryID.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Insert details",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBatteryID.setBackground(SystemColor.activeCaption);
		panelBatteryID.setBounds(115, 186, 428, 154);
		add(panelBatteryID);
		panelBatteryID.setLayout(null);

		lblFirstValue = new JLabel("Battery ID:");
		lblFirstValue.setBounds(10, 11, 137, 22);
		panelBatteryID.add(lblFirstValue);
		lblFirstValue.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFirstValue = new JTextField();
		textFirstValue.setBounds(157, 15, 261, 20);
		panelBatteryID.add(textFirstValue);
		textFirstValue.setColumns(10);

		lblSecondValue = new JLabel("Battery Volt:");
		lblSecondValue.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSecondValue.setBounds(10, 44, 137, 22);
		panelBatteryID.add(lblSecondValue);
		lblSecondValue.setVisible(false);

		textSecondValue = new JTextField();
		textSecondValue.setBounds(157, 46, 261, 20);
		panelBatteryID.add(textSecondValue);
		textSecondValue.setColumns(10);
		textSecondValue.setVisible(false);

		btnSearch = new JButton("Search");

		btnSearch.setBounds(313, 104, 105, 39);
		panelBatteryID.add(btnSearch);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(55, 382, 101, 36);
		add(btnReturn);
	}

	private void listners() {
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnBatteryId.isSelected()) {
					searchBttery();
				}

				else if (rdbtnAmper.isSelected()) {
					searchAmper(Integer.parseInt(textFirstValue.getText()));
				}

				else if (rdbtnVolt.isSelected()) {
					searchVolt(Integer.parseInt(textFirstValue.getText()));
				}

				else if (rdbtnAmperVolt.isSelected()) {
					searchAmperVolt(
							Integer.parseInt(textSecondValue.getText()),
							Integer.parseInt(textFirstValue.getText()));
				}

				else if (rdbtnClientId.isSelected()) {
					searchClientByID(Integer.parseInt(textFirstValue.getText()));
				}
				
				else if (rdbtnClientName.isSelected()){
					searchClientByName(textFirstValue.getText());
				}

			}
		});
		
		rdbtnClientName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textSecondValue.setVisible(false);
				lblSecondValue.setVisible(false);
				lblFirstValue.setText("Client name:");
				lblFirstValue.setFont(new Font("Tahoma", Font.BOLD, 18));
			}
		});
		
		rdbtnClientId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textSecondValue.setVisible(false);
				lblSecondValue.setVisible(false);
				lblFirstValue.setText("Client ID:");
				lblFirstValue.setFont(new Font("Tahoma", Font.BOLD, 18));
			}
		});
		
		
		rdbtnBatteryId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textSecondValue.setVisible(false);
				lblSecondValue.setVisible(false);
				lblFirstValue.setText("Battery ID:");
				lblFirstValue.setFont(new Font("Tahoma", Font.BOLD, 18));
			}
		});

		rdbtnAmper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textSecondValue.setVisible(false);
				lblSecondValue.setVisible(false);
				lblFirstValue.setText("Battery amper:");
				lblFirstValue.setFont(new Font("Tahoma", Font.BOLD, 18));
			}
		});

		rdbtnVolt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textSecondValue.setVisible(false);
				lblSecondValue.setVisible(false);
				lblFirstValue.setText("Battery volt:");
				lblFirstValue.setFont(new Font("Tahoma", Font.BOLD, 18));
			}
		});

		rdbtnAmperVolt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textSecondValue.setVisible(true);
				lblSecondValue.setText("Battery volt:");
				lblSecondValue.setVisible(true);

				textFirstValue.setVisible(true);
				lblFirstValue.setText("Battery amper:");
				lblFirstValue.setFont(new Font("Tahoma", Font.BOLD, 18));
			}
		});
	}

	protected void searchAmperVolt(int volt, int amper) {
		Object[] searchByAmperVolt = {
				"SELECT * FROM `bsi_db`.`clientbatteryinfo` WHERE amper = ? AND volt = ?;",
				amper, volt };
		insertDataAndShow(sendToSql(searchByAmperVolt));
	}

	protected void searchAmper(int amper) {
		Object[] searchByAmper = {
				"SELECT * FROM `bsi_db`.`clientbatteryinfo` WHERE amper = ?;",
				amper };
		insertDataAndShow(sendToSql(searchByAmper));
	}

	protected void searchVolt(int volt) {
		Object[] searchByVolt = {
				"SELECT * FROM `bsi_db`.`clientbatteryinfo` WHERE volt = ?;",
				volt };
		insertDataAndShow(sendToSql(searchByVolt));
	}

	protected void searchClientByID(int idclient) {
		Object[] searchByClientID = {
				"SELECT * FROM `bsi_db`.`clientbatteryinfo` WHERE idclient = ?;",
				idclient };
		insertDataAndShow(sendToSql(searchByClientID));
	}

	protected void searchClientByName(String name) {
		Object[] searchByClientName = {
				"SELECT * FROM `bsi_db`.`clientbatteryinfo` WHERE name = ?;",
				name };
		insertDataAndShow(sendToSql(searchByClientName));
	}

	public ArrayList<Object> dataFromBatteryTable() {
		Object[] msg = {
				"SELECT * FROM bsi_db.clientbatteryinfo WHERE idbattery = ? ;",
				Integer.parseInt(textFirstValue.getText()) };
		sendToSql(msg);
		return getSqlRslt(con);
	}

	public JButton getBtnReturn() {
		if (btnReturn == null) {
			btnReturn = new JButton("Return");
			btnReturn.setBounds(55, 382, 101, 36);
			add(btnReturn);
		}
		return btnReturn;
	}

	public ArrayList<Object> getSqlRslt(MySqlConnection con) {
		ArrayList<Object> result = null;
		while ((result = con.getResult()) == null) {
		}
		return result;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public JTextField getTextFirstValue() {
		return textFirstValue;
	}

	public ArrayList<Object> dataFromClientBattery() {
		Object[] getDataFromClientBattery = {
				"SELECT * FROM bsi_db.clientbatteryinfo WHERE clientbatteryinfo.idbattery = ?;",
				Integer.parseInt(textFirstValue.getText()) };
		sendToSql(getDataFromClientBattery);
		return getSqlRslt(con);
	}

	public ArrayList<Object> sendToSql(Object[] msg) {
		con = new MySqlConnection();
		con.update(con.getConn(), msg);
		return getSqlRslt(con);
	}

	protected void searchBttery() {
		ArrayList<Object> ResultFromBatteryTable = dataFromBatteryTable();
		insertDataAndShow(ResultFromBatteryTable);
	}

	protected void insertDataAndShow(ArrayList<Object> resultFromSql) {
		Vector<Object> row = new Vector<Object>(columnNames.length);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		int i = 1;
		for (Object value : resultFromSql) {
			if (!value.equals("no value"))
				row.add(value);
			else
				row.add("0");
			if(i % (columnNames.length) == 0){
				data.add(row);
				i = 1;
				row = new Vector<Object>(columnNames.length);
			}
			else
				i++;
			
		}
		creatResultTable(data);
	}

	protected void creatResultTable(Vector<Vector<Object>> data) {
		resultFrame = new ResultFrame(data, cols);
		resultFrame.setEnabled(true);
		resultFrame.setVisible(true);
		resultFrame.getBtnClose().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultFrame.setEnabled(false);
				resultFrame.setVisible(false);
				resultFrame.dispose();
				resultFrame = null;
			}
		});
	}

}
