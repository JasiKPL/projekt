package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connector.Connector;
import models.User;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
	}
	
	public void doGet(HttpServletRequest request,
	                 HttpServletResponse response)
	         throws ServletException, IOException {
	   response.setContentType("text/html");
	
	   PrintWriter out = response.getWriter();
	   out.println("<form method='post'>");
	   out.println("Username: <input type='text' name='username'/><br/>");
	   out.println("Password: <input type='password' name='password'/><br/>");
	   out.println("<input type='submit' value='Login'/>");
	   out.println("</form>");
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		User user = Connector.authenticate(
				request.getParameter("username"), request.getParameter("password"));
		if (user == null) {
			out.println("Wrong username or password");
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("user", user.getUsername());
		
		out.println("Successfully logged in");
	}
	
	public void destroy()
	{
	   // do nothing.
	}
}
