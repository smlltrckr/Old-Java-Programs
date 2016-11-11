import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("serial")
public class MVCFrame extends JFrame implements ChangeListener
{
	@SuppressWarnings("unused")
	private DataModel dataModel;
	private ArrayList<String> stringList = new ArrayList<String>();
	JTextArea jTextArea;
	
	public MVCFrame(final DataModel dataModel) 
	{
		this.dataModel = dataModel;
		stringList = dataModel.getStringList();
		jTextArea = new JTextArea(20, 40);
		
		setLayout(new BorderLayout());
		
		JButton button = new JButton("add");
		final JTextField jTextField = new JTextField();
		JScrollPane textScroller = new JScrollPane(jTextArea);
		textScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		jTextArea.setEditable(false);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				dataModel.addString(jTextField.getText());
				jTextField.setText("");
			}
		});
		
		add(textScroller, BorderLayout.NORTH);
		add(jTextField, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public void stateChanged(ChangeEvent arg0) 
	{
		int size = stringList.size();
		
		if(size > 1)
		{
			jTextArea.append("\n");
		}
		jTextArea.append(stringList.get(size -1));
		
	}

}
