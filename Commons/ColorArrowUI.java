package Commons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;



class ArrowButtonDark extends BasicArrowButton{

	public ArrowButtonDark(int direction, Color background, Color shadow, Color darkShadow, Color highlight) {
		super(direction, background, shadow, darkShadow, highlight);
		// TODO Auto-generated constructor stub
	}

	//These local variables replace the private variables used in BasicArrowButton.
	Color background = UIManager.getColor("control");
	Color shadow = UIManager.getColor("controlShadow");
	Color darkShadow = UIManager.getColor("controlDkShadow");
	Color highlight = UIManager.getColor("controlLtHighlight");


	private void paintUnscaledTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled)
	{
		Color oldColor = g.getColor();
		int mid, i, j;

		j = 0;
		size = Math.max(size, 2);
		mid = (size / 2) - 1;

		g.translate(x, y);
		if(isEnabled)
			g.setColor(darkShadow);
		else
			g.setColor(shadow);

		switch(direction)       {
		case NORTH:
			for(i = 0; i < size; i++)      {
				g.drawLine(mid-i, i, mid+i, i);
			}
			if(!isEnabled)  {
				g.setColor(highlight);
				g.drawLine(mid-i+2, i, mid+i, i);
			}
			break;
		case SOUTH:
			if(!isEnabled)  {
				g.translate(1, 1);
				g.setColor(highlight);
				for(i = size-1; i >= 0; i--)   {
					g.drawLine(mid-i, j, mid+i, j);
					j++;
				}
				g.translate(-1, -1);
				g.setColor(shadow);
			}

			j = 0;
			for(i = size-1; i >= 0; i--)   {
				g.drawLine(mid-i, j, mid+i, j);
				j++;
			}
			break;
		case WEST:
			for(i = 0; i < size; i++)      {
				g.drawLine(i, mid-i, i, mid+i);
			}
			if(!isEnabled)  {
				g.setColor(highlight);
				g.drawLine(i, mid-i+2, i, mid+i);
			}
			break;
		case EAST:
			if(!isEnabled)  {
				g.translate(1, 1);
				g.setColor(highlight);
				for(i = size-1; i >= 0; i--)   {
					g.drawLine(j, mid-i, j, mid+i);
					j++;
				}
				g.translate(-1, -1);
				g.setColor(shadow);
			}

			j = 0;
			for(i = size-1; i >= 0; i--)   {
				g.drawLine(j, mid-i, j, mid+i);
				j++;
			}
			break;
		}
		g.translate(-x, -y);
		g.setColor(oldColor);
	}

	private void paintScaledTriangle(Graphics g, double x, double y, double size,
			int direction, boolean isEnabled) {
		size = Math.max(size, 2);
		Path2D.Double path = new Path2D.Double();
		path.moveTo(-size, size / 2);
		path.lineTo(size, size / 2);
		path.lineTo(0, -size / 2);
		path.closePath();
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.PI * (direction - 1) / 4);
		path.transform(affineTransform);

		Graphics2D g2d = (Graphics2D) g;
		double tx = x + size / 2;
		double ty = y + size / 2;
		g2d.translate(tx, ty);
		Color oldColor = g.getColor();
		if (!isEnabled) {
			g2d.translate(1, 0);
			g2d.setColor(highlight);
			g2d.fill(path);
			g2d.translate(-1, 0);
		}
		g2d.setColor(isEnabled ? darkShadow : shadow);
		g2d.fill(path);
		g2d.translate(-tx, -ty);
		g2d.setColor(oldColor);
	}
}