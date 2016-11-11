package Project;

import java.sql.Date;

public class Reservation {

	private Date startDate;
	private Date endDate;
	private Model.Room room;
	private Model.User user;

	public Reservation(Model.Room room, String userID, Date sDate, Date eDate) {
		this.room = room;
		this.startDate = sDate;
		this.endDate = eDate;
		userID = new String();
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

	public Model.User getUser() {
		return this.user;
	}

	public boolean isOccupied(Date start, Date end) {
		// (StartA <= EndB) and (EndA >= StartB)
		// (~(StartA > EndB)) and (~(EndA < StartB))
		// !startA.after(endB) && !endA.before(startB)
		return (!this.startDate.after(end)) && (!this.endDate.before(start));
	}

	public boolean isOccupied(Date day) {
		return this.startDate.before(day) && this.endDate.after(day)
				|| this.startDate.equals(day) || this.endDate.equals(day);
	}

	public int hashCode() {
		return this.user.hashCode();
	}

	public boolean equals(Object other) {
		return this.user.equals(((Reservation) other).user);
	}
}
