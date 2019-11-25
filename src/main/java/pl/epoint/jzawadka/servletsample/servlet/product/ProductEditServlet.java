package pl.epoint.jzawadka.servletsample.servlet.product;

import pl.epoint.jzawadka.servletsample.DAO.BaseDAO;
import pl.epoint.jzawadka.servletsample.DAO.DatasourceHolder;
import pl.epoint.jzawadka.servletsample.model.Product;
import pl.epoint.jzawadka.servletsample.util.CounterUtil;
import pl.epoint.jzawadka.servletsample.util.ValidatorUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductEditServlet extends HttpServlet {

    private static final String ACTION_SAVE_PRODUCT = "save_product";
    private static final String ACTION_UPDATE_PRODUCT = "update_product";

    private Integer visitCounter;

    private static BaseDAO productDAO;

    @Override
    public void init() {
        productDAO = DatasourceHolder.getDatasource();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        visitCounter = CounterUtil.incrementAllCounters(visitCounter, req);
        String action = Optional.ofNullable(req.getParameter("action"))
                .orElse(ACTION_SAVE_PRODUCT);
        req.setAttribute("action", action);
        if (action.equals(ACTION_SAVE_PRODUCT)) {
            req.setAttribute("productName", req.getParameter("productName"));
            req.setAttribute("productPrice", req.getParameter("productPrice"));
        }
        if (action.equals(ACTION_UPDATE_PRODUCT)) {
            Product product = productDAO.getProductById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("productId", product.getId());
            req.setAttribute("productName", product.getName());
            req.setAttribute("productPrice", product.getPrice());
        }
        req.getRequestDispatcher("productEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String productId = req.getParameter("productId");
        String productName = req.getParameter("productName");
        String productPrice = req.getParameter("productPrice");

        if (action.equals(ACTION_SAVE_PRODUCT)) {
            List<String> errors = validation(null, productName, productPrice, false);
            if (errors.isEmpty()) {
                Product product = Product.builder()
                        .name(productName)
                        .price(new BigDecimal(productPrice))
                        .build();
                productDAO.insertProduct(product);
                resp.sendRedirect(req.getContextPath() + "/list");
            } else {
                handleErrorsForwarding(req, resp, errors);
            }
        }

        if (action.equals(ACTION_UPDATE_PRODUCT)) {
            List<String> errors = validation(productId, productName, productPrice, true);
            if (errors.isEmpty()) {
                Product product = Product.builder()
                        .id(Integer.parseInt(productId))
                        .name(productName)
                        .price(new BigDecimal(productPrice))
                        .build();
                productDAO.updateProduct(product);
                resp.sendRedirect(req.getContextPath() + "/list");
            } else {
                handleErrorsForwarding(req, resp, errors);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        productDAO.deleteProductById(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/list");
    }

    private void handleErrorsForwarding(HttpServletRequest req, HttpServletResponse resp, List<String> errors) throws ServletException, IOException {
        req.setAttribute("errors", errors);
        req.setAttribute("action", req.getParameter("action"));
        req.setAttribute("productId", req.getParameter("productId"));
        req.setAttribute("productName", req.getParameter("productName"));
        req.setAttribute("productPrice", req.getParameter("productPrice"));
        req.getRequestDispatcher("productEdit.jsp").forward(req, resp);
    }

    private List<String> validation(String productId, String productName, String productPrice, boolean idValidation) {
        List<String> errors = new ArrayList<>();
        if (!ValidatorUtil.stringIsNotBlank(productName)) {
            errors.add("brak nazwy produktu");
        }
        if (!ValidatorUtil.stringIsPrice(productPrice)) {
            errors.add("brak lub niepoprawna cena produktu");
        }
        if (idValidation && !ValidatorUtil.stringIsNumber(productId)) {
            errors.add("brak id");
        }
        return errors;
    }
}
