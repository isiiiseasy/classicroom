package classicroom;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CheckNewsServlet", urlPatterns = { "checknews" }, loadOnStartup = 1)
public class CheckNewsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/teacher/CheckNews.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/teacher/CheckNews.jsp").forward(request, response);

		String ntext = request.getParameter("newstext");

        try {
        	OutputStreamWriter osw  = new OutputStreamWriter(new FileOutputStream("newnews.txt",true), "UTF-8");
            BufferedWriter objBw = new BufferedWriter(osw);

            objBw.write(ntext);
            objBw.newLine();
            objBw.close();

        }catch(IOException e) {
        	System.out.println(e);
        }
	}
}