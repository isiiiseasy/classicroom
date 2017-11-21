package classicroom;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent ev) {
        // nothing to do..
    }

    public void contextInitialized(ServletContextEvent ev) {
    	DataBase db = new DataBase();

        db.createDataBase();	//テーブル作成
        db.helloTableList();	//テーブル一覧表示
        db.sampleDate();		//サンプルデータ挿入
        db.print();				//行表示
    }
}
