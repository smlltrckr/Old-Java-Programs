package Project;
/**
 * Formatter for a simple receipt
 * 
 * @author Wombo Combo
 *
 */
public class SimpleFormatter implements ReceiptFormatter{
	private double total;
	/**
	 * Formats a header for a simple receipt
	 * 
	 * @param userID - users ID
	 * @param name - users name
	 * @return Formatted string of userID and name
	 */
	public String formatHeader(String userID, String name) {
		total = 0;
		return "~SIMPLE~\r\nUSERID: " + userID + "\n" + "NAME: " + name;
	}
	/**
	 * Formats a line item for a given reservation
	 * 
	 * @param reservations - a users reservation
	 * @return formatted string of reservations
	 */
	public String formatLineItem(Reservation reservations) {
		long lengthOfStay = (reservations.getEndDate().getTime() - reservations.getStartDate().getTime())
				/(1000 * 60 * 60 * 24);
		total += reservations.getRoom().getRoomType().getCost() * lengthOfStay;
		return String.format("\nROOM %d\n     TYPE: %s\n     COST: $%s.00", reservations.getRoom().getRoomNumber(), 
				reservations.getRoom().getRoomType().toString(), reservations.getRoom().getRoomType().getCost());
	}
	/**
	 * Formats a footer for a simple receipt
	 * 
	 * @return formatted string of Total Due
	 */
	public String formatFooter() {
		return String.format("\nTOTAL DUE: $%.2f\n", total);
	}
}
