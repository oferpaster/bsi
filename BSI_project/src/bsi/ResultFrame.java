package bsi;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ResultFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResultPanel resultPanel;
	private Vector<Vector<Object>> data;
	private Vector<String> cols;
	
	public ResultFrame(Vector<Vector<Object>> data, Vector<String> cols) {
		super();
		this.data = data;
		this.cols = cols;
		initialize();
	}

	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        	JOptionPane.showMessageDialog(this,"setLookAndFeel error: " + e.getMessage() , "setLookAndFeel ERRORE", JOptionPane.ERROR_MESSAGE);
        }
		this.setSize(690, 475);
		this.setContentPane(getResultPanel());
		this.setTitle("BSI Managment Tool - Search Results");
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	public ResultPanel getResultPanel() {
		if(resultPanel == null)
			resultPanel = new ResultPanel(data, cols);
		return resultPanel;
	}
	
	public JButton getBtnClose() {
		return getResultPanel().getBtnClose();
	}

}
