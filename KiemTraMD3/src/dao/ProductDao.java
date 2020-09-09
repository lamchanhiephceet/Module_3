package dao;

import connection.ConnectionSQL;
import model.Category;
import model.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements DAO {
    ConnectionSQL connectionSQL = new ConnectionSQL();

    private static final String SELECT_ALL_PRODUCT = "select * from products";
    private static final String SELECT_PRODUCT_SQL = "insert into products " + " (name, price, quantity, color, description, category) " + "value " + " (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT_SQL = "update products set name = ?, price = ?, quantity = ?, color = ?, description = ?, category = ? " + " where id = ?";
    private static final String SELECT_PRODUCT_BY_ID = "select * from products where id = ?";
    private static final String DELETE_PRODUCT_SQL = "delete from products where id=?";


    @Override
    public Products selectProduct(int id) {
        Products products = null;
        try (Connection connection = connectionSQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                String category = rs.getString("category");
                products = new Products(id, name, price, quantity, color, description, category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Products> selectAllProduct() {
        List<Products> products = new ArrayList<>();
        try (Connection connection = connectionSQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCT)) {

            ResultSet rs = statement.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                String category = rs.getString("category");
                products.add(new Products(id, name, price, quantity, color, description, category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void insertProduct(Products product) throws SQLException {
        try (Connection connection = connectionSQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_SQL)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getColor());
            statement.setString(5, product.getDescription());
            statement.setString(6, product.getCategory());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = connectionSQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateProduct(Products products) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = connectionSQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            statement.setString(1, products.getName());
            statement.setInt(2, products.getPrice());
            statement.setInt(3, products.getQuantity());
            statement.setString(4, products.getColor());
            statement.setString(5, products.getDescription());
            statement.setString(6, products.getCategory());
            statement.setInt(7, products.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public List<Category> getAllCategory() {
        ConnectionSQL connectionSQL = new ConnectionSQL();
        String sql = "select * from category";
        List<Category> list = new ArrayList<>();
        try (Statement stm = connectionSQL.getConnection().createStatement()) {
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                list.add(new Category(
                        rs.getString("name")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<Products> searchItemByName(String search) {
        List<Products> list = new ArrayList<>();
        String SQL = "select * from products where trim(replace(name,' ','')) like '%" + search + "%'";
        try (Statement ps = connectionSQL.getConnection().createStatement()) {
            ResultSet rs = ps.executeQuery(SQL);
            while (rs.next()) {
                list.add(
                        new Products(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("price"),
                                rs.getInt("quantity"),
                                rs.getString("color"),
                                rs.getString("description"),
                                rs.getString("category")
                        )
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
