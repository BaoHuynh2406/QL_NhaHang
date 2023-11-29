
package Dao;
import Entity.Orders;
import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao extends RestauranDao<Orders, Integer>{
    final String INSERT_SQL = "INSERT INTO Orders (ID_Table, ID_Employee, OrderDate, NumberOfGuests, IsPaid)" +
                "	VALUES (?, ?, ?, ?, ?)";
    final String UPDATE_ALL = "UPDATE Orders SET "
            + "ID_Table = ?, ID_Employee = ?, OrderDate = ?, "
            + "NumberOfGuests = ?, IsPaid = ? WHERE ID_Order = ?";
    final String DELETE_SQL = "DELETE FROM Orders WHERE ID_Order = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Orders";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Orders WHERE ID_Order = ?";

    public int GetID() {
        try {
            String sql = "Select MAX(ID_Order) ID From Orders";
            ResultSet r = jdbc.query(sql);
            int id = 0;
            while (r.next()) {
                id = r.getInt("ID");
            }
            return id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }
    
    
     @Override
    public void insert(Orders entity) {
        jdbc.update(INSERT_SQL,
                entity.getID_Table(),
                entity.getID_Employee(),
                entity.getOrderDate(),
                entity.getNumberOfGuests(),
                entity.isIsPaid());
    }

    @Override
    public void update(Orders entity) {
        jdbc.update(UPDATE_ALL,
                entity.getID_Table(),
                entity.getID_Employee(),
                entity.getOrderDate(),
                entity.getNumberOfGuests(),
                entity.isIsPaid(),
                entity.getID_Order());
    }

    @Override
    public void delete(Integer id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<Orders> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Orders selectById(Integer id) {
        List<Orders> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Orders> selectBySql(String sql, Object... args) {
        List<Orders> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while(rs.next()) {
                Orders entity = new Orders();
                entity.setID_Order(rs.getInt("ID_Order"));
                entity.setID_Table(rs.getInt("ID_Table"));
                entity.setID_Employee(rs.getInt("ID_Employee"));
                entity.setOrderDate(rs.getDate("OrderDate"));
                entity.setNumberOfGuests(rs.getInt("NumberOfGuests"));
                entity.setIsPaid(rs.getBoolean("IsPaid"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  list;
    }
    
}
