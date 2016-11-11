package Scenes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Project.ComprehensiveFormatter;
import Project.Model;
import Project.ReceiptFormatter;
import Project.SceneManager;
import Project.SimpleFormatter;

public class ReceiptDisplayScene extends Scene{
	public ReceiptDisplayScene(SceneManager sceneManager, Model model){
		super(sceneManager, model, "Receipt");
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
				receiptArea.setText(simpleFormatter.formatHeader("1122", "Sam"));//remove later
				/*String header = simpleFormatter.formatHeader(model.getCurrentUserID, model.getUserName;
				receiptArea.setText(header);
				for(int i = 0; i < model.getReservations(model.getCurrentUserID).size(); i++)
				{
					String body = simpleFormatter.formatLineItem(model.getReservations(model.getCurrentUser).get(i)); //gets user reservations for current session
					receiptArea.append(body);
				}*/
				receiptArea.append(simpleFormatter.formatFooter());
			}
		});
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		this.add(simple, c);
		
		comprehensive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				receiptArea.setText(simpleFormatter.formatHeader("1122", "Sam"));//remove later
				/*String header = comprehensiveFormatter.formatHeader(model.getCurrentUserID, model.getUserName;
				receiptArea.setText(header);
				for(int i = 0; i < model.getReservations(model.getCurrentUserID).size(); i++)
				{
					String body = comprehensiveFormatter.formatLineItem(model.getReservations(model.getCurrentUser).get(i)); //gets user reservations for current session
					receiptArea.append(body);
				}*/
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
				manager.setScene(new SignInScene(manager, model));
			}
		});
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 7;
		this.add(ok, c);
		
	}
}
