package classicroom;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "MyPageServlet", urlPatterns = { "my_results.csv" }, loadOnStartup = 1)
public class ResultCSVServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataBase db = new DataBase();
		String userId = (String) request.getSession(false).getAttribute("userId");
		ResultSet result = db.getUserResult(userId);
		response.setContentType("text/csv;charset=utf-8");
		PrintWriter writer=response.getWriter();

		writer.println("test_name,my_point,average_point");
		try {
			while(result!=null&&result.next()) {
				writer.println(result.getString("test_name")+","+result.getInt("my_point")+","+result.getInt("average_point"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}