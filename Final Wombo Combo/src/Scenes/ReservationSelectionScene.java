package Scenes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import Project.Model;
import Project.Model.Room;
import Project.Reservation;
import Project.RoomType;

/**
 * Allows User to Select & Confirm Reservations
 */
@SuppressWarnings("serial")
public class ReservationSelectionScene extends Scene {

	private MakeReservationScene controllerPanel;
	private DateFormat format;
	private SelectionModel model;

	/**
	 * Default constructor declares Reservation Scene
	 */
	public ReservationSelectionScene() {
		super("Select Reservation");
	}

	/**
	 * Initializes Components in the class
	 * North Panel contains all controllers
	 * South Panel contains the view
	 */
	public void initComponents() {
		format = new SimpleDateFormat("M/d/yyyy");
		// COMPONENT DECLARATIONS
		this.setLayout(new BorderLayout());
		controllerPanel = new MakeReservationScene();
		this.add(controllerPanel, BorderLayout.NORTH);
		JPanel southP = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		southP.setLayout(layout);
		JButton confirm = new JButton("Confirm");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		southP.add(confirm, c);
		JButton done = new JButton("Done");
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		southP.add(done, c);
		final DefaultListModel<Room> data = new DefaultListModel<Room>();
		final JList<Room> view = new JList<Room>(data);
		view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		view.setPreferredSize(new Dimension(250, 300));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		JScrollPane pane = new JScrollPane(view);
		pane.setPreferredSize(new Dimension(view.getPreferredSize().width + 10,
				view.getPreferredSize().height + 10));
		southP.add(pane, c); // Uses ScrollBar Decorator
		this.add(southP, BorderLayout.CENTER);

		// TEXTBOX AUTOUPDATING
		Document startD = controllerPanel.getStartDateField().getDocument();
		startD.addDocumentListener(factoryDocumentListener(
				controllerPanel.getStartDateField(),
				controllerPanel.getErrorLabelStart()));
		Document endD = controllerPanel.getEndDateField().getDocument();
		endD.addDocumentListener(factoryDocumentListener(
				controllerPanel.getEndDateField(),
				controllerPanel.getErrorLabelEnd()));
		controllerPanel.getRoomTypeComboBox().addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkUpdateView();
			}
		});
		
		// Confirm / Done Buttons
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Room r = view.getSelectedValue();
				if(r == null)
					return;
				Reservation curr = new Reservation(r, manager.getCurrentUser(),
						model.getStartDate(), model.getEndDate());
				manager.getModel().addReservation(curr);
				manager.getCurrentSession().add(curr);
				checkUpdateView();
			} 
		});
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setScene(new ReceiptDisplayScene());
			}
		});
		
		// MVC attachments
		model = new SelectionModel();
		ChangeListener l = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ArrayList<Model.Room> rooms = manager.getModel()
						.searchAvalibleReservations(model.getRoomType(),
								model.getStartDate(), model.getEndDate());
				data.clear();
				for(Model.Room r : rooms)
					data.addElement(r);
			}
		};
		model.attach(l);
	}

	/**
	 * Factory Method to produce document listener for dynamic date textfield parsing
	 * @param dateField field to parse
	 * @param errorLabel label to indicate parse results
	 * @return Document Listener for date text fields
	 */
	private DocumentListener factoryDocumentListener(
			final JTextField dateField, final JLabel errorLabel) {
		return new DocumentListener() {
			public void changedUpdate(DocumentEvent arg0) {
				if (!isValidDateFormat(dateField.getText()))
					errorLabel.setText(" X");
				else {
					errorLabel.setText(" OK");
				}
				checkUpdateView();
			}
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
		};
	}

	/**
	 * Checks if all elements within a field are valid
	 * if valid, then model is updated
	 * if not, then label is set to "invalid dates"
	 */
	private void checkUpdateView() {
		String textS = controllerPanel.getStartDateField().getText();
		String textE = controllerPanel.getEndDateField().getText();
		if (!dateTester(textS, textE))
			controllerPanel.getErrorLabelTop().setText("Invalid Dates");
		else {
			controllerPanel.getErrorLabelTop().setText("Now confirm your reservation(s)");
			Date start = null, end = null;
			try {
				start = format.parse(textS);
				end = format.parse(textE);
			} catch (ParseException e) {e.printStackTrace();}
			model.setStartDate(start);
			model.setEndDate(end);
			RoomType type = RoomType.values()[controllerPanel.getRoomTypeComboBox().getSelectedIndex()];
			model.setRoomType(type);
		}
	}

	/**
	 * Checks to see if a date string is formatted properly
	 */
	private boolean isValidDateFormat(String date) {
		try {
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(format.parse(date));
			return (int) Integer.toString(cal.get(java.util.Calendar.YEAR)).length() == 4;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Checks to see if the start and end dates follow all specified requirements
	 * relative to each other.
	 * @param start date String of startDate
	 * @param end date String of endDate
	 * @return false if dates are invalid true otherwise
	 */
	private boolean dateTester(String start, String end) {
		Date sDate;
		Date eDate;
		try {
			sDate = format.parse(start);
			eDate = format.parse(end);
		} catch (ParseException e) {
			return false;
		}
		
		long lengthOfStay = (eDate.getTime() - sDate.getTime())
				/ (1000 * 60 * 60 * 24);
		if (sDate.after(eDate)|| lengthOfStay > 60 || lengthOfStay == 0)
			return false;
		return true;
	}
	
	/**
	 * Model of selection that updates all attached views 
	 * when setter methods are called.
	 */
	private class SelectionModel {
		private Date startDate, endDate;
		private RoomType roomType;
		private ArrayList<ChangeListener> listeners;
		/**
		 * declares an empty ArrayList of changelisteners
		 */
		public SelectionModel() {
			listeners = new ArrayList<ChangeListener>();
		}
		/**
		 * get start date of model
		 */
		public Date getStartDate() {
			return startDate;
		}
		/**
		 * get end date of model
		 */
		public Date getEndDate() {
			return endDate;
		}
		/**
		 * get room type of model
		 */
		public RoomType getRoomType() {
			return roomType;
		}
		/**
		 * sets start date of model & updates listeners
		 */
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
			sendEvent();
		}
		/**
		 * sets end date of model & updates listeners
		 */
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
			sendEvent();
		}
		/**
		 * sets room type of model & updates listeners
		 */
		public void setRoomType(RoomType roomType) {
			this.roomType = roomType;
			sendEvent();
		}
		/**
		 * Attaches changeListeners to model
		 */
		public void attach(ChangeListener listener) {
			this.listeners.add(listener);
		}
		/**
		 * responcible for releasing all change events of model
		 */
		private void sendEvent() {
			ChangeEvent evt = new ChangeEvent(this);
			for(ChangeListener l : listeners)
				l.stateChanged(evt);
		}
	}
}
