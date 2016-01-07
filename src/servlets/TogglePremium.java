package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connector.Connector;

public class TogglePremium extends HttpServlet {
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
	   out.println("<input type='submit' value='Toggle premium'/>");
	   out.println("</form>");
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String result = Connector.togglePremium(request.getParameter("username"));

		out.println(result);
	}
	
	public void destroy()
	{
	   // do nothing.
	}
}
