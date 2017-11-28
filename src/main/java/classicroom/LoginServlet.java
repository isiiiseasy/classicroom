package classicroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = { "login" }, loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/loginpage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataBase DB = new DataBase();

		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
		if (userID == null || userID.equals("") || password == null || password.equals("")) {
			request.setAttribute("warning", "ユーザーIDとパスワードを入力してください");
			request.getRequestDispatcher("/WEB-INF/jsp/loginpage.jsp").forward(request, response);
		} else if (!(DB.auth(userID, password))) {
			request.setAttribute("warning", "ユーザーIDかパスワードが間違っています");
			request.getRequestDispatcher("/WEB-INF/jsp/loginpage.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession(false);
			if(session!=null) {
				session.invalidate();
			}
			session = request.getSession(true);
			session.setAttribute("userID", userID);

			if(DB.getRank(userID)) {
				session.setAttribute("userRank","teacher");
			}else {
				session.setAttribute("userRank","student");
			}
			response.sendRedirect("mypage");
		}
	}
}