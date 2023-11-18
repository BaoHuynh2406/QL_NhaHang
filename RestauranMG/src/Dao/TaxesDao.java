
package Dao;

import Entity.Taxes;
import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class TaxesDao extends RestauranDao<Taxes, String>{
    final String INSERT_SQL = "INSERT INTO Taxes (TaxName, TaxRate) VALUES (?, ?)";
    final String UPDATE_ALL = "UPDATE Taxes SET TaxName = ?, TaxRate = ? WHERE ID_Tax = ?";
    final String DELETE_SQL = "DELETE FROM Taxes WHERE ID_Tax = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Taxes";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Taxes WHERE ID_Tax = ?";
    @Override
    public void insert(Taxes entity) {
        jdbc.update(INSERT_SQL,
                entity.getID_Tax(),
                entity.getTaxName(),
                entity.getTaxRate());
    }

    @Override
    public void update(Taxes entity) {
        jdbc.update(UPDATE_ALL,
                entity.getID_Tax(),
                entity.getTaxName(),
                entity.getTaxRate());
    }

    @Override
    public void delete(String id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<Taxes> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Taxes selectById(String id) {
        List<Taxes> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Taxes> selectBySql(String sql, Object... args) {
        List<Taxes> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while(rs.next()) {
                Taxes entity = new Taxes();
                entity.setID_Tax(rs.getInt("ID_Tax"));
                entity.setTaxName(rs.getString("TaxName"));
                entity.setTaxRate(rs.getDouble("TaxRate"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  list;
    }
    
}
