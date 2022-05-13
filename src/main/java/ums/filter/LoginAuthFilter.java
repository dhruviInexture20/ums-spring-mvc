package ums.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/"  ,"/login" })
public class LoginAuthFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session=req.getSession(false);
		
		if(session != null) {
			
			String role = (String) session.getAttribute("role");
			
			if(role != null && role.equals("user")) {
				res.sendRedirect("profile");
			}
			else if(role != null && role.equals("admin")) {
				res.sendRedirect("adminDashboard");
			}
			else {
				
				chain.doFilter(request, response);
			}
		}
		else {
				
			chain.doFilter(request, response);
		}	
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
