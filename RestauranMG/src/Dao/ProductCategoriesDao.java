
package Dao;

import Entity.ProductCategories;
import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class ProductCategoriesDao extends RestauranDao<ProductCategories, Integer>{
    final String INSERT_SQL = "INSERT INTO ProductCategories (CategoryName) VALUES (?)";
    final String UPDATE_ALL = "UPDATE ProductCategories SET CategoryName = ? WHERE ID_Categories = ?";
    final String DELETE_SQL = "DELETE FROM ProductCategories WHERE ID_Categories = ?";
    final String SELECT_ALL_SQL = "SELECT *  FROM ProductCategories";
    final String SELECT_BY_ID_SQL = "SELECT *  FROM ProductCategories WHERE ID_Categories = ?";
    @Override
    public void insert(ProductCategories entity) {
        jdbc.update(INSERT_SQL,
                entity.getCategoryName());
    }

    @Override
    public void update(ProductCategories entity) {
        jdbc.update(UPDATE_ALL,
                entity.getCategoryName(),
                entity.getID_Categories()); 
    }

    @Override
    public void delete(Integer id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<ProductCategories> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ProductCategories selectById(Integer id) {
        List<ProductCategories> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0); 
    }

    @Override
    public List<ProductCategories> selectBySql(String sql, Object... args) {
        List<ProductCategories> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while(rs.next()) {
                ProductCategories entity = new ProductCategories();
                entity.setID_Categories(rs.getInt("ID_Categories"));
                entity.setCategoryName(rs.getString("CategoryName"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  list;
    }
    
    
}
