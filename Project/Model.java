package Project;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.event.ChangeListener;

// TODO: Design and figure this class out
public class Model {
	ArrayList<ChangeListener> listeners;
	ArrayList<Room> rooms;
	ArrayList<Reservation> reservations;

	public Model(ArrayList<Room> rooms) {
		this.rooms = rooms;
		listeners = new ArrayList<ChangeListener>();
		reservations = new ArrayList<Reservation>();
	}

	public ArrayList<Reservation> getReservations(Date day) {
		ArrayList<Reservation> answer = new ArrayList<Reservation>();
		for (Reservation r : reservations)
			if (r.isOccupied(day))
				answer.add(r);
		return answer;
	}

	public ArrayList<Reservation> getReservations(User user) {
		ArrayList<Reservation> answer = new ArrayList<Reservation>();
		for (Reservation r : reservations)
			if (r.getUser().equals(user))
				answer.add(r);
		return answer;
	}

	public ArrayList<Reservation> getReservations(Room room) {
		ArrayList<Reservation> answer = new ArrayList<Reservation>();
		for (Reservation r : reservations)
			if (r.getRoom().equals(room))
				answer.add(r);
		return answer;
	}

	public ArrayList<Room> searchAvalibleReservations(RoomType type,
			Date start, Date end) {
		ArrayList<Room> answer = new ArrayList<Room>();
		for (Room r : rooms) {
			if (r.roomType != type)
				continue;
			ArrayList<Reservation> booked = getReservations(r);
			for (Reservation bookedR : booked)
				if (!bookedR.isOccupied(start, end))
					answer.add(r);
		}
		return answer;

	}

	public static class User {
		private String userName;
		private int userID;

		public User(String userName, int userID) {
			this.userName = userName;
			this.userID = userID;
		}

		public String getUserName() {
			return this.userName;
		}

		public int getUserID() {
			return this.userID;
		}

		public boolean equals(Object other) {
			return ((User) (other)).userID == this.userID;
		}

		public int hashCode() {
			return this.userID;
		}
	}

	public static class Room {
		private int roomNumber;
		private RoomType roomType;

		public Room(int rNumber, RoomType rType) {
			this.roomNumber = rNumber;
			this.roomType = rType;
		}

		public int getRoomNumber() {
			return this.roomNumber;
		}

		public RoomType getRoomType() {
			return this.roomType;
		}
	}
}
