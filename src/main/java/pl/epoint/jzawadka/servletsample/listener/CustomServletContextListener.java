package pl.epoint.jzawadka.servletsample.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info(Thread.currentThread().getName() + " - context destroy");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info(Thread.currentThread().getName() + " - context destroy");
    }
}
