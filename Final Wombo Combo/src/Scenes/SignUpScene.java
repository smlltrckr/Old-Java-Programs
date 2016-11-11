package Scenes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Describes what the SignUpScene looks like and does
 * 
 * @author Wombo Combo
 *
 */
@SuppressWarnings("serial")
public class SignUpScene extends Scene {
	/**
	 * Constructor for the SignUpScene
	 */
	public SignUpScene() {
		super("Sign Up");
	}
	/**
	 * Adds the components and listeners to SignUpScene
	 */
	public void initComponents() {
		this.setLayout(new BorderLayout());
		JPanel centerComponent = new JPanel();
		centerComponent.setLayout(new GridBagLayout());

		final JTextField fieldName = new JTextField();
		JLabel labelName = new JLabel();
		fieldName.setPreferredSize(new Dimension(175, fieldName.getPreferredSize().height));
		labelName.setText("Username: ");
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		centerComponent.add(labelName,c);
		c.gridx = 1;
		c.gridy = 0;
		centerComponent.add(fieldName,c);
		final JTextField fieldID = new JTextField();
		JLabel labelID = new JLabel();
		fieldID.setPreferredSize(new Dimension(175, fieldID.getPreferredSize().height));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		centerComponent.add(labelID,c);
		c.gridx = 1;
		c.gridy = 1;
		centerComponent.add(fieldID,c);
		fieldID.setEditable(false);
		labelID.setText("Assigned ID: ");

		JButton register = new JButton("Register");
		c.gridx = 0;
		c.gridy = 2;
		centerComponent.add(register, c);
		JButton ok = new JButton("OK");
		c.gridx = 1;
		c.gridy = 2;
		centerComponent.add(ok, c);

		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String suggested = fieldName.getText();
				if(suggested.length() > 0 && !manager.getModel().userExists(suggested)){
					manager.getModel().addUser(suggested);
					fieldID.setText(Integer.toString(manager.getModel().getUserID(suggested)));
				}
				else
					fieldID.setText("[Username Invalid or already taken]");
			}
		});
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manager.setScene(new SignInScene());
			}
		});
		this.add(centerComponent, BorderLayout.CENTER);
	}

}
