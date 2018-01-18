package classicroom;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ResultServlet", urlPatterns = { "result" }, loadOnStartup = 1)
public class ResultServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/student/ResultTest.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String que1 = request.getParameter("que-1");
		String que2 = request.getParameter("que-2");
		String que3 = request.getParameter("que-3");
		String que4 = request.getParameter("que-4");

		int score=0;


		if(que1.equals("no2")) {
		  score+=25;
		}
		if(que2.equals("no4")) {
		  score+=25;
		}
		if(que3.equals("no3")) {
		  score+=25;
		}
		if(que4.equals("no1")) {
		  score+=25;
		}

		PrintWriter out = response.getWriter();

		request.setAttribute("score", Integer.toString(score));      //getParameter

		out.println(score);  //ここのscoreはちゃんときてる

		request.getRequestDispatcher("/WEB-INF/jsp/student/ResultTest.jsp").forward(request, response);





		out.close();





	}
}