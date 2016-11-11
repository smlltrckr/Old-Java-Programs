import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.Icon;


public class ClockDisplay implements Icon
{
	final double hourHandLength = 50;
	final double minHandLength = 100;
	final double secHandLength = 125;
	
	private double hour;
	private double minute;
	private double second;
	
	/**
	 * Constructs the clocks display
	 * 
	 * @param currTime - String of the current Time hh:mm:ss
	 */
	public ClockDisplay(String currTime)
	{
		String[] time = currTime.split(":");
		hour = Double.parseDouble(time[0]);
		minute = Double.parseDouble(time[1]);
		second = Double.parseDouble(time[2]);
	}
	
	/**
	 * paints the clock display
	 * 
	 * @param arg0  component
	 * @param g		graphics object
	 * @param arg2	int
	 * @param arg3	int
	 */
	public void paintIcon(Component arg0, Graphics g, int arg2, int arg3) {
		Graphics2D g2D = (Graphics2D) g;
		Ellipse2D.Double circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(250, 250, 400, 400);
		//g2D.setColor(Color.BLACK);
		g2D.draw(circle);
		
		Line2D.Double hourHand = new Line2D.Double(250, 250, 250 + hourHandLength * Math.sin(Math.PI * (double) hour / 6), 
				 250 - hourHandLength * Math.cos(Math.PI * (double) hour / 6));
		
		Line2D.Double minHand = new Line2D.Double(250, 250, 250 + minHandLength * Math.sin(Math.PI * (double) minute / 30), 
				 250 - minHandLength * Math.cos(Math.PI * (double) minute / 30));
		
		Line2D.Double secHand = new Line2D.Double(250, 250, 250 + secHandLength * Math.sin(Math.PI * (double) second / 30), 
				 250 - secHandLength * Math.cos(Math.PI * (double) second / 30));
		
		g2D.draw(hourHand);
		g2D.draw(minHand);
		g2D.draw(secHand);
		
	}
	/**
	 * paints the clock display
	 * @param g
	 
	public void paintDisplay(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		Ellipse2D.Double circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(250, 250, 400, 400);
		g2D.setColor(Color.BLACK);
		g2D.draw(circle);
		
		Line2D.Double hourHand = new Line2D.Double(250, 250, 250 + hourHandLength * Math.sin(Math.PI * (double) hour / 6), 
				 250 - hourHandLength * Math.cos(Math.PI * (double) hour / 6));
		
		Line2D.Double minHand = new Line2D.Double(250, 250, 250 + minHandLength * Math.sin(Math.PI * (double) minute / 30), 
				 250 - minHandLength * Math.cos(Math.PI * (double) minute / 30));
		
		Line2D.Double secHand = new Line2D.Double(250, 250, 250 + secHandLength * Math.sin(Math.PI * (double) second / 30), 
				 250 - secHandLength * Math.cos(Math.PI * (double) second / 30));
		
		g2D.draw(hourHand);
		g2D.draw(minHand);
		g2D.draw(secHand);
	}
	*/
	/**
	 * updates the time
	 * @param currTime
	 */
	public void updateTime(String currTime)
	{
		String[] time = currTime.split(":");
		hour = Double.parseDouble(time[0]);
		minute = Double.parseDouble(time[1]);
		second = Double.parseDouble(time[2]);
	}

	public int getIconHeight() {
		return 0;
	}

	public int getIconWidth() {
		return 0;
	}

	
}
