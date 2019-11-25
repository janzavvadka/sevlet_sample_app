package pl.epoint.jzawadka.servletsample.DAO;

import pl.epoint.jzawadka.servletsample.model.Product;

import java.util.List;

public interface BaseDAO {
    List<Product> getProductsList();
    Product getProductById(Integer id);
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProductById(Integer id);
}
