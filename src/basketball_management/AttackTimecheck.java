package basketball_management;

import javax.swing.*;
import java.lang.Thread.*;

public class AttackTimecheck extends Thread {
	private JLabel attacktimeLabel;
	
	AttackTimecheck(JLabel attacktimeLabel){
		this.attacktimeLabel= attacktimeLabel;
	}
	
	public void run(){
		while(true) {
			int attacktime = 24;
			attacktimeLabel.setText(Integer.toString(attacktime));
			try {
				sleep(1000);
				attacktime--;
				if(attacktime <= 0) {
					attacktime = 24;
				}
			}
			catch(InterruptedException e){
				return;
			}
			
		}
	}

}
