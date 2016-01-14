package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connector.Connector;
import models.Address;

public class CreateAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		
	}
	
	public void doGet(HttpServletRequest request,
	                 HttpServletResponse response)
	         throws ServletException, IOException {
	   response.setContentType("text/html");
	
	   PrintWriter out = response.getWriter();
	   out.println("<form method='post'>");
	   out.println("Province: <select name='province'/>");
	   out.println("<option value='Dolnośląskie'/>Dolnośląskie</option>");
	   out.println("<option value='Kujawsko-pomorskie'/>Kujawsko-pomorskie</option>");
	   out.println("<option value='Lubelskie'/>Lubelskie</option>");
	   out.println("<option value='Lubuskie'/>Lubuskie</option>");
	   out.println("<option value='Łódzkie'/>Łódzkie</option>");
	   out.println("<option value='Małopolskie'/>Małopolskie</option>");
	   out.println("<option value='Mazowieckie'/>Mazowieckie</option>");
	   out.println("<option value='Opolskie'/>Opolskie</option>");
	   out.println("<option value='Podlaskie'/>Podlaskie</option>");
	   out.println("<option value='Pomorskie'/>Pomorskie</option>");
	   out.println("<option value='Śląskie'/>Śląskie</option>");
	   out.println("<option value='Świętokrzyskie'/>Świętokrzyskie</option>");
	   out.println("<option value='Warmińsko-mazurskie'/>Warmińsko-mazurskie</option>");
	   out.println("<option value='Wielkopolskie'/>Wielkopolskie</option>");
	   out.println("<option value='Zachodniopomorskie'/>Zachodniopomorskie</option>");
	   out.println("</select><br/>");
	   out.println("City: <input type='text' name='city'/><br/>");
	   out.println("Zip: <input type='text' name='zip'/><br/>");
	   out.println("Street: <input type='text' name='street'/><br/>");
	   out.println("Apartment: <input type='text' name='apartment'/><br/>");
	   out.println("Type: <select name='type'/>");
	   out.println("<option value='0'/>Permanent</option>");
	   out.println("<option value='1'/>Contact</option>");
	   out.println("<option value='2'/>Work</option>");
	   out.println("</select><br/>");
	   out.println("<input type='submit' value='Create'/>");
	   out.println("</form>");
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException {
		response.setContentType("text/html");
		
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("user");
		PrintWriter out = response.getWriter();
		Address address = new Address();
		address.setUsername(username);
		address.setProvince(request.getParameter("province"));
		address.setCity(request.getParameter("city"));
		address.setZip(request.getParameter("zip"));
		address.setStreet(request.getParameter("street"));
		address.setApartment(request.getParameter("apartment"));
		address.setType(Address.Type.values()[Integer.parseInt(request.getParameter("type"))]);
		String result = Connector.createAddress(address);
		out.println(result);
	}
	
	public void destroy()
	{
	   // do nothing.
	}
}
