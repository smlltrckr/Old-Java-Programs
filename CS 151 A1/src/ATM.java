import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author Sam
 *
 */

public class ATM {
	private static ArrayList cardNumLog = new ArrayList();
	private double maxDailyWithdraw = 0;
	private double maxTransactionWithdraw = 0;
	private double minCashInAtmToPermitAtmTransaction = 0;
	private double atmDailyStartingAmount = 0;
	private Scanner sc;
	private String password;
	private String atmID;
	
	private ATM(String atmID)
	{
		sc = new Scanner(System.in);
		this.maxDailyWithdraw = 40.00;
		this.maxTransactionWithdraw = 40.00;
		this.minCashInAtmToPermitAtmTransaction = 1.00;
		this.atmDailyStartingAmount = 40.00;
	}
	
	/**
	 * unavailable displays an error message if ATM is out of funds
	 */
	public void unavailable ()
	{
		if(this.atmDailyStartingAmount < this.minCashInAtmToPermitAtmTransaction)
		{
			System.out.println("Not Enough Funds Available in ATM");
		}
		return;
	}
	/**
	 * checkValidity checks given cards validity by checking expiration date
	 */
	public static void checkValidity()
	{
		if(CashCard.getExpDate().equals("Invalid"))
		{
			System.out.println("Card is Invalid, Card returned.");
		}
		if(CashCard.getExpDate().equals("Valid"))
		{
			logCardNumber(CashCard.getExpDate());
		}
	}
	/**
	 * 
	 * @param cardNum
	 */
	public static void logCardNumber(String cardNum)
	{
		cardNumLog.add(cardNum);
	}
	/**
	 * 
	 */
	public void authorization()
	{
		System.out.println("Enter Password: ");
		password = sc.nextLine();
	}
	/**
	 * requestWithdraw gets user input for withdraw amount
	 */
	public void requestWithdraw()
	{

		
	}
}
