package Scenes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Project.ComprehensiveFormatter;
import Project.Model;
import Project.ReceiptFormatter;
import Project.Reservation;
import Project.SceneManager;
import Project.SimpleFormatter;

public class ReceiptDisplayScene extends Scene{
	public ReceiptDisplayScene(){
		super("Receipt");
	}
	
	public void initComponents(){
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		this.setLayout(layout);
		final JTextArea receiptArea = new JTextArea();
		final JScrollPane pane = new JScrollPane(receiptArea);
		JButton simple = new JButton("Simple");
		JButton comprehensive = new JButton("Comprehensive");
		final ReceiptFormatter simpleFormatter = new SimpleFormatter();
		final ReceiptFormatter comprehensiveFormatter = new ComprehensiveFormatter();
		
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		receiptArea.setRows(20);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		this.add(pane, c);
		
		simple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				int userID = manager.getCurrentUser();
				String name = manager.getModel().getUserName(userID);
				receiptArea.setText(simpleFormatter.formatHeader(Integer.toString(userID), name));
				ArrayList<Reservation> reservations = manager.getCurrentSession();

				for(Reservation r : reservations)
					receiptArea.append(simpleFormatter.formatLineItem(r));
				
				receiptArea.append(simpleFormatter.formatFooter());
			}
		});
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		this.add(simple, c);
		
		comprehensive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				int userID = manager.getCurrentUser();
				String name = manager.getModel().getUserName(userID);
				ArrayList<Reservation> reservations = manager.getModel().getReservations(userID);
				receiptArea.setText(comprehensiveFormatter.formatHeader(Integer.toString(userID), name));
				
				for(Reservation r : reservations)
					receiptArea.append(comprehensiveFormatter.formatLineItem(r));
				
				receiptArea.append(comprehensiveFormatter.formatFooter());
			}
		});
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		this.add(comprehensive, c);
		
		
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				manager.getCurrentSession().clear();
				manager.setScene(new ReservationScene());
			}
		});
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 7;
		this.add(ok, c);
		
	}
}
