package Dao;

import Entity.Products;
import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductsDao extends RestauranDao<Products, String> {

    final String INSERT_SQL = "INSERT INTO Products (ID_product, Name, Quantity, Unit, Price) "
            + "VALUES (?, ?, ?, ?, ?)";
    final String UPDATE_ALL = "UPDATE Products SET "
            + "Name = ?, Quantity = ?, Unit = ?, Price = ? Where ID_product = ?";
    final String DELETE_SQL = "DELETE FROM Products WHERE ID_product = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Products";
    final String SELECT_BY_ID_SQL = "SELECT *  FROM Products WHERE ID_product = ?";

    @Override
    public void insert(Products entity) {
        jdbc.update(INSERT_SQL,
                entity.getID_product(),
                entity.getName(),
                entity.getQuantity(),
                entity.getUnit(),
                entity.getPrice());
    }

    @Override
    public void update(Products entity) {
        jdbc.update(UPDATE_ALL,
                entity.getName(),
                entity.getQuantity(),
                entity.getUnit(),
                entity.getPrice(),
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

    public List<Products> Search(String key) {
        String sql = "select * from Products where ID_product like ? OR Name like ?";
        return selectBySql(sql, "%" + key + "%", "%" + key + "%");
    }
    public int updateQuantity(String idProduct, int newQuantity) {
        String UPDATE_QUANTITY_SQL = "UPDATE Products SET Quantity = ? WHERE ID_product = ?";
        return jdbc.update(UPDATE_QUANTITY_SQL, newQuantity, idProduct);
    }
    
//    public Products SearchFirst(String maHang, String tenHang) {
//        String sql = "select * from Products where ID_product like ?  OR Name like ?";
//        List<Products> l = selectBySql(sql, "%" + maHang + "%", "%" + tenHang + "%");
//        if(l.isEmpty()){
//            return null;
//        }
//        return l.get(0);
//    }

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
                entity.setQuantity(rs.getInt("Quantity"));
                entity.setUnit(rs.getString("Unit"));
                entity.setPrice(rs.getDouble("Price"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
