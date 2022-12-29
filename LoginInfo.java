
public class LoginInfo {

	public static String id = "";
	public static String name = "";
	
	public LoginInfo() {
		
	}
	
	void setId(String id) {
		LoginInfo.id = id;
	}
	
	void setName(String name) {
		LoginInfo.name = name;
	}
	
	 public static String getId() {
		return LoginInfo.id;
	}
	
	public static String getName() {
		return LoginInfo.name;
	}
	
	
}
