
/**
 * 
 * @author Sam
 *
 */
public class Event 
{
	private String title;
	private String date;
	private String startTime;
	private String endTime = "     ";
	
	/**
	 * constructs and event
	 */
	public Event()
	{
		
	}
	
	/**
	 * Set the event title
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * Set the event date
	 * @param date
	 */
	public void setDate(String date)
	{
		this.date = date;
	}
	
	/**
	 * Set the events time range
	 * @param timeRange
	 */
	public void setTimeRange(String timeRange)
	{
		String delim = "[ ]";
		String[] startEnd = timeRange.split(delim);
		
		startTime = startEnd[0];
		
		if(startEnd.length == 2)
		{
			endTime = startEnd[1];
		}
	}
	
	/**
	 * get the start time
	 * @return startTime
	 */
	public String getStartTime()
	{
		return startTime;
	}
	
	/**
	 * get the end time
	 * @return endTime
	 */
	public String getEndTime()
	{
		return endTime;
	}
	
	/**
	 * get the event title
	 * @return title
	 */
	public String getEventTitle()
	{
		return title;
	}
	
	/**
	 * get the event date
	 * @return
	 */
	public String getDate()
	{
		return date;
	}
	/**
	 * compares to events by start time
	 * @param event
	 * @return
	 */
	public int compareTo(Event event)
	{
		return startTime.compareTo(event.getStartTime());
	}
}
