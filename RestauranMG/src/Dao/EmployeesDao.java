
package Dao;

import Entity.Employees;
import Utils.jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDao extends RestauranDao<Employees, String>{
    final String INSERT_SQL = "INSERT INTO Employees (FullName, Password, Sex, birthday, PhoneNumber, Email, Address, ID_role, Photo)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
    final String UPDATE_ALL = "UPDATE Employees SET "
            + "FullName = ?, Password = ?, Sex = ?, birthday = ?, PhoneNumber = ?, Email = ?, Address = ?, ID_role = ?, Photo = ? WHERE ID_Employee = ?";
    final String DELETE_SQL = "DELETE FROM Employees WHERE ID_Employee = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Employees";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Employees WHERE ID_Employee = ?";
    @Override
    public void insert(Employees entity) {
        jdbc.update(INSERT_SQL,
                entity.getID_Employee(),
                entity.getFullName(),
                entity.getPassword(),
                entity.isSex(),
                entity.getBirthday(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getID_role(),
                entity.getPhoto());
    }

    @Override
    public void update(Employees entity) {
        jdbc.update(UPDATE_ALL,
                entity.getID_Employee(),
                entity.getFullName(),
                entity.getPassword(),
                entity.isSex(),
                entity.getBirthday(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getID_role(),
                entity.getPhoto());
    }

    @Override
    public void delete(String id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<Employees> selectAll() {
        return selectBySql(SELECT_ALL_SQL); 
    }

    @Override
    public Employees selectById(String id) {
        List<Employees> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Employees> selectBySql(String sql, Object... args) {
        List<Employees> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while(rs.next()) {
                Employees entity = new Employees();
                entity.setID_Employee(rs.getInt("ID_Employee"));
                entity.setFullName(rs.getString("FullName"));
                entity.setPassword(rs.getString("Password"));
                entity.setSex(rs.getBoolean("Sex"));
                entity.setBirthday(rs.getDate("birthday"));
                entity.setPhoneNumber(rs.getString("PhoneNumber"));
                entity.setEmail(rs.getString("Email"));
                entity.setAddress(rs.getString("Address"));
                entity.setID_role(rs.getString("ID_role"));
                entity.setPhoto(rs.getString("Photo"));
                list.add(entity);
            }
        } catch (Exception e) {
            System.out.println("Loi sql " + e.getMessage());
            throw new RuntimeException(e);
        }
        return  list;
    }

    public Employees selectById(int idEmployee) {
        return selectById(String.valueOf(idEmployee));
    }
    
}
