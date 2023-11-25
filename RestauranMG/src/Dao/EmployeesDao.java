package Dao;

import Entity.Employees;
import Utils.jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDao extends RestauranDao<Employees, Integer> {

    final String INSERT_SQL = "INSERT INTO Employees (FullName, Password, Sex, birthday, PhoneNumber, Email, Address, ID_role, Photo)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
    final String UPDATE_ALL = "UPDATE Employees SET "
            + "FullName = ?, Password = ?, Sex = ?, birthday = ?, PhoneNumber = ?, Email = ?, Address = ?, ID_role = ?, Photo = ? WHERE ID_Employee = ?";
    final String UPDATE_Not_Pass = "UPDATE Employees SET "
            + "FullName = ?, Sex = ?, birthday = ?, PhoneNumber = ?, Email = ?, Address = ?, ID_role = ?, Photo = ? WHERE ID_Employee = ?";
    final String DELETE_SQL = "DELETE FROM Employees WHERE ID_Employee = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Employees";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Employees WHERE ID_Employee = ?";

    public int getNewID() {
        try {
            String sql = "Select MAX(ID_Employee) ID From Employees";
            ResultSet r = jdbc.query(sql);
            int id = 0;
            while (r.next()) {
                id = r.getInt("ID");
            }
            return id + 1;
        } catch (Exception e) {
            return -100000;
        }

    }
    
    @Override
    public void insert(Employees entity) {
        jdbc.update(INSERT_SQL,
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
                entity.getFullName(),
                entity.getPassword(),
                entity.isSex(),
                entity.getBirthday(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getID_role(),
                entity.getPhoto(),
                entity.getID_Employee());
    }

    public void updateNotPass(Employees entity) {
        jdbc.update(UPDATE_Not_Pass,
                entity.getFullName(),
                entity.isSex(),
                entity.getBirthday(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getID_role(),
                entity.getPhoto(),
                entity.getID_Employee());
    }

    @Override
    public void delete(Integer id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<Employees> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Employees selectById(Integer id) {
        List<Employees> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Employees> selectBySql(String sql, Object... args) {
        List<Employees> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while (rs.next()) {
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
        return list;
    }
}
