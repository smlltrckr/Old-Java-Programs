package Project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Reservation {

	private Date startDate;
	private Date endDate;
	private Model.Room room;
	private int userID;

	public Reservation(Model.Room room, int userID, Date sDate, Date eDate) {
		this.room = room;
		this.startDate = removeTime(sDate);
		this.endDate = removeTime(eDate);
		this.userID = userID;
		// Remove time from the initilization
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Model.Room getRoom() {
		return room;
	}

	public int getUser() {
		return this.userID;
	}

	public boolean isOccupied(Date start, Date end) {
		// Logic breakdown to check if two number ranges collide:
		// (StartA <= EndB) and (EndA >= StartB)
		// (~(StartA > EndB)) and (~(EndA < StartB))
		// !startA.after(endB) && !endA.before(startB)
		return (!this.startDate.after(end)) && (!this.endDate.before(start));
	}

	public boolean isOccupied(Date day) {
		return this.startDate.before(day) && this.endDate.after(day)
				|| this.equalsDate(startDate, day) || equalsDate(endDate, day);
	}
	
	private boolean equalsDate(Date i, Date o) {
		GregorianCalendar c1 = new GregorianCalendar();
		c1.setTime(i);
		GregorianCalendar c2 = new GregorianCalendar();
		c2.setTime(o);
		return c1.get(GregorianCalendar.DAY_OF_MONTH) == c2.get(GregorianCalendar.DAY_OF_MONTH) &&
				c1.get(GregorianCalendar.YEAR) == c2.get(GregorianCalendar.YEAR) &&
				c1.get(GregorianCalendar.MONTH) == c2.get(GregorianCalendar.MONTH);
	}
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
	
	public int hashCode() {
		return this.userID;
	}
	
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
		return String.format("%s - %s Room: %d Type: %s", formatter.format(this.startDate),
				formatter.format(this.endDate), this.room.getRoomNumber(), this.room.getRoomType().name());
	}

	public boolean equals(Object other) {
		return this.userID == ((Reservation) other).userID;
	}
}
