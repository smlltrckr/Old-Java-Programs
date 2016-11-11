package Project;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Date;

import Project.Model.Room;
import Scenes.*;

public class Driver {
	public static void main(String[] args) {
		ArrayList<Model.Room> rooms = new ArrayList<Model.Room>();
		populateRoom(rooms);
		Model m;
		try {
			ObjectInputStream in1 = new ObjectInputStream(new FileInputStream("Reservations.data"));
			ArrayList<Reservation> reservations = (ArrayList<Reservation>) in1.readObject();
			in1.close();
			ObjectInputStream in2 = new ObjectInputStream(new FileInputStream("Users.data"));
			ArrayList<String> users = (ArrayList<String>) in2.readObject();
			in2.close();
			m = new Model(rooms, users);
			m.load(reservations);
		} catch (Exception e) {
			ArrayList<String> users = new ArrayList<String>();
			users.add("manager");
			m = new Model(rooms, users);
		}
		SceneManager manager = new SceneManager(m);
		manager.setScene(new SignInScene());
	}

	private static Model populateModel(ArrayList<Model.Room> rooms) {
		ArrayList<String> users = generateUsers();
		Model m = new Model(rooms, users);

		for(int i=0;i<20;i++)
		{
			Room randR = rooms.get((int)(Math.random() * rooms.size()));
			int randU = (int)(Math.random() * users.size());
			Date a = generateRandomDate();
			Date b = generateRandomDate();
	
			Date min = (a.compareTo(b) < 0) ? a : b;
			Date max = (a.compareTo(b) < 0) ? b : a;
			Reservation r = new Reservation(randR, randU, min, max);

			m.addReservation(r);
		}
		return m;
	}

	private static ArrayList<String> generateUsers() {
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<25;i++)
			list.add(new String((char)('A' + i) + ""));
		return list;
	}

	private static void populateRoom(ArrayList<Room> rooms) {
		// 100 -109
		for(int i=100;i<110;i++)
			rooms.add(new Model.Room(i, RoomType.Economy));
		for(int i=200;i<210;i++)
			rooms.add(new Model.Room(i, RoomType.Luxury));
	}
	
	private static Date generateRandomDate()
	{
		GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(2000, 2010);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        return gc.getTime();
	}

	private static int randBetween(int start, int end) {
		return start + (int)Math.round(Math.random() * (end - start));
	}
}