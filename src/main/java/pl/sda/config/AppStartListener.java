package pl.sda.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*nigdzie nie używamy tej klasy. Przez taką implementacje wykonuje się ona na początku i końcu procesu*/
public class AppStartListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DbUtil.getConnection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DbUtil.closeConnection();
    }
}
