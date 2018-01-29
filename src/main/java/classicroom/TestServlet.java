package classicroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "TestServlet", urlPatterns = { "test" }, loadOnStartup = 1)
public class TestServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataBase db = new DataBase();

		HttpSession session = request.getSession(false);
		Object userRank = request.getSession(false).getAttribute("userRank");
		if (userRank.equals("student")) {
			request.getRequestDispatcher("/WEB-INF/jsp/student/test.jsp").forward(request, response);
		} else if (userRank.equals("teacher")) {
			request.getRequestDispatcher("/WEB-INF/jsp/teacher/test.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/student/test.jsp").forward(request, response);

	}
}