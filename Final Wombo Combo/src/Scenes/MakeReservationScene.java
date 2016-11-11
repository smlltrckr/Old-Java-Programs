package Scenes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Project.RoomType;

/**
 * Holds all controllers for ReservationSelectionScene
 */
@SuppressWarnings("serial")
public class MakeReservationScene extends Scene {
	private JTextField startDateField, endDateField;
	private JLabel errorLabelTop, errorLabelS, errorLabelE;
	private JComboBox<String> reserveRoomType;

	/**
	 * Default construction initializes Scene
	 * and components
	 */
	public MakeReservationScene() {
		super("Reservation");
		initComponents();
	}

	/**
	 * initializes components
	 */
	public void initComponents() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		this.setLayout(layout);
		startDateField = new JTextField();
		endDateField = new JTextField();
		errorLabelTop = new JLabel("Invalid Dates");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		this.add(errorLabelTop, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		this.add(new JLabel("Check-In:"), c);
		c.gridx = 0;
		c.gridy = 2;
		this.add(new JLabel("Check-Out:"), c);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		this.add(startDateField, c);
		c.gridx = 1;
		c.gridy = 2;
		this.add(endDateField, c);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		this.add(new JLabel("Room Type: "), c);
		errorLabelS = new JLabel(" X");
		c.gridx = 3;
		c.gridy = 1;
		this.add(errorLabelS, c);
		errorLabelE = new JLabel(" X");
		c.gridx = 3;
		c.gridy = 2;
		this.add(errorLabelE, c);

		// JButtons
		reserveRoomType = new JComboBox<String>();
		for(RoomType r : RoomType.values())
			reserveRoomType.addItem(r.name() + ":    $" + r.getCost() + ".00");
		c.gridx = 1;
		c.gridy = 3;
		this.add(reserveRoomType, c);
	}

	/**
	 * Gets field containing start date
	 * @return startDate JTextfield
	 */
	protected JTextField getStartDateField() {
		return this.startDateField;
	}

	/**
	 * Gets field containing end date
	 * @return endDate JTextfield
	 */
	protected JTextField getEndDateField() {
		return this.endDateField;
	}

	/**
	 * Gets RoomType JComboBox
	 * @return JComboBox of RoomTypes
	 */
	protected JComboBox<String> getRoomTypeComboBox() {
		return this.reserveRoomType;
	}

	/**
	 * Gets Error Labels @ top of scene
	 * @return error label @ top
	 */
	protected JLabel getErrorLabelTop() {
		return this.errorLabelTop;
	}

	/**
	 * Gets Error Labels @ start field
	 * @return error label @ start field
	 */
	protected JLabel getErrorLabelStart() {
		return this.errorLabelS;
	}

	/**
	 * Gets Error Labels @ end field
	 * @return error label @ end field
	 */
	protected JLabel getErrorLabelEnd() {
		return this.errorLabelE;
	}
}
