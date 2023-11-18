
package Dao;

import Entity.PaymentMethods;
import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodsDao extends RestauranDao<PaymentMethods, String>{
    final String INSERT_SQL = "INSERT INTO PaymentMethods (MethodName) VALUES (?)";
    final String UPDATE_ALL = "UPDATE PaymentMethods SET MethodName = ? WHERE ID_Method = ?";
    final String DELETE_SQL = "DELETE FROM PaymentMethods WHERE ID_Method = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM PaymentMethods";
    final String SELECT_BY_ID_SQL = "SELECT * FROM PaymentMethods WHERE ID_Method = ?";
    @Override
    public void insert(PaymentMethods entity) {
        jdbc.update(INSERT_SQL, 
                entity.getID_Method(),
                entity.getMethodName());
    }

    @Override
    public void update(PaymentMethods entity) {
        jdbc.update(UPDATE_ALL, 
                entity.getID_Method(),
                entity.getMethodName());
    }

    @Override
    public void delete(String id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<PaymentMethods> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public PaymentMethods selectById(String id) {
        List<PaymentMethods> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<PaymentMethods> selectBySql(String sql, Object... args) {
        List<PaymentMethods> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while(rs.next()) {
                PaymentMethods entity = new PaymentMethods();
                entity.setID_Method(rs.getInt("ID_Method"));
                entity.setMethodName(rs.getString("MethodName"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  list;
    }
    
}
