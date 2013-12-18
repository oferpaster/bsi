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
	private ButtonGroup radioGroup;
	private JPanel panelBatteryID;
	private JPanel panelSelection;
	private JLabel lblSecondValue;
	private JTextField textSecondValue;
	private JButton btnSearch;
	private JButton btnReturn;
	private JLabel lblFirstValue;
	private ResultFrame resultFrame;

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

		JRadioButton rdbtnClientId = new JRadioButton("Client ID");
		radioGroup.add(rdbtnClientId);
		rdbtnClientId.setBackground(SystemColor.activeCaption);
		rdbtnClientId.setBounds(253, 16, 67, 23);
		panelSelection.add(rdbtnClientId);

		JRadioButton rdbtnClientName = new JRadioButton("Client name");
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
				lblFirstValue.setText("Battery volte:");
				lblFirstValue.setFont(new Font("Tahoma", Font.BOLD, 18));
			}
		});

		rdbtnAmperVolt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textSecondValue.setVisible(true);
				lblSecondValue.setText("Battery volte:");
				lblSecondValue.setVisible(true);

				lblFirstValue.setText("Battery amper:");
				lblFirstValue.setFont(new Font("Tahoma", Font.BOLD, 18));
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnBatteryId.isEnabled()) {

					ArrayList<Object> ResultFromBatteryTable = dataFromBatteryTable();

					Integer idclient = Integer.parseInt(ResultFromBatteryTable
							.get(1).toString());
					String[] columnNames = { "Battery ID", "Client name",
							"Volt", "Amper", "Start date", "End date",
							"Refurbished", "scrap", "status" };
					int len = columnNames.length;
					Vector<String> cols = new Vector<String>(len);
					for (int i = 0; i < len; i++)
						cols.add(columnNames[i]);

					String findClient = findClientName(idclient);/*
																 * find client
																 * name by id
																 */
					ArrayList<Object> resultFromClientBattry = dataFromClientBattery();

					Vector<Object> row = new Vector<Object>(len);
					Vector<Vector<Object>> data = new Vector<Vector<Object>>();

					row.add(ResultFromBatteryTable.get(0));/* batteryID */
					row.add(findClient);/* Client name */
					row.add(ResultFromBatteryTable.get(2));/* battery volt */
					row.add(ResultFromBatteryTable.get(3));/* battery amper */
					for (Object value : resultFromClientBattry) {
						if (!value.equals("no value"))
							row.add(value);
						else
							row.add("0");
					}

					data.add(row);

					resultFrame = new ResultFrame(data, cols);
					resultFrame.setEnabled(true);
					resultFrame.setVisible(true);
					resultFrame.getBtnClose().addActionListener(
							new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									resultFrame.setEnabled(false);
									resultFrame.setVisible(false);
									resultFrame.dispose();
									resultFrame = null;
								}
							});
				}
			}
		});
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

	public String findClientName(int id) {
		Object[] findClientName = {
				"SELECT client.name FROM bsi_db.client WHERE client.idclient = ?;",
				id };
		MySqlConnection con = new MySqlConnection();
		con.update(con.getConn(), findClientName);
		return getSqlRslt(con).get(0).toString();
	}

	public ArrayList<Object> dataFromClientBattery() {
		Object[] getDataFromClientBattery = {
				"SELECT clientbattery.startDate, clientbattery.endDate, clientbattery.refurbished, clientbattery.scrap, clientbattery.status FROM bsi_db.clientbattery WHERE clientbattery.batteryid = ?;",
				Integer.parseInt(textFirstValue.getText()) };
		MySqlConnection con = new MySqlConnection();
		con.update(con.getConn(), getDataFromClientBattery);
		return getSqlRslt(con);

	}

	public ArrayList<Object> dataFromBatteryTable() {
		Object[] msg = {
				"SELECT battery.idbattery, battery.idclient, battery.volt, battery.amper FROM bsi_db.battery WHERE idbattery = ? ;",
				Integer.parseInt(textFirstValue.getText()) };
		MySqlConnection con = new MySqlConnection();
		con.update(con.getConn(), msg);
		return getSqlRslt(con);
	}
}
