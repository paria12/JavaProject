package Commons;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class JButtonDark extends JButton {
	
	public JButtonDark(String arg) {
		super(arg);
		this.setBackground(Colors.darkestBlue);
		this.setForeground(Colors.lightText);
		Border line = BorderFactory.createLineBorder(Color.black);
		Border empty = new EmptyBorder(4, 5, 4, 5);
		CompoundBorder border = new CompoundBorder(line, empty);
		this.setBorder(border);
	}
	
	@Override
	public void setEnabled(boolean b) {
		super.setEnabled(b);
		if (b == true) {
			this.setBackground(Colors.darkestBlue);
			this.setForeground(Colors.lightText);
			Border line = BorderFactory.createLineBorder(Color.black);
			Border empty = new EmptyBorder(5, 5, 5, 5);
			CompoundBorder border = new CompoundBorder(line, empty);
			this.setBorder(border);
		} else {
			this.setBackground(Colors.backBlue);
			this.setForeground(Color.gray);
			Border line = BorderFactory.createLineBorder(Color.gray);
			Border empty = new EmptyBorder(5, 5, 5, 5);
			CompoundBorder border = new CompoundBorder(line, empty);
			this.setBorder(border);
		}
	} 
	
}
