package basketball_management;

import java.lang.Thread;
import java.awt.*;
import javax.swing.*;

public class Timecheck extends Thread {
	public int playtime; //10분
	public int quarter;
	private JLabel timerLabel;
	
	
	public Timecheck(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}
	
	@Override
	public void run() {
		playtime = 6000;
		quarter = 1;
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
			timerLabel.setText("0" + min + ":" + sec + ":" + milsec);
			try {
				sleep(100); //1000이 1초 이건 0.1초
				playtime = playtime - 1;
				if (playtime <= 0 && quarter != 4) {
					playtime = 6000;
					quarter++;	
				}
				else if(playtime <= 0 && quarter == 4) {
					timerLabel.setText("Game Over");
					break;
				}
				
			}
			catch(InterruptedException e){
				return;
			}
					
		}
	}

}
