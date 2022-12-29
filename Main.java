import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;



class FirstInit { // connect mySQL
	static Connection con;

	FirstInit() {
		MySQLConnect();
		// Admin.resetDB();
	}
	
	

	public void MySQLConnect() {
		String Driver = "";
		String url = "jdbc:mysql://localhost:3306/cafeorder?&serverTimezone=Asia/Seoul";
		String userid = "cafeorder";
		String pwd = "cafeorder";

		try { /* 드라이버를 찾는 과정 */
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try { /* 데이터베이스를 연결하는 과정 */
			System.out.println("데이터베이스 연결 준비...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {


	public Home homePanel = new Home();
	public AdminCheckOrder adminCheckPanel = new AdminCheckOrder();
	public CustomerLogin customerLoginPanel = new CustomerLogin();
	public CustomerOrder customerOrderPanel = new CustomerOrder();
	
	
	public static String loginId = "";
	
	Main() {

		setTitle("CAFE ORDER");
		setSize(700, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// this
		homePanel.admin.addActionListener(this);
		homePanel.memberLogin.addActionListener(this);
		homePanel.NonMemberOrder.addActionListener(this);
		
		customerLoginPanel.btn_Home.addActionListener(this);
		customerLoginPanel.btn_order.addActionListener(this);
		
		customerOrderPanel.btn_Home.addActionListener(this);
		
		adminCheckPanel.btn_Home.addActionListener(this);
		
		add(homePanel);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnStr = e.getActionCommand();

		change(btnStr);
	}

	// 프레임 Main에 하나의 패널씩만 추가해서 보여줌
	public void change(String btnStr) {
		if (btnStr.equals("Admin")) {
			getContentPane().removeAll(); // 현재 패널을 찾아 지움
			getContentPane().add(adminCheckPanel);
			revalidate();
			repaint();
		} else if (btnStr.equals("Member Login")) {
			getContentPane().removeAll();
			getContentPane().add(customerLoginPanel);
			revalidate();
			repaint();
		} else if (btnStr.equals("NonMember Order")) {
			getContentPane().removeAll(); // 현재 패널을 찾아 지움
			getContentPane().add(customerOrderPanel);
			Main.loginId = ""; //NonMember 이므로 정보 초기화 
			revalidate();
			repaint();
		} else if (btnStr.equals("Home")) {
			getContentPane().removeAll(); // 현재 패널을 찾아 지움
			getContentPane().add(homePanel);
			revalidate();
			repaint();
		}else if (btnStr.equals("Order")) {
			getContentPane().removeAll(); // 현재 패널을 찾아 지움
			getContentPane().add(customerOrderPanel);
			revalidate();
			repaint();
		}
		/*
		 * else if (btnStr.equals("입력  /  삭제  /  변경")) { getContentPane().removeAll();
		 * // 현재 패널을 찾아 지움 getContentPane().add(crudPanel); revalidate(); repaint(); }
		 */
	}

	public static void main(String args[]) {
		
		//public static LoginInfo info = new LoginInfo();
		
		new FirstInit();
		new Main();
	}
}
