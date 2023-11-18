
package Dao;

import Entity.PurchaseOrdersDetail;

import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrdersDetailDao extends RestauranDao<PurchaseOrdersDetail, String>{
    final String INSERT_SQL = "INSERT INTO PurchaseOrdersDetail (ID_PurchaseOrder, ID_product, Quantity, Price) "
            + "VALUES (?, ?, ?, ?)";
    final String UPDATE_ALL = "UPDATE PurchaseOrdersDetail SET"
            + " ID_PurchaseOrder = ?, ID_product = ?, Quantity = ?, Price = ? WHERE ID_POD = ?";
    final String DELETE_SQL = "DELETE FROM PurchaseOrdersDetail WHERE ID_POD = ?";
    final String SELECT_ALL_SQL = "SELECT *  FROM PurchaseOrdersDetail";
    final String SELECT_BY_ID_SQL = "SELECT *  FROM PurchaseOrdersDetail WHERE ID_POD = ?";
    @Override
    public void insert(PurchaseOrdersDetail entity) {
        jdbc.update(INSERT_SQL,
                entity.getID_POD(),
                entity.getID_PurchaseOrder(),
                entity.getID_Product(),
                entity.getQuantity(),
                entity.getPrice());
    }

    @Override
    public void update(PurchaseOrdersDetail entity) {
        jdbc.update(UPDATE_ALL,
                entity.getID_POD(),
                entity.getID_PurchaseOrder(),
                entity.getID_Product(),
                entity.getQuantity(),
                entity.getPrice());
    }

    @Override
    public void delete(String id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<PurchaseOrdersDetail> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public PurchaseOrdersDetail selectById(String id) {
        List<PurchaseOrdersDetail> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<PurchaseOrdersDetail> selectBySql(String sql, Object... args) {
        List<PurchaseOrdersDetail> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while(rs.next()) {
                PurchaseOrdersDetail entity = new PurchaseOrdersDetail();
                entity.setID_POD(rs.getInt("ID_POD"));
                entity.setID_PurchaseOrder(rs.getInt("ID_PurchaseOrder"));
                entity.setID_Product(rs.getString("ID_product"));
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
