package classicroom;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TestsAPIServlet", urlPatterns = { "api/tests" }, loadOnStartup = 1)
public class TestsAPIServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataBase db = new DataBase();
		String userRank = (String) request.getSession(false).getAttribute("userRank");
		ResultSet result = db.getTests();

		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();

		try {
			writer.print("[");
			while (result != null && result.next()) {
				if (userRank.equals("teacher") || result.getBoolean("public_flg")) {
					if (!result.isFirst()) {
						writer.print(",");
					}
					writer.print("{\"test_id\":" + result.getInt("test_id") + "," + "\"test_name\":\""
							+ result.getString("test_name") + "\",\"subject_id\":" + result.getInt("subject_id")
							+ ",\"public_flg\":" + result.getBoolean("public_flg") + ",\"end_flg\":"
							+ result.getBoolean("end_flg") + ",\"test_date\":\"" + result.getDate("test_date") + "\"}");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			writer.print("]");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataBase db = new DataBase();
		String userRank = (String) request.getSession(false).getAttribute("userRank");
		String subjectId = request.getParameter("subject-id");
		String testName = request.getParameter("test-name");
		String testDate = request.getParameter("test-date");

		try {
			if (userRank.equals("teacher") && subjectId != null && testName != null && testDate != null) {
				db.addTest(Integer.parseInt(subjectId), testName, testDate);
				doGet(request, response);
			} else {
				response.sendError(404);
			}

		} catch (Exception e) {
			response.sendError(404);
			e.printStackTrace();
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataBase db = new DataBase();
		String userRank = (String) request.getSession(false).getAttribute("userRank");
		String testId = request.getParameter("test-id");

		try {
			if (userRank.equals("teacher") && testId != null) {
				db.deleteTest(Integer.parseInt(testId));
				doGet(request, response);
			} else {
				response.sendError(404);
			}
		} catch (Exception e) {
			response.sendError(404);
			e.printStackTrace();
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataBase db = new DataBase();
		String userRank = (String) request.getSession(false).getAttribute("userRank");
		String testId = request.getParameter("test-id");
		String publicFlg = request.getParameter("public-flg");
		String endFlg = request.getParameter("end-flg");

		try {
			if (userRank.equals("teacher") && testId != null && publicFlg != null) {
				db.setTestPublic(Integer.parseInt(testId), Boolean.parseBoolean(publicFlg));
				doGet(request, response);
			} else if (userRank.equals("teacher") && testId != null && endFlg != null) {
				db.setTestEnd(Integer.parseInt(testId), Boolean.parseBoolean(publicFlg));
				doGet(request, response);
			} else {
				response.sendError(404);
			}
		} catch (Exception e) {
			response.sendError(404);
			e.printStackTrace();
		}
	}
}