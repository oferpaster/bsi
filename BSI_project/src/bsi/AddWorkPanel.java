package bsi;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

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
	
	public AddWorkPanel() {
		super();
		initialize();
		
	}

	private void initialize() {
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
		btnAddWorkReturn.setBounds(37, 379, 100, 36);
		add(btnAddWorkReturn);
	}
	
	public JButton getBtnAddWork() {
		return btnAddWork;
	}

	public JButton getBtnReturn() {
		return btnAddWorkReturn;
	}
}
