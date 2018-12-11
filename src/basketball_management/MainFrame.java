package basketball_management;

import java.awt.*;
import java.util.Vector;
import java.awt.event.*;

import javax.swing.*;

public class MainFrame extends JFrame{
	public Object [] team = new Object[2];
	public Vector<Player> team_a = new Vector<>();
	public Vector<Player> team_b = new Vector<>();
 	private int Teamstate;
	private int Playernumstate;
	private int eventstate;
	private int [] count= {0, 1};//입력받은 팀의 선수 명수
	
	public MainFrame() {
		setTitle("basketball management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		team[0] = team_a;
		team[1] = team_b;
		
		Container management = getContentPane();
        management.setLayout(new BorderLayout());
        
        TimePanel timepanel = new TimePanel();
        management.add(timepanel, BorderLayout.NORTH);

        // A팀과 B팀 선수를 보여줄 패널
        JTextArea TeamA_Record = new JTextArea(10, 12);
        JLabel TeamA_R = new JLabel("Team A");
        JPanel Westpanel = new JPanel();
        Westpanel.setLayout(new BorderLayout());
        Westpanel.add(TeamA_R, BorderLayout.NORTH);
        Westpanel.add(new JScrollPane(TeamA_Record), BorderLayout.CENTER);
        
		JTextArea TeamB_Record = new JTextArea(10, 12);
    	JLabel TeamB_R = new JLabel("Team B");
		JPanel Eastpanel = new JPanel();
	    Eastpanel.setLayout(new BorderLayout());
	    Eastpanel.add(TeamB_R, BorderLayout.NORTH);
        Eastpanel.add(new JScrollPane(TeamB_Record), BorderLayout.CENTER);    
		
		management.add(Eastpanel, BorderLayout.EAST);
		management.add(Westpanel, BorderLayout.WEST);
		        
        //RecordPanel BorderLayout.CENTER에 위치, 각종 입력 받아야 됨
        
        //입력할 정보가 A팀인지 B팀인지 
        JPanel RecordPanel = new JPanel();
        
        RecordPanel.setLayout(new BorderLayout());
		ButtonGroup Teambuttongroup = new ButtonGroup();
		
		JRadioButton TeamA = new JRadioButton("Team A");
		JRadioButton TeamB = new JRadioButton("Team B");
		JRadioButton NoTeam = new JRadioButton("");
		
		Teambuttongroup.add(TeamA);
		Teambuttongroup.add(TeamB);
		Teambuttongroup.add(NoTeam);
		
		class TeamListener implements ItemListener{			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(e.getItem() == TeamA){
						Teamstate = 0;
					}
					else {
						Teamstate = 1;
					}
				}
				else return;
			}
		}
        
		TeamListener teamlistener = new TeamListener();
		TeamA.addItemListener(teamlistener);
		TeamB.addItemListener(teamlistener);
		
		JPanel Northpanel = new JPanel();
		Northpanel.add(TeamA);
		Northpanel.add(TeamB);
		
		
		//선수 번호 입력 받음
		JLabel label = new JLabel("선수 번호 입력 후 enterkey를 누르시오");
		JTextField Playernumber = new JTextField(10);
		JPanel Centerpanel = new JPanel();
				
