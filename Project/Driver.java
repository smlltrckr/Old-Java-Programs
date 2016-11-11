package Project;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Project.Model.Room;
import Scenes.*;

public class Driver {
	public static void main(String[] args) {
		ArrayList<Room> rooms = new ArrayList<Room> ();
		SceneManager manager = new SceneManager();
		SignInScene s = new SignInScene(manager, null);
		Model m = new Model(rooms);
		manager.setScene(new ReservationSelectionScene(manager, m, RoomType.Luxury));
		JOptionPane optionPane = new JOptionPane(
				"The only way to close this dialog is by\n"
						+ "pressing one of the following buttons.\n"
						+ "Do you understand?", JOptionPane.QUESTION_MESSAGE,
				JOptionPane.YES_NO_OPTION);
	}
}
