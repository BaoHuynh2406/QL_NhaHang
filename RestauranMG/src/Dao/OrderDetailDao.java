
package Dao;

import Entity.OrderDetail;
import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class OrderDetailDao extends RestauranDao<OrderDetail, Integer>{
    final String INSERT_SQL = "INSERT INTO OrderDetail (ID_Order, ID_Item, Quantity, Price, TotalPrice) "
            + "VALUES (?, ?, ?, ?, ?)";
    final String UPDATE_ALL = "UPDATE OrderDetail SET "
            + "ID_Order = ?, ID_Item = ?, Quantity = ?, Price = ?,"
            + " TotalPrice = ? WHERE ID_Order = ? and ID_Item = ?";
    final String DELETE_SQL = "DELETE FROM OrderDetail WHERE ID_OrderDetail = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM OrderDetail";
    final String SELECT_BY_ID_SQL = "SELECT * FROM OrderDetail WHERE ID_OrderDetail = ?";
    @Override   
    public void insert(OrderDetail entity) {
        jdbc.update(INSERT_SQL,
                entity.getID_Order(),
                entity.getID_Item(),
                entity.getQuantity(),
                entity.getPrice(),
                entity.getTotalPrice());
    }

    @Override
    public void update(OrderDetail entity) {
        jdbc.update(UPDATE_ALL,
                entity.getID_Order(),
                entity.getID_Item(),
                entity.getQuantity(),
                entity.getPrice(),
                entity.getTotalPrice(),
                entity.getID_Order(),
                entity.getID_Item()
        );
    }

    @Override
    public void delete(Integer id) {
        jdbc.update(DELETE_SQL, id);
    }
    
    public void delete(Integer ID_item, Integer ID_DonHang){
        jdbc.update("DELETE FROM OrderDetail WHERE ID_Item = ? AND ID_Order = ?", ID_item, ID_DonHang);
    }

    @Override
    public List<OrderDetail> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public OrderDetail selectById(Integer id) {
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
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  list;
    }
    
}
