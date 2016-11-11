package Scenes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Project.RoomType;

public class MakeReservationScene extends Scene {

	private Date startD = null;
	private Date endD = null;
	
	public MakeReservationScene() {
		super("Reservation");
	}

	public void initComponents() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		this.setLayout(layout);
		final JTextField startDate = new JTextField();
		final JTextField endDate = new JTextField();
		
		c.gridx = 0;
		c.gridy = 0;
		this.add(new JLabel("Check-In:"), c);
		c.gridx = 0;
		c.gridy = 1;
		this.add(new JLabel("Check-Out:"), c);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		this.add(startDate, c);
		c.gridx = 1;
		c.gridy = 1;
		this.add(endDate, c);
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
		reserveEconomy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				if(dateTester(startDate.getText(), endDate.getText()))
					manager.setScene(new ReservationSelectionScene(startD, endD, RoomType.Economy));
				startDate.setText("[Invalid Date]");
				endDate.setText("[Invalid Date]");
			}
		});
		
		reserveLuxury.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				if(dateTester(startDate.getText(), endDate.getText()))
				{
					manager.setScene(new ReservationSelectionScene(startD, endD, RoomType.Luxury));
				}
				startDate.setText("[Invalid Date]");
				endDate.setText("[Invalid Date]");
			}
		});

	}
	/**
	 * 
	 * @param start
	 * @param end
	 * @return false if dates are invalid true otherwise
	 */
	private boolean dateTester(String start, String end){
		DateFormat format = new SimpleDateFormat("M/d/yyyy");
		Date sDate;
		Date eDate;
		try {
			sDate = format.parse(start);
			eDate = format.parse(end);
		} catch (ParseException e) {
			return false;
		}
		
		long lengthOfStay = (eDate.getTime() - sDate.getTime())/(1000 * 60 * 60 * 24);
		if(sDate.after(eDate) || lengthOfStay > 60 || lengthOfStay == 0)
			return false;
		
		startD = sDate;
		endD = eDate;
		return true;
	}
}
