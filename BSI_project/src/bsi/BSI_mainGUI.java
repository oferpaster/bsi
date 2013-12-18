package bsi;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class BSI_mainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private main_panel mainPanel;
	private AddWorkPanel addWorkPanel;
	private SearchBatteryPanel searchBatteryPanel;
	private SearchClientPanel searchClientPanel;
	private ReportPanel reportPanel;
	private AddBatteryFrame addBatteryFrame;
	private ResultFrame resultFrame;

	public BSI_mainGUI() {
		super();
		initialize();
	}

	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        	JOptionPane.showMessageDialog(this,"setLookAndFeel error: " + e.getMessage() , "setLookAndFeel ERRORE", JOptionPane.ERROR_MESSAGE);
        }
		this.setSize(690, 475);
		this.setContentPane(getMainPanel());
		this.setTitle("BSI Managment Tool");
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		getMainPanel();
		Listners();
	}

	private void Listners() {

		mainPanel.getBtnExit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame();

				int result = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to exit the application?",
						"Exit Application", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					closeMainFrame();
				}
			}
		});

		mainPanel.getBtnAddWork().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setContentPane(getAddWorkPanel());
				getAddWorkPanel().getBtnReturn().addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setContentPane(getMainPanel());
								addWorkPanel = null;
							}
						});
			}
		});

		

		mainPanel.getBtnSearchBattery().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(getSearchBatteryPanel());
				getSearchBatteryPanel().getBtnReturn().addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setContentPane(getMainPanel());
								searchBatteryPanel = null;
							}
						});
			}
		});

		
		mainPanel.getBtnSearchClient().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(getSearchClientPanel());
				getSearchClientPanel().getBtnReturn().addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setContentPane(getMainPanel());
								searchClientPanel = null;
							}
						});
			}
		});

		

		mainPanel.getBtnReport().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(getReportPanel());
				getReportPanel().getBtnReturn().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setContentPane(getMainPanel());
						reportPanel = null;
					}
				});
			}
		});

		mainPanel.getBtnAddNewBattery().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disableMainFrame();
				getAddBatteryFrame();
				getAddBatteryFrame().getBtnReturn().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						enableMainFrame();
						getAddBatteryFrame().setVisible(false);
						getAddBatteryFrame().dispose();
						addBatteryFrame = null;
					}
				});
				getAddBatteryFrame().addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						enableMainFrame();
						getAddBatteryFrame().setVisible(false);
						getAddBatteryFrame().dispose();
					}
				});
			}
		});	

	}
	
	protected void disableMainFrame() {
		this.setEnabled(false);
		this.setFocusable(false);
		getMainPanel().setFocusable(false);
	}
	
	protected void enableMainFrame() {
		this.setEnabled(true);
		this.setFocusable(true);
		getMainPanel().setFocusable(true);
	}
	
	protected void closeMainFrame() {
		this.setVisible(false);
		this.dispose();
	}

	public main_panel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new main_panel();
		}
		return mainPanel;
	}

	public AddWorkPanel getAddWorkPanel() {
		if (addWorkPanel == null) {
			addWorkPanel = new AddWorkPanel();
		}
		return addWorkPanel;
	}

	public SearchBatteryPanel getSearchBatteryPanel() {
		if (searchBatteryPanel == null) {
			searchBatteryPanel = new SearchBatteryPanel();
		}
		return searchBatteryPanel;
	}

	public SearchClientPanel getSearchClientPanel() {
		if (searchClientPanel == null) {
			searchClientPanel = new SearchClientPanel();
		}
		return searchClientPanel;
	}

	public ReportPanel getReportPanel() {
		if (reportPanel == null) {
			reportPanel = new ReportPanel();
		}
		return reportPanel;
	}

	public AddBatteryFrame getAddBatteryFrame() {
		if(addBatteryFrame == null){
			addBatteryFrame = new AddBatteryFrame();
		}
		return addBatteryFrame;
	}

}
