package classicroom;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

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

@WebFilter(filterName="LoginFilter",urlPatterns="/*",asyncSupported=true)
public class LoginFilter implements Filter{

	private static final HashSet<String> PUBLIC_PATHS = new HashSet<String>(Arrays.asList("","/login"));

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		return;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest)request;
		HttpServletResponse hres = (HttpServletResponse)response;
		HttpSession session = hreq.getSession();
		String path = hreq.getRequestURI().substring(hreq.getContextPath().length()).replaceAll("[/]+$", "");
        boolean isLoggedIn = (session != null && session.getAttribute("userId") != null);
        boolean isPublic = PUBLIC_PATHS.contains(path);

        if (isLoggedIn || isPublic) {
            chain.doFilter(request, response);
        }
        else {
            hres.sendRedirect(hreq.getContextPath() + "/login");
        }

	}

	@Override
	public void destroy() {
		return;
	}

}
