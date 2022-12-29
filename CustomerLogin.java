import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class CustomerLogin extends JPanel{
	
	//public static String login_id = "";
	public String id = "";
	public String password ="";
	private String loginResult = "";
	public String customername = "";
	// information for login
	
	//public static LoginInfo info = new LoginInfo();

	JButton btn_Home = new JButton("Home");
	JButton btn_order = new JButton("Order");
	
	public CustomerLogin() {
		
		memberLoginGUI();
		//setTitle("Membership Login Page");
	}
	
	public void memberLoginGUI() {
	
		JLabel l1 = new JLabel();
		l1.setText("ID");
		
		JTextField tf1 = new JTextField(null,20);

		

		JLabel l2 = new JLabel();
		l2.setText("Password");

		JTextField tf2 = new JTextField(null,20);
		
		JButton btn_login = new JButton("Login");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,2,10,20));
		
		panel.add(l1);
		panel.add(tf1);
		panel.add(l2);
		panel.add(tf2);
		
		panel.add(btn_login);
		panel.add(btn_Home);
		panel.add(btn_order);
		btn_order.setVisible(false);
		add(panel);
		
		
		btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent args) {

				id = tf1.getText();
				password = tf2.getText();
				
				
				customername = "";
				// ta.append(name+"님이 로그인되었습니다.");
				String query = "select customername from customer " + "where (customerid = '" + id
						+ "')and (customerpass = '" + password + "');";
				
				//int var = -1;
				
				try {
					Statement stmt = FirstInit.con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					while (rs.next()) {

						customername = rs.getString(1);

					}

					if (customername.isBlank()) {
						// ta.append("Check Id or Password again\n");
						loginResult = "Check Id or Password again\n";
						JOptionPane.showMessageDialog(null,loginResult,"Error",JOptionPane.ERROR_MESSAGE);
						btn_order.setVisible(false);
						
						
						
					}

					else // (!customername.isEmpty())
					{
						loginResult = "Wellcome " + customername + "!!";
						//var = JOptionPane.showConfirmDialog(null,loginResult,"Welcome",JOptionPane.DEFAULT_OPTION);
						JOptionPane.showMessageDialog(null,loginResult,"Welcome!",JOptionPane.INFORMATION_MESSAGE);
						btn_order.setVisible(true);
					
						Main.loginId = id;
					}
					
					
					
					

				} catch (SQLException k) {
					k.printStackTrace();
				}

			}
		});

	}
	
	

}
