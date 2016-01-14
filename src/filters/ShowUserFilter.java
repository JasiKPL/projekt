package filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class ShowUserFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(true);
		String username = (String) session.getAttribute("user");
		
		if (username == null || username.isEmpty()) {
			res.sendRedirect("/servlet2/login");
			return;
		}
		filter.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
