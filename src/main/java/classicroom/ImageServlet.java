package classicroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ImageServlet", urlPatterns = { "image" }, loadOnStartup = 1)

public class ImageServlet extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

	  String userId = (String) request.getAttribute("userId");
	  request.setAttribute("userId",userId);

	  request.getRequestDispatcher("/WEB-INF/jsp/image.jsp").forward(request, response);
  }
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	  doPost(request,response);
  }
}