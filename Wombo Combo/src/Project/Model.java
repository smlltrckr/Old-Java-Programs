package Project;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

import javax.swing.event.ChangeListener;

public class Model {
	private ArrayList<ChangeListener> listeners;
	private ArrayList<Room> rooms;
	private ArrayList<Reservation> reservations;
	private ArrayList<String> users;

	public Model(ArrayList<Room> rooms, ArrayList<String> users) {
		this.rooms = rooms;
		this.users = users;
		listeners = new ArrayList<ChangeListener>();
		reservations = new ArrayList<Reservation>();
	}

	// ACCESSORS
	public ArrayList<Reservation> getReservations(Date day) {
		ArrayList<Reservation> answer = new ArrayList<Reservation>();
		for (Reservation r : reservations)
			if (r.isOccupied(day))
				answer.add(r);
		return answer;
	}

	public ArrayList<Reservation> getReservations(int userID) {
		ArrayList<Reservation> answer = new ArrayList<Reservation>();
		for (Reservation r : reservations)
			if (r.getUser() == userID)
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
			boolean isBooked = false;
			for (int i = 0; i < booked.size() && !isBooked; i++)
				isBooked = booked.get(i).isOccupied(start, end);
			if (!isBooked)
				answer.add(r);
		}
		return answer;
	}

	// Users
	public boolean userExists(String checkID) {
		for (int i = 0; i < users.size(); i++)
			if (users.get(i).equals(checkID))
				return true;
		return false;
	}

	public int getUserID(String userID) {
		for (int i = 0; i < users.size(); i++)
			if (users.get(i).equals(userID))
				return i;
		throw new NoSuchElementException("NO SUCH USER");
	}
	
	public boolean isManager(int userID) {
		return userID == 0;
	}

	public String getUserName(int userID) {
		return this.users.get(userID);
	}

	public void addUser(String userName) {
		this.users.add(users.size(), userName);
	}

	// MUTATORS
	public void cancelReservation(Reservation r) {
		this.reservations.remove(r);
	}

	public void addReservation(Reservation r) {
		this.reservations.add(r);
	}

	// MVC
	public void attach(ChangeListener l) {
		this.listeners.add(l);
	}

	public void detach(ChangeListener l) {
		this.listeners.remove(l);
	}

	// SERIALIZATION
	public void save() throws FileNotFoundException, IOException {
		ObjectOutputStream out1 = new ObjectOutputStream(new FileOutputStream(
				"Reservations.data"));
		out1.writeObject(reservations);
		out1.close();
		ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream(
				"Users.data"));
		out2.writeObject(reservations);
		out2.close();
	}

	public void load(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

	public static class Room implements Serializable {
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

		public String toString() {
			return String.format("Room#: %s    %s Suite", this.roomNumber,
					this.roomType.name());
		}
	}
}
