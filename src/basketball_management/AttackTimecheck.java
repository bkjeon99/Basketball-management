package basketball_management;

import java.lang.Thread;
import java.awt.*;
import javax.swing.*;

public class AttackTimecheck extends Thread{
	private JLabel attacktimelabel;
	private TimebuttonListener tt;
	public int forattchange2 = 1;
	public int forfoul2 = 1;
	
	public AttackTimecheck(JLabel attacktimelabel, TimebuttonListener tt) {
		this.attacktimelabel = attacktimelabel;
		this.tt = tt;
	}
	
	@Override
	public void run() {
		int attacktime = 24;
		while(true) {
			attacktimelabel.setText("      공격시간 : " + Integer.toString(attacktime));
			try {
				Thread.sleep(1000);
				if(attacktime <= 0) {
					attacktime = 24;
					tt.threadlive = 0;
				}
			}
			catch(InterruptedException e) {
				return;
			}
			if(tt.threadlive == 0) { // stop
				
			}
			else if(tt.threadlive == 1) { //start
				
				attacktime--;
			}
			else if(tt.threadlive == 2)	{ //attack change
				if(tt.forattchange == forattchange2) {
					attacktime = 24;
					forattchange2++;
				}
				else attacktime--;
				
			}
			else if(tt.threadlive == 3) { //foul
				if(attacktime <= 14 && tt.forfoul == forfoul2) {
					attacktime = 14;
					forfoul2++;
				}
			}
		}
	}
}

