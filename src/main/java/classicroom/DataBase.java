package classicroom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DataBase{

	private final static String DRIVER_URL = "jdbc:h2:./test";
	private final static String DRIVER_NAME = "org.h2.Driver";
	private final static String USER_NAME = "sa";
	private final static String PASSWORD = "";

	public static Connection createConnection() {
		try {
			Class.forName(DRIVER_NAME);
	        Connection con=DriverManager.getConnection(DRIVER_URL,USER_NAME,PASSWORD);
	        return con;
	        }catch(ClassNotFoundException e){
	            System.out.println("Can't Find H2 Driver.\n");
	        }catch(SQLException e){
	            System.out.println("Connection Error.\n");
	        }
	            return null;
		}

	public static void closeConnection(Connection con){
        try{
            con.close();
        }catch(Exception ex){}
    }


	public static void CreateDataBase() {


		try {
		Connection con = null;
		con = createConnection();

		String sql = "CREATE TABLE TEST(ID INT PRIMARY KEY,PASS INT,NAME VARCHAR(40),)";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

		stmt.close();
		con = null;
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}

/*テンプレ
Connection con = null;
con = createConnection();

String sql = "SQL文";

PreparedStatement stmt = con.prepareStatement(sql);
ResultSet rs = stmt.executeQuery();

stmt.close();
con = null;
*/


