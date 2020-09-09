package dao;

import model.Categoryhihi;
import model.Product;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class ProductDAO {
     Properties loadConnectionProperties() {
        Properties properties = new Properties();
         try {
             properties.load(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
         } catch (IOException e) {
             e.printStackTrace();
         }
        return properties;
    }

    private String jdbcUsername = loadConnectionProperties().getProperty("jdbcUsername");;
    private String jdbcURL = loadConnectionProperties().getProperty("jdbcURL");
    private String jdbcPassword = loadConnectionProperties().getProperty("jdbcPassword");;

    private static final String SELECT_ALL_PRODUCTS = "select product.id, product.name, product.price , category.name, product.description ,product.img from product  left join category on product.category_id = category.id";
    private static final String SELECT_ID_PRODUCTS = "select product.id, product.name, product.price ,category.name , product.description ,product.img from product  left join category on product.category_id = category.id where product.id=?";
    private static final String SELECT_ALL_CATEGORIES = " select * from category";
    private static final String SEARCH_PRODUCT_NAME = "select product.id, product.name, product.price , category.name , product.description ,product.img from product  left join category on product.category_id = category.id where product.name like ?";
    private static final String SELECT_PRODUCT_SQL = "insert into product " + " (name, price, category_id, description, img) " + "value " + " (?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT_SQL = "update product set name = ?, price = ?, category_id = ?, description = ?, img = ? " + " where id = ?";
    private static final String DELETE_PRODUCT_SQL = "delete from product where id=?";
    public ProductDAO(){

    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }


    public List<Product> selectALLProducts() throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement prs = conn.prepareStatement(SELECT_ALL_PRODUCTS);
        System.out.println(SELECT_ALL_PRODUCTS);
        System.out.println(prs);
        ResultSet rs = prs.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            String category = rs.getString(4);
            String des  = rs.getString(5);
            String img = rs.getString(6);


            list.add(new Product(id,name, price, category,des,img));
        }
        return list;
    }

    public Product productselectID(int id) throws SQLException {
        Product product = null;
        Connection conn = getConnection();
        PreparedStatement prs = conn.prepareStatement(SELECT_ID_PRODUCTS);
        System.out.println(SELECT_ID_PRODUCTS);
        System.out.println(prs);
        prs.setInt(1,id);
        ResultSet rs = prs.executeQuery();
        while (rs.next()){
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            String category = rs.getString(4);
            String des  = rs.getString(5);
            String img = rs.getString(6);


            product=new Product(id,name, price,category,des,img );
        }
        return product;
    }

    public List<Categoryhihi> selectALLCategories() throws SQLException {
        List<Categoryhihi> categories = new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement prs = conn.prepareStatement(SELECT_ALL_CATEGORIES);
        System.out.println(SELECT_ALL_PRODUCTS);
        System.out.println(prs);
        ResultSet rs = prs.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            categories.add(new Categoryhihi(id, name));
        }
        return categories;
    }

    public List<Product> searchrProducts(String s) throws SQLException {
        List<Product> list2 = new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement prs = conn.prepareStatement(SEARCH_PRODUCT_NAME);
        System.out.println(SELECT_ID_PRODUCTS);
        System.out.println(prs);
        prs.setString(1,"%" + s + "%");
        ResultSet rs = prs.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            String category = rs.getString(4);
            String des  = rs.getString(5);
            String img = rs.getString(6);


            list2.add(new Product(id,name, price,category, des,img));
        }
        return list2;
    }

    public void insertProduct(Product product) {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_PRODUCT_SQL)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getCategory());
            statement.setString(4, product.getDescription());
            statement.setString(5, product.getImg());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdated;
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_PRODUCT_SQL)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getCategory());
            statement.setString(4, product.getDescription());
            statement.setString(5, product.getImg());
            statement.setInt(6, product.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_PRODUCT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

}
