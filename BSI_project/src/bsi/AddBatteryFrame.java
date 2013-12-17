package bsi;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class AddBatteryFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AddBatteryPanel addBatteryPanel = null;
	
	public AddBatteryFrame() {
		super();
		
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(this,
					"setLookAndFeel error: " + e.getMessage(),
					"setLookAndFeel ERRORE", JOptionPane.ERROR_MESSAGE);
		}
		setContentPane(getAddBatteryPanel());
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(410, 310);
		this.setVisible(true);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		getContentPane().setBackground(SystemColor.activeCaption);
		this.requestFocus();
	}
	
	public AddBatteryPanel getAddBatteryPanel() {
		if(addBatteryPanel == null)
		{
			addBatteryPanel = new AddBatteryPanel();
		}
		return addBatteryPanel;
	}
	
	public JButton getBtnReturn() {
		return getAddBatteryPanel().getBtnReturn();
	}

}
