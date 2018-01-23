package classicroom;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.stream.Stream;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PracticesAPIServlet", urlPatterns = { "api/practices" }, loadOnStartup = 1)
public class PracticesAPIServlet extends HttpServlet {

    public void init() throws ServletException {
        try {
            Files.createDirectories(Paths.get("data/practice"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sectionId = request.getParameter("section-id");
        if (sectionId != null && sectionId.length() > 0) {
            try (Stream<String> lines = Files.lines(Paths.get("data/practice/" + sectionId + ".json"),
                    StandardCharsets.UTF_8);) {
                PrintWriter responseWriter = response.getWriter();
                response.setContentType("application/json;charset=utf-8");
                lines.forEachOrdered(line -> responseWriter.print(line));
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            response.sendError(404);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userRank = (String) request.getSession(false).getAttribute("userRank");
        String sectionId = request.getParameter("section-id");
        String jsonData = request.getParameter("data");

        if (userRank.equals("teacher") && sectionId != null && sectionId.length() > 0 && jsonData != null) {
            try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("data/practice/" + sectionId + ".json"),
                    StandardCharsets.UTF_8); PrintWriter pw = new PrintWriter(bw, true);) {
                pw.print(jsonData);
                PrintWriter responseWriter = response.getWriter();
                response.setContentType("application/json;charset=utf-8");
                responseWriter.print(jsonData);
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            response.sendError(404);
        }

    }
}