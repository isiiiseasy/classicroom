package classicroom;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBase{

	Connection con = null;

	public DataBase() {
		try {
			final String DRIVER_URL = "jdbc:h2:./test";
			final String DRIVER_NAME = "org.h2.Driver";
			final String USER_NAME = "sa";
			final String PASSWORD = "";

			Class.forName(DRIVER_NAME);
	        con = DriverManager.getConnection(DRIVER_URL,USER_NAME,PASSWORD);
	        }catch(ClassNotFoundException e){
	            System.out.println("Can't Find H2 Driver.\n");
	        }catch(SQLException e){
	            System.out.println("Connection Error.\n");
	        }
		}

	public void close(){
        try{
            con.close();
        }catch(Exception ex){}
    }


	public void createDataBase() {


		try {

		String sql;

		sql = "CREATE TABLE accounts(user_id VARCHAR(255),pass VARCHAR(255) NOT NULL,user_name VARCHAR(255) NOT NULL,teacher_flg BOOLEAN NOT NULL,icon VARCHAR(255),PRIMARY KEY(user_id))";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

		sql = "CREATE TABLE subjects(subject_id INT,subject_name VARCHAR(255) NOT NULL,PRIMARY KEY(subject_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE lessons(lesson_id INT,subject_id INT NOT NULL,times INT NOT NULL,lesson_date DATE NOT NULL,PRIMARY KEY(lesson_id),FOREIGN KEY (subject_id)REFERENCES subjects(subject_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE tests(test_id INT,test_name VARCHAR(255) NOT NULL,test_date DATE NOT NULL,end_flg BOOLEAN NOT NULL,subject_id INT NOT NULL,PRIMARY KEY(test_id),FOREIGN KEY (subject_id)REFERENCES subjects(subject_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE chapters(chapter_id INT,chapter_name VARCHAR(255) NOT NULL,subject_id INT NOT NULL,PRIMARY KEY(chapter_id),FOREIGN KEY (subject_id)REFERENCES subjects(subject_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE sections(section_id INT,section_name VARCHAR(255) NOT NULL,chapter_id INT NOT NULL,PRIMARY KEY(section_id),FOREIGN KEY (chapter_id)REFERENCES chapters(chapter_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE progress(section_id INT,user_id VARCHAR(255),intelligibility INT NOT NULL,working_date DATE NOT NULL,PRIMARY KEY(section_id,user_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE results(test_id INT,user_id VARCHAR(255),point INT NOT NULL,PRIMARY KEY(test_id,user_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE attendances(lesson_id INT,user_id VARCHAR(255),attendance_situation INT NOT NULL,note VARCHAR(255),PRIMARY KEY(lesson_id,user_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public void helloTableList(){
	       try {

	           DatabaseMetaData dmd = con.getMetaData();
	           ResultSet rs = null;
	           String types[] = { "TABLE" };
	           rs = dmd.getTables(null, null,"%", types);
	           try {
	               while(rs.next()){
	                   System.out.println(rs.getString("TABLE_TYPE") + ":");
	                   System.out.println(rs.getString("TABLE_NAME") + "\n");
	               }
	           } finally {
	               rs.close();
	           }
	       } catch (Exception e) {
		           e.printStackTrace();
		       }
	}



	public void sampleDate() {
		try {
		Statement statement=con.createStatement();
		statement.addBatch("INSERT INTO accounts VALUES ('student','student','大原 太郎',false,'',)");
	    statement.addBatch("INSERT INTO accounts VALUES ('1674401','ssap','大原 花子',false,'',)");
	    statement.addBatch("INSERT INTO accounts VALUES ('teacher','teacher','大原 教師郎',true,'',)");

	    statement.addBatch("INSERT INTO subjects VALUES (01,'Java')");
	    statement.addBatch("INSERT INTO subjects VALUES (02,'C言語')");

	    statement.addBatch("INSERT INTO lessons VALUES (001,01,1,to_date('2017/04/05','YYYY/MM/DD'))");
	    statement.addBatch("INSERT INTO lessons VALUES (002,01,2,to_date('2017/04/07','YYYY/MM/DD'))");
	    statement.addBatch("INSERT INTO lessons VALUES (003,02,1,to_date('2017/04/12','YYYY/MM/DD'))");

	    statement.addBatch("INSERT INTO tests VALUES (001,'第1回Java効果測定',to_date('2017/04/20','YYYY/MM/DD'),true,01)");
	    statement.addBatch("INSERT INTO tests VALUES (003,'第2回Java効果測定',to_date('2017/04/21','YYYY/MM/DD'),true,01)");
	    statement.addBatch("INSERT INTO tests VALUES (002,'第1回C言語効果測定',to_date('2017/04/22','YYYY/MM/DD'),false,02)");

	    statement.addBatch("INSERT INTO chapters VALUES (101,'J1章',01)");
	    statement.addBatch("INSERT INTO chapters VALUES (102,'J2章',01)");
	    statement.addBatch("INSERT INTO chapters VALUES (201,'C1章',02)");

	    statement.addBatch("INSERT INTO sections VALUES (1101,'J1章1項目',101)");
	    statement.addBatch("INSERT INTO sections VALUES (1102,'J1章2項目',101)");
	    statement.addBatch("INSERT INTO sections VALUES (1201,'J2章1項目',102)");
	    statement.addBatch("INSERT INTO sections VALUES (2101,'C1章1項目',201)");

	    statement.addBatch("INSERT INTO progress VALUES (1101,1674400,80,to_date('2017/04/06','YYYY/MM/DD'))");
	    statement.addBatch("INSERT INTO progress VALUES (1102,1674400,70,to_date('2017/04/08','YYYY/MM/DD'))");
	    statement.addBatch("INSERT INTO progress VALUES (2101,1674400,100,to_date('2017/04/15','YYYY/MM/DD'))");
	    statement.addBatch("INSERT INTO progress VALUES (1101,1674401,30,to_date('2017/04/06','YYYY/MM/DD'))");
	    statement.addBatch("INSERT INTO progress VALUES (1102,1674401,40,to_date('2017/04/10','YYYY/MM/DD'))");

	    statement.addBatch("INSERT INTO results VALUES (001,1674400,98)");
	    statement.addBatch("INSERT INTO results VALUES (001,1674401,100)");
	    statement.addBatch("INSERT INTO results VALUES (002,1674400,80)");
	    statement.addBatch("INSERT INTO results VALUES (002,1674401,50)");
	    statement.addBatch("INSERT INTO results VALUES (003,1674401,50)");


	    statement.executeBatch();

		}catch(SQLException e) {
			System.out.println(e);
		}
	}

	public void print(){
		System.out.println("printstart");
		try {
			String sql = "SELECT * FROM accounts";

			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
		        String userId = rs.getString("user_id");
		        String pass = rs.getString("pass");
		        String userName = rs.getString("user_name");
		        boolean teacherFlg = rs.getBoolean("teacher_flg");
		        String icon = rs.getString("icon");
		        System.out.println(userId+","+pass+","+userName+","+teacherFlg+","+icon+",");
		      }

			sql = "SELECT * FROM tests";

			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while(rs.next()) {
		        int testId = rs.getInt("test_id");
		        String testName = rs.getString("test_name");
		        Date testDate = rs.getDate("test_date");
		        boolean endFlg = rs.getBoolean("end_flg");
		        int subjectId = rs.getInt("subject_id");
		        System.out.println(testId+","+testName+","+testDate+","+endFlg+","+subjectId+",");
		      }

			sql = "SELECT * FROM attendances";

			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while(rs.next()){
		        int lessonId = rs.getInt("lesson_id");
		        String userId = rs.getString("user_id");
		        int attendanceSituation = rs.getInt("attendance_situation");
		        String note = rs.getString("note");
		        System.out.println(lessonId+","+userId+","+attendanceSituation+","+note);
		      }

            System.out.println("printok");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}


	public boolean addUser(String userId,String password,String userName,String teacherFlg) {
		boolean flg = false;

		try {
			String sql = "INSERT INTO accounts (user_id,pass,user_name,teacher_flg,icon) VALUES (?,?,?,?,?)";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,userId);
			stmt.setString(2,password);
			stmt.setString(3,userName);
			stmt.setBoolean(4,Boolean.valueOf(teacherFlg));
			stmt.setString(5,"default");
			stmt.executeUpdate();

			flg = true;
		}catch(SQLException e) {
			System.out.println(e);
		}

		return flg;
	}

	public boolean auth(String userId,String password) {
		boolean flg = false;

		try {
			String sql = "SELECT pass FROM accounts WHERE user_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, userId);
			ResultSet result = stmt.executeQuery();

			if(result.next()) {
				flg = result.getString("pass").equals(password);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return flg;
	}

	public boolean getRank(String userId) {
		boolean teacherFlg = false;

		try {
			String sql = "SELECT teacher_flg FROM accounts WHERE user_id = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,userId);

			ResultSet rs = stmt.executeQuery();

			rs.next();
			teacherFlg = rs.getBoolean(1);

		}catch(Exception e) {
			System.out.println(e);
		}
		return teacherFlg;
	}

	public String getUserName(String userId) {
		String userName = "";

		try {
			String sql = "SELECT user_name FROM accounts WHERE user_id = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,userId);

			ResultSet rs = stmt.executeQuery();

			rs.next();
			userName = rs.getString(1);

		}catch(Exception e) {
			System.out.println(e);
		}

		return userName;
	}

	public String getUserID(String userName) {
		String userId = "";

		try {
			String sql = "SELECT user_id FROM accounts WHERE user_name = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,userName);

			ResultSet rs = stmt.executeQuery();

			rs.next();
			userId = rs.getString(1);

		}catch(Exception e) {
			System.out.println(e);
		}

		return userId;
	}

	public String getSubjectName(int subjectId) {
		String subjectName = "";

		try {
			String sql = "SELECT subject_name FROM subjects WHERE subject_id = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,subjectId);

			ResultSet rs = stmt.executeQuery();

			rs.next();
			subjectName = rs.getString(1);

		}catch(Exception e) {
			System.out.println(e);
		}

		return subjectName;
	}

	public ResultSet getSubjects() {
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM subjects");
		}catch(Exception e) {
			System.out.println(e);
		}

		return rs;
	}

	public ResultSet getChapters(int subjectId) {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM chapters WHERE subject_id = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,subjectId);

			rs = stmt.executeQuery();
		}catch(Exception e) {
			System.out.println(e);
		}

		return rs;
	}

	public ResultSet getSections(int chapterId) {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM sections WHERE chapter_id = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,chapterId);

			rs = stmt.executeQuery();
		}catch(Exception e) {
			System.out.println(e);
		}

		return rs;
	}

	public ResultSet getUserResults(String userId) {
		ResultSet rs = null;
		try {
			String sql = "SELECT tests.test_id, tests.test_name, results.point AS my_point, average.average_point, tests.subject_id"
					+ " FROM tests"
					+ " LEFT OUTER JOIN results ON (tests.test_id = results.test_id AND results.user_id = ?)"
					+ " RIGHT OUTER JOIN ("
					+ "     SELECT results.test_id, AVG(point) AS average_point"
					+ "     FROM results"
					+ "     GROUP BY results.test_id"
					+ " ) AS average ON (tests.test_id = average.test_id)"
					+ " ORDER BY tests.subject_id, tests.test_id";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,userId);

			rs = stmt.executeQuery();

		}catch(Exception e) {
			System.out.println(e);
		}

		return rs;
	}



	public String getImgFileName(String userId) {
		String imgPath = "";

		try {
			String sql = "SELECT icon FROM accounts WHERE user_id = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,userId);

			ResultSet rs = stmt.executeQuery();

			rs.next();
			imgPath = rs.getString(1);

		}catch(Exception e) {
			System.out.println(e);
		}

		return imgPath;
	}

	public void IconSet(String userId,String fileName) {
		try {
			String sql = "UPDATE accounts SET icon = ? WHERE user_id = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,fileName);
			stmt.setString(2,userId);

			stmt.executeUpdate();

		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public void SetAttendances(int lessonId,String userId) {
		try {
			String sql = "INSERT INTO attendances VALUES (?,?,1,'')";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,lessonId);
			stmt.setString(2,userId);

			stmt.executeUpdate();

		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public List<String> getAttendancesTable() {
		List<String> list = new ArrayList<String>();
		DataBase db = new DataBase();
		try {
			String sql = "SELECT * FROM attendances ORDER BY lesson_Id DESC";

			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			rs.next();

			System.out.println(rs);

			while(rs.next()) {
				list.add("第"+String.valueOf(rs.getInt(1))+"回");
				list.add(db.getUserName(rs.getString(2)));
				if(rs.getInt(3)==1)
					list.add("欠席");
				else if(rs.getInt(3)==2)
					list.add("遅刻");
				else if(rs.getInt(3)==3)
					list.add("早退");
				list.add(rs.getString(4));
				list.add("<br>");
			}

		}catch(Exception e) {
			System.out.println(e);
		}

		return list;
	}

	public boolean UpdateAttendances(int lessonId,String userId,int kubun,String biko) {
		boolean flg = false;

		try {
			if(kubun != 4) {
				String sql = "UPDATE attendances SET attendance_situation = ?,note = ? WHERE lesson_id = ? AND user_id = ?";

				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1,kubun);
				stmt.setString(2,biko);
				stmt.setInt(3,lessonId);
				stmt.setString(4,userId);

				stmt.executeUpdate();

				flg = true;
			}else {
				String sql = "DELETE FROM attendances WHERE lesson_id = ? AND user_id = ?";

				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1,lessonId);
				stmt.setString(2,userId);

				stmt.executeUpdate();

				flg = true;
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return flg;
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


