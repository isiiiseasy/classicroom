package classicroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LssonUpServlet", urlPatterns = { "lessonupload" }, loadOnStartup = 1)
public class LessonUpServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object userRank = request.getSession(false).getAttribute("userRank");
		if (userRank.equals("teacher")) {

			DataBase db = new DataBase();

			String subject = db.getSubjectToUpload();
			String lessonname = db.getLessonName();

			request.setAttribute("subject", subject);
			request.setAttribute("lessonname", lessonname);

			request.getRequestDispatcher("/WEB-INF/jsp/teacher/lessonupload.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	 public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
		{
		 DataBase db = new DataBase();

		 String subjectId = request.getParameter("subjectId");
		 String lessonName = request.getParameter("lessonName");

		 if(db.setLesson(subjectId,lessonName)) {
				request.setAttribute("message", "登録完了");
			}else {
				request.setAttribute("message", "登録失敗");
			}

		 String subject = db.getSubjectToUpload();
		 String lessonname = db.getLessonName();

		 request.setAttribute("subject", subject);
		 request.setAttribute("lessonname", lessonname);

		 request.getRequestDispatcher("/WEB-INF/jsp/teacher/lessonupload.jsp").forward(request, response);
		}
}