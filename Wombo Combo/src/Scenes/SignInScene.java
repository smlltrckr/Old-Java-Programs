package Scenes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Project.Model;
import Project.SceneManager;

public class SignInScene extends Scene {
	public SignInScene() {
		super("Sign In");
	}

	public void initComponents() {
		this.setLayout(new GridLayout(2, 1));
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new FlowLayout());
		fieldPanel.add(new JLabel("User ID: "), BorderLayout.NORTH);
		final JTextField longField = new JTextField();
		longField.setPreferredSize(new Dimension(250, longField
				.getPreferredSize().height + 5));
		fieldPanel.add(longField, BorderLayout.CENTER);
		this.add(fieldPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JButton signup = new JButton("Sign Up");
		signup.setPreferredSize(new Dimension(100,
				signup.getPreferredSize().height));
		JButton signin = new JButton("Sign In");
		signin.setPreferredSize(new Dimension(200,
				signin.getPreferredSize().height));
		buttonPanel.add(signin);
		buttonPanel.add(signup);
		this.add(buttonPanel);

		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manager.setScene(new SignUpScene());
			}
		});
		signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Model m = manager.getModel();
				String t = longField.getText();
				if(m.userExists(t))
				{
					int uID = m.getUserID(t);
					manager.setCurrentUser(uID);
					if(m.isManager(uID))
						manager.setScene(new ManagerViewScene());
					else
						manager.setScene(new ReservationScene());
				}
				else
					longField.setText("[Invalid Username]");
			}
		});

	}
}
