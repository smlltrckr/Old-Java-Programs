package Project;
import java.awt.*;

import javax.swing.*;

import Scenes.Scene;

public class SceneManager extends JFrame {

    private static final long serialVersionUID = 1L;
    private JViewport centeredViewport;
    private JLabel titleLabel;
    
    public SceneManager() {
    	this.setVisible(true);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setSize(500,400);
    	this.setLayout(new BorderLayout());
    	centeredViewport = new JViewport();
    	centeredViewport.setPreferredSize(new Dimension(300,300));
    	titleLabel = new JLabel();
    	titleLabel.setFont(new Font("Algerian", Font.BOLD, 20));
    	titleLabel.setForeground(Color.WHITE);
    	JPanel northPanel = new JPanel();
    	northPanel.setLayout(new GridBagLayout());
    	northPanel.add(titleLabel);
    	this.add(northPanel, BorderLayout.NORTH);
    	this.add(centeredViewport, BorderLayout.CENTER);
    	centeredViewport.setLayout(new GridBagLayout());
    	centeredViewport.setBackground(Color.DARK_GRAY);
    	northPanel.setBackground(Color.DARK_GRAY.darker());
    }
    
    /**
     * Sets the current Scene in the center of this JFrame (inside a Viewport)
     * Sets the Scene Name as the Title of this JFrame (inside a JLabel)
     * Sets the minimum size of the JFrame. Expands if Necissary.
     */
    public void setScene(Scene nextScene)
    {
    	titleLabel.setText(nextScene.getName());
    	centeredViewport.setView(nextScene);
    	centeredViewport.setViewSize(nextScene.getPreferredSize());
    	centeredViewport.setPreferredSize(nextScene.getPreferredSize());
    	this.setPreferredSize(centeredViewport.getPreferredSize());
    	this.setMinimumSize(new Dimension(centeredViewport.getPreferredSize().width + 100, centeredViewport.getPreferredSize().height + 100));
    }
}