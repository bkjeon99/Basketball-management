# Basketball-management
The program was developed to run the exact basketball game.
You can use this program if you want to play the exact basketball game

# Purpose
 The program implements functions that are more user friendly than traditional basketball watches.
With this program, you no longer have to carry heavy basketball watches in basketball games. You 
only need to have a laptop. In addition, the program implements more user-friendly features than 
traditional basketball watches.

# How to use
 
 a) Use of clock function
  
  1. Playtime funcion
   The play time is set at 10 minutes per quarter. If 10 minutes have passed, the play time will 
  automatically be set back to 10 minutes and the quarter will increase.The start and stop buttons 
  can be used to influence the play time
  2. Attack time funcion
   If a foul occurs, press the foul button. If the right of attack has changed or if the attack 
  rebound has been captured, press the "Change Attack" button. and the The start and stop buttons 
  also can be used to influence the attack time
   
 b) Enrollment of players
 
  1. Register Player
   You can register with the player's team, back number, score and foul. By Enterbutton. And than the
   JTextField show the record.

# Explain the code
```java
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

```
▶ This class determines the state of a variable Threadlive by following four buttons: 
   Attack, Stop, Replace Attack, and Foul.

```java
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
```
▶ This class is worked by Threadlive. This class is a clock for play time. And show the time.

```java
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
```
▶ This class is also worked by Threadlive. This class is a clock for attack time. And show the time.

```java
public class Player {
	public int foul;
	public int score;
	public int number;
	public int Team;
	
	public Player(int number) {
		this.number = number;
	}
	
	public int returnNumber () {
		return number;
	}
	
	public void changefoul (int a) {
		foul = foul + a;
	}
	public void changescore (int a) {
		score = score + a;
	}
}
```
▶ This is Player class

```java
	class EnterListener extends MouseAdapter {		
			public void mouseClicked(MouseEvent e) {
				NoTeam.setSelected(true);
				Playernumber.setText("");
				NoEvent.setSelected(true);
				if(Playernumstate <=0 || Playernumstate >= 100 ) return;
				
				int state = 0;
				System.out.println(state);
				for(int i = 0; i < count[Teamstate] ; i++) {
					if(Playernumstate == ((Vector<Player>)team[Teamstate]).get(i).number) {
						state = 1;
						
						switch(eventstate) {
						case 0 :
							((Vector<Player>)team[Teamstate]).get(i).changescore(1);
							team_score[Teamstate] += 1;
							break;
						case 1 :
							((Vector<Player>)team[Teamstate]).get(i).changescore(2);
							team_score[Teamstate] += 2;
							break;
						case 2 :
							((Vector<Player>)team[Teamstate]).get(i).changescore(3);
							team_score[Teamstate] +=3;
							break;		
						case 3 :
							((Vector<Player>)team[Teamstate]).get(i).changefoul(1);
						}
					}
				}
				System.out.println(state);
				if(state == 0) {
					Player player = new Player(Playernumstate);
					((Vector<Player>)team[Teamstate]).add(player);
					
					switch(eventstate) {
					case 0 :
						((Vector<Player>)team[Teamstate]).get(count[Teamstate]).changescore(1);
						team_score[Teamstate] += 1;
						break;						
					case 1 :
						((Vector<Player>)team[Teamstate]).get(count[Teamstate]).changescore(2);
						team_score[Teamstate] += 2;
						break;
					case 2 :
						((Vector<Player>)team[Teamstate]).get(count[Teamstate]).changescore(3);
						team_score[Teamstate] += 3;
						break;
					case 3 :
						((Vector<Player>)team[Teamstate]).get(count[Teamstate]).changefoul(1);
					}
					
					count[Teamstate]++;
				}
				if(Teamstate == 0) {
					printer(TeamA_Record, (Vector<Player>)team[0], Teamstate);
				}
				else {
					printer(TeamB_Record, (Vector<Player>)team[1], Teamstate);
				}
				TeamA_R.setText("Team A : " + team_score[0]);
				TeamB_R.setText("Team B : " + team_score[1]);
				
			}
		}
```
▶ This is the most important class in MainFrame. this class is the register player and show the player information 

# Sugestion

When you register the player number you must press enter key and if you register player you can delete sobe careful 
when registering players
