package pl.epoint.jzawadka.servletsample.DAO;

import pl.epoint.jzawadka.servletsample.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductMockDAO implements BaseDAO {

    private static List<Product> products;

    private static Integer nextId = 9;

    private ProductMockDAO() {
    }

    private static ProductMockDAO singleton;

    static synchronized ProductMockDAO getInstance() {
        if (singleton == null) {
            singleton = new ProductMockDAO();
            singleton.populate();
        }
        return singleton;
    }

    private void populate() {
        products = new ArrayList<>(Arrays.asList(
                Product.builder()
                        .id(1)
                        .name("Reloop ELITE - NOWOŚĆ")
                        .price(new BigDecimal("3999.99"))
                        .build(),

                Product.builder()
                        .id(2)
                        .name("Pioneer DJM-450 Mikser")
                        .price(new BigDecimal("1099.49"))
                        .build(),

                Product.builder()
                        .id(3)
                        .name("MIXARS MXR-4")
                        .price(new BigDecimal("3999.99"))
                        .build(),

                Product.builder()
                        .id(4)
                        .name("MIXARS MXR-2")
                        .price(new BigDecimal("1199.50"))
                        .build(),

                Product.builder()
                        .id(5)
                        .name("MIXARS CUT MK II")
                        .price(new BigDecimal("2500."))
                        .build(),

                Product.builder()
                        .id(6)
                        .name("MIXARS DUO MK II")
                        .price(new BigDecimal("12099.99"))
                        .build(),

                Product.builder()
                        .id(7)
                        .name("MIXARS QUATTRO")
                        .price(new BigDecimal("15000.99"))
                        .build(),

                Product.builder()
                        .id(8)
                        .name("Reloop KUT Mikser Battle FX")
                        .price(new BigDecimal("24999.99"))
                        .build()
        ));
    }

    @Override
    public List<Product> getProductsList() {
        return products;
    }

    @Override
    public Product getProductById(Integer id) {
        return products.stream()
                .filter(products -> id.equals(products.getId()))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void insertProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
    }

    @Override
    public void updateProduct(Product updateProduct) {
        Product oldProduct = products.stream()
                .filter(products -> updateProduct.getId().equals(products.getId()))
                .findFirst().orElseThrow(NoSuchElementException::new);
        oldProduct.setName(updateProduct.getName());
        oldProduct.setPrice(updateProduct.getPrice());
    }

    @Override
    public void deleteProductById(Integer id) {
        products.removeIf(product -> product.getId().equals(id));
    }

}
