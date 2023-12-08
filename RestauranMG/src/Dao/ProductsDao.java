package Dao;

import Entity.Products;
import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductsDao extends RestauranDao<Products, String> {

    final String INSERT_SQL = "INSERT INTO Products (ID_product, Name, Quantity, Unit, Price, ID_Categories) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE_ALL = "UPDATE Products SET "
            + "Name = ?, Quantity = ?, Unit = ?, Price = ?, ID_Categories = ? Where ID_product = ?";
    final String DELETE_SQL = "DELETE FROM Products WHERE ID_product = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Products";
    final String SELECT_BY_ID_SQL = "SELECT *  FROM Products WHERE ID_product = ?";
    final String FIND_BY_ID_OR_NAME = "SELECT TOP 1 * "
            + "FROM Products "
            + "WHERE ID_product LIKE ? OR Name LIKE ?";

    @Override
    public void insert(Products entity) {
        jdbc.update(INSERT_SQL,
                entity.getID_product(),
                entity.getName(),
                entity.getQuantity(),
                entity.getUnit(),
                entity.getPrice(),
                entity.ID_Catory
        );
    }

    @Override
    public void update(Products entity) {
        jdbc.update(UPDATE_ALL,
                entity.getName(),
                entity.getQuantity(),
                entity.getUnit(),
                entity.getPrice(),
                entity.ID_Catory,
                entity.getID_product());
    }

    @Override
    public void delete(String id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<Products> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    public List<Products> getProductsByCategory(String categoryName) {
        String sql = "SELECT p.* FROM Products p "
                + "INNER JOIN ProductCategories pc ON p.ID_Categories = pc.ID_Categories "
                + "WHERE pc.CategoryName = ?";
        return selectBySql(sql, categoryName);
    }

    public List<Products> Search(String key) {
        String sql = "select * from Products where ID_product like ? OR Name like ?";
        return selectBySql(sql, "%" + key + "%", "%" + key + "%");
    }

    public void updateQuantity(String idProduct, double quantity) {
        try {
            String sql = "UPDATE Products SET Quantity = Quantity + ? WHERE ID_product = ?";
            jdbc.update(sql, quantity, idProduct);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Products SearchFirst(String maHang, String tenHang) {
        if (maHang.isBlank()) {
            maHang = "NULL";
        }
        if (tenHang.isBlank()) {
            tenHang = "NULL";
        }
        List<Products> l = selectBySql(FIND_BY_ID_OR_NAME, "%" + maHang + "%", "%" + tenHang + "%");
        if (l.isEmpty()) {
            return null;
        }
        return l.get(0);
    }

    @Override
    public Products selectById(String id) {
        List<Products> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Products> selectBySql(String sql, Object... args) {
        List<Products> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while (rs.next()) {
                Products entity = new Products();
                entity.setID_product(rs.getString("ID_product"));
                entity.setName(rs.getString("Name"));
                entity.setQuantity(rs.getFloat("Quantity"));
                entity.setUnit(rs.getString("Unit"));
                entity.setPrice(rs.getInt("Price"));
                entity.ID_Catory = rs.getInt("ID_Categories");
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
