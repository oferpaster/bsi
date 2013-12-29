package bsi;

import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;

public class ResultPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Vector<Vector<Object>> data;
	private Vector<String> cols;
	private JButton btnClose;
	private JButton btnPrint;
	
	public ResultPanel(Vector<Vector<Object>> data, Vector<String> cols) {
		super();
		this.data = data;
		this.cols = cols;
		initialize();
	}

	private void initialize() {
		setLayout(null);
		setBackground(SystemColor.activeCaption);
		this.setSize(785, 575);
		
		JLabel lblSearchResults = new JLabel("Search Results");
		lblSearchResults.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSearchResults.setBounds(250, 11, 175, 29);
		add(lblSearchResults);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 765, 408);
		add(scrollPane);
		
		table = new JTable(data, cols) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int data,
					int columnNames) {
				return false;
			}
		};
		table.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(table);
		scrollPane.setViewportView(table);
		
		btnClose = new JButton("Close");
		btnClose.setBounds(10, 504, 108, 36);
		add(btnClose);
		
		btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						table.print();
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnPrint.setBounds(128, 511, 89, 23);
		add(btnPrint);
		
	}

	public JButton getBtnClose() {
		return btnClose;
	}
}
