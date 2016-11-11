import java.util.ArrayList;


public class MVCTester 
{
	public static void main(String[] args)
	{
		ArrayList<String> stringList = new ArrayList<String>();
		DataModel dataModel = new DataModel(stringList);
		MVCFrame mvcFrame = new MVCFrame(dataModel);
		dataModel.addChangeListener(mvcFrame);
	}
}
