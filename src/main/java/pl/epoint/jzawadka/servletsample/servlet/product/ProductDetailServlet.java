package pl.epoint.jzawadka.servletsample.servlet.product;

import pl.epoint.jzawadka.servletsample.DAO.BaseDAO;
import pl.epoint.jzawadka.servletsample.DAO.DatasourceHolder;
import pl.epoint.jzawadka.servletsample.model.Product;
import pl.epoint.jzawadka.servletsample.util.CounterUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class ProductDetailServlet extends HttpServlet {

    private Integer visitCounter;

    private static BaseDAO productDAO;

    @Override
    public void init() {
        productDAO = DatasourceHolder.getDatasource();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        visitCounter = CounterUtil.incrementAllCounters(visitCounter, req);
        Optional.ofNullable(req.getParameter("id"))
                .ifPresent(id -> {
                    Product product = productDAO.getProductById(Integer.parseInt(id));
                    req.setAttribute("product", product);
                });
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("productDetail.jsp");
        requestDispatcher.forward(req, resp);
    }
}
