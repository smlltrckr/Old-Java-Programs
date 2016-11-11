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

import Project.Model;
import Project.SceneManager;

public class SignUpScene extends Scene {

	public SignUpScene(SceneManager manager, Model model) {
		super(manager, model, "Sign Up");
	}

	public void initComponents() {
		this.setLayout(new BorderLayout());
		JPanel centerComponent = new JPanel();
		centerComponent.setLayout(new GridBagLayout());

		JTextField[] fields = new JTextField[2];
		JLabel[] labels = new JLabel[fields.length];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField();
			fields[i].setPreferredSize(new Dimension(175, fields[i]
					.getPreferredSize().height));
			labels[i] = new JLabel();
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = i;
			centerComponent.add(labels[i],c);
			c.gridx = 1;
			c.gridy = i;
			centerComponent.add(fields[i],c);
		}
		labels[0].setText("Username: ");
		labels[1].setText("Assigned ID: ");

		this.add(centerComponent, BorderLayout.CENTER);

		JButton register = new JButton("register");
		this.add(register, BorderLayout.SOUTH);

		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO: Add registration code
				manager.setScene(new SignInScene(manager, model));
			}
		});
	}

}
