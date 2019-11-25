package pl.epoint.jzawadka.servletsample.DAO;

import pl.epoint.jzawadka.servletsample.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductPostgresDAO implements BaseDAO {

    private ProductPostgresDAO() { }

    private static ProductPostgresDAO dao;
    private static Connection connection;

    public static ProductPostgresDAO getInstance() {
        if (dao == null) {
            dao = new ProductPostgresDAO();
            connection = getPostgresConnection();
        }
        return dao;
    }

    public static Connection getPostgresConnection() {
        if(connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                Properties props = new Properties();
                props.setProperty("user","sapp");
                props.setProperty("password","servlet");
                return DriverManager.getConnection(
                        "jdbc:postgresql://postgresdata:5432/servlet_app",
                        props);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        return connection;
    }

    @Override
    public List<Product> getProductsList() {
        List<Product> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product");
            while (resultSet.next()) {
                list.add(Product.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .price(resultSet.getBigDecimal("price"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Product getProductById(Integer id) {
        Product product = null;
        try (Connection conn = connection;
             PreparedStatement preparedStatement =
                     conn.prepareStatement("select * from product * where id = ?");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.setInt(1, id);
            if (resultSet.next()) {
                product = Product.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .price(resultSet.getBigDecimal("price"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void insertProduct(Product product) {
        try (Connection conn = connection;
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("INSERT INTO Product(name, price) VALUES (" +
                    "'" + product.getName() + "'," +
                    product.getPrice() + ",");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        try (Connection conn = connection;
             Statement statement = conn.createStatement()) {
            statement.executeLargeUpdate("update Product set" +
                    " name='" + product.getName() + "'," +
                    " price=" + product.getPrice() +
                    " where id=" + product.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProductById(Integer id) {
        try (Connection conn = connection;
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("DELETE FROM Product where id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
