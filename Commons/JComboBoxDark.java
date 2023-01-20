package Commons;

import java.awt.Color;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class JComboBoxDark<Object> extends JComboBox<Object>{
	
	public JComboBoxDark() {
	      setOpaque(true);
	      setBackground(Colors.darkestBlue);
	      setForeground(Colors.lightText);
	  }
		
}
