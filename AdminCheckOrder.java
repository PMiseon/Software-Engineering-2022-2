import java.awt.FlowLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminCheckOrder extends JPanel {

	public String adminId = "";
	public String adminPW = "";
	public String adminName = "";

	JLabel infoL = new JLabel();
	JButton btn_Home = new JButton("Home");

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel panel = new JPanel();

	public AdminCheckOrder() {
		AdminGui();
	}

	public void AdminGui() {

		JLabel idl = new JLabel();
		idl.setText("ID");
		JTextField idtf = new JTextField(null, 10);

		JLabel pwl = new JLabel();
		pwl.setText("Password");

		JTextField pwtf = new JTextField(null, 10);

		JButton btn_login = new JButton("Login");

		p1.setBounds(10, 10, 500, 20);
		p1.setLayout(new GridLayout(1, 4, 10, 0));
		p1.add(idl);
		p1.add(idtf);
		p1.add(pwl);
		p1.add(pwtf);
		p1.add(btn_login);
		

		infoL.setText("Admin Name : ---");

		JButton btn_inquiry = new JButton("Order Inquiry");
		JButton btn_cus = new JButton("Customer Info");

		p2.setBounds(10, 30, 700, 20);
		p2.setLayout(new GridLayout(1, 3, 10, 0));
		p2.add(infoL);
		p2.add(btn_inquiry);
		p2.add(btn_cus);
		

		JLabel orderidL = new JLabel();
		orderidL.setText("Order ID");

		JTextField orderidtf = new JTextField(null, 5);

		JButton btn_accept = new JButton("ACCEPT");
		JButton btn_decline = new JButton("DECLINE");

		p3.setBounds(10, 50, 700, 20);
		p3.setLayout(new GridLayout(1, 4, 10, 0));
		p3.add(orderidL);
		p3.add(orderidtf);
		p3.add(btn_accept);
		p3.add(btn_decline);
	
	
		JButton btn_clear = new JButton("clear");
		
		p4.setBounds(getVisibleRect());
		p4.setLayout(new GridLayout(1, 2, 10, 0));
		p4.add(btn_Home);
		p4.add(btn_clear);

	
		JTextArea ta = new JTextArea(); // 출력창
		JScrollPane jsp = new JScrollPane(ta);
		jsp.setSize(700,400);
		jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
		ta.append("\n");
		
		panel.setLayout(new GridLayout(4,1,10,10));
		panel.add(p1);
		panel.add(p2);
		panel.add(p3);
		panel.add(p4);
		
		JPanel fpanel = new JPanel();
		fpanel.setSize(750,500);
		fpanel.setLayout(new GridLayout(2,1,10,10));
		fpanel.add(panel);
		fpanel.add(jsp);
		
		add(fpanel);

		btn_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent args) {

				ta.setText("");
			}
		});
		
		
		btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent args) {

				adminId = idtf.getText();
				adminPW = pwtf.getText();

				adminName = "";
				// ta.append(name+"님이 로그인되었습니다.");
				String query = "select managername from manager " + "where (managerid = '" + adminId
						+ "')and (managerpass = '" + adminPW + "');";

				int var = -1;
				String loginResult = "";

				try {
					Statement stmt = FirstInit.con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					while (rs.next()) {

						adminName = rs.getString(1);

					}

					if (adminName.isBlank()) {
						// ta.append("Check Id or Password again\n");
						loginResult = "Check Id or Password again\n";
						JOptionPane.showMessageDialog(null, loginResult, "Error", JOptionPane.ERROR_MESSAGE);
						infoL.setText("Admin Name : ---");
					}

					else // (!customername.isEmpty())
					{
						loginResult = "Wellcome " + adminName + "!!";
						var = JOptionPane.showConfirmDialog(null, loginResult, "Manager", JOptionPane.DEFAULT_OPTION);
						infoL.setText("Admin Name : " + adminName);

					}

				} catch (SQLException k) {
					k.printStackTrace();
				}

			}
		});

		btn_inquiry.addActionListener(new ActionListener() {
			// get information about order

			@Override
			public void actionPerformed(ActionEvent a) {
				//
				// movie_search = tf_search.getText();
				if (adminName.isBlank()) {
					JOptionPane.showMessageDialog(null, "You are not authorized", "Error", JOptionPane.ERROR_MESSAGE);
				}

				else {
					String OrderQuery = "Select * FROM orders";
					try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
						Statement stmt = FirstInit.con.createStatement();
						ResultSet rs = stmt.executeQuery(OrderQuery);
						ta.append("orderid\t date \t price\t customerid\t menu id \t state\t manager id \n");
						jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());

						while (rs.next()) {
							ta.append(rs.getInt(1) + "\t"); // orderid
							ta.append(rs.getString(2) + "\t"); // date
							ta.append(rs.getInt(3) + "\t"); // price
							ta.append(rs.getInt(4) + "\t"); // customerid
							ta.append(rs.getInt(5) + "\t");// menuid
							// ta.append(rs.getInt(6) + "\t"); //state
							if (rs.getInt(6) == 0)
								ta.append("---\t");
							else if (rs.getInt(6) == 1)
								ta.append("Accpeted\t");
							else if (rs.getInt(6) == -1)
								ta.append("Declined\t");
							ta.append(rs.getInt(7) + "\t\n"); // managerid
							jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
						}
						ta.append("----------------------------------------\n");

						jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
						// con.close();
					} catch (SQLException k) {
						k.printStackTrace();
					}
				}

			}

		});

		btn_cus.addActionListener(new ActionListener() {
			// get information about order

			@Override
			public void actionPerformed(ActionEvent a) {
				//
				// movie_search = tf_search.getText();
				if (adminName.isBlank()) {
					JOptionPane.showMessageDialog(null, "You are not authorized", "Error", JOptionPane.ERROR_MESSAGE);
				}

				else {
					String OrderQuery = "Select * FROM Customer";
					try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
						Statement stmt = FirstInit.con.createStatement();
						ResultSet rs = stmt.executeQuery(OrderQuery);
						ta.append("cutomer id\t name\t point\t\n");
						jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());

						while (rs.next()) {
							ta.append(rs.getInt(1) + "\t"); // cutomer id
							ta.append(rs.getString(2) + "\t"); // name
							ta.append(rs.getInt(4) + "\t\n"); // point

							jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
						}
						ta.append("----------------------------------------\n");

						jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
						// con.close();
					} catch (SQLException k) {
						k.printStackTrace();
					}
				}

			}

		});

		btn_accept.addActionListener(new ActionListener() {
			// get information about order

			String orderid = "";
			String customer_id = "";

			@Override
			public void actionPerformed(ActionEvent a) {

				orderid = orderidtf.getText();

				if (orderid.isBlank()) {
					JOptionPane.showMessageDialog(null, "Input Orderid", "Error", JOptionPane.ERROR_MESSAGE);
				}

				else {
					String AcceptQuery1 = "update Orders set state = 1 where (orderid =" + orderid + ");";
					String AcceptQuery2 = "update Orders set managerid = " + adminId + " where (orderid = " + orderid
							+ ");";

					String pointQuery1 = "select customerid from orders where (orderid = " + orderid + ");";
					try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
						Statement stmt = FirstInit.con.createStatement();

						stmt.executeUpdate(AcceptQuery1);
						stmt.executeUpdate(AcceptQuery2);
						ta.append(orderid + ": Accepted\n");

						ResultSet rs = stmt.executeQuery(pointQuery1);
						while (rs.next()) {
							customer_id = rs.getString(1);
						}

						if (!customer_id.isBlank()) {
							String pointQuery2 = "update Customer set customerpoint = Customer.customerpoint + \r\n"
									+ "   ((select orderprice from Orders where (orderid = " + orderid + "))*0.1)\r\n"
									+ "    where customerid = " + customer_id + ";";
							stmt.executeUpdate(pointQuery2);
						}

						jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());

					} catch (SQLException k) {
						k.printStackTrace();
					}
				}

			}

		});

		btn_decline.addActionListener(new ActionListener() {
			// get information about order

			String orderid = "";

			@Override
			public void actionPerformed(ActionEvent a) {

				orderid = orderidtf.getText();

				if (orderid.isBlank()) {
					JOptionPane.showMessageDialog(null, "Input Orderid", "Error", JOptionPane.ERROR_MESSAGE);
				}

				else {
					String AcceptQuery1 = "update Orders set state = -1 where (orderid =" + orderid + ");";
					String AcceptQuery2 = "update Orders set managerid = " + adminId + " where (orderid = " + orderid
							+ ");";
					try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
						Statement stmt = FirstInit.con.createStatement();

						stmt.executeUpdate(AcceptQuery1);
						stmt.executeUpdate(AcceptQuery2);
						ta.append(orderid + ": Declined\n");

						jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());

					} catch (SQLException k) {
						k.printStackTrace();
					}
				}

			}

		});

	}

}