package vue;

import java.awt.Color;

import javax.swing.JButton;

public class JButtonYellow extends JButton {
	
	public JButtonYellow(String arg) {
		super(arg);
		this.setBackground(Color.yellow);
		this.setForeground(Colors.backBlue);
	}
	
	@Override
	public void setEnabled(boolean b) {
		super.setEnabled(b);
		if (b == true) {
			this.setBackground(Color.yellow);
			this.setForeground(Colors.backBlue);
		} else {
			this.setBackground(Colors.darkerYellow);
			this.setForeground(Color.gray);
		}
	}
	
}
