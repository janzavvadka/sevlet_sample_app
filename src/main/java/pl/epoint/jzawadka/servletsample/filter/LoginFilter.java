package pl.epoint.jzawadka.servletsample.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession();

        if (session.getAttribute("user") != null) {
            chain.doFilter(request, response);
            return;
        }

        String path = httpReq.getRequestURI();
        if (path.endsWith(".css") || path.endsWith("/login")) {
            chain.doFilter(request, response);
            return;
        }

        session.setAttribute("referer", httpReq.getRequestURL());
        httpRes.sendRedirect(httpReq.getContextPath() + "/login");
    }

    @Override
    public void destroy() {

    }
}
