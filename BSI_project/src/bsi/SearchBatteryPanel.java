package bsi;

import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class SearchBatteryPanel extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFirstValue;
	private JRadioButton rdbtnBatteryId;
	private JRadioButton rdbtnAmper;
	private JRadioButton rdbtnVolte;
	private JRadioButton rdbtnAmperVolte;
	private ButtonGroup radioGroup;
	private JPanel panelBatteryID;
	private JPanel panelSelection;
	private JLabel lblSecondValue;
	private JTextField textSecondValue;
	private JButton btnSearch;
	private JButton btnReturn;
	private JLabel lblFirstValue;
	

	public SearchBatteryPanel() {
		super();
		initialize();
		listners();
	}
	
	private void initialize() {
		setLayout(null);
		setBackground(SystemColor.activeCaption);
		this.setSize(675, 465);
		
		JLabel lblSearchBattery = new JLabel("Search battery");
		lblSearchBattery.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSearchBattery.setBounds(250, 38, 175, 29);
		add(lblSearchBattery);
		
		panelSelection = new JPanel();
		panelSelection.setBackground(SystemColor.activeCaption);
		panelSelection.setBorder(new TitledBorder(null, "Select", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSelection.setBounds(220, 99, 232, 72);
		add(panelSelection);
		panelSelection.setLayout(null);
		radioGroup = new ButtonGroup();
		
		rdbtnBatteryId = new JRadioButton("Battery ID",true);
		rdbtnBatteryId.setBounds(6, 16, 109, 23);
		panelSelection.add(rdbtnBatteryId);
		rdbtnBatteryId.setBackground(SystemColor.activeCaption);
		radioGroup.add(rdbtnBatteryId);
		
		rdbtnAmper = new JRadioButton("Amper",false);
		rdbtnAmper.setBounds(6, 42, 109, 23);
		panelSelection.add(rdbtnAmper);
		rdbtnAmper.setBackground(SystemColor.activeCaption);
		radioGroup.add(rdbtnAmper);
		
		rdbtnVolte = new JRadioButton("Volte",false);
		rdbtnVolte.setBounds(117, 16, 109, 23);
		panelSelection.add(rdbtnVolte);
		rdbtnVolte.setBackground(SystemColor.activeCaption);
		radioGroup.add(rdbtnVolte);
		
		rdbtnAmperVolte = new JRadioButton("Amper & Volte",false);
		rdbtnAmperVolte.setBounds(117, 42, 109, 23);
		panelSelection.add(rdbtnAmperVolte);
		rdbtnAmperVolte.setBackground(SystemColor.activeCaption);
		radioGroup.add(rdbtnAmperVolte);
		
		JLabel lblSearchBy = new JLabel("Search by:");
		lblSearchBy.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSearchBy.setBounds(115, 112, 101, 22);
		add(lblSearchBy);
		
		panelBatteryID = new JPanel();
		panelBatteryID.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Insert details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		lblSecondValue = new JLabel("Battery Volte:");
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
		
		rdbtnVolte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textSecondValue.setVisible(false);
				lblSecondValue.setVisible(false);
				lblFirstValue.setText("Battery volte:");
				lblFirstValue.setFont(new Font("Tahoma", Font.BOLD, 18));
			}
		});
		
		rdbtnAmperVolte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textSecondValue.setVisible(true);
				lblSecondValue.setText("Battery volte:");
				lblSecondValue.setVisible(true);
				
				lblFirstValue.setText("Battery amper:");
				lblFirstValue.setFont(new Font("Tahoma", Font.BOLD, 18));
			}
		});
		
	}
	
	public JButton getBtnReturn() {
		if(btnReturn == null)
		{
			btnReturn = new JButton("Return");
			btnReturn.setBounds(55, 382, 101, 36);
			add(btnReturn);
		}
		return btnReturn;
	}
}
