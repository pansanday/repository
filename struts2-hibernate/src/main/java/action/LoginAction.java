package action;

public class LoginAction {

	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// 控制struts跳转到哪个页面的方法
	public String execute() throws Exception {
		if ("test".equals(this.userName) && "test".equals(this.password))
			return "SUCCESS";
		return "ERROR";
	}
}
