package classicroom;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TestContentsAPIServlet", urlPatterns = { "api/testcontents" }, loadOnStartup = 1)
public class TestContentsAPIServlet extends HttpServlet {

    public void init() throws ServletException {
        try {
            Files.createDirectories(Paths.get("data/test"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String testId = request.getParameter("test-id");
        if (testId != null && testId.length() > 0) {
            try (Stream<String> lines = Files.lines(Paths.get("data/test/" + testId + ".json"),
                    StandardCharsets.UTF_8);) {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter responseWriter = response.getWriter();
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
        String testId = request.getParameter("test-id");
        String jsonData = request.getParameter("data");

        if (userRank.equals("teacher") && testId != null && testId.length() > 0 && jsonData != null) {
            try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("data/test/" + testId + ".json"),
                    StandardCharsets.UTF_8); PrintWriter pw = new PrintWriter(bw, true);) {
                pw.print(jsonData);
                response.setContentType("application/json;charset=utf-8");
                PrintWriter responseWriter = response.getWriter();
                responseWriter.print(jsonData);
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            response.sendError(404);
        }

    }
}