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

	// ����struts��ת���ĸ�ҳ��ķ���
	public String execute() throws Exception {
		if ("test".equals(this.userName) && "test".equals(this.password))
			return "SUCCESS";
		return "ERROR";
	}
}
