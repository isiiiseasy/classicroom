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
        DataBase.CreateDataBase();
    }
}
