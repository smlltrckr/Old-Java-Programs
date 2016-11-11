import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;


public class ShapeAlter implements Icon
{
	private int width;
	private int height;
	private CarShape car;
	
	/**
	 * 
	 * @param car
	 * @param width
	 * @param height
	 */
	public ShapeAlter(CarShape car, int width, int height)
	{
		this.car = car;
		this.width = width;
		this.height = height;
	}
	/**
	 * @return
	 */
	public int getIconHeight() 
	{
		return height;
	}

	/**
	 * @return
	 */
	public int getIconWidth() 
	{
		return width;
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public void paintIcon(Component arg0, Graphics arg1, int arg2, int arg3) 
	{
		Graphics2D g2D = (Graphics2D) arg1;
		car.draw(g2D);
	}

}
