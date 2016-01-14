package servlets;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import connector.Connector;
import models.Address;

public class ListAddresses extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
	}
	
	public void doGet(HttpServletRequest request,
	                 HttpServletResponse response)
	         throws ServletException, IOException {
	   response.setContentType("text/html");

	   HttpSession session = request.getSession(true);
	   String username = (String) session.getAttribute("user");
	   ArrayList<Address> addresses = Connector.getAddresses(username);
	   
	   PrintWriter out = response.getWriter();
	   out.println("<table>");
	   out.println("<tr><th>Province</th><th>City</th><th>Zip</th><th>Street</th><th>Apartment</th><th></th><th></th></tr>");
	   for (Address address : addresses) {
		   out.print("<tr>");
		   out.println("<td>" + address.getProvince() + "</td>");
		   out.println("<td>" + address.getCity() + "</td>");
		   out.println("<td>" + address.getZip() + "</td>");
		   out.println("<td>" + address.getStreet() + "</td>");
		   out.println("<td>" + address.getApartment() + "</td>");
		   out.println("<td><a href='/servlet2/address/edit?id=" + address.getId() + "'>Edit</a></td>");
		   out.println("<td><a href='/servlet2/address/delete?id=" + address.getId() + "'>Delete</a></td>");
		   out.print("</tr>");
	   }
	   out.println("</table>");
	   out.println("<a href='/servlet2/address/create'>Add address</a>");
	}
	
	public void destroy()
	{
	   // do nothing.
	}
}
