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

@WebServlet(name = "SectionsAPIServlet", urlPatterns = { "api/sections" }, loadOnStartup = 1)
public class SectionsAPIServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataBase db = new DataBase();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();

		try {
			ResultSet subjects = db.getSubjects();
			JsonArrayBuilder subjectJA = Json.createArrayBuilder();

			while (subjects.next()) {
				JsonObjectBuilder subjectJO = Json.createObjectBuilder();
				subjectJO.add("subject_id", subjects.getInt("subject_id"));
				subjectJO.add("subject_name", subjects.getString("subject_name"));

				ResultSet chapters = db.getChapters(subjects.getInt("subject_id"));
				JsonArrayBuilder chapterJA = Json.createArrayBuilder();

				while (chapters.next()) {
					JsonObjectBuilder chapterJO = Json.createObjectBuilder();
					chapterJO.add("chapter_id", chapters.getInt("chapter_id"));
					chapterJO.add("chapter_name", chapters.getString("chapter_name"));

					ResultSet sections = db.getSections(chapters.getInt("chapter_id"));
					JsonArrayBuilder sectionJA = Json.createArrayBuilder();

					while (sections.next()) {
						JsonObjectBuilder sectionJO = Json.createObjectBuilder();
						sectionJO.add("section_id", sections.getInt("section_id"));
						sectionJO.add("section_name", sections.getString("section_name"));

						sectionJA.add(sectionJO);
						chapterJO.add("sections", sectionJA);
					}
					chapterJA.add(chapterJO);
					subjectJO.add("chapters", chapterJA);
				}
				subjectJA.add(subjectJO);
			}
			writer.println(subjectJA.build());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}