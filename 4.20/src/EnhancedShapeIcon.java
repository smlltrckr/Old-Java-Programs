import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;


public class EnhancedShapeIcon implements Icon
{
	private int width;
	private int height;
	private MovableShapes shape;
	
	public EnhancedShapeIcon(MovableShapes shape, int width, int height)
	{
		this.shape = shape;
		this.height = height;
		this.width = width;
	}
	
	@Override
	public int getIconHeight() 
	{
		return height;
	}

	@Override
	public int getIconWidth() 
	{
		return width;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) 
	{
		Graphics2D g2D = (Graphics2D) g;
		shape.draw(g2D);
	}
}
