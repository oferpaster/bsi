package bsi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BSI_mainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private main_panel mainPanel;
	private AddWorkPanel addWorkPanel;
	private SearchBatteryPanel searchBatteryPanel;
	private SearchClientPanel searchClientPanel;

	private MySqlConnection con;
	

	public BSI_mainGUI() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(690, 475);
		this.setContentPane(getMainPanel());
		this.setTitle("BSI Managment Tool");
		getMainPanel();
		Listners();
	}
	
	private void Listners(){
		
		mainPanel.getBtnExit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame();

				int result = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to exit the application?",
						"Exit Application", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION)
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				closeMainFrame();
			}
		});
		
		mainPanel.getBtnAddWork().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setContentPane(getAddWorkPanel());
			}
		});
		
		getAddWorkPanel().getBtnReturn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(getMainPanel());
			}
		});
		
		mainPanel.getBtnSearchBattery().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(getSearchBatteryPanel());
			}
		});
		
		getSearchBatteryPanel().getBtnReturn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(getMainPanel());
			}
		});
		
		mainPanel.getBtnSearchClient().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(getSearchClientPanel());
			}
		});
		
		getSearchClientPanel().getBtnReturn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(getMainPanel());
			}
		});
		
		
	}
	
	protected void closeMainFrame() {
		this.setVisible(false);
		this.dispose();
	}

	public main_panel getMainPanel() {
		if(mainPanel == null){
			mainPanel = new main_panel();
		}
		return mainPanel;
	}
	
	public AddWorkPanel getAddWorkPanel() {
		if(addWorkPanel == null){
			addWorkPanel = new AddWorkPanel();
		}
		return addWorkPanel;
	}
	
	public SearchBatteryPanel getSearchBatteryPanel() {
		if(searchBatteryPanel == null){
			searchBatteryPanel = new SearchBatteryPanel();
		}
		return searchBatteryPanel;
	}

	public SearchClientPanel getSearchClientPanel() {
		if(searchClientPanel == null){
			searchClientPanel = new SearchClientPanel();
		}
		return searchClientPanel;
	}

	
}
