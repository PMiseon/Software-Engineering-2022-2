package swe1207;

//import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

public class Home extends JPanel{
	
	JLabel title = new JLabel("Cafe Order program");
	
	JButton admin = new JButton("Admin");
	JButton member = new JButton("Member");
	JButton nonMember = new JButton("NonMemeber");
	
	JPanel btnPanel = new JPanel();
	
	Home(){
		title.setBounds(480,200,300,50);
		
		btnPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 행수, 열수, 가로간격, 세로간격
		//btnPanel.setBackground(new Color(255, 222, 102));
		
		
		btnPanel.add(member);
		btnPanel.add(nonMember);
		
		btnPanel.add(admin);
		
		add(title);
		add(btnPanel);
	}
}
