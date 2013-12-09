package bsi;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class AddBatteryFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AddBatteryPanel addBatteryPanel;
	
	public AddBatteryFrame() {
		super();
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		initialize();
	}

	private void initialize() {
		this.setSize(410, 310);
		this.setVisible(true);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		getContentPane().setBackground(SystemColor.activeCaption);
		setContentPane(getAddBatteryPanel());
		this.requestFocus();
	}
	
	public AddBatteryPanel getAddBatteryPanel() {
		if(addBatteryPanel == null)
		{
			addBatteryPanel = new AddBatteryPanel();
		}
		return addBatteryPanel;
	}

}
