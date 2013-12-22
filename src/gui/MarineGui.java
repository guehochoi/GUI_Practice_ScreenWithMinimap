package gui;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import agents.Marine;


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
	
	private String imagedir = "images"+File.separator;
    private String imageFileName = "Marine.png";
    BufferedImage icon;
	
	public MarineGui (Marine marine) {
		xPos = 0; yPos = 0;
		xDestination = 200; yDestination = 200;
		agent = marine;
		
		String imageCaption = "Marine:" +agent.getName();
    	ImageIcon temp = createImageIcon(imagedir + imageFileName, imageCaption);
    	icon = getScaledImage(temp.getImage(), MarineSizeX, MarineSizeY);
	}
	
	protected ImageIcon createImageIcon(String path, String description) {
    	///java.net.URL imgURL = getClass().getResource(path);
		String imgURL = imagedir+imageFileName;
    	
    	//System.out.println(getClass().getResource(path));
    	if(imgURL != null) {
    		return new ImageIcon(imgURL, description);
    	}else {
    		// could not find file
    		//System.out.println("\n\n\nCANNOT FIND THE IMAGE\n\n\n");
    		return null;
    	}
    }
    private BufferedImage getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
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
		//g.drawRect(xPos, yPos, MarineSizeX, MarineSizeY);
		g.drawImage(icon, xPos, yPos, MarineSizeX+5, MarineSizeY+5, null);
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
