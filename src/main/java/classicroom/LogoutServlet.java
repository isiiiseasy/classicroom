package classicroom;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LogoutServlet", urlPatterns = { "/logout" }, loadOnStartup = 1)
public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String userId = (String) session.getAttribute("userId");

		ServletContext attendances = this.getServletContext();

		attendances.setAttribute(userId,"out");

		if(session != null) {
			session.invalidate();
		}
		request.getRequestDispatcher("/WEB-INF/jsp/logoutpage.jsp").forward(request, response);
	}

}