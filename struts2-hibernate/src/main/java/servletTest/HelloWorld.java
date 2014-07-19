package servletTest;

public class HelloWorld {

	public String getMessage(String name) {
		String message = "";
		if (null != name && !"".equals(name)) {
			message = "Hello " + name;
		}
		return message;
	}
}
