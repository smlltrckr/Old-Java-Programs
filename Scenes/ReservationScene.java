package Scenes;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Project.Model;
import Project.SceneManager;

public class ReservationScene extends Scene {

	public ReservationScene(SceneManager m, Model model) {
		super(m, model, "Welcome");
	}

	public void initComponents() {
		this.setLayout(new FlowLayout());
		final JButton makeButton = new JButton("Make Reservation");
		final JButton viewButton = new JButton("View / Cancel");

		makeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setScene(new MakeReservationScene(manager, model));
			}
		});
		
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setScene(new GuestViewScene(manager, model));
			}
		});

		this.add(makeButton);
		this.add(viewButton);
	}

}
