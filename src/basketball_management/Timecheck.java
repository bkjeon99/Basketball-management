package basketball_management;

import java.lang.Thread;
import java.awt.*;
import javax.swing.*;

public class Timecheck extends Thread {
	public int playtime; //10분
	private int quarter;
	private JLabel timerLabel;
	private TimebuttonListener tt;
	
	
	public Timecheck(JLabel timerLabel, int playtime, int quarter, TimebuttonListener tt) {
		this.timerLabel = timerLabel;
		this.playtime = playtime;
		this.quarter = quarter;
		this.tt = tt;
	}
	
	@Override
	public void run() {
		//playtime = 6000;
		//quarter = 1;
		int time;
		String min;
		String sec;
		String milsec;
		
		while(true) {
			time = playtime % 10;
			milsec = Integer.toString(time);
			time = playtime / 10 % 60;
			sec = Integer.toString(time);
			time = playtime/600;
			min = Integer.toString(time);
			timerLabel.setText("      "+quarter+"쿼터    " +"0" + min + ":" + sec + ":" + milsec);
			try {
				sleep(100); //1000이 1초 이건 0.1초
				if (playtime <= 0 && quarter != 4) {
					playtime = 6000;
					quarter++;	
					tt.threadlive = 0;
				}
				else if(playtime <= 0 && quarter == 4) {
					timerLabel.setText("Game Over");
					break;
				}				
			}
			catch(InterruptedException e){
				return;
			}
			if(tt.threadlive == 0) { // stop
				
			}
			else if(tt.threadlive == 1) { //start
				playtime--;
			}
			else if(tt.threadlive == 2)	{ //attack change
				playtime--;
			}
			else if(tt.threadlive == 3) { //foul
				
			}
		}
	}

}
