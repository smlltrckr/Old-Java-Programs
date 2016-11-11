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

public class SignUpScene extends Scene {

	public SignUpScene() {
		super("Sign Up");
	}

	public void initComponents() {
		this.setLayout(new BorderLayout());
		JPanel centerComponent = new JPanel();
		centerComponent.setLayout(new GridBagLayout());

		final JTextField field = new JTextField();
		JLabel label = new JLabel();
		field.setPreferredSize(new Dimension(175, field.getPreferredSize().height));
		label = new JLabel();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		centerComponent.add(label,c);
		c.gridx = 1;
		c.gridy = 0;
		centerComponent.add(field,c);
		label.setText("Username: ");
		this.add(centerComponent, BorderLayout.CENTER);

		JButton register = new JButton("Register");
		this.add(register, BorderLayout.SOUTH);

		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String suggested = field.getText();
				if(suggested.length() > 0 && !manager.getModel().userExists(suggested)){
					manager.getModel().addUser(suggested);
					manager.setScene(new SignInScene());
				}
				else
					field.setText("[Username Invalid or already taken]");
			}
		});
	}

}
