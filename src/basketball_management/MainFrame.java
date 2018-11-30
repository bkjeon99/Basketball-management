package basketball_management;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame{

	//public TimePanel timepanel;
	public MainFrame() {
		setTitle("basketball management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container management = getContentPane();
        management.setLayout(new BorderLayout());
        TimePanel timepanel = new TimePanel();
        management.add(timepanel, BorderLayout.NORTH);
        
        setSize(750, 500);
        setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
