package filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import connector.Connector;
import models.User;

public class TogglePremiumFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(true);
		String username = (String) session.getAttribute("user");
		
		if (username.isEmpty()) {
			res.sendError(401);
		}
		User user = Connector.getUser(username);
		if (!user.isAdmin()) {
			res.sendError(401);
		}
		filter.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
