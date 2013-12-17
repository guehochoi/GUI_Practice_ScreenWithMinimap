import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;




public class Marine extends Agent{

	/*		Data		*/
	private enum Objective { MoveLeft, MoveRight, MoveDown, MoveUp}
	private class Command {
		Objective obj;
		Command(Objective obj) {
			this.obj = obj;
		}
	}
	List<Command> commands = 
			Collections.synchronizedList(new ArrayList<Command>());
	
	MarineGui gui;
	
	Semaphore atDest = new Semaphore(0, true);
	
	/*		Message		*/
	
	public void msgMoveLeft() {
		commands.add(new Command(Objective.MoveLeft));
	}
	public void msgMoveRight() {
		commands.add(new Command(Objective.MoveRight));
	}
	public void msgMoveDown() {
		commands.add(new Command(Objective.MoveDown));
	}
	public void msgMoveUp() {
		commands.add(new Command(Objective.MoveUp));
	}
	public void msgAtDest() {
		atDest.release();
	}
	
	/*		Scheduler	*/
	@Override
	protected boolean pickAndExecuteAnAction() {
		
		Command temp = null;
		
		synchronized(commands) {
		for(Command c : commands) {
			if (c.obj == Objective.MoveDown || c.obj == Objective.MoveUp ||
					c.obj == Objective.MoveLeft ||c.obj == Objective.MoveRight) {
				temp = c;
			}
		}
		} if(temp != null) { move(temp); return true; }
		
		
		return false;
	}
	
	/*		Action		*/
	
	private void move(Command c) {
		switch(c.obj) {
			case MoveDown:
				gui.DoMoveDown(); break;
			case MoveUp:
				gui.DoMoveUp(); break;
			case MoveLeft:
				gui.DoMoveLeft(); break;
			case MoveRight:
				gui.DoMoveRight(); break;
			default: break;
		}
		commands.remove(c);
	}
	
	/*		Utilities	*/
}
