package classicroom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PushServlet", urlPatterns = {"/push"})
public class PushServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            request.setCharacterEncoding("utf-8");
            String textonly = request.getParameter("text");

            Calendar date = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("  yyyy/MM/dd HH:mm:ss  ");
            String dateonly = (sdf.format(date.getTime()));
            String username = (String) request.getSession(false).getAttribute("userID");
            StringBuffer buf = new StringBuffer();
            buf.append(username);
            buf.append(dateonly);
            buf.append(textonly);
            String text = buf.toString();
            try {
                BufferedWriter objBw=new BufferedWriter(new FileWriter("chatlog.txt",true));
                objBw.write(text);
                objBw.close();

            }catch(IOException e) {
            	System.out.println(e);
            }

            ServletContext servletContext = request.getServletContext();
            List<AsyncContext> contexts = (List<AsyncContext>) servletContext.getAttribute(PollingServlet.CONTEXT_NAME);
            if(contexts != null){
                for(AsyncContext ac : contexts ){
                    try{
                        ac.getResponse().setCharacterEncoding("utf-8");
                        PrintWriter writer = ac.getResponse().getWriter();
                        writer.println(text);
                        writer.close();
                        ac.complete();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                contexts.clear();
            }
            out.println("ok");
        } finally {
            out.close();
        }


    }
}
