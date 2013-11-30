package spriteAnimator;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.Timer;

public class  SpriteAnimatorPanel extends JPanel{
	
	private static final int SPACE = KeyEvent.VK_SPACE;
	//private static final int A = KeyEvent.VK_A;
	//private static final int D = KeyEvent.VK_D;
	private static final int LEFT = KeyEvent.VK_LEFT;
	private static final int RIGHT = KeyEvent.VK_RIGHT;

	private ImageIcon s01 = new ImageIcon("Bane_00.png");
	private ImageIcon s01R = new ImageIcon("Bane_00R.png");
	private ImageIcon s02 = new ImageIcon("Bane_01.png");
	private ImageIcon s02R = new ImageIcon("Bane_01R.png");

	private Image sprite01;
	private Image sprite01R;
	private Image sprite02;
	private Image sprite02R;

	private Image imageToDisplay;

	private int spriteX;
	private int spriteY;

	private final Set<Integer> pressed = new HashSet<Integer>();

	public SpriteAnimatorPanel(){
		spriteX = 125;
		spriteY = 125;
		sprite01 = s01.getImage().getScaledInstance(250, 250, 0);
		sprite01R = s01R.getImage().getScaledInstance(250, 250, 0);
		sprite02 = s02.getImage().getScaledInstance(250, 250, 0);
		sprite02R = s02R.getImage().getScaledInstance(250, 250, 0);
		imageToDisplay = sprite01;
	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(imageToDisplay, spriteX, spriteY, null);
		repaint();
	}
	
	public class AL extends KeyAdapter{
		
		public AL(){
		}
		
		public void keyPressed(KeyEvent e){
			pressed.add(e.getKeyCode());
			if (pressed.contains(LEFT) && pressed.contains(SPACE)){ 
				imageToDisplay = sprite02R;
			}
			else if(pressed.contains(RIGHT) && pressed.contains(SPACE)){
				imageToDisplay = sprite02;
			}
			else{
				if (pressed.contains(LEFT))
					imageToDisplay = sprite01R;
				else if(pressed.contains(RIGHT))
					imageToDisplay = sprite01;
				else if(pressed.contains(SPACE) && imageToDisplay == sprite01)
					imageToDisplay = sprite02;
				else if(pressed.contains(SPACE) && imageToDisplay == sprite01R)
					imageToDisplay = sprite02R;
			}
			if (pressed.contains(LEFT) && !pressed.contains(RIGHT))
				spriteX -= 10;
			else if (pressed.contains(RIGHT) && !pressed.contains(LEFT))
				spriteX += 10;
			repaint();
		}

		public void keyReleased(KeyEvent e){
			int removed = e.getKeyCode();
			pressed.remove(removed);
			if(removed == LEFT && imageToDisplay == sprite01R)
				imageToDisplay = sprite01R;
			else if(removed == SPACE && imageToDisplay == sprite02)
				imageToDisplay = sprite01;
			else if(removed == LEFT && imageToDisplay == sprite02R)
				imageToDisplay = sprite02R;
			else if(removed == SPACE && imageToDisplay == sprite02R)
				imageToDisplay = sprite01R;
			repaint();
		}
		
		public void keyTyped(KeyEvent e) {

		}
	}
	
	
	public KeyListener getKeyListener(){
		return new AL();
	}

	public static void main(String[] args){
		SpriteAnimatorPanel spriteAnimatorPanel = new SpriteAnimatorPanel();
		DisplayWindow window = new DisplayWindow();
		window.addKeyListener(spriteAnimatorPanel.getKeyListener());
		window.addPanel(spriteAnimatorPanel);
		window.showFrame();
	}
}
