import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("serial")
public class ButtonView extends JPanel implements ChangeListener
{
	private JButton[][] bView;
	private Model model;
	
	/**
	 * 
	 * @param model
	 */
	public ButtonView(Model model)
	{
		this.model = model;
		
		setLayout(new GridLayout(9, 9));
		this.bView = new JButton[9][9];
		
		for(int i = 0; i < 9; i++)
		{	
			for(int j = 0; j < 9; j++)
			{
				this.bView[i][j] = new JButton();
				this.bView[i][j].setPreferredSize(new Dimension(50, 50));
				this.bView[i][j].setFont(bView[i][j].getFont().deriveFont(20.0f));
				add(this.bView[i][j]);
			}
		}
	}
	
	/**
	 * 
	 */
	public void stateChanged(ChangeEvent event)
	{
		String string = this.model.toString();
		updateView(string);
	}
	
	/**
	 * 
	 * @param string
	 */
	public void updateView(String string)
	{
		int num = 0;
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.bView[i][j].setText(string.substring(num, num + 1));
				num++;
			}
		}
	}
}

