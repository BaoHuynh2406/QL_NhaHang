
package Dao;

import Entity.OrderDetail;
import Entity.Role;
import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class OrderDetailDao extends RestauranDao<OrderDetail, String>{
    final String INSERT_SQL = "INSERT INTO OrderDetail (ID_Order, ID_Item, Quantity, Price, TotalPrice) "
            + "VALUES ('?', '?', '?', '?', '?')";
    final String UPDATE_ALL = "UPDATE OrderDetail SET "
            + "ID_Order = '?', ID_Item = '?', Quantity = '?', Price = '?',"
            + " TotalPrice = '?' WHERE ID_OrderDetail = '?'";
    final String DELETE_SQL = "DELETE FROM OrderDetail WHERE ID_OrderDetail = '?'";
    final String SELECT_ALL_SQL = "SELECT * FROM OrderDetail";
    final String SELECT_BY_ID_SQL = "SELECT * FROM OrderDetail WHERE ID_OrderDetail = '?'";
    @Override   
    public void insert(OrderDetail entity) {
        jdbc.update(INSERT_SQL,
                entity.getID_OrderDetail(),
                entity.getID_Order(),
                entity.getID_Item(),
                entity.getQuantity(),
                entity.getPrice(),
                entity.getTotalPrice());
    }

    @Override
    public void update(OrderDetail entity) {
        jdbc.update(UPDATE_ALL,
                entity.getID_OrderDetail(),
                entity.getID_Order(),
                entity.getID_Item(),
                entity.getQuantity(),
                entity.getPrice(),
                entity.getTotalPrice());
    }

    @Override
    public void delete(String id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<OrderDetail> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public OrderDetail selectById(String id) {
        List<OrderDetail> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<OrderDetail> selectBySql(String sql, Object... args) {
        List<OrderDetail> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while(rs.next()) {
                OrderDetail entity = new OrderDetail();
                entity.setID_OrderDetail(rs.getInt("ID_OrderDetail"));
                entity.setID_Order(rs.getInt("ID_Order"));
                entity.setID_Item(rs.getInt("ID_Item"));
                entity.setQuantity(rs.getInt("Quantity"));
                entity.setPrice(rs.getDouble("Price"));
                entity.setTotalPrice(rs.getDouble("TotalPrice"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  list;
    }
    
}
