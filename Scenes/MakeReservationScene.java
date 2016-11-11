package Scenes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Project.Model;
import Project.RoomType;
import Project.SceneManager;

public class MakeReservationScene extends Scene {

	public MakeReservationScene(SceneManager manager, Model model) {
		super(manager, model, "Reservation");
	}

	public void initComponents() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		this.setLayout(layout);

		c.gridx = 0;
		c.gridy = 0;
		this.add(new JLabel("Check-In:"), c);
		c.gridx = 0;
		c.gridy = 1;
		this.add(new JLabel("Check-Out:"), c);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		this.add(new JTextField("   [MM/DD/YYYY]"), c);
		c.gridx = 1;
		c.gridy = 1;
		this.add(new JTextField("   [MM/DD/YYYY]"), c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		this.add(new JLabel("Room Type: "), c);

		// JButtons
		JButton reserveEconomy = new JButton("   $100   ");
		c.gridx = 1;
		c.gridy = 2;
		this.add(reserveEconomy, c);
		JButton reserveLuxury = new JButton("   $200   ");
		c.gridx = 2;
		c.gridy = 2;
		this.add(reserveLuxury, c);

		// Action Listeners
		reserveEconomy.addActionListener(actionListenerFactory(RoomType.Economy));
		reserveLuxury.addActionListener(actionListenerFactory(RoomType.Luxury));
	}

	//TODO: Will soon require Dates as a parameter
	private ActionListener actionListenerFactory(final RoomType type) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent evnt) {
				manager.setScene(new ReservationSelectionScene(manager, model, type));
			}
		};
	}

}
