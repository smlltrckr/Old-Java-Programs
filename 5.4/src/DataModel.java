import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class DataModel 
{
	private int width;
	ArrayList<ChangeListener> listedListeners;
	/**
	 * 
	 * @param width
	 */
	public DataModel(int width)
	{
		this.width = width;
		listedListeners = new ArrayList<ChangeListener>();
	}
	/**
	 * 
	 * @param changeListener
	 */
	public void addChangeListener(ChangeListener changeListener)
	{
		listedListeners.add(changeListener);
	}
	/**
	 * 
	 * @return
	 */
	public int getWidth()
	{
		return width;
	}
	/**
	 * 
	 * @return
	 */
	public ChangeListener getChangeListener()
	{
		return listedListeners.get(0);
	}
	/**
	 * 
	 * @param width
	 */
	public void update(int width)
	{
		this.width = width;
		
		for(int i = 0; i < listedListeners.size(); i++)
		{
			listedListeners.get(i).stateChanged(new ChangeEvent(this));
		}
	}
}
