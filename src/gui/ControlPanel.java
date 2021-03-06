package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import core.Game;

public class ControlPanel extends JPanel implements ActionListener {

	private Game game;
	
	JButton addMarine = new JButton("AddMarine");
	public ControlPanel() {
		addMarine.setActionCommand("addMarine");
		addMarine.addActionListener(this);
		add(addMarine);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "addMarine") {
			game.addMarine();
		}
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
}
