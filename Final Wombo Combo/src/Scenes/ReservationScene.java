package Scenes;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/**
 * Designs the reservation scene
 * 
 * @author Wombo Combo
 *
 */
@SuppressWarnings("serial")
public class ReservationScene extends Scene {
	/**
	 * Constructor for the ReservationScene
	 */
	public ReservationScene() {
		super("Welcome");
	}
	/**
	 * Adds the components and listeners to ReservationScene
	 */
	public void initComponents() {
		this.setLayout(new FlowLayout());
		final JButton makeButton = new JButton("Make Reservation");
		final JButton viewButton = new JButton("View / Cancel");
		final JButton logOffButton = new JButton("Log Off");

		makeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setScene(new ReservationSelectionScene());
			}
		});
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setScene(new GuestViewScene());
			}
		});
		logOffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setScene(new SignInScene());
			}
		});

		this.add(makeButton);
		this.add(viewButton);
		this.add(logOffButton);
	}

}
