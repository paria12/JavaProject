package Commons;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

public class JSpinnerDark extends JSpinner{
	public JSpinnerDark(int value, int minimum, int maximum, int stepSize) {
		super();
		this.changeModel(value, minimum, maximum, stepSize);	
	}
	
	public void changeModel(int value, int minimum, int maximum, int stepSize) {
		if (maximum < minimum) {
			this.setModel(new SpinnerNumberModel(value, minimum, null, stepSize));
		} else {
			this.setModel(new SpinnerNumberModel(value, minimum, maximum, stepSize));
		}
		this.setBorder(new LineBorder(Color.BLACK));
		((JSpinner.NumberEditor)this.getEditor()).getTextField().setBackground(Colors.darkestBlue);
		((JSpinner.NumberEditor)this.getEditor()).getTextField().setForeground(Colors.lightText);
	}
}
