package classicroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SubjectAPIServlet", urlPatterns = { "api/subjects" }, loadOnStartup = 1)
public class SubjectsAPIServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataBase db = new DataBase();
		ResultSet result = db.getSubjects();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();

		try {
			writer.print("[");
			while (result != null && result.next()) {
				writer.print("{\"subject_id\":" + result.getInt("subject_id") + "," + "\"subject_name\":\""
						+ result.getString("subject_name") + "\"}");
				if (!result.isLast()) {
					writer.print(",");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			writer.print("]");
		}

	}
}