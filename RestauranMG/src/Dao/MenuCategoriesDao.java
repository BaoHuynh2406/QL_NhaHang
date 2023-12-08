
package Dao;

import Entity.MenuCategories;
import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuCategoriesDao extends RestauranDao<MenuCategories, Integer>{
    final String INSERT_SQL = "INSERT INTO MenuCategories (CategoryName) VALUES (?)";
    final String UPDATE_ALL = "UPDATE MenuCategories SET CategoryName = ? WHERE ID_Category = ?";
    final String DELETE_SQL = "DELETE FROM MenuCategories WHERE ID_Category = ?";
    final String SELECT_ALL_SQL = "SELECT *  FROM MenuCategories";
    final String SELECT_BY_ID_SQL = "SELECT *  FROM MenuCategories WHERE ID_Category = ?";
    @Override
    public void insert(MenuCategories entity) {
        jdbc.update(INSERT_SQL,
                entity.getCategoryName());
    }

    @Override
    public void update(MenuCategories entity) {
        jdbc.update(UPDATE_ALL,
                entity.getCategoryName(),
                entity.getID_Category());
                
    }

    @Override
    public void delete(Integer id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<MenuCategories> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public MenuCategories selectById(Integer id) {
        List<MenuCategories> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<MenuCategories> selectBySql(String sql, Object... args) {
        List<MenuCategories> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while(rs.next()) {
                MenuCategories entity = new MenuCategories();
                entity.setID_Category(rs.getInt("ID_Category"));
                entity.setCategoryName(rs.getString("CategoryName"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  list;
    }
    
}
