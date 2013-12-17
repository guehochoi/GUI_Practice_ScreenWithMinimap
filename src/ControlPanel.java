
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ControlPanel extends JPanel implements ActionListener {

	
	JButton addMarine = new JButton("AddMarine");
	public ControlPanel() {
		addMarine.setActionCommand("addMarine");
		addMarine.addActionListener(this);
		add(addMarine);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "addMarine") {
			
		}
	}
	
}
