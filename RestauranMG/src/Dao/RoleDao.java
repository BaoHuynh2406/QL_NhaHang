
package Dao;

import Entity.Role;
import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends RestauranDao<Role, String>{
    final String INSERT_SQL = "INSERT INTO Role (ID_role, RoleName) VALUES (?, ?)";
    final String UPDATE_ALL = "UPDATE Role SET RoleName = ? WHERE ID_role = ?";
    final String DELETE_SQL = "DELETE FROM Role WHERE ID_role = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Role";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Role WHERE ID_role = ?";
    @Override
    public void insert(Role entity) {
        jdbc.update(INSERT_SQL,
                entity.getID_role(),
                entity.getRoleNam());
    }

    @Override
    public void update(Role entity) {
        jdbc.update(UPDATE_ALL,
                entity.getID_role(),
                entity.getRoleNam());
    }

    @Override
    public void delete(String id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<Role> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Role selectById(String id) {
        List<Role> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Role> selectBySql(String sql, Object... args) {
        List<Role> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while(rs.next()) {
                Role entity = new Role();
                entity.setID_role(rs.getString("ID_role"));
                entity.setRoleNam(rs.getString("RoleName"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  list;
    }
}
