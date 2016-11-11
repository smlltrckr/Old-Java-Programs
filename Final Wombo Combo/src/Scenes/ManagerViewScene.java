package Scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Project.Calendar;
import Project.Model.Room;
import Project.Reservation;

/**
 * Scene displaying the Manager View of the System
 * @author Team Wombo Combo
 *
 */
@SuppressWarnings("serial")
public class ManagerViewScene extends Scene {
	private CalendarPanel[][] panels;
	private int calRow;
	private int calCol;
	
	/**
	 * Initializes ManagerViewScene with Scene components.
	 */
	public ManagerViewScene() {
		super("Manager");
	}

	/**
	 * Creates and initializes all ManagerViewScene components and listeners.
	 */
	public void initComponents() {
		JPanel main = new JPanel();
		main.setLayout(new BorderLayout());
		
		//Buttons
		JPanel buttons = new JPanel();
		JButton quit = new JButton("Save and Quit");
		quit.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						manager.getModel().save();
					} catch (Exception e) {e.printStackTrace();}
					System.exit(0);	
				}
		});
		
		JButton logout = new JButton("Log Off");
		logout.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					manager.setScene(new SignInScene());
				}
		});
		
		buttons.add(logout);
		buttons.add(quit);
		main.add(buttons, BorderLayout.SOUTH);
		
		//Availability View
		JPanel viewPanel = new JPanel();
		viewPanel.setLayout(new BorderLayout());
		JLabel label = new JLabel("Reservations", SwingConstants.CENTER);
		viewPanel.add(label, BorderLayout.NORTH);

		final JTextArea view = new JTextArea(12, 25);
		view.setLineWrap(true);
		JScrollPane scrollView = new JScrollPane(view);
		viewPanel.add(scrollView, BorderLayout.SOUTH);
		main.add(viewPanel, BorderLayout.EAST);
		
		
		//CalendarView
		final Calendar cal = new Calendar();
		
		JPanel calendarPanel = new JPanel();
		calendarPanel.setLayout(new BorderLayout());
		
		JPanel selections = new JPanel();
	    JComboBox<String> monthPicker = new JComboBox<String>(cal.getMonths());
	    monthPicker.setSelectedIndex(cal.getMonth()); //initialize to current month
	    monthPicker.addItemListener(
	    		new ItemListener() {
					public void itemStateChanged(ItemEvent event) {
						if (event.getStateChange() == ItemEvent.SELECTED) {
					          String month = (String)event.getItem();
					          int monthID = cal.getMonthID(month);
					          cal.setMonth(monthID);
					          updateTable(cal);
					          view.setText("");
					          for(int r = 0; r < 6; r++) {
						        	for(int c = 0; c < 7; c++) {
						        		panels[r][c].setBackground(Color.DARK_GRAY.darker());
						        	}
						        }
					          //TODO: Update view with Reservations
					    }
					}
	    });
	    
	    final JSpinner years = new JSpinner();
	    years.setValue(cal.getYear()); //initialize to current year
	    years.addChangeListener(
	    		new ChangeListener() {
					public void stateChanged(ChangeEvent event) {
						int newYear = (int) years.getValue();
						cal.setYear(newYear);
				        //System.out.println(cal.getYear() + " " + cal.getMonth() + " " + cal.getDayNum());
				        updateTable(cal);
				        view.setText("");
				        for(int r = 0; r < 6; r++) {
				        	for(int c = 0; c < 7; c++) {
				        		panels[r][c].setBackground(Color.DARK_GRAY.darker());
				        	}
				        }
				        	
				        //TODO: Update view with Reservations
					}
	    });
		
	    selections.add(monthPicker);
		selections.add(years);
		calendarPanel.add(selections, BorderLayout.NORTH);
		
		JPanel calendar = new JPanel();
		calendar.setLayout(new BorderLayout());
		JPanel monthView = new JPanel();
	    String [] days = cal.getDays();
		monthView.setLayout(new GridLayout(0, days.length));
		
		//days of the week
		for(int i = 0; i < days.length; i++)
		{
			JPanel dayPanel = new JPanel();
			dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel dLabel = new JLabel(days[i]);
            dayPanel.add(dLabel);
            monthView.add(dayPanel);
		}
		
		//initialize calendar panels
		this.panels = new CalendarPanel[6][7];
		for(int r=0;r<panels.length;r++)
			for(int c=0;c<panels[r].length;c++)
			{
				final int row = r;
				final int col = c;
				this.panels[r][c] = new CalendarPanel();
				panels[r][c].addMouseListener(
						new MouseAdapter() {
							public void mousePressed(MouseEvent event) {
								if(!panels[row][col].isEmpty()) {
									panels[calRow][calCol].setBackground(Color.DARK_GRAY.darker());
									panels[row][col].setBackground(Color.gray);
									calRow = row;
									calCol = col;
									
									cal.setDay(Integer.parseInt(panels[calRow][calCol].getText()));
									Date newDate = cal.getDate();
									ArrayList<Reservation> dayReservations = manager.getModel().getReservations(newDate);
									String reservations = "Reservations: \n";
									for(Reservation res : dayReservations) {
										reservations += res.toString() + "\n";
									}
									
									ArrayList<Room> rooms = manager.getModel().searchAvalibleReservations(newDate);
									String availableRooms = "Available Rooms \n";
									for(Room r : rooms) {
										availableRooms += r.toString() + "\n";
									}
									view.setText(reservations+"\n"+availableRooms);
									
								}
							}
						});
				monthView.add(panels[r][c]);
			}
		
		calendar.add(monthView, BorderLayout.NORTH);
		calendarPanel.add(calendar, BorderLayout.CENTER);
		
		main.add(calendarPanel, BorderLayout.WEST);
		updateTable(cal);
		
		add(main); //adds all components to the scene
	}
	
	/**
	 * Updates the CalendarPanel [][] with the specified Calendar.
	 * @param cal Calendar with desired date information
	 */
	private void updateTable(Calendar cal)
	{
		int first = cal.getFirstDayOfMonth();
		for(int i = 0; i < first-1; i++)
			this.panels[0][i].setText("  ");
		int k = 0;
		for(int i = 0; i< cal.getNumDaysInMonth(); i++){
			int j = i + first - 1;
			k = j;
			int row = j / panels[0].length;
			int col = j % panels[0].length;
			this.panels[row][col].setText(String.format("%02d", i+1));
		}
		for(k = k + 1; k<panels.length * panels[0].length; k++)
		{
			int row = k / panels[0].length;
			int col = k % panels[0].length;
			this.panels[row][col].setText("  ");
		}
	}
	
	/**
	 * Node class representing single Calendar days.
	 * @author Team Wombo Combo
	 *
	 */
	private class CalendarPanel extends JPanel {
		private JLabel label;

		/**
		 * Initializes CalendarPanel with a blank label.
		 */
		public CalendarPanel()
		{
			this.label = new JLabel();
			this.label.setText("  ");
			this.setLayout(new GridBagLayout());
			this.add(label);
		}
		
		/**
		 * Sets the label with the specified text.
		 * @param s the desired text
		 */
		public void setText(String s)
		{
			if(s.trim().length() == 0)
				this.setBorder(BorderFactory.createEmptyBorder());
			else
				this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.label.setText(s);
		}
		
		/**
		 * Gets the label text
		 * @return String of the label's text
		 */
		public String getText()
		{
			return this.label.getText();
		}
		
		/**
		 * Checks whether this CalendarPanel has a blank label.
		 * @return whether the CalendarPanel is black or not
		 */
		public boolean isEmpty() {
			return getText().trim().length() == 0;
		}
	}
}