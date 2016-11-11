import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("serial")
public class Slider extends JSlider implements ChangeListener
{
	private DataModel dataModel;
	
	/**
	 * 
	 * @param dataModel
	 */
	public Slider(DataModel dataModel)
	{
		this.dataModel = dataModel;
		addChangeListener(this);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void stateChanged(ChangeEvent e) 
	{
		dataModel.update(this.getValue() * 4);
	}
	
}
