
package Dao;

import Entity.Role;
import Entity.Tables;
import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TablesdDao extends RestauranDao<Tables, String>{
    final String INSERT_SQL = "INSERT INTO Tables (TableName, ID_Area, IsOccupied) VALUES (N'?', '?', '?')";
    final String UPDATE_ALL = "UPDATE Tables SET TableName = '?', ID_Area = '?', IsOccupied = '?' WHERE ID_Table = '?'";
    final String DELETE_SQL = "DELETE FROM Tables WHERE ID_Table = '?'";
    final String SELECT_ALL_SQL = "SELECT * FROM Tables";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Tables WHERE ID_Table = '?'";
    @Override
    public void insert(Tables entity) {
        jdbc.update(INSERT_SQL,
                entity.getID_Table(),
                entity.getTableName(),
                entity.getID_Area(),
                entity.isIsOccupied());
    }

    @Override
    public void update(Tables entity) {
        jdbc.update(UPDATE_ALL, 
                entity.getID_Table(),
                entity.getTableName(),
                entity.getID_Area(),
                entity.isIsOccupied());
    }

    @Override
    public void delete(String id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<Tables> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Tables selectById(String id) {
        List<Tables> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Tables> selectBySql(String sql, Object... args) {
        List<Tables> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while(rs.next()) {
                Tables entity = new Tables();
                entity.setID_Table(rs.getInt("ID_Table"));
                entity.setTableName(rs.getString("TableName"));
                entity.setID_Area(rs.getInt("ID_Area"));
                entity.setIsOccupied(rs.getBoolean("IsOccupied"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  list;
    }
    
}
