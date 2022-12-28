package swe1207;

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

public class Main extends JFrame implements ActionListener {

	public Home homePanel = new Home();
	public AdminLogin adminLoginPanel = new AdminLogin();

	public MemberLogin memberPanel = new MemberLogin();
	public JFrame MemberLoginWindow = new JFrame("Membership Login");

	Main() {
		setTitle("CAFE ORDER");
		setSize(1270, 450); // �ʼ�. ������ â ������
		setLocationRelativeTo(null); // �������� ����� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		homePanel.admin.addActionListener(this);
		homePanel.member.addActionListener(this);

		//adminPanel.showAll.addActionListener(this);
		//adminPanel.crud.addActionListener(this);
		
		add(homePanel); // Main �����ӿ� home �г� �߰�
		setVisible(true); // �ʼ�
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnStr = e.getActionCommand();

		change(btnStr);
	}

	public void change(String btnStr) {

		/*
		 * if (btnStr.equals("������ ���")) { getContentPane().removeAll(); // ���� �г��� ã�� ����
		 * getContentPane().add(adminPanel); revalidate(); repaint(); }
		 */
		// else
		if (btnStr.equals("Member")) {

			getContentPane().removeAll();
			getContentPane().add(memberPanel);
			revalidate();
			repaint();
		}
		/*
		 else if (btnStr.equals("Admin")) { getContentPane().removeAll(); 
		 // ���� �г��� ã�� ���� 
		 getContentPane().add(adminLoginPanel); 
		 revalidate(); 
		 repaint(); } 
		
		 else if
		 (btnStr.equals("�Է�  /  ����  /  ����")) { getContentPane().removeAll(); 
		 // ���� �г���ã�� ���� 
		 getContentPane().add(crudPanel); 
		 revalidate(); repaint(); }
		 */
		 
	}

	public static void main(String args[]) {
		new FirstInit();
		new Main();
	}
}
