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

		try { /* ����̹��� ã�� ���� */
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("����̹� �ε� ����");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try { /* �����ͺ��̽��� �����ϴ� ���� */
			System.out.println("�����ͺ��̽� ���� �غ�...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("�����ͺ��̽� ���� ����");
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

	// ������ Main�� �ϳ��� �гξ��� �߰��ؼ� ������
	public void change(String btnStr) {
		if (btnStr.equals("Admin")) {
			getContentPane().removeAll(); // ���� �г��� ã�� ����
			getContentPane().add(adminCheckPanel);
			revalidate();
			repaint();
		} else if (btnStr.equals("Member Login")) {
			getContentPane().removeAll();
			getContentPane().add(customerLoginPanel);
			revalidate();
			repaint();
		} else if (btnStr.equals("NonMember Order")) {
			getContentPane().removeAll(); // ���� �г��� ã�� ����
			getContentPane().add(customerOrderPanel);
			Main.loginId = ""; //NonMember �̹Ƿ� ���� �ʱ�ȭ 
			revalidate();
			repaint();
		} else if (btnStr.equals("Home")) {
			getContentPane().removeAll(); // ���� �г��� ã�� ����
			getContentPane().add(homePanel);
			revalidate();
			repaint();
		}else if (btnStr.equals("Order")) {
			getContentPane().removeAll(); // ���� �г��� ã�� ����
			getContentPane().add(customerOrderPanel);
			revalidate();
			repaint();
		}
		/*
		 * else if (btnStr.equals("�Է�  /  ����  /  ����")) { getContentPane().removeAll();
		 * // ���� �г��� ã�� ���� getContentPane().add(crudPanel); revalidate(); repaint(); }
		 */
	}

	public static void main(String args[]) {
		
		//public static LoginInfo info = new LoginInfo();
		
		new FirstInit();
		new Main();
	}
}
