package classicroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyResultAPIServlet", urlPatterns = { "api/myresult" }, loadOnStartup = 1)
public class MyResultAPIServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataBase db = new DataBase();
		String userId = (String) request.getSession(false).getAttribute("userId");
		ResultSet rs = db.getUserResults(userId);
		String currentSubjectId = null;
		JsonArrayBuilder subjectsArray = Json.createArrayBuilder();

		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		// subjectsArray[subject{subject_id,resultsArray[testResult{test_name,my_point,average_point}]}]
		try {
			if (rs != null) {
				rs.next();
			}
			while (rs != null && !rs.isAfterLast()) {
				JsonObjectBuilder subject = Json.createObjectBuilder();
				currentSubjectId = rs.getString("subject_id");
				subject.add("subject_id", currentSubjectId);

				JsonArrayBuilder resultsArray = Json.createArrayBuilder();

				while (rs.getString("subject_id").equals(currentSubjectId)) {
					JsonObjectBuilder testResult = Json.createObjectBuilder();
					testResult.add("test_name", rs.getString("test_name"));
					testResult.add("my_point", rs.getInt("my_point"));
					testResult.add("average_point", rs.getInt("average_point"));
					resultsArray.add(testResult);
					if (!rs.next()) {
						break;
					}
				}

				subject.add("results", resultsArray);
				subjectsArray.add(subject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.println(subjectsArray.build());
		}

	}
}