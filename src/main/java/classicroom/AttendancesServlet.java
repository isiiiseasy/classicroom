package classicroom;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AttendancesServlet", urlPatterns = { "attendances" }, loadOnStartup = 1)
public class AttendancesServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataBase db = new DataBase();
																							//System.out.println("a");
		ServletContext attendances = this.getServletContext();
																							//System.out.println("b");
		Enumeration<String> e = attendances.getAttributeNames();
																							//System.out.println("c");
		while( e.hasMoreElements() ) {
																							//System.out.println("d");
			String userId = e.nextElement();
																							//System.out.println("e");

			if((boolean) attendances.getAttribute(userId).equals("out")) {
																							//System.out.println("f");
				db.SetAttendances(000, userId);
																							//System.out.println("g");
			}

			//System.out.println( userId + "=" + attendances.getAttribute(userId));
																							//System.out.println("h");
		}

		db.print();
		request.getRequestDispatcher("/WEB-INF/jsp/teacher/attendances.jsp").forward(request, response);

	}

}