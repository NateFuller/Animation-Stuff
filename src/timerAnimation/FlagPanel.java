package timerAnimation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlagPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image[] theImages;
	private Image imageToDisplay;
	private int imageCycler;
	private Timer t = new Timer(500, this);
	
	private void populateImages(String fileNames, String fileType)
	{
		for(int i = 1; i <= theImages.length; i++)
		{
			// formatted so there's a series of images in a local file whose end of their file increments by 1 starting at 1. 
			theImages[i - 1] = ((new ImageIcon(fileNames + i + "." + fileType)).getImage()).getScaledInstance(250, 250, 0);
		}
	}
	
	public FlagPanel(String fileNames, String fileType, int howManyImages)
	{
		theImages = new Image[howManyImages];
		populateImages(fileNames, fileType);
		imageCycler = 0;
		imageToDisplay = theImages[imageCycler];
		t.start();
		t.addActionListener(this);
		
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(imageToDisplay, 100, 100, null);
		repaint();
	}
	
	
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource() == t)
		{
			imageToDisplay = theImages[imageCycler++ % 4];
		}
		
	}
	
	
	public static void main(String[] args)
	{
		FlagPanel flagPanel = new FlagPanel("images/Flag_Flutter_0", "png", 4);
		DisplayWindow window = new DisplayWindow();
		window.addPanel(flagPanel);
		window.showFrame();
	}
}
