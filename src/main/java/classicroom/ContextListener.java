package classicroom;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent ev) {
        // nothing to do..
    }

    public void contextInitialized(ServletContextEvent ev) {
		if (Files.exists(Paths.get("test.mv.db"))) { // DBファイルが存在したら何もせず返る
			System.out.println("dbファイルが既にあるためテーブル作成・サンプルデータの挿入を行いません");
		}else {
			DataBase db = new DataBase();

    		db.createDataBase();	//テーブル作成
    		db.helloTableList();	//テーブル一覧表示
    		db.sampleDate();		//サンプルデータ挿入
    		db.print();				//行表示
		}
    }
}
