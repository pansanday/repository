package servletTest;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = -2425645639457000616L;

	public HelloServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		//super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("<HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("<BODY>");
		out.println(" This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println(" </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		System.out.println("userName is: "+userName);
		HelloWorld helloWorld = new HelloWorld();
		String message = helloWorld.getMessage(userName);
		System.out.println("message value is: "+ message);
		request.setAttribute("message", message);
		System.out.println("request.getAttribute() result is: "+request.getAttribute("message"));
		RequestDispatcher rd = request
				.getRequestDispatcher("/helloTest.jsp");
		rd.forward(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
