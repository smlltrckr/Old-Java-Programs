package Project;

public class SimpleFormatter implements ReceiptFormatter{
	private double total = 0;

	public String formatHeader(String userID, String name) {
		return "~SIMPLE~\r\nUSERID: " + userID + "\n" + "NAME: " + name;
	}

	public String formatLineItem(Reservation reservations) {
		total += reservations.getRoom().getRoomType().getCost();
		return String.format("\nROOM %d\n\tTYPE: %s\n\tCOST: $%s.00", reservations.getRoom().getRoomNumber(), 
				reservations.getRoom().getRoomType().toString(), reservations.getRoom().getRoomType().getCost());
	}

	public String formatFooter() {
		return String.format("\nTOTAL DUE: $%.2f\n", total);
	}
}
