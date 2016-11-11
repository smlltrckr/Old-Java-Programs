package Scenes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Project.Reservation;
/**
 * 
 * @author Wombo Combo
 *
 */
@SuppressWarnings("serial")
public class GuestViewScene extends Scene
{
	/**
	 * Constructor for the GuestViewScene
	 */
	public GuestViewScene()
	{
		super("Your Reservation(s)");
	}
	/**
	 * Adds all the components and listeners to GuestViewScene
	 */
	public void initComponents() 
	{
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		this.setLayout(layout);
		final int userID = manager.getCurrentUser();
		final ArrayList<Reservation> reservations = manager.getModel().getReservations(userID);
		JButton cancel = new JButton("Cancel");
		JButton ok = new JButton("OK");
		
		
		final DefaultListModel<Reservation> data = new DefaultListModel<Reservation>();
		final JList<Reservation> view = new JList<Reservation>(data);
		view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for(Reservation r : reservations)
			data.addElement(r);
		view.setPreferredSize(new Dimension(250,300));
		JScrollPane pane = new JScrollPane(view);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		this.add(pane, c);
		
		cancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event)
			{	
				if(view.getSelectedIndex() < 0)//if nothing is selected
					return;
				manager.getModel().cancelReservation(view.getSelectedValue());
				data.remove(view.getSelectedIndex());
			}
		});
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		this.add(cancel, c);
		
		ok.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event)
			{
				manager.setScene(new ReservationScene());
			}
		});
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		this.add(ok, c);
	}
}
