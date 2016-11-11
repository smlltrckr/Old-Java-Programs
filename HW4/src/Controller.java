public class Controller implements Runnable
{ 
	private Model model;
	/**
	 * 
	 * @param m
	 */
	public Controller (Model m) 
	{
		this.model =m;
	}
	/**
	 * 
	 */
	public void run()
	{ 
		try
		{
			Thread.sleep(1000) ; // delay to see the initial position
			this.model.solve(0,0); // to solve the puzzle in the top left corner
		}
		catch( Exception e ){ }
	}
}