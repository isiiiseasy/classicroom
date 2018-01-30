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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataBase db = new DataBase();
		String subjectIdString = request.getParameter("subject-id");
		String subjectName = request.getParameter("subject-name");
		String chapterIdString = request.getParameter("chapter-id");
		String chapterName = request.getParameter("chapter-name");
		String sectionName = request.getParameter("section-name");
		int subjectId = -1, chapterId = -1;
		try {
			if (subjectIdString != null && !subjectIdString.equals("")) {
				subjectId = Integer.parseInt(subjectIdString);
			}
			if (chapterIdString != null && !chapterIdString.equals("")) {
				chapterId = Integer.parseInt(chapterIdString);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendError(404);
			return;
		}

		if (subjectName != null) {
			if (db.addSubject(subjectName)) {
				doGet(request, response);
			} else {
				response.sendError(404);
			}
			return;
		}

		if (subjectId > 0 && chapterName != null) {
			if (db.addChapter(subjectId, chapterName)) {
				doGet(request, response);
			} else {
				response.sendError(404);
			}
			return;
		}

		if (chapterId > 0 && sectionName != null) {
			if (db.addSection(chapterId, sectionName)) {
				doGet(request, response);
			} else {
				response.sendError(404);
			}
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataBase db = new DataBase();
		String subjectIdString = request.getParameter("subject-id");
		String chapterIdString = request.getParameter("chapter-id");
		String sectionIdString = request.getParameter("section-id");
		int subjectId = -1, chapterId = -1, sectionId = -1;
		try {
			if (subjectIdString != null && !subjectIdString.equals("")) {
				subjectId = Integer.parseInt(subjectIdString);
			}
			if (chapterIdString != null && !chapterIdString.equals("")) {
				chapterId = Integer.parseInt(chapterIdString);
			}
			if (sectionIdString != null && !sectionIdString.equals("")) {
				sectionId = Integer.parseInt(sectionIdString);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendError(404);
			return;
		}

		if (subjectId > 0) {
			if (db.deleteSubject(subjectId)) {
				doGet(request, response);
			} else {
				response.sendError(404);
			}
			return;
		}

		if (chapterId > 0) {
			if (db.deleteChapter(chapterId)) {
				doGet(request, response);
			} else {
				response.sendError(404);
			}
			return;
		}

		if (sectionId > 0) {
			if (db.deleteSection(sectionId)) {
				doGet(request, response);
			} else {
				response.sendError(404);
			}
			return;
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataBase db = new DataBase();
		String subjectIdString = request.getParameter("subject-id");
		String subjectName = request.getParameter("subject-name");
		String chapterIdString = request.getParameter("chapter-id");
		String chapterName = request.getParameter("chapter-name");
		String sectionIdString = request.getParameter("section-id");
		String sectionName = request.getParameter("section-name");
		int subjectId = -1, chapterId = -1, sectionId = -1;
		try {
			if (subjectIdString != null && !subjectIdString.equals("")) {
				subjectId = Integer.parseInt(subjectIdString);
			}
			if (chapterIdString != null && !chapterIdString.equals("")) {
				chapterId = Integer.parseInt(chapterIdString);
			}
			if (sectionIdString != null && !sectionIdString.equals("")) {
				sectionId = Integer.parseInt(sectionIdString);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendError(404);
			return;
		}

		if (subjectId > 0 && subjectName != null) {
			if (db.changeSubjectName(subjectId, subjectName)) {
				doGet(request, response);
			} else {
				response.sendError(404);
			}
		}

		if (chapterId > 0 && chapterName != null) {
			if (db.changeChapterName(chapterId, chapterName)) {
				doGet(request, response);
			} else {
				response.sendError(404);
			}
		}

		if (sectionId > 0 && sectionName != null) {
			if (db.changeSectionName(sectionId, sectionName)) {
				doGet(request, response);
			} else {
				response.sendError(404);
			}
		}
	}
}