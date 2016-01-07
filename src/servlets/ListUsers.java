package servlets;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import connector.Connector;
import models.User;

public class ListUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
	}
	
	public void doGet(HttpServletRequest request,
	                 HttpServletResponse response)
	         throws ServletException, IOException {
	   response.setContentType("text/html");
	
	   ArrayList<User> users = Connector.getUsers();
	   
	   PrintWriter out = response.getWriter();
	   out.println("<table>");
	   out.println("<tr><th>Username</th><th>Email</th><th>Premium</th></tr>");
	   for (User user : users) {
		   out.print("<tr>");
		   out.println("<td>" + user.getUsername() + "</td>");
		   out.println("<td>" + user.getEmail() + "</td>");
		   out.println("<td>" + user.isPremium() + "</td>");
		   out.print("</tr>");
	   }
	   out.println("</table>");
	}
	
	public void destroy()
	{
	   // do nothing.
	}
}
