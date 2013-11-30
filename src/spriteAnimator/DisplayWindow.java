package spriteAnimator;

import java.awt.*;
import javax.swing.*;

public class DisplayWindow extends JFrame {
	
	private final static int WIDTH = 500;
	private final static int HEIGHT = 500;
	private final static String DISPLAY_NAME = "Sprite Animator";
	
	private Container c;
	
	public DisplayWindow() {
		super(DISPLAY_NAME);
		c = this.getContentPane();
	}
	
	public void addPanel(JPanel p){
		p.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		c.add(p);
	}
	
	public void addPanel(JPanel p, boolean useDefault){
		if(useDefault)
			p.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		c.add(p);
	}
	
	public void showFrame(){
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
