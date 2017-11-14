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

		String sql;

		sql = "CREATE TABLE accounts(user_id INT,pass VARCHAR(255) NOT NUL,user_name VARCHAR(255) NOT NUL,teacher_flg BOOLEAN NOT NUL,icon VARCHAR(255),PRIMARY KEY(user_id))";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

		sql = "CREATE TABLE subjects(subject_id INT,subject_name VARCHAR(255) NOT NUL,PRIMARY KEY(subject_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE lessons(lesson_id INT,subject_id INT NOT NUL,times INT NOT NUL,lesson_date DATE NOT NUL,PRIMARY KEY(lesson_id),FOREIGN KEY (subject_id)REFERENCES subjects(subject_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE tests(test_id INT,test_name VARCHAR(255) NOT NUL,test_date DATE NOT NUL,end_flg BOOLEAN NOT NUL,subject_id INT NOT NUL,PRIMARY KEY(test_id),FOREIGN KEY (subject_id)REFERENCES subjects(subject_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE chapters(chapter_id INT,chapter_name VARCHAR(255) NOT NUL,subject_id INT NOT NUL,PRIMARY KEY(chapter_id),FOREIGN KEY (subject_id)REFERENCES subjects(subject_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE sections(section_id INT,section_name VARCHAR(255) NOT NUL,chapter_id INT NOT NUL,PRIMARY KEY(section_id),FOREIGN KEY (chapter_id)REFERENCES chapters(chapter_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE progress(content_id INT,user_id INT,intelligibility INT NOT NUL,working_date DATE NOT NUL,PRIMARY KEY(content_id,user_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE results(test_id INT,test_id INT,point INT NOT NUL,PRIMARY KEY(test_id,test_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE attendances(lesson_id INT,user_id INT,attendance_situation INT NOT NUL,class_attitude INT NOT NUL,note VARCHAR(255),PRIMARY KEY(lesson_id,user_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

		stmt.close();
		con = null;
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
//CREATE TABLE ()     INT   VARCHAR(255)   BOOLEAN   DATE    ,PRIMARY KEY()  ,FOREIGN KEY (子カラム名)REFERENCES 親テーブル名(親カラム名)    NOT NULL
/*テンプレ
Connection con = null;
con = createConnection();


sql = "";

stmt = con.prepareStatement(sql);
stmt.executeUpdate();


String sql = "SQL文(CREATE TABLE)";

PreparedStatement stmt = con.prepareStatement(sql);
stmt.executeUpdate();

String sql = "SQL文";

PreparedStatement stmt = con.prepareStatement(sql);
ResultSet rs = stmt.executeQuery();

stmt.close();
con = null;
*/


