package basketball_management;

import java.awt.*;
import javax.swing.*;

public class TimePanel extends JPanel {
	public JButton timepause;
    public JButton foul;
    public JButton reset;
    public JLabel timerLabel2; //Timecheck의 timeLabel과 구분해 주려고 2붙임
    public JLabel attacktimeLabel;
    
	public TimePanel(){
		timerLabel2 = new JLabel();
		timerLabel2.setFont(new Font("Gothic", Font.ITALIC, 80));
		setLayout(new BorderLayout());
		this.add(timerLabel2, BorderLayout.NORTH);
		
		attacktimeLabel = new JLabel();
		//this.add(attacktimeLabel, BorderLayout.CENTER);
		
		
		
		timepause = new JButton("attack change"); //공격시간 재설정
		foul = new JButton("Foul"); // Start 버튼으로 바뀌게 만들기, 공격시간 재설정, 
		reset = new JButton("reset");
	
		setVisible(true);
		Timecheck time = new Timecheck(timerLabel2);
		time.start();
		//AttackTimecheck Att = new AttackTimecheck(attacktimeLabel);
		//Att.start();
	}
}
