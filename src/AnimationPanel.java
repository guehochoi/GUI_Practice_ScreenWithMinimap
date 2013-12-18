
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class AnimationPanel extends JPanel implements ActionListener, MouseListener{

	public static final int XTOTAL = 2000;
	public static final int YTOTAL = 1600;
	
	public static int windowWidth = Screen.screenSize.width/2 - 5;
	public static int windowHeight = Screen.screenSize.height/3 - 5;
	public static int windowCurrentX = 0;
	public static int windowCurrentY = 0;
	
	private List<Gui> guis = new ArrayList<Gui>();
	private Game game;
	
	private Timer timer;
	private static final int DELAY = 10;
	
	public AnimationPanel () {
		timer = new Timer(DELAY, this);
		timer.setActionCommand("tick");
		timer.start();
		addMouseListener(this);
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
	
	public void addGui(MarineGui gui) {
		guis.add(gui);
	}
	public void setGame(Game game) {
		this.game = game;
	}


	javax.swing.SwingUtilities util;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(util.isLeftMouseButton(e)) {
			System.out.println("Left, " + e.getX() + " : " + e.getY());
		}else if (util.isRightMouseButton(e)) {
			System.out.println("Right, " + e.getX() + " : " + e.getY());
		}
		
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
