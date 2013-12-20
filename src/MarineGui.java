import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class MarineGui implements Gui{

	private int xPos, yPos;
	private final int MarineSizeX = 10, MarineSizeY = 10;
	private int xDestination, yDestination;
	private Marine agent;
	
	private enum Command { move, noCommand }
	private class Destination {
		Point p;
		Command command;
		Destination (Point p, Command command) {
			this.p = p;
			this.command = command;
		}
	}
	List<Destination> destinations= new ArrayList<Destination>();
	Command command = Command.noCommand;
	
	public MarineGui (Marine marine) {
		xPos = 0; yPos = 0;
		xDestination = 200; yDestination = 200;
		agent = marine;
	}
	
	@Override
	public void updatePosition() {
		// TODO Auto-generated method stub
		if (xPos < xDestination)
            xPos++;
        else if (xPos > xDestination)
            xPos--;

        if (yPos < yDestination)
            yPos++;
        else if (yPos > yDestination)
            yPos--;
        
//        if (xPos == xDestination && yPos == yDestination) {
//        	if(!destinations.isEmpty()) {
//        		Destination dest = destinations.remove(0);
//        		xDestination = dest.p.x;
//        		yDestination = dest.p.y;
//        		command = dest.command;
//        	}
//        }		//Now, I want units to move even though they do not get to destination
        if(!destinations.isEmpty()) {	//Do I want if statement to check command?
    		Destination dest = destinations.remove(0);
    		xDestination = dest.p.x;
    		yDestination = dest.p.y;
    		command = dest.command;
    	}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.cyan);
		g.drawRect(xPos, yPos, MarineSizeX, MarineSizeY);
	}

	@Override
	public boolean isPresent() {
		if (xPos < AnimationPanel.windowCurrentX) {
			return false;
		}
		if (xPos > AnimationPanel.windowCurrentX + AnimationPanel.windowWidth) {
			return false;
		}
		if (yPos < AnimationPanel.windowCurrentY) {
			return false;
		}
		if (yPos > AnimationPanel.windowCurrentY + AnimationPanel.windowHeight) {
			return false;
		}
		return true;
	}

	public void DoMove(Point p) {
		destinations.add(new Destination(p, Command.move));
	}

	public Point getPosition() {
		return new Point(xPos, yPos);
	}
	
	public Marine getAgent() {
		return agent;
	}

}
