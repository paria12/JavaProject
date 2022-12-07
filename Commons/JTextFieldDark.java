package Commons;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class JTextFieldDark extends JTextField {
	
	public JTextFieldDark() {
		super();
		this.setForeground(Colors.lightText);
		this.setBackground(Colors.darkestBlue);
		this.setBorder(new LineBorder(Color.black));
		this.setCaretColor(Colors.lightText);
	}
	
}
