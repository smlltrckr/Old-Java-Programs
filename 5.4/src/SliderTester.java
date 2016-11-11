import java.awt.GridLayout;

import javax.swing.JFrame;


public class SliderTester 
{
	public static void main(String args[])
	{
		JFrame jFrame = new JFrame();
		jFrame.setLayout(new GridLayout(2, 1));
		
		DataModel dataModel = new DataModel(50);
		
		CarShape car = new CarShape(0, 0, 100, dataModel);
		ShapeAlter alteredShape = new ShapeAlter(car, 500, 250);
		Label label = new Label(alteredShape);
		
		Slider slider = new Slider(dataModel);
		dataModel.addChangeListener(car);
		dataModel.addChangeListener(label);
		jFrame.add(label);
		jFrame.add(slider);
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.pack();
		jFrame.setVisible(true);
	}
}
