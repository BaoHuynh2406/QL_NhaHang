/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Form;

import Dao.EmployeesDao;
import Entity.Employees;
import Utils.Encryption;
import Utils.msg;

/**
 *
 * @author mtsst
 */
public class test {
    public static void main(String[] args) {
        try {
                int ID = 100000;
                
                EmployeesDao dao = new EmployeesDao();
                Employees nv = dao.selectById(ID);
                nv.setPassword(Encryption.hashPassword("admin123"));
                System.out.println(nv.toString());
                dao.update(nv);
                msg.Info("Đã thay đổi mật khẩu!");
               
            } catch (Exception e) {
                msg.Error("Lỗi! "+e.getMessage());
            }
    }
    
}
