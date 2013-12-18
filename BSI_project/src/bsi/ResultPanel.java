package bsi;

import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ResultPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Vector<Vector<Object>> data;
	private Vector<String> cols;
	private JButton btnClose;
	
	public ResultPanel(Vector<Vector<Object>> data, Vector<String> cols) {
		super();
		this.data = data;
		this.cols = cols;
		initialize();
	}

	private void initialize() {
		setLayout(null);
		setBackground(SystemColor.activeCaption);
		this.setSize(675, 465);
		
		JLabel lblSearchResults = new JLabel("Search Results");
		lblSearchResults.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSearchResults.setBounds(250, 11, 175, 29);
		add(lblSearchResults);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 62, 619, 323);
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
		btnClose.setBounds(10, 388, 108, 36);
		add(btnClose);
		
	}

	public JButton getBtnClose() {
		return btnClose;
	}
}
