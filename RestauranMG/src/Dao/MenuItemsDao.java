
package Dao;

import Entity.MenuItems;

import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuItemsDao extends RestauranDao<MenuItems, String>{
    final String INSERT_SQL = "INSERT INTO MenuItems (ItemName, ID_Category, Price, Photo) "
            + "VALUES (?, ?, ?, ?)";
    final String UPDATE_ALL = "UPDATE MenuItems SET ItemName = ?, ID_Category = ?, Price = ?, Photo = ?, IsAvailable = ? WHERE ID_Item = ?";
    final String DELETE_SQL = "DELETE FROM MenuItems WHERE ID_Item = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM MenuItems";
    final String SELECT_BY_ID_SQL = "SELECT * FROM MenuItems WHERE ID_Item = ?";
    @Override
    public void insert(MenuItems entity) {
        jdbc.update(INSERT_SQL,
                entity.getID_Item(),
                entity.getItemName(),
                entity.getID_Category(),
                entity.getPrice(),
                entity.getPhoto());
    }

    @Override
    public void update(MenuItems entity) {
        jdbc.update(UPDATE_ALL,
                entity.getID_Item(),
                entity.getItemName(),
                entity.getID_Category(),
                entity.getPrice(),
                entity.getPhoto(),
                    entity.isAvailable());
    }

    @Override
    public void delete(String id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<MenuItems> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    public List<MenuItems> selectCatory(int ID_catory){
        if(ID_catory == 0){
            return selectAll();
        }
        
        String sql = "Select * from MenuItems where ID_Category = ?";
        return selectBySql(sql, ID_catory);
    }
    
    @Override
    public MenuItems selectById(String id) {
        List<MenuItems> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<MenuItems> selectBySql(String sql, Object... args) {
        List<MenuItems> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while(rs.next()) {
                MenuItems entity = new MenuItems();
                entity.setID_Item(rs.getInt("ID_Item"));
                entity.setItemName(rs.getString("ItemName"));
                entity.setID_Category(rs.getInt("ID_Category"));
                entity.setPrice(rs.getInt("Price"));
                entity.setPhoto(rs.getString("Photo"));
                entity.setAvailable(rs.getBoolean("isAvailable"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  list;
    }
    
}
