package bsi;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class main_panel extends JPanel {
	
	private JButton btnAddWork;
	private JButton btnSearchBattery;
	private JButton btnSearchClient;
	private JButton btnReport;
	private JButton btnExit;
	
	public main_panel() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(675, 465);
		setBackground(SystemColor.activeCaption);
		setLayout(null);

		btnAddWork = new JButton("Add work");
		btnAddWork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnAddWork.setBounds(521, 64, 133, 25);
		add(btnAddWork);

		btnSearchBattery = new JButton("Search battery");
		btnSearchBattery.setBounds(521, 100, 133, 25);
		add(btnSearchBattery);

		btnSearchClient = new JButton("Search client");
		btnSearchClient.setBounds(521, 136, 133, 23);
		add(btnSearchClient);

		btnReport = new JButton("Report");
		btnReport.setBounds(48, 83, 120, 58);
		add(btnReport);

		btnExit = new JButton("Exit");
		
		btnExit.setBounds(48, 388, 108, 36);
		add(btnExit);
	}
	
	public JButton getBtnExit() {
		return btnExit;
	}

}
