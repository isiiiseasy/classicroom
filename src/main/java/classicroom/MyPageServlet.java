package classicroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyPageServlet", urlPatterns = { "mypage" }, loadOnStartup = 1)
public class MyPageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DataBase db = new DataBase();

		String imgFileName = db.getImgPath((String) request.getSession(false).getAttribute("userId"));

		request.setAttribute("imgFileName",imgFileName);

		Object userRank = request.getSession(false).getAttribute("userRank");
		if (userRank.equals("student")) {
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