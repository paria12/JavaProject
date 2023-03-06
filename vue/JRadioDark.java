package vue;

import java.awt.Color;

import javax.swing.JRadioButton;

public class JRadioDark extends JRadioButton{
	public JRadioDark(String args) {
		super(args);
		this.setBackground(Colors.backBlue);
		this.setForeground(Colors.lightText);
	}
}
