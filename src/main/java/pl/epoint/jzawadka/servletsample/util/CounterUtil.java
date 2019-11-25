package pl.epoint.jzawadka.servletsample.util;

import lombok.experimental.UtilityClass;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@UtilityClass
public class CounterUtil {

    private static final String COUNTER_KEY = "counter";

    private Integer incrementServletCounter(Integer counter, HttpServletRequest req) {
        if(counter == null){
            counter = 0;
        }
        counter++;
        req.setAttribute(COUNTER_KEY, counter);
        return counter;
    }

    private void incrementSessionCounter(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        Object counter_raw = session.getAttribute(COUNTER_KEY);
        if(counter_raw != null) {
            int counter = (Integer) counter_raw;
            session.setAttribute(COUNTER_KEY, ++counter);
        } else {
            session.setAttribute(COUNTER_KEY, 1);
        }
    }

    private void incrementServletContextCounter(HttpServletRequest req) {
        ServletContext servletContext = req.getServletContext();
        Object counter_raw = servletContext.getAttribute(COUNTER_KEY);
        if(counter_raw != null) {
            int counter = (Integer) counter_raw;
            servletContext.setAttribute(COUNTER_KEY, ++counter);
        } else {
            servletContext.setAttribute(COUNTER_KEY, 1);
        }
    }

    public Integer incrementAllCounters(Integer counter, HttpServletRequest req) {
        incrementServletContextCounter(req);
        incrementSessionCounter(req);
        return incrementServletCounter(counter, req);
    }

}
