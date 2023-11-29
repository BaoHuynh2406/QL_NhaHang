
package Dao;

import UI.Model.Model_Mon_Da_Goi;
import Utils.jdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class procDao {
 
    public static void DeleteOrdersWithoutDetails() {
        try {
            PreparedStatement stmt = jdbc.getStmt("{CALL DeleteOrdersWithoutDetails}");
            stmt.execute();
        } catch (Exception e) {
        }

    }
    
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
    
    
    public Map<Integer, Model_Mon_Da_Goi> GetGetItemsByTableID(int ID_table){
        try {
        Map<Integer, Model_Mon_Da_Goi> list = new HashMap<>();
        String sql = "{CALL GetItemsByTableID(?)}";
        ResultSet r = jdbc.query(sql, ID_table);
        while(r.next()){
            Model_Mon_Da_Goi i = new Model_Mon_Da_Goi();
            i.setId(r.getInt("ID_item"));
            i.setName(r.getString("ItemName"));
            i.setPrice(r.getInt("Price"));
            i.setSl(r.getInt("Quantity"));
            i.ID_Order = r.getInt("ID");
            i.setType(Model_Mon_Da_Goi.ItemType.ChuaGoi);
            list.put(i.getId(), i);
        }
        return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
