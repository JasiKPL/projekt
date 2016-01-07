package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connector.Connector;
import models.User;

public class RegisterUser extends HttpServlet {
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
	   out.println("Confirm Password: <input type='password' name='confirm_password'/><br/>");
	   out.println("Email: <input type='text' name='email'/><br/>");
	   out.println("<input type='submit' value='Register'/>");
	   out.println("</form>");
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		User user = new User();
		user.setType(User.Type.REGULAR);
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setPasswordConfirmation(request.getParameter("confirm_password"));
		user.setEmail(request.getParameter("email"));
		
		String validate = user.validate();
		if (validate != "") {
			out.println(validate);
			return;
		}
		
		String result = Connector.register(user);
		out.println(result);
	}
	
	public void destroy()
	{
	   // do nothing.
	}
}
