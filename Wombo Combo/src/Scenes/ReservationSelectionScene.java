package Scenes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Project.Model;
import Project.Model.Room;
import Project.Reservation;
import Project.RoomType;
import Project.SceneManager;

public class ReservationSelectionScene extends Scene {

	private Date start, end;
	private RoomType room;
	private DefaultListModel<Room> data;

	public ReservationSelectionScene(Date start, Date end, RoomType roomType) {
		super("Select Reservation");
		this.start = start;
		this.end = end;
		this.room = roomType;
	}

	public void initComponents() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		this.setLayout(layout);

		JButton confirm = new JButton("Confirm");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		this.add(confirm, c);
		JButton done = new JButton("Done");
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		this.add(done, c);

		// TODO: DefaultListModel & JList should not be strings for long
		data = new DefaultListModel<Room>();
		updateListModel();
		final ChangeListener listener = new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				updateListModel();
			}
		};
		manager.getModel().attach(listener);

		final JList<Room> view = new JList<Room>(data);
		view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		view.setPreferredSize(new Dimension(250, 300));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		JScrollPane pane = new JScrollPane(view);
		pane.setPreferredSize(new Dimension(view.getPreferredSize().width + 10,
				view.getPreferredSize().height + 10));
		this.add(pane, c); // Uses ScrollBar Decorator

		// action listeners
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Room current = view.getSelectedValue();
				if(current == null)
					return;
				confirmReservation(current);
				manager.getModel().detach(listener);
				manager.setScene(new ReceiptDisplayScene());
			}

		});
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Room current = view.getSelectedValue();
				if(current == null)
					return;
				confirmReservation(current);
				manager.getModel().detach(listener);
				manager.setScene(new MakeReservationScene());
			}
		});
		Dimension d1 = done.getPreferredSize();
		Dimension d2 = view.getPreferredSize();
		this.setPreferredSize(new Dimension(d1.width + d2.width + 50, d1.height
				+ d2.height + 50));
	}

	private void updateListModel() {
		data.clear();
		ArrayList<Room> possibleReservations = manager.getModel()
				.searchAvalibleReservations(room, start, end);
		for (Room room : possibleReservations)
			data.addElement(room);
	}
	
	private void confirmReservation(Room room)
	{
		Model m = manager.getModel();
		Reservation reserved = new Reservation(room, manager.getCurrentUser(), start, end);
		manager.getCurrentSession().add(reserved);
		m.addReservation(reserved);
	}
}
