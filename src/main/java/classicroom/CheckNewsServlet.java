package classicroom;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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


		String ntext = request.getParameter("newstext");


			    File file = new File("newnews.txt");

			    if (file.exists()){
			      if (file.delete()){
			        System.out.println("ファイルを削除しました");
			      }else{
			        System.out.println("ファイルの削除に失敗しました");
			      }
			    }else{
			      System.out.println("ファイルが見つかりません");
			    }



        try {
        	OutputStreamWriter osw  = new OutputStreamWriter(new FileOutputStream("newnews.txt",true), "UTF-8");
            BufferedWriter objBw = new BufferedWriter(osw);

            objBw.write(ntext);
            objBw.newLine();
            objBw.close();

        }catch(IOException e) {
        	System.out.println(e);
        }

        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("  yyyy/MM/dd  ");
        String dateonly = (sdf.format(date.getTime()));
        String username = (String) request.getSession(false).getAttribute("userName");
        StringBuffer buf = new StringBuffer();
        buf.append(ntext);
        buf.append(username);
        buf.append(dateonly);
        String text = buf.toString();

        try {
        	OutputStreamWriter osw2  = new OutputStreamWriter(new FileOutputStream("newslog.txt",true), "UTF-8");
            BufferedWriter objBw2 = new BufferedWriter(osw2);

            objBw2.write(text);
            objBw2.newLine();
            objBw2.close();

        }catch(IOException e) {
        	System.out.println(e);
        }

        request.getRequestDispatcher("/WEB-INF/jsp/teacher/CheckNews.jsp").forward(request, response);
	}
}