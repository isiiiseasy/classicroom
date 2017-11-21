package classicroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Useradd", urlPatterns = { "useradd" }, loadOnStartup = 1)
public class Useradd extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/useradd.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

    	String userId = request.getParameter("userId");
    	String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String teacherFlg = request.getParameter("teacherFlg");

		DataBase db = new DataBase();

		if(db.useradd(userId, password,userName,teacherFlg)) {
			request.setAttribute("message", "登録完了");
		}else {
			request.setAttribute("message", "登録失敗");
		}

		request.getRequestDispatcher("/WEB-INF/jsp/useradd.jsp").forward(request, response);

    }
}
