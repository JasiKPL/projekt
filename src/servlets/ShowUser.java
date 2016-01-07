package servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import connector.Connector;
import models.User;

public class ShowUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
	}
	
	public void doGet(HttpServletRequest request,
	                 HttpServletResponse response)
	         throws ServletException, IOException {
	   response.setContentType("text/html");
	
	   HttpSession session = request.getSession(true);
	   String username = (String) session.getAttribute("user");
	   User user = Connector.getUser(username);
	   
	   PrintWriter out = response.getWriter();
	   out.println("Username: " + user.getUsername() + "<br/>");
	   out.println("Email: " + user.getEmail() + "<br/>");
	   out.println("Is premium: " + user.isPremium() + "<br/>");
	}
	
	public void destroy()
	{
	   // do nothing.
	}
}
