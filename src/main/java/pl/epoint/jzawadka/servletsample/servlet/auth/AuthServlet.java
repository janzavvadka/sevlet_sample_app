package pl.epoint.jzawadka.servletsample.servlet.auth;

import lombok.extern.java.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log
public class AuthServlet extends HttpServlet {

    private static final String LOGIN = "login";
    private static final String LOGOUT = "logout";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String action = req.getParameter("action");

        if(LOGIN.equals(action)) {
            if(login.equals("user") == password.equals("123456")) {
                req.getSession().setAttribute("user", "authorized");
                HttpSession session = req.getSession();
                Object referer = null;
                if(session != null) {
                    referer = session.getAttribute("referer");
                }
                resp.sendRedirect(referer == null ? req.getContextPath() : referer.toString());
            } else {
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        }
        if(LOGOUT.equals(action)) {
            req.getSession().invalidate();
            resp.sendRedirect("login?action=" + LOGIN);
        }

    }
}
