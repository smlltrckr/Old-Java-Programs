/**
 * 
 * @author Sam
 *
 */
public class Checking 
{
	private long accountID = 0;
	private static double amount = 0;
	/**
	 * checking Constructs a Checking account
	 */
	
	public Checking (double amount, long accountID)
	{
		Checking.amount = amount;
		this.accountID = accountID;
	}
	
	/**
	 * withdraw Withdraws funds
	 */
	public static void withdraw (double withdraw)
	{
		amount = amount - withdraw;
	}
	
	/**
	 * currentAmount Returns current amount in checking
	 */
	public double currentAmount()
	{
		return amount;
	}
}
