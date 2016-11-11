package Project;
/**
 * An Interface which describes the requirements for 
 * formatting a receipt
 * 
 * @author Wombo Combo
 *
 */
public interface ReceiptFormatter {
	String formatHeader(String userID, String name);
	String formatLineItem(Reservation reservations);
	String formatFooter();
}
