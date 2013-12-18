
public class Game {
	
	AnimationPanel ap;
	ControlPanel cp;
	
	public void addMarine() {
		Marine m = new Marine("Marine");
		MarineGui g = new MarineGui(m);
	}
	
	public Game(AnimationPanel ap, ControlPanel cp) {
		this.ap = ap;
		this.cp = cp;
	}
}
