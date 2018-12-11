package basketball_management;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.lang.Thread;

public class TimebuttonListener implements ActionListener {
	public int threadlive = 0; // 0 stop, 1 start, 2 attack change, 3 foul	
	public int forattchange = 0;
	public int forfoul = 0;
	private TimePanel TimePanel;
	
	public TimebuttonListener(TimePanel TimePanel) {

		this.TimePanel = TimePanel;
	}
	
	public void actionPerformed (ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if(button.getText().equals("start")) {	
			threadlive = 1;
			
		}
		else if(button.getText().equals("stop")) {
			threadlive = 0;
		}
		else if(button.getText().equals("attack change")) {
			threadlive = 2;
			forattchange ++;
		}
		else {
			threadlive = 3;
			forfoul ++;
		}
	}

}
