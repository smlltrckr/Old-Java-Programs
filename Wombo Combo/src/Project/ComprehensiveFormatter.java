package Project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ComprehensiveFormatter implements ReceiptFormatter {
	private double total = 0;

	public String formatHeader(String userID, String name) {
		return "~Comprehensive~\r\nUSERID: " + userID + "\n" + "NAME: " + name;
	}

	public String formatLineItem(Reservation reservations) {
		total += reservations.getRoom().getRoomType().getCost();
		DateFormat format = new SimpleDateFormat("M/d/yyyy");
		return String.format(
				"\nROOM %d\n\tTYPE: %s\n\tCOST: $%s.00\n\tCheckIn: %s\n\tCheckOut: %s",
				reservations.getRoom().getRoomNumber(), reservations.getRoom()
						.getRoomType().name(), reservations.getRoom()
						.getRoomType().getCost(), format.format(reservations.getStartDate()), 
						format.format(reservations.getEndDate()));
	}

	public String formatFooter() {
		return String.format("\nTOTAL DUE: $%.2f\n", total);
	}
}
