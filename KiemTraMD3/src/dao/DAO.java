package dao;

import model.Products;

import java.sql.SQLException;
import java.util.List;

public interface DAO {
    public Products selectProduct(int id);

    public List<Products> selectAllProduct();

    public void insertProduct(Products product) throws SQLException;

    public boolean deleteProduct(int id) throws SQLException;

    public boolean updateProduct (Products product) throws SQLException;
}
