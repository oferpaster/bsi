package bsi;

import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

import com.toedter.calendar.JMonthChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.toedter.calendar.JDateChooser;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JDayChooser;

import javax.swing.JComboBox;

public class ReportPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnReturn;
	private JButton btnDone;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JDateChooser dateChooser;
	private JButton btnShowReport;
	private JRadioButton rdbtnDailyReport;
	private JRadioButton rdbtnMonthlyReport;
	private JRadioButton rdbtnQuarterlyReport;
	private JRadioButton rdbtnAnnualReport;
	private JMonthChooser monthChooserMonthly;
	private JYearChooser yearChooserMonthly;
	private JPanel panelDateSelection;
	private JDayChooser dayChooser;
	private JComboBox<Integer> comboBoxQuater;
	private JYearChooser yearChooserQuater;
	private JPanel panelSelectDate;
	private JYearChooser yearChooserAnnual;
	private MySqlConnection con;
	private String[] columnNames = { "Battery ID", "Client ID", "Client name",
			"Amper", "Volt", "Start date", "End date", "Refurbished", "scrap",
			"status" };
	private ResultFrame resultFrame;
	private Vector<String> cols;

	public ReportPanel() {
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

		int len = columnNames.length;
		cols = new Vector<String>(len);
		for (int i = 0; i < len; i++)
			cols.add(columnNames[i]);

		JLabel lblReports = new JLabel("Reports");
		lblReports.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblReports.setBounds(302, 11, 70, 22);
		add(lblReports);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(10, 388, 108, 36);
		add(btnReturn);

		btnShowReport = new JButton("Show report");
		btnShowReport.setBounds(292, 278, 91, 23);
		add(btnShowReport);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Select report",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(178, 82, 318, 168);
		add(panel);
		panel.setLayout(null);

		rdbtnDailyReport = new JRadioButton("Daily report");
		rdbtnDailyReport.setBounds(6, 16, 139, 23);
		panel.add(rdbtnDailyReport);
		rdbtnDailyReport.setBackground(SystemColor.activeCaption);
		rdbtnDailyReport.setFont(new Font("Arial", Font.BOLD, 14));
		buttonGroup.add(rdbtnDailyReport);

		rdbtnMonthlyReport = new JRadioButton("Monthly report");

		rdbtnMonthlyReport.setBounds(6, 56, 139, 25);
		panel.add(rdbtnMonthlyReport);
		rdbtnMonthlyReport.setBackground(SystemColor.activeCaption);
		rdbtnMonthlyReport.setFont(new Font("Arial", Font.BOLD, 14));
		buttonGroup.add(rdbtnMonthlyReport);

		rdbtnQuarterlyReport = new JRadioButton("Quarterly report");
		rdbtnQuarterlyReport.setBounds(6, 96, 139, 25);
		panel.add(rdbtnQuarterlyReport);
		rdbtnQuarterlyReport.setBackground(SystemColor.activeCaption);
		rdbtnQuarterlyReport.setFont(new Font("Arial", Font.BOLD, 14));
		buttonGroup.add(rdbtnQuarterlyReport);

		rdbtnAnnualReport = new JRadioButton("Annual report");
		rdbtnAnnualReport.setBounds(6, 136, 139, 25);
		panel.add(rdbtnAnnualReport);
		rdbtnAnnualReport.setBackground(SystemColor.activeCaption);
		rdbtnAnnualReport.setFont(new Font("Arial", Font.BOLD, 14));
		buttonGroup.add(rdbtnAnnualReport);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(151, 16, 155, 25);
		dateChooser.setEnabled(false);
		panel.add(dateChooser);

		monthChooserMonthly = new JMonthChooser();
		monthChooserMonthly.setBounds(151, 56, 98, 25);
		monthChooserMonthly.setEnabled(false);
		panel.add(monthChooserMonthly);

		yearChooserMonthly = new JYearChooser();
		yearChooserMonthly.setBounds(259, 56, 47, 25);
		yearChooserMonthly.setEnabled(false);
		panel.add(yearChooserMonthly);

		comboBoxQuater = new JComboBox<Integer>();
		comboBoxQuater.setBounds(151, 96, 98, 25);
		comboBoxQuater.setEnabled(false);
		for (int i = 1; i <= 4; i++)
			comboBoxQuater.addItem(i);

		panel.add(comboBoxQuater);

		yearChooserQuater = new JYearChooser();
		yearChooserQuater.setBounds(259, 96, 47, 25);
		yearChooserQuater.setEnabled(false);
		panel.add(yearChooserQuater);

		yearChooserAnnual = new JYearChooser();
		yearChooserAnnual.setBounds(259, 136, 47, 25);
		yearChooserAnnual.setEnabled(false);
		panel.add(yearChooserAnnual);

	}

	private void Listners() {

		rdbtnDailyReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooser.setEnabled(true);
				monthChooserMonthly.setEnabled(false);
				yearChooserMonthly.setEnabled(false);
				comboBoxQuater.setEnabled(false);
				yearChooserQuater.setEnabled(false);
				yearChooserAnnual.setEnabled(false);

			}
		});

		rdbtnMonthlyReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooser.setEnabled(false);
				monthChooserMonthly.setEnabled(true);
				yearChooserMonthly.setEnabled(true);
				comboBoxQuater.setEnabled(false);
				yearChooserQuater.setEnabled(false);
				yearChooserAnnual.setEnabled(false);
			}
		});

		rdbtnQuarterlyReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooser.setEnabled(false);
				monthChooserMonthly.setEnabled(false);
				yearChooserMonthly.setEnabled(false);
				comboBoxQuater.setEnabled(true);
				yearChooserQuater.setEnabled(true);
				yearChooserAnnual.setEnabled(false);
			}
		});

		rdbtnAnnualReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooser.setEnabled(false);
				monthChooserMonthly.setEnabled(false);
				yearChooserMonthly.setEnabled(false);
				comboBoxQuater.setEnabled(false);
				yearChooserQuater.setEnabled(false);
				yearChooserAnnual.setEnabled(true);
			}
		});

		btnShowReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnDailyReport.isSelected()) {
					Date date = dateChooser.getDate();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String dateAndTime = dateFormat.format(date);
					searchDailyReport(dateAndTime);
				} else if (rdbtnMonthlyReport.isSelected()) {

				} else if (rdbtnQuarterlyReport.isSelected()) {

				} else if (rdbtnAnnualReport.isSelected()) {

				}
			}
		});

	}

	protected void searchDailyReport(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your
		try {
			java.util.Date dateStr = formatter.parse(date);
			java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
			Object[] dailyReport = {
					"SELECT * FROM `bsi_db`.`clientbatteryinfo` WHERE startDate LIKE ?;",
					dateDB };
			
			insertDataAndShow(sendToSql(dailyReport));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public void dailySelected() {
	}

	public JPanel getPanelDateSelection() {
		if (panelDateSelection == null) {
			panelDateSelection = new JPanel();
		}
		return panelDateSelection;

	}

	public JPanel getPanelSelectDate() {
		if (panelSelectDate == null) {
			panelSelectDate = new JPanel();
		}
		return panelSelectDate;
	}

	public ArrayList<Object> sendToSql(Object[] msg) {
		con = new MySqlConnection();
		con.update(con.getConn(), msg);
		return getSqlRslt(con);
	}

	public ArrayList<Object> getSqlRslt(MySqlConnection con) {
		ArrayList<Object> result = null;
		while ((result = con.getResult()) == null) {
		}
		return result;
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
			if (i % (columnNames.length) == 0) {
				data.add(row);
				i = 1;
				row = new Vector<Object>(columnNames.length);
			} else
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
