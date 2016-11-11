import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class DataModel 
{
	private ArrayList<String> stringList;
	ArrayList<ChangeListener> listenerList;
	
	public DataModel(ArrayList<String> stringList) 
	{
		this.stringList = stringList;
		listenerList = new ArrayList<ChangeListener>();
	}

	public void addChangeListener(ChangeListener changeListener) 
	{
		listenerList.add(changeListener);
	}
	
	public void addString(String string)
	{
		stringList.add(string);
		for(ChangeListener cL : listenerList)
		{
			cL.stateChanged(new ChangeEvent(this));
		}
	}
	
	public ArrayList<String> getStringList()
	{
		return stringList;
	}
}
