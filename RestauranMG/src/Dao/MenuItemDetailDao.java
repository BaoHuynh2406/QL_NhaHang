
package Dao;

import Entity.MenuItemDetail;
import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDetailDao extends RestauranDao<MenuItemDetail, String>{
    final String INSERT_SQL = "INSERT INTO MenuItemDetail (ID_Item, ID_Product, Quantity) VALUES (?, ?, ?)";
    final String UPDATE_ALL = "UPDATE MenuItemDetail SET ID_Item = ?, ID_Product = ?, Quantity = ? WHERE ID_MIT= ?";
    final String DELETE_SQL = "DELETE FROM MenuItemDetail WHERE ID_MIT = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM MenuItemDetail";
    final String SELECT_BY_ID_SQL = "SELECT * FROM MenuItemDetail WHERE ID_MIT = ?";
    @Override
    public void insert(MenuItemDetail entity) {
        jdbc.update(INSERT_SQL,
                entity.getID_MIT(),
                entity.getID_Item(),
                entity.getID_Product(),
                entity.getQuantity());
    }

    @Override
    public void update(MenuItemDetail entity) {
        jdbc.update(UPDATE_ALL,
                entity.getID_MIT(),
                entity.getID_Item(),
                entity.getID_Product(),
                entity.getQuantity());
    }

    @Override
    public void delete(String id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<MenuItemDetail> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public MenuItemDetail selectById(String id) {
        List<MenuItemDetail> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<MenuItemDetail> selectBySql(String sql, Object... args) {
        List<MenuItemDetail> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while(rs.next()) {
                MenuItemDetail entity = new MenuItemDetail();
                entity.setID_MIT(rs.getInt("ID_MIT"));
                entity.setID_Item(rs.getInt("ID_Item"));
                entity.setID_Product(rs.getString("ID_Product"));
                entity.setQuantity(rs.getInt("Quantity"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  list;
    }
    
}
