package bsi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BSI_mainGUI extends JFrame {

	private main_panel mainPanel = new main_panel();
	
	

	public BSI_mainGUI() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(690, 475);
		this.setContentPane(getMainPanel());
		this.setTitle("BSI Managment Tool");
		Listners();
	}
	
	private void Listners(){
		
		mainPanel.getBtnExit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeMainFrame();
			}
		});
	}
	
	protected void closeMainFrame() {
		this.setVisible(false);
		this.dispose();
	}

	public main_panel getMainPanel() {
		return mainPanel;
	}
	
}
