package Scenes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Project.Model;
import Project.RoomType;
import Project.SceneManager;

public class ReservationSelectionScene extends Scene{

	public ReservationSelectionScene(SceneManager manager, Model model, RoomType roomType) {
		super(manager, model, "Select Reservation");
		// TODO: Attach JList to the model
		// TODO: Populate List box w/ all events from start & end date
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
		DefaultListModel<String> data = new DefaultListModel<String>();
		data.addElement("This is an example List Box");
		data.addElement("This JList is going to be our view");
		data.addElement("DefaultListModel alters JList");
		data.addElement("Model alters DefaultListModel");
		
		JList<String> view = new JList<String>(data);
		view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // only select one at a time
		view.setLayoutOrientation(JList.HORIZONTAL_WRAP); // TODO: Delete this
		view.setVisibleRowCount(7);; // TODO: Delete this
		view.setPreferredSize(new Dimension(250,300));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		JScrollPane pane = new JScrollPane(view);
		//pane.setPreferredSize(new Dimension(view.getPreferredSize().width + 10, view.getPreferredSize().height + 10));
		this.add(pane, c); // Uses ScrollBar Decorator

		//action listeners
		done.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt) {
				// TODO: Not accurate at all. Go to RecieptSelectionScene then ReciptScene
				// TODO: Detach this view from the model now.
				//manager.setScene(new SignInScene(manager,model));
				manager.setScene(new ReceiptDisplayScene(manager, model));
			}
			
		});
		confirm.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt) {
				// TODO: Recall that you should be storing reservations for Simple receipt. Food for thought.
				// TODO: Detach this view from the model now.
				manager.setScene(new MakeReservationScene(manager,model));
			}
			
		});
		Dimension d1 = done.getPreferredSize();
		Dimension d2 = view.getPreferredSize();
		this.setPreferredSize(new Dimension(d1.width + d2.width + 50, d1.height + d2.height + 50));
	}

}
