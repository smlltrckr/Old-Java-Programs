package Project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * Formatter for a comprehensive receipt
 * 
 * @author Team: Wombo Combo
 *
 */
public class ComprehensiveFormatter implements ReceiptFormatter {
	private double total;
	/**
	 * Formats a header for a comprehensive receipt.
	 * 
	 * @param userID - users ID
	 * @param name - users name
	 * @return formatted string of userID and name
	 */
	public String formatHeader(String userID, String name) {
		total = 0;
		return "~Comprehensive~\r\nUSERID: " + userID + "\n" + "NAME: " + name;
	}
	/**
	 * Formats a line item for a comprehensive receipt.
	 * 
	 * @param reservations - a users reservation
	 * @return formatted string of a reservation
	 */
	public String formatLineItem(Reservation reservations) {
		long lengthOfStay = (reservations.getEndDate().getTime() - reservations.getStartDate().getTime())
				/(1000 * 60 * 60 * 24);
		total += reservations.getRoom().getRoomType().getCost() * lengthOfStay;
		DateFormat format = new SimpleDateFormat("M/d/yyyy");
		return String.format(
				"\nROOM %d\n     TYPE: %s\n     COST: $%s.00\n     CheckIn: %s\n     CheckOut: %s",
				reservations.getRoom().getRoomNumber(), reservations.getRoom()
						.getRoomType().name(), reservations.getRoom()
						.getRoomType().getCost(), format.format(reservations.getStartDate()), 
						format.format(reservations.getEndDate()));
	}
	/**
	 * Formats a footer for a comprehensive receipt
	 * 
	 * @return formatted string of Total Cost
	 */
	public String formatFooter() {
		return String.format("\nTOTAL DUE: $%.2f\n", total);
	}
}
