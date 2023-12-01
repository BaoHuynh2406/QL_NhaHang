
package Dao;

import Entity.Invoices;

import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InvoicesDao extends RestauranDao<Invoices, String>{
    final String INSERT_SQL = "INSERT INTO Invoices (ID_Order, ID_Method, ID_Employee, ID_Tax, InvoiceDate, TaxAmount, TotalAmount, IsPaid)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_ALL = "UPDATE Invoices SET ID_Order = ?, ID_Method = ?, "
            + "ID_Employee = ?, ID_Tax = ?, InvoiceDate = ?, TaxAmount = ?, "
            + "TotalAmount = ?, IsPaid = ? WHERE ID_Invoice = ?";
    final String DELETE_SQL = "DELETE FROM Invoices WHERE ID_Invoice = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Invoices";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Invoices WHERE ID_Invoice = ?";
    final String UpdateThanhToan = "UPDATE Invoices set ID_Method = ?, InvoiceDate = ?, IsPaid = ? where ID_Invoice = ?";
    @Override
    public void insert(Invoices entity) {
        jdbc.update(INSERT_SQL,
                entity.getID_Order(),
                entity.getID_Method(),
                entity.getID_Employee(),
                entity.getID_Tax(),
                entity.getInvoiceDate(),
                entity.getTaxAmount(),
                entity.getTotalAmount(),
                entity.isPaid());
    }

    public int getID(int ID_order){
        try {
            String sql = "select ID_Invoice from Invoices where ID_Order = ? and IsPaid = 0";
            ResultSet r = jdbc.query(sql, ID_order);
            int id = -1;
            while (r.next()) {
                id = r.getInt("ID_Invoice");
            }
            return id;
        } catch (Exception e) {
            return -1;
        }
    }
    
    public int getNewID(){
        try {
            String sql = "Select MAX(ID_Invoice) ID From Invoices";
            ResultSet r = jdbc.query(sql);
            int id = 0;
            while (r.next()) {
                id = r.getInt("ID");
            }
            return id;
        } catch (Exception e) {
            return -1;
        }
    }
    
    @Override
    public void update(Invoices entity) {
        jdbc.update(UPDATE_ALL,
                entity.getID_Invoice(),
                entity.getID_Order(),
                entity.getID_Method(),
                entity.getID_Employee(),
                entity.getID_Tax(),
                entity.getInvoiceDate(),
                entity.getTaxAmount(),
                entity.getTotalAmount(),
                entity.isPaid());
    }

    public void updateThanhToan(Invoices e){
        jdbc.update(UpdateThanhToan, 
                        e.getID_Method(),
                        e.getInvoiceDate(),
                        e.isPaid(),
                        e.getID_Invoice()
                );
    }
    @Override
    public void delete(String id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<Invoices> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Invoices selectById(String id) {
        List<Invoices> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Invoices> selectBySql(String sql, Object... args) {
        List<Invoices> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while(rs.next()) {
                Invoices entity = new Invoices();
                entity.setID_Invoice(rs.getInt("ID_Invoice"));
                entity.setID_Order(rs.getInt("ID_Order"));
                entity.setID_Method(rs.getInt("ID_Method"));
                entity.setID_Employee(rs.getInt("ID_Employee"));
                entity.setID_Tax(rs.getInt("ID_Tax"));
                entity.setInvoiceDate(rs.getDate("InvoiceDate"));
                entity.setTaxAmount(rs.getDouble("TaxAmount"));
                entity.setTotalAmount(rs.getDouble("TotalAmount"));
                entity.setPaid(rs.getBoolean("IsPaid"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  list;
    }
    
}
