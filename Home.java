import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JPanel {

	JButton admin = new JButton("Admin");
	JButton memberLogin = new JButton("Member Login");
	JButton NonMemberOrder = new JButton("NonMember Order");
	
	JPanel btnPanel = new JPanel();
	
	Home(){
		
		//btnPanel.setBounds(500, 200, 300, 50);
		btnPanel.setLayout(new GridLayout(3, 1, 5, 5));
		btnPanel.add(admin);
		btnPanel.add(memberLogin);
		btnPanel.add(NonMemberOrder);
	
		//setTitle("Cafe Order HOME");
		add(btnPanel);
		
	}
	
}
