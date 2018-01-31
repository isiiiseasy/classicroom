package classicroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MyPageServlet", urlPatterns = { "mypage" }, loadOnStartup = 1)
public class MyPageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DataBase db = new DataBase();

		HttpSession session = request.getSession(false);
		String userId = (String) request.getSession(false).getAttribute("userId");

		String imgFileName = db.getImgFileName((String)session.getAttribute("userId"));

		request.setAttribute("imgFileName",imgFileName);

		Object userRank = request.getSession(false).getAttribute("userRank");
		if (userRank.equals("student")) {

			int AttendanceSituation[] = new int[3];
			AttendanceSituation = db.AttendanceSituationCount(userId);

			request.setAttribute("kesseki",AttendanceSituation[0]);
			request.setAttribute("tikoku",AttendanceSituation[1]);
			request.setAttribute("soutai",AttendanceSituation[2]);

			request.getRequestDispatcher("/WEB-INF/jsp/student/mypage.jsp").forward(request, response);
		} else if (userRank.equals("teacher")) {
			request.getRequestDispatcher("/WEB-INF/jsp/teacher/mypage.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}