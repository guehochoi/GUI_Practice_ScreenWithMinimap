
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class AnimationPanel extends JPanel implements ActionListener{

	public static final int XTOTAL = 2000;
	public static final int YTOTAL = 1600;
	
	public static int windowWidth = Screen.screenSize.width/2 - 5;
	public static int windowHeight = Screen.screenSize.height/3 - 5;
	public static int windowCurrentX = 0;
	public static int windowCurrentY = 0;
	
	private List<Gui> guis = new ArrayList<Gui>();
	
	private Timer timer;
	private static final int DELAY = 10;
	
	public AnimationPanel () {
		timer = new Timer(DELAY, this);
		timer.setActionCommand("tick");
		timer.start();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="tick") {
			for (Gui gui : guis) {
				if(gui.isPresent()) {
					gui.updatePosition();
				}
			}
		}
	}
	
	public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        //Clear the screen by painting a rectangle the size of the frame
        g2.setColor(getBackground());
        g2.fillRect(0, 0, windowWidth, windowHeight);
        
        for (Gui gui : guis) {
        	if(gui.isPresent()) {
        		gui.draw(g2);
        	}
        }
	}
	
}
