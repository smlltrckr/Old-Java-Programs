package Project;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

/**
 * Model class used to store and modify user and reservation data.
 * @author Team Wombo Combo
 *
 */
public class Model {
	private ArrayList<Room> rooms;
	private ArrayList<Reservation> reservations;
	private ArrayList<String> users;

	/**
	 * Constructs the model with the list of hotel rooms and list of registered users.
	 * @param rooms list of hotel rooms
	 * @param users list of registered users
	 */
	public Model(ArrayList<Room> rooms, ArrayList<String> users) {
		this.rooms = rooms;
		this.users = users;
		reservations = new ArrayList<Reservation>();
	}

	// ACCESSORS
	/**
	 * Gets a list of reservations made on a specified date
	 * @param day date object describing a specific date
	 * @return list of reservations
	 */
	public ArrayList<Reservation> getReservations(Date day) {
		ArrayList<Reservation> answer = new ArrayList<Reservation>();
		for (Reservation r : reservations)
			if (r.isOccupied(day))
				answer.add(r);
		return answer;
	}

	/**
	 * Gets a list of reservations made by a specified user
	 * @param userID a specific user id
	 * @return list of reservations
	 */
	public ArrayList<Reservation> getReservations(int userID) {
		ArrayList<Reservation> answer = new ArrayList<Reservation>();
		for (Reservation r : reservations)
			if (r.getUser() == userID)
				answer.add(r);
		return answer;
	}

	/**
	 * Gets a list of reservations in a specified room
	 * @param room a hotel room
	 * @return list of reservations
	 */
	public ArrayList<Reservation> getReservations(Room room) {
		ArrayList<Reservation> answer = new ArrayList<Reservation>();
		for (Reservation r : reservations)
			if (r.getRoom().equals(room))
				answer.add(r);
		return answer;
	}

	/**
	 * Gets a list of available rooms of the specified type in the specified date range
	 * @param type the room type
	 * @param start the start date
	 * @param end the end date
	 * @return a list of available rooms
	 */
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
	
	/**
	 * Gets a list of available rooms on a specified date
	 * @param date the date
	 * @return a list of available rooms
	 */
	public ArrayList<Room> searchAvalibleReservations(Date date) {
		ArrayList<Room> answer = new ArrayList<Room>();
		for (Room r : rooms) {
			ArrayList<Reservation> booked = getReservations(r);
			boolean isBooked = false;
			for (int i = 0; i < booked.size() && !isBooked; i++)
				isBooked = booked.get(i).isOccupied(date);
			if (!isBooked)
				answer.add(r);
		}
		return answer;
	}

	// Users
	/**
	 * Checks whether a specified user exists
	 * @param checkID the user id to check
	 * @return whether the user exists
	 */
	public boolean userExists(int checkID) {
		return this.users.size() > checkID;
	}
	
	/**
	 * Checks whether a specified user exists
	 * @param checkID the user id to check
	 * @return whether the user exists
	 */
	public boolean userExists(String checkID) {
		for(String id : users)
			if(id.equals(checkID))
				return true;
		return false;
	}

	/**
	 * Gets the user id of a specified user
	 * @param userID a specified user
	 * @return the user id
	 */
	public int getUserID(String userID) {
		for (int i = 0; i < users.size(); i++)
			if (users.get(i).equals(userID))
				return i;
		throw new NoSuchElementException("NO SUCH USER");
	}
	
	/**
	 * Checks whether the specified user is a Manager
	 * @param userID the user id
	 * @return whether the specified user is a Manager
	 */
	public boolean isManager(int userID) {
		return userID == 0;
	}

	/**
	 * Gets the username of a specified user
	 * @param userID the user id of a user
	 * @return the username
	 */
	public String getUserName(int userID) {
		return this.users.get(userID);
	}

	/**
	 * Adds a user to the System
	 * @param userName the desired username
	 */
	public void addUser(String userName) {
		this.users.add(users.size(), userName);
	}

	// MUTATORS
	/**
	 * Cancel a specified reservation
	 * @param r the reservation to cancel
	 */
	public void cancelReservation(Reservation r) {
		this.reservations.remove(r);
	}

	/**
	 * Adds a reservation to the System
	 * @param r the reservation to add
	 */
	public void addReservation(Reservation r) {
		this.reservations.add(r);
	}

	// SERIALIZATION
	/**
	 * Writes lists of reservations and users to file for loading.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void save() throws FileNotFoundException, IOException {
		ObjectOutputStream out1 = new ObjectOutputStream(new FileOutputStream(
				"Reservations.data"));
		out1.writeObject(reservations);
		out1.close();
		ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream(
				"Users.data"));
		out2.writeObject(users);
		out2.close();
	}

	/**
	 * Loads a list of reservations to the System
	 * @param reservations the list of reservations to load
	 */
	public void load(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

	/**
	 * Node class representing a hotel room.
	 * @author Team Wombo Combo
	 *
	 */
	@SuppressWarnings("serial")
	public static class Room implements Serializable {
		private int roomNumber;
		private RoomType roomType;

		/**
		 * Constructs a hotel room with room number and type.
		 * @param rNumber the room number
		 * @param rType the room type
		 */
		public Room(int rNumber, RoomType rType) {
			this.roomNumber = rNumber;
			this.roomType = rType;
		}

		/**
		 * Gets the room number.
		 * @return the room number
		 */
		public int getRoomNumber() {
			return this.roomNumber;
		}

		/**
		 * Gets the room type.
		 * @return the room type
		 */
		public RoomType getRoomType() {
			return this.roomType;
		}

		/**
		 * Gets the String representation of this room.
		 * @return string representation of this room
		 */
		public String toString() {
			return String.format("Room#: %s    %s Suite", this.roomNumber,
					this.roomType.name());
		}
	}
}
