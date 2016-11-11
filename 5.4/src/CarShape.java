import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class CarShape implements ChangeListener
{
	private int x;
	private int y;
	private int width;
	private DataModel dataModel;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param dataModel
	 */
	public CarShape(int x, int y, int width, DataModel dataModel)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.dataModel = dataModel;
	}

	/**
	 * 
	 * @param g2D
	 */
	public void draw(Graphics2D g2D) 
	{
		Rectangle2D.Double base = new Rectangle2D.Double(x, y + width / 6, width - 1 , width / 6);
		Ellipse2D.Double frontWheel = new Ellipse2D.Double(x + width / 6, y + width / 3, width / 6, width / 6);
		Ellipse2D.Double backWheel = new Ellipse2D.Double(x + width * 2 / 3, y + width / 3, width / 6, width / 6);
		
		Point2D.Double botFrntWS = new Point2D.Double(x + width / 6, y + width / 6);
		Point2D.Double frntR = new Point2D.Double(x + width / 3, y);
		Point2D.Double rrR = new Point2D.Double(x + width * 2 / 3, y);
		Point2D.Double botrrWS = new Point2D.Double(x + width * 5 / 6, y + width / 6);
		
		Line2D.Double fWindshield = new Line2D.Double(botFrntWS, frntR);
		Line2D.Double roof = new Line2D.Double(frntR, rrR);
		Line2D.Double rWindshield = new Line2D.Double(rrR, botrrWS);
		
		g2D.draw(base);
		g2D.draw(frontWheel);
		g2D.draw(backWheel);
		g2D.draw(fWindshield);
		g2D.draw(roof);
		g2D.draw(rWindshield);
	}

	/**
	 * 
	 * @param xDir
	 * @param yDir
	 */
	public void speed(int xDir, int yDir) 
	{
		x += xDir;
		y += yDir;
		
		if(x > width)
		{
			x -= width;
		}
	}
	/**
	 * 
	 * @param event
	 */
	public void stateChanged(ChangeEvent event)
	{
		width = dataModel.getWidth();
	}
}
