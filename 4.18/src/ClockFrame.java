import javax.swing.JFrame;


@SuppressWarnings("serial")
public class ClockFrame extends JFrame
{
	private final int frameWidth = 500;
	private final int frameHeight = 500;
	
	/**
	 * ClockFrame - Constructs a frame for the ClockDisplay
	 */
	public ClockFrame()
	{
		setSize(frameWidth, frameHeight);
	}
}
