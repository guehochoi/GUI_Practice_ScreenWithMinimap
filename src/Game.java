import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Game {
	
	AnimationPanel ap;
	ControlPanel cp;
	
	private boolean[][] isOccupied = new boolean[Screen.screenSize.width][Screen.screenSize.height];
	
	private List<Marine> marines = new ArrayList<Marine>();
	
	public void addMarine() {
		Marine m = new Marine("Marine");
		MarineGui g = new MarineGui(m);
		
		marines.add(m);
		m.setGui(g);
		ap.addGui(g);
		m.startThread();
	}
	
	public Game(AnimationPanel ap, ControlPanel cp) {
		this.ap = ap;
		this.cp = cp;
		
		for(int i=0; i < Screen.screenSize.width; i++) 
			for(int j=0; j < Screen.screenSize.height; j++)
				isOccupied[i][j] = false;
	}
	
	public boolean isOccupied(Point p) {
		return isOccupied[p.x][p.y];
	}
	
	
	public void moveAll(Point p) {
		for(Marine m : marines) {
			m.msgMove(p);
		}
	}
}
