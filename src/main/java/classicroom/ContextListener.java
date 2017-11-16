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
        DataBase.CreateDataBase();	//テーブル作成
        //DataBase.HelloTableList();	//テーブル一覧表示
        DataBase.SampleDate();			//サンプルデータ挿入
        //DataBase.print();				//行表示
    }
}
