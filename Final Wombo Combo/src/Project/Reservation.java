package Project;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * Reservation stores information relevant to a reservation
 * 
 * @author Wombo Combo
 *
 */
@SuppressWarnings("serial")
public class Reservation implements Serializable{

	private Date startDate;
	private Date endDate;
	private Model.Room room;
	private int userID;
	/**
	 *  Constructs a reservation
	 *  
	 * @param room - Contains room information
	 * @param userID - Users ID
	 * @param sDate - Start date
	 * @param eDate - End date
	 */
	public Reservation(Model.Room room, int userID, Date sDate, Date eDate) {
		this.room = room;
		this.startDate = removeTime(sDate);
		this.endDate = removeTime(eDate);
		this.userID = userID;
	}
	/**
	 * gets the start date of the reservation
	 * 
	 * @return startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * gets the end date of the reservation
	 * 
	 * @return endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * gets the room being reserved
	 * 
	 * @return room
	 */
	public Model.Room getRoom() {
		return room;
	}
	/**
	 * gets the user ID of the one who reserved the room
	 * 
	 * @return userID
	 */
	public int getUser() {
		return this.userID;
	}
	/**
	 * Determines whether if the start date is before or after the end date
	 * 
	 * @param start - start date
	 * @param end - end date
	 * @return true if start date is before end date and end date is after start date
	 * 		   false otherwise
	 */
	public boolean isOccupied(Date start, Date end) {
		// Logic breakdown to check if two number ranges collide:
		// (StartA <= EndB) and (EndA >= StartB)
		// (~(StartA > EndB)) and (~(EndA < StartB))
		// !startA.after(endB) && !endA.before(startB)
		return (!this.startDate.after(end)) && (!this.endDate.before(start));
	}
	/**
	 * Determines if a date is occupied
	 * 
	 * @param day - day to be checked
	 * @return false if day is already occupied
	 * 		   true otherwise
	 */
	public boolean isOccupied(Date day) {
		return this.startDate.before(day) && this.endDate.after(day)
				|| this.equalsDate(startDate, day) || equalsDate(endDate, day);
	}
	/**
	 * Determines if dates are equal
	 * 
	 * @param i - first date
	 * @param o - second date
	 * @return true if equal
	 * 		   false otherwise
	 */
	private boolean equalsDate(Date i, Date o) {
		GregorianCalendar c1 = new GregorianCalendar();
		c1.setTime(i);
		GregorianCalendar c2 = new GregorianCalendar();
		c2.setTime(o);
		return c1.get(GregorianCalendar.DAY_OF_MONTH) == c2.get(GregorianCalendar.DAY_OF_MONTH) &&
				c1.get(GregorianCalendar.YEAR) == c2.get(GregorianCalendar.YEAR) &&
				c1.get(GregorianCalendar.MONTH) == c2.get(GregorianCalendar.MONTH);
	}
	/**
	 * takes a date and sets time parameters of the date to 0
	 * 
	 * @param date - a date
	 * @return date with updated time parameters
	 */
	private Date removeTime(Date date)
	{
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
		calendar.set(java.util.Calendar.MINUTE, 0);
		calendar.set(java.util.Calendar.SECOND, 0);
		calendar.set(java.util.Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	/**
	 * returns user ID associated with the reservation
	 * @return userID
	 */
	public int hashCode() {
		return this.userID;
	}
	/**
	 * Converts and formats reservation information to a string
	 * 
	 * @return formatted string
	 */
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
		return String.format("%s - %s Room: %d Type: %s", formatter.format(this.startDate),
				formatter.format(this.endDate), this.room.getRoomNumber(), this.room.getRoomType().name());
	}
	/**
	 * compares the user ID of one reservation to that of another
	 * 
	 * @return true if userID's are equal
	 * 		   false otherwise
	 */
	public boolean equals(Object other) {
		return this.userID == ((Reservation) other).userID;
	}
}