		Centerpanel.setLayout(new BorderLayout());
		class NumListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField num = (JTextField)e.getSource();
				try {
					Playernumstate = Integer.parseInt(num.getText());
					label.setText("선수 번호 입력 후 enterkey를 누르시오");
				} catch (NumberFormatException a) {
					label.setText("올바르지 않은 입력입니다");
				}
			}
		}
		NumListener numlistener = new NumListener();
		Playernumber.addActionListener(numlistener);
						
		Centerpanel.add(label, BorderLayout.NORTH);
		Centerpanel.add(Playernumber, BorderLayout.CENTER);
		
		
		// 선수가 어떤 이벤트를 발생시켰는지 기록
		ButtonGroup Eventgroup = new ButtonGroup();
		
		JRadioButton one = new JRadioButton("1점");
		JRadioButton two = new JRadioButton("2점");
		JRadioButton three = new JRadioButton("3점");
		JRadioButton foul = new JRadioButton("파울");
		JRadioButton NoEvent = new JRadioButton("");
		
		Eventgroup.add(one);
		Eventgroup.add(two);
		Eventgroup.add(three);
		Eventgroup.add(foul);
		Eventgroup.add(NoEvent);
		
		class EventListener implements ItemListener{			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(e.getItem() == one){
						eventstate = 0;
					}
					else if(e.getItem() == two) {
						eventstate = 1;
					}
					else if(e.getItem() == three) {
						eventstate = 2;
					}
					else if(e.getItem() == foul) {
						eventstate = 3;
					}
				}
				else return;
			}
		}
		EventListener eventlistener = new EventListener();
		one.addItemListener(eventlistener);
		two.addItemListener(eventlistener);
		three.addItemListener(eventlistener);
		foul.addItemListener(eventlistener);
						
		JPanel Southpanel = new JPanel();
		Southpanel.add(one);
		Southpanel.add(two);
		Southpanel.add(three);
		Southpanel.add(foul);
		
		//메인프레임의 센터에 레코드패널 붙이기
		RecordPanel.add(Northpanel, BorderLayout.NORTH);
		RecordPanel.add(Centerpanel, BorderLayout.CENTER);
		RecordPanel.add(Southpanel, BorderLayout.SOUTH);
		
		management.add(RecordPanel, BorderLayout.CENTER);
		
		//enterbutton 붙이기
		JButton enter = new JButton("입력");
		
		class EnterListener extends MouseAdapter {		
			public void mouseClicked(MouseEvent e) {
				NoTeam.setSelected(true);
				Playernumber.setText(" ");
				NoEvent.setSelected(true); 
				
				int state = 0;
				System.out.println(state);
				for(int i = 0; i < count[Teamstate] ; i++) {
					if(Playernumstate == ((Vector<Player>)team[Teamstate]).get(i).number) {
						state = 1;
						
						switch(eventstate) {
						case 0 :
							((Vector<Player>)team[Teamstate]).get(i).changescore(1);
							break;
						case 1 :
							((Vector<Player>)team[Teamstate]).get(i).changescore(2);
							break;
						case 2 :
							((Vector<Player>)team[Teamstate]).get(i).changescore(3);
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
						break;
					case 1 :
						((Vector<Player>)team[Teamstate]).get(count[Teamstate]).changescore(2);
						break;
					case 2 :
						((Vector<Player>)team[Teamstate]).get(count[Teamstate]).changescore(3);
						break;
					case 3 :
						((Vector<Player>)team[Teamstate]).get(count[Teamstate]).changefoul(1);
					}
					
					count[Teamstate]++;
				}
				if(Teamstate == 0) {
					printer(TeamA_Record, (Vector<Player>)team[0], count[Teamstate]);
				}
				else {
					printer(TeamA_Record, (Vector<Player>)team[1], count[Teamstate]);
				}
			}
		}
		EnterListener enterlistener = new EnterListener();
		enter.addMouseListener(enterlistener);
		management.add(enter, BorderLayout.SOUTH);
		        
        setSize(500, 550);
        setResizable(false);
        setVisible(true);
	}
	public void printer (JTextArea textarea, Vector<Player> player, int whatteam ) {
		textarea.setText("");
		for(int i = 0; i < count[whatteam] ; i++) {
			String playernum = Integer.toString(player.get(i).returnNumber());
			String playerScore = Integer.toString(player.get(i).score);
			String playerfoul = Integer.toString(player.get(i).foul);
			textarea.append("번호: "+ playernum + "득점: " + playerScore + "파울: " + playerfoul + "\n");
		}
	}
	
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
