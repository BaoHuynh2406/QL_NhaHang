
package Dao;

import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class procDao {
    private List<Object[]> getListOfArray(String sql, String[] cols, Object...args) {
        try {     
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = jdbc.query(sql, args);
            while (rs.next()) {                
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Object[]> GetTableSummary(int ID_Area) {
        String sql = "{CALL GetTableSummary(?)}";
        String[] cols = {"ID_Table", "TableName", "TotalAmount", "NumberOfGuests"};
        return getListOfArray(sql, cols, ID_Area);
    }
}
