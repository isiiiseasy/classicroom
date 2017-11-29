package classicroom;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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


		sql = "CREATE TABLE progress(section_id INT,user_id INT,intelligibility INT NOT NULL,working_date DATE NOT NULL,PRIMARY KEY(section_id,user_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE results(test_id INT,user_id INT,point INT NOT NULL,PRIMARY KEY(test_id,user_id))";

		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();


		sql = "CREATE TABLE attendances(lesson_id INT,user_id INT,attendance_situation INT NOT NULL,class_attitude INT NOT NULL,note VARCHAR(255),PRIMARY KEY(lesson_id,user_id))";

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
	    statement.addBatch("INSERT INTO tests VALUES (002,'第1回C言語効果測定',to_date('2017/04/22','YYYY/MM/DD'),false,02)");

	    statement.addBatch("INSERT INTO chapters VALUES (101,'J1章',01)");
	    statement.addBatch("INSERT INTO chapters VALUES (102,'J2章',01)");
	    statement.addBatch("INSERT INTO chapters VALUES (201,'C1章',02)");

	    statement.addBatch("INSERT INTO sections VALUES (1101,'J1章1項目',101)");
	    statement.addBatch("INSERT INTO sections VALUES (1102,'J1章2項目',101)");
	    statement.addBatch("INSERT INTO sections VALUES (1201,'J2章1項目',101)");
	    statement.addBatch("INSERT INTO sections VALUES (2101,'C1章1項目',102)");

	    statement.addBatch("INSERT INTO progress VALUES (1101,1674400,80,to_date('2017/04/06','YYYY/MM/DD'))");
	    statement.addBatch("INSERT INTO progress VALUES (1102,1674400,70,to_date('2017/04/08','YYYY/MM/DD'))");
	    statement.addBatch("INSERT INTO progress VALUES (2101,1674400,100,to_date('2017/04/15','YYYY/MM/DD'))");
	    statement.addBatch("INSERT INTO progress VALUES (1101,1674401,30,to_date('2017/04/06','YYYY/MM/DD'))");
	    statement.addBatch("INSERT INTO progress VALUES (1102,1674401,40,to_date('2017/04/10','YYYY/MM/DD'))");

	    statement.addBatch("INSERT INTO results VALUES (001,1674400,98)");
	    statement.addBatch("INSERT INTO results VALUES (001,1674401,100)");
	    statement.addBatch("INSERT INTO results VALUES (002,1674400,80)");
	    statement.addBatch("INSERT INTO results VALUES (002,1674401,50)");

	    //statement.addBatch("DELETE FROM attendances");
	    statement.addBatch("INSERT INTO attendances VALUES (003,1674401,2,50,'寝坊')");




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
		        int userId = rs.getInt("user_id");
		        int attendanceSituation = rs.getInt("attendance_situation");
		        int classAttitude = rs.getInt("class_attitude");
		        String note = rs.getString("note");
		        System.out.println(lessonId+","+userId+","+attendanceSituation+","+classAttitude+","+note);
		      }

            System.out.println("printok");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}


	public boolean addUser(String userId,String password,String userName,String teacherFlg) {
		boolean flg = false;

		try {
			String sql = "INSERT INTO accounts (user_id,pass,user_name,teacher_flg) VALUES (?,?,?,?)";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,userId);
			stmt.setString(2,password);
			stmt.setString(3,userName);
			stmt.setBoolean(4,Boolean.valueOf(teacherFlg));
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


