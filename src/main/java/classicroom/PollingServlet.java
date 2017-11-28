package classicroom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PollingServlet", urlPatterns = {"/chat"}, asyncSupported=true)
public class PollingServlet extends HttpServlet {
    public static final String CONTEXT_NAME = "contexts";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = request.getServletContext();
        List<AsyncContext> contexts = (List<AsyncContext>)servletContext.getAttribute(CONTEXT_NAME);
        if(contexts == null){
            contexts = new ArrayList<AsyncContext> ();
            servletContext.setAttribute(CONTEXT_NAME, contexts);
        }


        final AsyncContext ac = request.startAsync();
        contexts.add(ac);
    }
}