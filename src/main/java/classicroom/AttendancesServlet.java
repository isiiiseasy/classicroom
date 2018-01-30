package classicroom;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AttendancesServlet", urlPatterns = { "attendances" }, loadOnStartup = 1)
public class AttendancesServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException{
		DataBase db = new DataBase();

		String flg = request.getParameter("flg");

		if(flg.equals("1")) {		//変更処理
			int lessonNum = Integer.parseInt(request.getParameter("lessonNum"));
			String userName = request.getParameter("userName");
			int kubun = Integer.parseInt(request.getParameter("attendance_situation"));
			String biko = request.getParameter("note");

			String userId = db.getUserID(userName);

			System.out.println(lessonNum+userName+kubun+biko+userId);

			if(db.UpdateAttendances(lessonNum,userId,kubun,biko)) {
				request.setAttribute("message", "変更完了");
			}else {
				request.setAttribute("message", "変更失敗");
			}
			}
		else {		 		//参照処理
			String lesson = request.getParameter("lessonNum");
			int lessonNum = Integer.parseInt(lesson);

			ServletContext attendances = this.getServletContext();
			Enumeration<String> e = attendances.getAttributeNames();

			while( e.hasMoreElements() ) {

				String userId = e.nextElement();

				if((boolean) attendances.getAttribute(userId).equals("out")) {
					db.SetAttendances(lessonNum, userId);
				}
			}
		}
		List<String> list = db.getAttendancesTable();
		request.setAttribute("attendancestable", list);

		request.getRequestDispatcher("/WEB-INF/jsp/teacher/attendances.jsp").forward(request, response);

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException{

			request.getRequestDispatcher("/WEB-INF/jsp/teacher/attendances.jsp").forward(request, response);
	    }

}