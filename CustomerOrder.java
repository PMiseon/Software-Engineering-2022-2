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

public class CustomerOrder extends JPanel {

	public CustomerOrder() {
		orderGui();
	}

	JButton btn_Home = new JButton("Home");
	int point = 0;

	public void orderGui() {

		JPanel cPanel = new JPanel();
		JLabel idL = new JLabel();

		System.out.println(LoginInfo.getId());

		JButton btn_point = new JButton("Point");
		cPanel.setLayout(new GridLayout(1, 2, 5, 5));

		cPanel.add(btn_Home);
		cPanel.add(btn_point);
		
		JButton btn1 = new JButton("Coffee\n5000won");
		JButton btn2 = new JButton("Cake\n6000won");
		JButton btn3 = new JButton("Water\n2000won");
		
		
		//btn1.setSize();
		btn1.setSize(200,200);
		btn2.setSize(200,200);
		btn3.setSize(200,200);
		
		
		//JPanel bPanel = new JPanel();
		//bPanel.add(btn1);
		//bPanel.add(btn2);
		//bPanel.add(btn3);
		//bPanel.setLayout(new GridLayout(3, 1, 10, 10));

		
		JPanel finalPanel = new JPanel();
		finalPanel.setLayout(new GridLayout(4, 1, 10, 10));

		finalPanel.add(cPanel);
		//finalPanel.add(bPanel);
		finalPanel.add(btn1);
		finalPanel.add(btn2);
		finalPanel.add(btn3);
		
		add(finalPanel);

		btn_point.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent args) {

				String query = "select customerpoint from customer " + "where (customerid =" + Main.loginId + ");";

				if (Main.loginId.isBlank()) {
					JOptionPane.showMessageDialog(null, "Logout status.", "Error", JOptionPane.ERROR_MESSAGE);
				}

				else {
					try {
						Statement stmt = FirstInit.con.createStatement();
						ResultSet rs = stmt.executeQuery(query);

						while (rs.next()) {

							point = rs.getInt(1);

						}

						JOptionPane.showMessageDialog(null, "id: " +Main.loginId  +"\n"+point +" point", "POINT", JOptionPane.INFORMATION_MESSAGE);

					} catch (SQLException k) {
						k.printStackTrace();
					}

				}

			}
		});
		
		
		
		btn1.addActionListener(new ActionListener() { //coffee
			@Override
			public void actionPerformed(ActionEvent args) {
				int var = JOptionPane.showConfirmDialog(null,"Coffee 5000won\n Would you like to order?", "Order", JOptionPane.YES_NO_OPTION);
				
				if(var == 0){ //YES 
				String query;
				
				if (!Main.loginId.isBlank()) {
					query = "INSERT INTO Orders(orderdate,orderprice,customerid,menuid,state) values(DATE(sysdate()),5000,"+Main.loginId+",1,0);";
				}
				
				else {
					query = "INSERT INTO Orders(orderdate,orderprice,menuid,state) values(DATE(sysdate()),5000,1,0);";
				}

				{
					try {
						Statement stmt = FirstInit.con.createStatement();
						stmt.executeUpdate(query);

						
						JOptionPane.showMessageDialog(null,"Coffee 5000won\n Order Complete ", "Order complete", JOptionPane.INFORMATION_MESSAGE);

					} catch (SQLException k) {
						k.printStackTrace();
					}

				}

			}}
		});
		
		
		btn2.addActionListener(new ActionListener() { //cake
			@Override
			public void actionPerformed(ActionEvent args) {
				int var = JOptionPane.showConfirmDialog(null,"Cake 6000won\n Would you like to order?", "Order", JOptionPane.YES_NO_OPTION);
				
				if(var == 0){ //YES 
				String query;
				
				if (!Main.loginId.isBlank()) {
					query = "INSERT INTO Orders(orderdate,orderprice,customerid,menuid,state) values(DATE(sysdate()),6000,"+Main.loginId+",2,0);";
				}
				
				else {
					query = "INSERT INTO Orders(orderdate,orderprice,menuid,state) values(DATE(sysdate()),6000,2,0);";
				}

				{
					try {
						Statement stmt = FirstInit.con.createStatement();
						stmt.executeUpdate(query);

						
						JOptionPane.showMessageDialog(null,"Cake 6000won\n Order Complete ", "Order complete", JOptionPane.INFORMATION_MESSAGE);

					} catch (SQLException k) {
						k.printStackTrace();
					}

				}

			}}
		});
		
		btn3.addActionListener(new ActionListener() { //cake
			@Override
			public void actionPerformed(ActionEvent args) {
				int var = JOptionPane.showConfirmDialog(null,"Water 2000won\n Would you like to order?", "Order", JOptionPane.YES_NO_OPTION);
				
				if(var == 0){ //YES 
				String query;
				
				if (!Main.loginId.isBlank()) {
					query = "INSERT INTO Orders(orderdate,orderprice,customerid,menuid,state) values(DATE(sysdate()),2000,"+Main.loginId+",3,0);";
				}
				
				else {
					query = "INSERT INTO Orders(orderdate,orderprice,menuid,state) values(DATE(sysdate()),2000,3,0);";
				}

				{
					try {
						Statement stmt = FirstInit.con.createStatement();
						stmt.executeUpdate(query);

						
						JOptionPane.showMessageDialog(null,"Water 2000won\n Order Complete ", "Order complete", JOptionPane.INFORMATION_MESSAGE);

					} catch (SQLException k) {
						k.printStackTrace();
					}

				}

			}}
		});
		

	}
}
