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

    	String userID = request.getParameter("userID");
    	String password = request.getParameter("password");
		String user_name = request.getParameter("userName");
		String teacher_flg = request.getParameter("teacherF");

		DataBase DB = new DataBase();

		if(DB.useradd(userID, password,user_name,teacher_flg)) {
			request.setAttribute("message", "登録完了");
		}else {
			request.setAttribute("message", "登録失敗");
		}

		request.getRequestDispatcher("/WEB-INF/useradd.jsp").forward(request, response);

    }
}
