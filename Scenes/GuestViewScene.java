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

import Project.Model;
import Project.Reservation;
import Project.SceneManager;

public class GuestViewScene extends Scene
{
	/**
	 * 
	 * @param sceneManager
	 * @param model
	 */
	public GuestViewScene(SceneManager sceneManager, Model model)
	{
		super(sceneManager, model, "Your Reservation(s)");
	}
	/**
	 * 
	 */
	public void initComponents() 
	{
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		this.setLayout(layout);
		final Model.User user = manager.getCurrentUser();
		final ArrayList<Reservation> reservations = model.getReservations(user);
		JButton cancel = new JButton("Cancel");
		JButton ok = new JButton("OK");
		
		
		final DefaultListModel<String> data = new DefaultListModel<String>();
		final JList<String> view = new JList<String>(data);
		view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for(Reservation r : reservations)
		{
			data.addElement(String.format("%s - %s Room: %d Type: %s", 
					r.getStartDate(), r.getEndDate(), r.getRoom().getRoomNumber(), 
					r.getRoom().getRoomType().name()));
		}
		data.addElement("This is an example List Box");
		data.addElement("This is an example List Box");
		data.addElement("This is an example List Box");
		data.addElement("This is an example List Box");
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
				data.remove(view.getSelectedIndex());	
				reservations.remove(view.getSelectedIndex());
				//TODO: update users arraylist of reservations
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
				manager.setScene(new ReservationScene(manager, model));
			}
		});
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		this.add(ok, c);
	}
}
