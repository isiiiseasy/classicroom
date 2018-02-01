package classicroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TestSubmitAPIServlet", urlPatterns = { "api/testsubmit" }, loadOnStartup = 1)
public class TestSubmitAPIServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String userId = (String)request.getSession(false).getAttribute("userId");
        String testId = request.getParameter("test-id");
        String point = request.getParameter("point");

        DataBase db = new DataBase();
        if (testId != null &&  point != null) {
            try {
            	db.addResult(Integer.parseInt(testId), userId, Integer.parseInt(point));
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            response.sendError(404);
        }

    }
}