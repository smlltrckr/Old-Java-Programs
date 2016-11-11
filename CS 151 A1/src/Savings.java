/**
 * 
 * @author Sam
 *
 */
public class Savings 
{
	private long accountID = 0;
	private static double amount = 0;
	/**
	 * 
	 * @param amount
	 * @param accountID
	 */
	public Savings(double amount, long accountID)
	{
		Savings.amount = amount;
		this.accountID = accountID;
	}
	/**
	 * 
	 * @param withdraw
	 */
	public static void withdraw (double withdraw)
	{
		amount = amount - withdraw;
	}
	/**
	 * 
	 * @return
	 */
	public double currentAmount ()
	{
		return amount;
	}
}
