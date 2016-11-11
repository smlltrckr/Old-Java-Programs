package Project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Utility class used to generate Calendar information using Java GregorianCalendar.
 * @author Team Wombo Combo
 *
 */
public class Calendar {
	
	private GregorianCalendar cal;
	
	/**
	 * Constructs a Calendar using a GregorianCalendar initialized with current system time.
	 */
	public Calendar() {
		cal = (GregorianCalendar) java.util.Calendar.getInstance();
	}
	
	/**
	 * Gets an array of the String representation of Calendar days.
	 * @return String array of Calendar days
	 */
	public String [] getDays() {
		String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		return days;
	}
	
	/**
	 * Gets an array of the String representation of Calendar months.
	 * @return String array of Calendar months
	 */
	public String [] getMonths() {
		String[] months = { "January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December"};
		return months;
	}
	
	/**
	 * Gets the int representation of a specified month.
	 * @param month the specified month
	 * @return int representing the specified month or -1 if invalid
	 */
	public int getMonthID(String month) {
		int id = 0;
		String [] months = getMonths();
		for(String m : months)
		{
			if(m.equals(month))
				return id;
			else
				id++;
		}

		return -1;
	}
	
	/**
	 * Gets the int representation of the current month.
	 * @return int representation of the current month
	 */
	public int getMonth() {
		return cal.get(GregorianCalendar.MONTH);
	}
	
	/**
	 * Gets the current calendar year.
	 * @return the current year
	 */
	public int getYear(){
		return cal.get(GregorianCalendar.YEAR);
	}
	
	/**
	 * Sets the current Calendar to the described date
	 * @param year the desired year
	 * @param monthID the desired month
	 * @param date the desired day of the month
	 */
	public void setCalendar(int year, int monthID, int date){
		cal.set(year, monthID, date);
	}
	
	/**
	 * Gets the current calendar date.
	 * @return the current date
	 */
	public Date getDate() {
		return cal.getTime();
	}
	
	/**
	 * Gets the current day of the month.
	 * @return the day of the month
	 */
	public int getDayNum() {
		return cal.get(GregorianCalendar.DATE);
	}
	
	/**
	 * Prints the specified date.
	 * @param date A Date object describing a particular date (MM/dd/YYYY)
	 */
	public void printDate(Date date){
		SimpleDateFormat format = new SimpleDateFormat("EEE, MMM dd yyyy");
		String str = format.format(date);
		System.out.println(str);
	}
	
	/**
	 * Helper method that calculates the first day of the current month using a GregorianCalendar
	 * @return a value used to describe the first day of the month
	 */
	public int getFirstDayOfMonth() {
		GregorianCalendar c = cal;
		c.set(GregorianCalendar.DAY_OF_MONTH, 1);
		return c.get(GregorianCalendar.DAY_OF_WEEK);
	}
	
	/**
	 * Gets the number of days in the current month.
	 * @return the number of days in the current month
	 */
	public int getNumDaysInMonth() {
		return cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	}
	
	/**
	 * Sets the year to the specified year.
	 * @param year the desired year
	 */
	public void setYear(int year) {
		cal.set(GregorianCalendar.YEAR, year);
	}
	
	/**
	 * Sets the month to the specified month.
	 * @param month the desired month
	 */
	public void setMonth(int month) {
		cal.set(GregorianCalendar.MONTH, month);
	}
	
	/**
	 * Sets the day of the month to the specified day.
	 * @param day the desired day of the month
	 */
	public void setDay(int day) {
		cal.set(GregorianCalendar.DAY_OF_MONTH, day);
	}
	
	

}
