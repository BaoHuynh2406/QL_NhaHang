
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
    
    public List<Object[]> getDonHang(int ID_Order){
        String sql = "select od.ID_Item,  m.ItemName, od.Quantity, od.price, od.totalPrice from OrderDetail od "
                + "inner join Orders o ON o.ID_Order = od.ID_Order "
                + "inner join tables t ON t.ID_Table = o.ID_table "
                +"inner join MenuItems m ON od.ID_Item = m.ID_Item "
                + "where od.ID_order = ?";
        String cols[] = {"ID_Item","ItemName","Quantity","Price","TotalPrice"};
        return getListOfArray(sql, cols, ID_Order);
    }
}
