import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;


public class MovingCarTester 
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(3, 1));
		
		final MovableShapes shape = new CarShape(0, 0, 100);
		
		ArrayList<EnhancedShapeIcon> list = new ArrayList<EnhancedShapeIcon>();
		final ArrayList<JLabel> labels = new ArrayList<JLabel>();
		
		list.add(new EnhancedShapeIcon(shape, 400, 100));
		list.add(new EnhancedShapeIcon(shape, 400, 100));
		list.add(new EnhancedShapeIcon(shape, 400, 100));
		
		for(int i = 0; i < list.size(); i++)
		{
			labels.add(new JLabel(list.get(i)));
			frame.add(labels.get(i));
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		Timer timer = new Timer(100, new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				int moveAmount = 5;
				
				
				for(int j = 0; j < labels.size(); j++)
				{	
					shape.speed(moveAmount, 0);
					labels.get(j).repaint();
					moveAmount *= 2;
				}
			}
		});
		timer.start();
	}
}
