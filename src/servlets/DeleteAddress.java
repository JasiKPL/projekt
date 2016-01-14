package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connector.Connector;

public class DeleteAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		
	}
	
	public void doGet(HttpServletRequest request,
	                 HttpServletResponse response)
	         throws ServletException, IOException {
		response.setContentType("text/html");
		
		String id = request.getParameter("id");
		PrintWriter out = response.getWriter();
		String result = Connector.deleteAddress(id);
		out.println(result);
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException {
		
	}
	
	public void destroy()
	{
	   // do nothing.
	}
}
