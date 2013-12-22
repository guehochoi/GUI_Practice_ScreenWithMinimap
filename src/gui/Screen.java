package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import core.Game;


public class Screen extends JFrame {//implements ActionListener {
	private AnimationPanel animationPanel;
	private ControlPanel controlPanel;
	
	public final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
//	private Timer timer;
//	private static final int DELAY = 10;
	
	public Screen() {
		
		animationPanel = new AnimationPanel();
		controlPanel = new ControlPanel();
		Game game = new Game(animationPanel, controlPanel);
		animationPanel.setGame(game);
		controlPanel.setGame(game);
		
		
		Dimension dim = new Dimension(screenSize.width/2, screenSize.height/2);
		setSize(dim);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		setLocation(screenSize.width/4, screenSize.height/4);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension aniDim = new Dimension(dim.width, dim.height/3*2);
		animationPanel.setBackground(Color.black);
		animationPanel.setSize(aniDim);
		animationPanel.setPreferredSize(aniDim);
		animationPanel.setMinimumSize(aniDim);
		animationPanel.setMaximumSize(aniDim);
		controlPanel.setBackground(Color.yellow);
		Dimension conDim = new Dimension(dim.width, dim.height/3*1);
		controlPanel.setSize(conDim);
		controlPanel.setPreferredSize(conDim);
		controlPanel.setMinimumSize(conDim);
		controlPanel.setMaximumSize(conDim);
		
		
//		setLayout(new BorderLayout());
//		add(animationPanel, BorderLayout.CENTER);
//		add(controlPanel, BorderLayout.SOUTH);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		add(animationPanel, c);
		c.gridy = 1;
		add(controlPanel, c);
		
		setVisible(true);
		
//		timer = new Timer(DELAY, this);
//		timer.setActionCommand("tick");
//		timer.start();
		
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if(e.getActionCommand()=="tick") {
//			animationPanel.tick();
//		}
//	}
}

