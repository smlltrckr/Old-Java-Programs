/**
 * 
 * @author Sam
 *
 */
public class CashCard 
{
	private static String bankID;
	private static String cardNumber;
	private static String expDate;
	
	/**
	 * 
	 * @param bankID
	 * @param cardNumber
	 * @param expDate
	 */
	public CashCard(String bankID, String cardNumber, String expDate)
	{
		CashCard.bankID = bankID;
		CashCard.cardNumber = cardNumber;
		CashCard.expDate = expDate;
	}

	/**
	 * 
	 * @return
	 */
	public static String getBankID()
	{
		return bankID;
	}
	/**
	 * 
	 * @return
	 */
	public static String getCardNumber()
	{
		return cardNumber;
	}
	/**
	 * 
	 * @return
	 */
	public static String getExpDate()
	{
		return expDate;
	}
}
