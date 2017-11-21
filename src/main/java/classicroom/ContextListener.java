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
    	DataBase DB = new DataBase();

        DB.createDataBase();	//テーブル作成
        DB.helloTableList();	//テーブル一覧表示
        DB.sampleDate();		//サンプルデータ挿入
        DB.print();				//行表示
    }
}
