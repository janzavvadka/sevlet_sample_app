package pl.epoint.jzawadka.servletsample.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.logging.Logger;

public class CustomHttpSessionListener implements HttpSessionListener {

    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.info(Thread.currentThread().getName() + " - session create");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.info(Thread.currentThread().getName() + " - session destroy");
    }
}
