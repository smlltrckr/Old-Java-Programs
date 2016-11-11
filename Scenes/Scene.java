package Scenes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JPanel;

import Project.Model;
import Project.SceneManager;

public abstract class Scene extends JPanel {
	private static final Color FOREGROUND_COLOR = Color.WHITE;
	private static final Color BACKGROUND_COLOR = Color.DARK_GRAY.darker();

	protected SceneManager manager;
	protected Model model;
	private String name;

	public Scene(SceneManager manager, Model model, String name) {
		this.name = name;
		this.manager = manager;
		this.setForeground(FOREGROUND_COLOR);
		this.setBackground(BACKGROUND_COLOR);
		initComponents();
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Simultaneously add & stylize JFrame Components
	 * @param c Component to be added
	 */
	public Component add(Component c) {
		stylizeComponent(c);
		return super.add(c);
	}

	/**
	 * Simultaneously add & stylize JFrame Components
	 * @param c Component to be added
	 * @param data used as additional layout information
	 */
	public void add(Component c, Object data) {
		stylizeComponent(c);
		super.add(c, data);
	}

	/**
	 * Recursively calls and stylizes All components within given component
	 * @param c Component to be stylized
	 */
	private void stylizeComponent(Component c) {
		c.setForeground(FOREGROUND_COLOR);
		c.setBackground(BACKGROUND_COLOR);
		if (c instanceof Container)
			for (Component child : ((Container) c).getComponents())
				stylizeComponent(child);
	}
	
	public abstract void initComponents();
}
