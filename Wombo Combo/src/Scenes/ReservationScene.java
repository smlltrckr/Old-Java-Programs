package Scenes;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Project.Model;
import Project.SceneManager;

public class ReservationScene extends Scene {

	public ReservationScene() {
		super("Welcome");
	}

	public void initComponents() {
		this.setLayout(new FlowLayout());
		final JButton makeButton = new JButton("Make Reservation");
		final JButton viewButton = new JButton("View / Cancel");
		final JButton logOffButton = new JButton("Log Off");

		makeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setScene(new MakeReservationScene());
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
