package Dao;

import Entity.PurchaseOrders;
import Utils.jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrdersDao extends RestauranDao<PurchaseOrders, Integer> {

    final String INSERT_SQL = "INSERT INTO PurchaseOrders (ID_PurchaseOrder, OrderDate, ID_Employee) VALUES (?, ?, ?)";
    final String UPDATE_ALL = "UPDATE PurchaseOrders SET OrderDate = ?, ID_Employee = ? WHERE ID_PurchaseOrder = ?";
    final String DELETE_SQL = "DELETE FROM PurchaseOrders WHERE ID_PurchaseOrder = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM PurchaseOrders";
    final String SELECT_BY_ID_SQL = "SELECT * FROM PurchaseOrders WHERE ID_PurchaseOrder = ?";

    @Override
    public void insert(PurchaseOrders entity) {
        jdbc.update(INSERT_SQL,
                entity.getID_PurchaseOrder(),
                entity.getOrderDate(),
                entity.getID_Employee());
    }

    @Override
    public void update(PurchaseOrders entity) {
        jdbc.update(UPDATE_ALL,
                entity.getOrderDate(),
                entity.getID_Employee(),
                entity.getID_PurchaseOrder());
    }

    @Override
    public void delete(Integer id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<PurchaseOrders> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public PurchaseOrders selectById(Integer id) {
        List<PurchaseOrders> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<PurchaseOrders> selectBySql(String sql, Object... args) {
        List<PurchaseOrders> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while (rs.next()) {
                PurchaseOrders entity = new PurchaseOrders();
                entity.setID_PurchaseOrder(rs.getInt("ID_PurchaseOrder"));
                entity.setOrderDate(rs.getDate("OrderDate"));
                entity.setID_Employee(rs.getInt("ID_Employee"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
