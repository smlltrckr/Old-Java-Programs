import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Sam
 *
 */
public class Eventstorage
{
	private int initialCapacity = 1000;
	private HashMap<String, ArrayList<Event>> eventMap;
	/**
	 * constructs a hashmap to store events
	 */
	public Eventstorage()
	{
		eventMap = new HashMap<String, ArrayList<Event>>(initialCapacity);
	}
	/**
	 * adds an event
	 * @param date
	 * @param event
	 */
	public void addEvent(String date, Event event)
	{
		ArrayList<Event> eventList;
		int i = 0;
		
		if(!eventMap.containsKey(date))
		{
			eventMap.put(date, new ArrayList<Event>());
			eventMap.get(date).add(event);
		}
		else
		{
			eventList = eventMap.get(date);
			for(int a = 0; a < eventList.size(); a++)//
			{
				if(event.compareTo(eventList.get(i)) > 0)
				{
					i++;
				}
			}
			eventList.add(i, event);
		}
	}
	
	/**
	 * Method - returns ArrayList of events on a specified date
	 * @param date
	 * @return
	 */
	public ArrayList<Event> getDayEvents(String date)
	{
		return eventMap.get(date);
	}
	/**
	 * gets the hashmap of all created events
	 * @return
	 */
	public HashMap<String, ArrayList<Event>> getAllEvents()
	{
		return eventMap;
	}
	/**
	 * Method - clears events for a specified date
	 * @param date
	 */
	public void clearEvent(String date)
	{
		eventMap.remove(date);
	}
	
	/**
	 * Method - clears all events on all dates
	 */
	public void clearAllEvents()
	{
		eventMap.clear();
	}
}
