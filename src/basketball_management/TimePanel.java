package basketball_management;

import java.awt.*;
import javax.swing.*;

public class TimePanel extends JPanel {
	public JButton attackchange;
    public JButton foul;
    public JButton start;
    public JButton stop;
    public JLabel timerLabel2; //Timecheck의 timeLabel과 구분해 주려고 2붙임
    public JLabel attacktimeLabel;
    public JPanel southpanel;
    public int playtime = 6000;
    public int quarter;
    
	public TimePanel(){
		setLayout(new BorderLayout());
		timerLabel2 = new JLabel();
		timerLabel2.setFont(new Font("Gothic", Font.ITALIC, 80));
		this.add(timerLabel2, BorderLayout.NORTH);
		
		attacktimeLabel = new JLabel();
		timerLabel2.setFont(new Font("Gothic", Font.ITALIC, 40));
		this.add(attacktimeLabel, BorderLayout.CENTER);
		
		
		
		attackchange = new JButton("attack change"); //attacktime 재설정, 버튼누르면 멈췄다가 시작
		foul = new JButton("Foul"); // Start 버튼으로 바뀌게 만들기, 공격시간 재설정, 
		start = new JButton("start");
		stop = new JButton("stop");
		
		southpanel = new JPanel();
		southpanel.setLayout(new FlowLayout());
		
		southpanel.add(start);
		southpanel.add(stop);
		
		this.add(attackchange, BorderLayout.EAST);
		this.add(foul, BorderLayout.WEST);
		this.add(southpanel, BorderLayout.SOUTH);
		
		TimebuttonListener tt = new TimebuttonListener(this);
		start.addActionListener(tt);
		stop.addActionListener(tt);
		attackchange.addActionListener(tt);
		foul.addActionListener(tt);
		
		Timecheck time = new Timecheck(timerLabel2, playtime, 1, tt);
		time.start();
		AttackTimecheck Att = new AttackTimecheck(attacktimeLabel, tt);
		Att.start();
		
	
	}
}
