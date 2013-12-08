package bsi;

import java.awt.SystemColor;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBatteryPanel extends JPanel {
	private JTextField txtBatteryVoltage;
	private JTextField txtClientName;
	private JTextField txtBatteryAmper;
	public AddBatteryPanel() {
		super();
		initialize();
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
		this.setSize(390, 290);
		this.setVisible(true);
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
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(10, 243, 108, 36);
		add(btnReturn);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(251, 237, 129, 49);
		add(btnAdd);
		
	}

}
