package classicroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LssonUpServlet", urlPatterns = { "lessonupload" }, loadOnStartup = 1)
public class LessonUpServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Object userRank = request.getSession(false).getAttribute("userRank");
		if (userRank.equals("teacher")) {

			DataBase db = new DataBase();

			String str = db.getSubjectToUpload();

			request.setAttribute("subject", str);

			request.getRequestDispatcher("/WEB-INF/jsp/teacher/lessonupload.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}
}