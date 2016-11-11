import java.awt.FlowLayout;

import javax.swing.JFrame;


public class SudokuSolver 
{
	public static void main(String[] arg0)
	{
		Model model = new Model();
		ButtonView buttonView = new ButtonView(model);
		model.addChangeListener(buttonView);
		Controller controller = new Controller(model);
		JFrame jFrame = new JFrame();
		
		jFrame.setSize(500, 500);
		jFrame.setLayout(new FlowLayout());
		jFrame.add(buttonView);
		jFrame.setDefaultCloseOperation(3);
		jFrame.invalidate();
		jFrame.setVisible(true);
		
		Thread thread = new Thread(controller);
		thread.start();
	}
}
