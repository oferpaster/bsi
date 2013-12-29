package bsi;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;

public class main_panel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnExit;
	private JButton btnAddWork;
	private JButton btnSearchBattery;
	private JButton btnReport;
	private JLabel lblBsiManagmentTool;
	private JButton btnAddNewBattery;
	
	public main_panel() {
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
		this.setSize(675, 465);
		setBackground(SystemColor.activeCaption);
		setLayout(null);

		btnAddWork = new JButton("Add work");
		btnAddWork.setBounds(521, 64, 113, 36);
		add(btnAddWork);

		btnSearchBattery = new JButton("Search battery");
		btnSearchBattery.setBounds(521, 118, 113, 36);
		add(btnSearchBattery);

		btnReport = new JButton("Report");
		btnReport.setBounds(48, 83, 120, 58);
		add(btnReport);

		btnExit = new JButton("Exit");
		btnExit.setBounds(10, 388, 113, 36);
		add(btnExit);

		lblBsiManagmentTool = new JLabel("BSI Managment Tool");
		lblBsiManagmentTool.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblBsiManagmentTool.setBounds(212, 11, 250, 29);
		add(lblBsiManagmentTool);
		
		btnAddNewBattery = new JButton("Add new Battery");
		btnAddNewBattery.setBounds(521, 172, 113, 36);
		add(btnAddNewBattery);
		
	}

	public JButton getBtnExit() {
		return btnExit;
	}

	public JButton getBtnAddWork() {
		return btnAddWork;
	}

	public JButton getBtnSearchBattery() {
		return btnSearchBattery;
	}

	public JButton getBtnReport() {
		return btnReport;
	}

	public JButton getBtnAddNewBattery() {
		return btnAddNewBattery;
	}

}
