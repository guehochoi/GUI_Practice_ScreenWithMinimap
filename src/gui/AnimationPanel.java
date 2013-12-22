package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import core.Game;
import agents.Marine;
import gui.MarineGui;

public class AnimationPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{

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
	
	private boolean isDragEnabled = false;
	private boolean isMouseInside = false;
	
	public AnimationPanel () {
		timer = new Timer(DELAY, this);
		timer.setActionCommand("tick");
		timer.start();
		addMouseListener(this);
		addMouseMotionListener(this);
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
        
        if (isDragEnabled) {
        	if (isMouseInside) {
        		g2.setColor(Color.DARK_GRAY);
        		//horizontal
        		g2.drawLine(start.x, start.y, current.x, start.y);
        		g2.drawLine(start.x, current.y, current.x, current.y);
        		//vertical
        		g2.drawLine(current.x, start.y, current.x, current.y);
        		g2.drawLine(start.x, start.y, start.x, current.y);
        		// use expanding, to fill
        	}
        }
        
        repaint();
	}
	
	public void addGui(MarineGui gui) {
		guis.add(gui);
	}
	public void setGame(Game game) {
		this.game = game;
	}


	javax.swing.SwingUtilities util;
	Point start = new Point();
	Point current = new Point();
	Point end = new Point();
	List<Marine> dragSelectedUnits = new ArrayList<Marine>();
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(util.isLeftMouseButton(e)) {
			//System.out.println("Left, " + e.getX() + " : " + e.getY());
		}else if (util.isRightMouseButton(e)) {
			//System.out.println("Right, " + e.getX() + " : " + e.getY());
			//game.moveAll(new Point(e.getX(), e.getY()));
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		isMouseInside = true;
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		isMouseInside = false;
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(util.isLeftMouseButton(e)) {
			start = e.getPoint();
			isDragEnabled = true;
		}else if (util.isRightMouseButton(e)) {
			
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(util.isLeftMouseButton(e)) {
			end = e.getPoint();
			dragSelectedUnits = game.selectUnits(start, end); 
			isDragEnabled = false;
		}else if (util.isRightMouseButton(e)) {
			//game.moveAll(new Point(e.getX(), e.getY()));
			if (dragSelectedUnits.isEmpty()) {
				System.out.println("None selected");
			}else {
				for(Marine m : dragSelectedUnits) {
					m.msgMove(e.getPoint());
				}
			}
		}
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		current = e.getPoint();
		//System.out.println(isDragEnabled + ", " + isMouseInside + ", " + current);
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		current = e.getPoint();
		//System.out.println(isDragEnabled + ", " + isMouseInside + ", " + current);
	}
	
}
