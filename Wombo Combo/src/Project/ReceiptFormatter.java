package Project;

public interface ReceiptFormatter {
	String formatHeader(String userID, String name);
	String formatLineItem(Reservation reservations);
	String formatFooter();
}
