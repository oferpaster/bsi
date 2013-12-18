package bsi;

import javax.swing.JPanel;

import java.awt.Component;
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
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class AddWorkPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAddWork;
	private JButton btnAddWorkReturn;
	private JDateChooser dateChooser;
	private JTextField textFieldRefurbished;
	private JTextField txtScrap;
	private JComboBox<String> comboBoxSelectClient;
	private ArrayList<Object> result;
	private JTable table;
	private JScrollPane scrollPane;
	private String clientid;

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
		setBackground(SystemColor.activeCaption);
		this.setSize(675, 465);
		setLayout(null);

		JLabel lblAddWork = new JLabel("Add new work");
		lblAddWork.setBounds(247, 24, 181, 42);
		lblAddWork.setFont(new Font("Tahoma", Font.BOLD, 24));
		add(lblAddWork);

		JLabel lblClientName = new JLabel("Client name:");
		lblClientName.setBounds(103, 101, 115, 22);
		lblClientName.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblClientName);

		btnAddWork = new JButton("Add Work");
		btnAddWork.setEnabled(false);
		btnAddWork.setBounds(418, 382, 129, 49);
		add(btnAddWork);

		btnAddWorkReturn = new JButton("Return");
		btnAddWorkReturn.setBounds(10, 388, 108, 36);
		add(btnAddWorkReturn);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(103, 332, 136, 22);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblDate);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(260, 334, 115, 20);
		add(dateChooser);

		JLabel lblRefurbished = new JLabel("Refurbished:");
		lblRefurbished.setBounds(103, 266, 136, 22);
		lblRefurbished.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblRefurbished);

		textFieldRefurbished = new JTextField();
		textFieldRefurbished.setEditable(false);
		textFieldRefurbished.setBounds(260, 270, 287, 20);
		add(textFieldRefurbished);
		textFieldRefurbished.setColumns(10);

		JLabel lblScrap = new JLabel("Scrap:");
		lblScrap.setBounds(103, 299, 136, 22);
		lblScrap.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblScrap);

		txtScrap = new JTextField();
		txtScrap.setEditable(false);
		txtScrap.setBounds(260, 301, 287, 20);
		add(txtScrap);
		txtScrap.setColumns(10);

		comboBoxSelectClient = new JComboBox<String>();
		comboBoxSelectClient.setBounds(260, 105, 287, 20);
		comboBoxSelectClient.setFont(new Font("Arial", Font.BOLD, 14));
		add(comboBoxSelectClient);

		/*
		 * Object[][] data = { { "Kathy", "Smith", "Snowboarding", new
		 * Integer(5), new Boolean(false) }, { "John", "Doe", "Rowing", new
		 * Integer(3), new Boolean(true) }, { "Sue", "Black", "Knitting", new
		 * Integer(2), new Boolean(false) }, { "Jane", "White", "Speed reading",
		 * new Integer(20), new Boolean(true) }, { "Joe", "Brown", "Pool", new
		 * Integer(10), new Boolean(false) } };
		 */
		scrollPane = new JScrollPane();
		scrollPane.setBounds(257, 144, 290, 109);
		add(scrollPane);

		MySqlConnection con = new MySqlConnection();
		String[] clientList = { "SELECT * FROM bsi_db.client;" };
		con.update(con.getConn(), clientList);
		comboBoxSelectClient.addItem("בחר לקוח");
		result = null;
		result = getSqlRslt(con);
		for (int i = 1; i < result.size(); i++)
			if (i % 2 == 1)
				comboBoxSelectClient.addItem(result.get(i).toString());
	}

	private void Listners() {
		comboBoxSelectClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxSelectClient.getSelectedIndex() != 0) {
					String[] columnNames = { "Battery ID", "Volt", "Amper" };
					int len = 3;
					Vector<String> cols = new Vector<String>(len);
					for (int i = 0; i < len; i++)
						// Note starting at 1
						cols.add(columnNames[i]);
					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
					int selected = getIndex(comboBoxSelectClient
							.getSelectedItem().toString());
					if (selected != -1) {
						clientid = result.get(selected - 1).toString();
						Object[] batteryOfClientList = {
								"SELECT battery.idbattery, battery.volt, battery.amper FROM bsi_db.battery WHERE idclient = ? ;",
								Integer.parseInt(clientid) };
						MySqlConnection con = new MySqlConnection();
						con.update(con.getConn(), batteryOfClientList);
						ArrayList<Object> result2 = getSqlRslt(con);
						ArrayList<Object> rs = (ArrayList<Object>) result2;
						Vector<Object> row = new Vector<Object>(len);
						
						int k = 0;
						for (int j = 0; j < rs.size() / 3; j++) {
							for (int i = 1; i <= len; i++) {
								row.add(rs.get(k));
								k++;
							}
							data.add(row);
							row = new Vector<Object>(len);
						}

						table = new JTable(data, cols) {
							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							public boolean isCellEditable(int data,
									int columnNames) {
								return false;
							}
						};
						table.setSurrendersFocusOnKeystroke(true);
						scrollPane.setViewportView(table);
						table.getSelectionModel().addListSelectionListener(
								new ListSelectionListener() {
									public void valueChanged(
											ListSelectionEvent event) {
										// do some actions here, for example
										// print first column value from
										// selected row
										btnAddWork.setEnabled(true);
										textFieldRefurbished.setEditable(true);
										txtScrap.setEditable(true);
									}
								});
					} else
						System.out.println("No client id");
				} else {
					table = new JTable();
					scrollPane.setViewportView(table);
				}
			}// end listener
		});

		btnAddWork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] updateClientBattery = {
						"UPDATE bsi_db.clientbattery SET refurbished = ? ,scrap = ? WHERE batteryid = ? AND clientid = ? ;",
						Integer.parseInt(textFieldRefurbished.getText()),
						Integer.parseInt(txtScrap.getText()),
						Integer.parseInt(table.getValueAt(
								table.getSelectedRow(), 0).toString()),
						Integer.parseInt(clientid) };
				MySqlConnection con = new MySqlConnection();
				con.update(con.getConn(), updateClientBattery);
				ArrayList<Object> result2 = getSqlRslt(con);
				System.out.println(result2);
			}
		});

	}

	public JButton getBtnAddWork() {
		return btnAddWork;
	}

	public JButton getBtnReturn() {
		return btnAddWorkReturn;
	}

	public ArrayList<Object> getSqlRslt(MySqlConnection con) {
		ArrayList<Object> result = null;
		while ((result = con.getResult()) == null) {
		}
		return result;
	}

	public int getIndex(String itemName) {
		for (int i = 0; i < result.size(); i++) {
			String auction = result.get(i).toString();
			if (itemName.equals(auction)) {
				return i;
			}
		}

		return -1;
	}
}
