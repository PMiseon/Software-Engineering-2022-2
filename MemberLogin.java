package swe1207;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MemberLogin extends JFrame {

	public String id;
	public String password;
	public String customername = "";
	private String loginResult = "";
	// information for login

	public MemberLogin() {
		memberLoginGui();
		setTitle("Membership Login Page");
	}

	
	public void memberLoginGui() {

		// setLayout(null);

		JLabel labelID = new JLabel();
		labelID.setText("ID");
		labelID.setSize(100, 30);
		labelID.setLocation(20, 10);
		add(labelID);

		JTextField tf1 = new JTextField();
		tf1.setSize(200, 30);
		tf1.setLocation(100, 10);
		add(tf1);

		JLabel l2 = new JLabel();
		l2.setSize(100, 30);
		l2.setLocation(20, 50);
		add(l2);
		l2.setText("Password");

		JTextField tf2 = new JTextField();
		tf2.setSize(200, 30);
		tf2.setLocation(100, 50);
		add(tf2);

		/*
		 * JTextArea ta = new JTextArea(); // 출력창 JScrollPane jsp = new JScrollPane(ta);
		 * jsp.setSize(1220, 190); jsp.setLocation(20, 200);
		 * 
		 * add(jsp);
		 */

		JButton btn_login = new JButton("Login");
		add(btn_login);
		btn_login.setBounds(450, 10, 100, 30);
		btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent args) {

				id = tf1.getText();
				password = tf2.getText();
				
				
				// ta.append(name+"님이 로그인되었습니다.");
				String query = "select customername from customer " + "where (customerid = '" + id
						+ "')and (customerpass = '" + password + "');";
				try {
					Statement stmt = FirstInit.con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					while (rs.next()) {

						customername = rs.getString(1);

					}

					if (customername.isBlank()) {
						// ta.append("Check Id or Password again\n");
						loginResult = "Check Id or Password again\n";
					}

					else // (!customername.isEmpty())
					{
						loginResult = "Wellcome " + customername + "!!";
						// ta.append(customername + "님 환영합니다.\n");
						// jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
					}
					
					JOptionPane.showMessageDialog(null,loginResult);

				} catch (SQLException k) {
					k.printStackTrace();
				}

			}
		});

	}

}
