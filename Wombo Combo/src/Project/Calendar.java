package Project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Calendar {
	
	private GregorianCalendar cal;
	
	public Calendar() {
		cal = (GregorianCalendar) java.util.Calendar.getInstance();
	}
	
	public String [] getDays() {
		String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		return days;
	}
	
	public String [] getMonths() {
		String[] months = { "January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December"};
		return months;
	}
	
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
	 * Sets the current GregorianCalendar to the specified date
	 * @param date a Date object describing a particular date (MM/dd/YYYY)
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
	
	public int getNumDaysInMonth() {
		return cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	}
	
	public void setYear(int year) {
		cal.set(GregorianCalendar.YEAR, year);
	}
	
	public void setMonth(int month) {
		cal.set(GregorianCalendar.MONTH, month);
	}
	
	public void setDay(int day) {
		cal.set(GregorianCalendar.DAY_OF_MONTH, day);
	}
	
	

}
