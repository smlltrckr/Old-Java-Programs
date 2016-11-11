/**
 * 
 * @author Sam
 *
 */
public class BankComputer 
{
	private String acceptedBankID;
	/**
	 * 
	 * @param bankCode
	 */
	public static String checkATMRequest(String bankID)
	{
		//if(bankCode not valid){return "BankCode Valid!";}
		//if(password not valid){return "Invalid password!";}
		return "OK";
	}
	/**
	 * 
	 * @param withdrawAmount
	 * @return
	 */
	public static String checkTransactionPossibilities (double withdrawAmount)
	{
		//if(withdrawAmount > Savings/Checking Amount) {return "Not enough cash in account!";}
		//saving/checking.withdraw(withdrawAMount);
		return "Transaction Successful!";
	}
}
