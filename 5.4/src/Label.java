import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("serial")
public class Label extends JLabel implements ChangeListener
{
	/**
	 * 
	 * @param picture
	 */
	public Label(Icon picture)
	{
		super(picture);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void stateChanged(ChangeEvent e) 
	{
		repaint();
	}
}
