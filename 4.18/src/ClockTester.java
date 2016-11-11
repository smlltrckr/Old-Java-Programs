import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClockTester
{
	public static void main (String args[])
	{
		final DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss:a");
		final GregorianCalendar gregorianCalendar = new GregorianCalendar();
		
		ClockFrame clockFrame = new ClockFrame();
		final ClockDisplay clockDisplay = new ClockDisplay(dateFormat.format(gregorianCalendar.getTime()));
		final JLabel displayLabel = new JLabel(clockDisplay);
		clockFrame.add(displayLabel);
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				DateFormat currentFormat = new SimpleDateFormat("hh:mm:ss:a");
				GregorianCalendar currGregCal = new GregorianCalendar();
				clockDisplay.updateTime(currentFormat.format(currGregCal.getTime()));
				displayLabel.repaint();
			}
		};
		
		javax.swing.Timer t = new javax.swing.Timer(1000, listener);
		t.start();
		
		clockFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clockFrame.setVisible(true);
	}
}
