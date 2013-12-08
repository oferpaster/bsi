package bsi;


import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchClientPanel extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFirstValue;
	private JLabel lblFirstValue;
	private JRadioButton rdbtnClientName;
	private JRadioButton rdbtnClientId;
	private JButton btnReturn;

	public SearchClientPanel() {
		super();
		initialize();
		listners();
	}

	

	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        	JOptionPane.showMessageDialog(this,"setLookAndFeel error: " + e.getMessage() , "setLookAndFeel ERRORE", JOptionPane.ERROR_MESSAGE);
        }
		this.setSize(675, 465);
		setBackground(SystemColor.activeCaption);
		setLayout(null);
		
		JLabel lblSearchClient = new JLabel("Search Client");
		lblSearchClient.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSearchClient.setBounds(259, 31, 157, 22);
		add(lblSearchClient);
		
		JLabel lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSearchBy.setBounds(105, 103, 97, 22);
		add(lblSearchBy);
		
		JPanel panelSelection = new JPanel();
		panelSelection.setBackground(SystemColor.activeCaption);
		panelSelection.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Select", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSelection.setBounds(202, 90, 232, 46);
		add(panelSelection);
		panelSelection.setLayout(null);
		
		rdbtnClientId = new JRadioButton("Client ID");
		rdbtnClientId.setBackground(SystemColor.activeCaption);
		buttonGroup.add(rdbtnClientId);
		rdbtnClientId.setBounds(117, 16, 109, 23);
		panelSelection.add(rdbtnClientId);
		
		rdbtnClientName = new JRadioButton("Client name");
		rdbtnClientName.setBackground(SystemColor.activeCaption);
		rdbtnClientName.setSelected(true);
		buttonGroup.add(rdbtnClientName);
		rdbtnClientName.setBounds(6, 16, 109, 23);
		panelSelection.add(rdbtnClientName);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(55, 382, 101, 36);
		add(btnReturn);
		
		JPanel panelSearch = new JPanel();
		panelSearch.setBackground(SystemColor.activeCaption);
		panelSearch.setBorder(new TitledBorder(null, "Insert details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSearch.setBounds(115, 186, 428, 154);
		add(panelSearch);
		panelSearch.setLayout(null);
		
		lblFirstValue = new JLabel("Client name:");
		lblFirstValue.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFirstValue.setBounds(10, 11, 137, 22);
		panelSearch.add(lblFirstValue);
		
		textFirstValue = new JTextField();
		textFirstValue.setBounds(157, 15, 261, 20);
		panelSearch.add(textFirstValue);
		textFirstValue.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(313, 104, 105, 39);
		panelSearch.add(btnSearch);
	}
	
	private void listners() {
		rdbtnClientName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblFirstValue.setText("Client name:");
				lblFirstValue.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblFirstValue.setBounds(10, 11, 137, 22);
			}
		});
		
		rdbtnClientId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblFirstValue.setText("Client ID:");
				lblFirstValue.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblFirstValue.setBounds(10, 11, 137, 22);
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
