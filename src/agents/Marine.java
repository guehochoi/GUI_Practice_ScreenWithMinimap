package agents;
import gui.MarineGui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

import Agent.Agent;




public class Marine extends Agent{

	String name;
	
	/*		Data		*/
	private enum Objective { Move }
	private class Command {
		Objective obj;
		Point p;
		Command(Objective obj, Point p) {
			this.obj = obj;
			this.p = p;
		}
	}
	List<Command> commands = 
			Collections.synchronizedList(new ArrayList<Command>());
	
	MarineGui gui;
	
	Semaphore atDest = new Semaphore(0, true);
	
	/*		Message		*/
	
	public void msgMove(Point p) {
		commands.add(new Command(Objective.Move, p));
		stateChanged();
	}
	
	/*		Scheduler	*/
	@Override
	protected boolean pickAndExecuteAnAction() {
		
		Command temp = null;
		
		synchronized(commands) {
		for(Command c : commands) {
			if (c.obj == Objective.Move) {
				temp = c;
			}
		}
		} if(temp != null) { move(temp); return true; }
		
		
		return false;
	}
	
	/*		Action		*/
	
	private void move(Command c) {
		
		gui.DoMove(c.p);
		
		commands.remove(c);
	}
	
	/*		Utilities	*/
	public Marine(String name) {
		this.name = name;
	}
	public void setGui(MarineGui gui) {
		this.gui = gui;
	}
	public String getName() {
		return this.name;
	}
}
