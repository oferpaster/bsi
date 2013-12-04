package bsi;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.SystemColor;

public class main_panel extends JPanel {
	public main_panel() {
		setBackground(SystemColor.activeCaption);
		setLayout(null);
		
		JButton btnAddWork = new JButton("Add work");
		btnAddWork.setBounds(325, 63, 133, 25);
		add(btnAddWork);
		
		JButton btnSearchBattery = new JButton("Search battery");
		btnSearchBattery.setBounds(325, 112, 133, 25);
		add(btnSearchBattery);
		
		JButton btnSearchClient = new JButton("Search client");
		btnSearchClient.setBounds(325, 160, 133, 23);
		add(btnSearchClient);
		
		JButton btnReport = new JButton("Report");
		btnReport.setBounds(48, 64, 120, 58);
		add(btnReport);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(48, 165, 108, 36);
		add(btnExit);
	}

}
