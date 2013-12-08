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

public class ReportPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnDailyReport;
	private JButton btnMonthlyReport;
	private JButton btnAnnualReport;
	private JButton btnQuarterlyReport;
	private JButton btnReturn;
	private JInternalFrame internalFrameSelectDate;
	private JMonthChooser monthChooser;
	private JButton btnDone;

	public ReportPanel() {
		super();
		initialize();
		Listners();
	}

	private void initialize() {
		
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        	JOptionPane.showMessageDialog(this,"setLookAndFeel error: " + e.getMessage() , "setLookAndFeel ERRORE", JOptionPane.ERROR_MESSAGE);
        }
		setLayout(null);
		setBackground(SystemColor.activeCaption);
		this.setSize(675, 465);		
		
		JLabel lblReports = new JLabel("Reports");
		lblReports.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblReports.setBounds(302, 11, 70, 22);
		add(lblReports);
		
		btnDailyReport = new JButton("Daily report");
		btnDailyReport.setBounds(10, 97, 133, 33);
		add(btnDailyReport);
		
		btnMonthlyReport = new JButton("Monthly Report");
		btnMonthlyReport.setBounds(184, 97, 133, 33);
		add(btnMonthlyReport);
		
		btnAnnualReport = new JButton("Annual Report");
		btnAnnualReport.setBounds(358, 97, 133, 33);
		add(btnAnnualReport);
		
		btnQuarterlyReport = new JButton("Quarterly Report");
		btnQuarterlyReport.setBounds(532, 97, 133, 33);
		add(btnQuarterlyReport);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(10, 388, 108, 36);
		add(btnReturn);
		
	}
	
	
	private void Listners() {
		
		btnMonthlyReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				internalFrameSelectDate = new JInternalFrame("Select date");
				internalFrameSelectDate.setClosable(true);
				internalFrameSelectDate.setBounds(281, 171, 141, 134);
				add(internalFrameSelectDate);
				internalFrameSelectDate.getContentPane().setLayout(null);
				internalFrameSelectDate.setVisible(true);
				
				monthChooser = new JMonthChooser();
				monthChooser.setBounds(10, 11, 98, 20);
				internalFrameSelectDate.getContentPane().add(monthChooser);
				
				btnDone = new JButton("Done");
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						internalFrameSelectDate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						internalFrameSelectDate.setVisible(false);
						internalFrameSelectDate.dispose();
					}
				});
				btnDone.setBounds(19, 71, 89, 23);
				internalFrameSelectDate.getContentPane().add(btnDone);
			}
		});
		
		
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}
}
