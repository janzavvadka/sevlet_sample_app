package pl.epoint.jzawadka.servletsample.servlet.monitor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class LiveCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Writer writer = resp.getWriter();
        writer.append("<html>");
        writer.append("<body>");
        writer.append("<h1>I'am alive</h1>");
        writer.append("</body>");
        writer.append("</html>");
    }

}
