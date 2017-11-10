import java.sql.*;

@WebServlet(name = "DB", urlPatterns = {"hello"}, loadOnStartup = 1)

public class DB extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws Exception {
	        Class.forName("org.h2.Driver");
	        Connection conn = DriverManager.
	            getConnection("jdbc:h2:~/test", "sa", "");
	        // add application code here
	        conn.close();
		}
	}




public class Test {
    public static void main(String[] a)
            throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.
            getConnection("jdbc:h2:~/test", "sa", "");
        // add application code here
        conn.close();
    }
}