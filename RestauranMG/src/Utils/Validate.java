
package Utils;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Validate {
    
        public static boolean isNotEmpty(JTextField textField, String fieldName) {
            String text = textField.getText().trim();
            if (text.isEmpty()) {
                msg.Warning("Không được bỏ trống!");
                textField.setBackground(Color.yellow);
                
                return false;
            }
            return true;
        }
        
        public static boolean isNotEmpty(JTextField textField, String fieldName, String lbl) {
            String text = textField.getText().trim();
            if (text.isEmpty()) {
                msg.Warning("Không được bỏ trống "+lbl+"!");
                textField.setBackground(Color.yellow);
                textField.requestFocus();
                return false;
            }
            return true;
        }

        public static boolean isSoDuong(JTextField textField, String fieldName) {
            String text = textField.getText().trim();
            double num = Double.parseDouble(text);
            if (num < 0) {
                msg.Warning("Bạn phải nhập số dương!");
                textField.setBackground(Color.yellow);
                textField.requestFocus();
                return false;
            }
            return true;
        }
        
        public static boolean isSoDuong(JTextField textField, String fieldName, String lbl) {
            String text = textField.getText().trim();
            double num = Double.parseDouble(text);
            if (num < 0) {
                msg.Warning("Bạn phải nhập số dương "+lbl+"!");
                textField.setBackground(Color.yellow);
                textField.requestFocus();
                return false;
            }
            return true;
        }
        
        //danh cho text field không tiêu đề
        public static boolean isLength(JTextField textFild, String str, int length){
            if(str.trim().length() < length){
                msg.Warning("Bạn phải nhập ít nhất "+length+" kí tự!");
                textFild.setBackground(Color.yellow);
                textFild.requestFocus();
                return false;
            }
            return true;
        }
        
        //danh cho text field có tiêu đề
        public static boolean isLength(JTextField textFild, String str, int length, String title){
            if(str.trim().length() < length){
                msg.Warning("Bạn phải nhập ít nhất "+length+" kí tự cho "+title+"!");
                textFild.setBackground(Color.yellow);
                textFild.requestFocus();
                return false;
            }
            return true;
        }
        
        //Dành cho password không có tiêu đề
        public static boolean isLength(JPasswordField textFild, String str, int length){
            if(str.trim().length() < length){
                msg.Warning("Bạn phải nhập ít nhất "+length+" kí tự!");
                textFild.setBackground(Color.yellow);
                textFild.requestFocus();
                return false;
            }
            return true;
        }
        //Dành cho password có tiêu đề
         public static boolean isLength(JPasswordField textFild, String str, int length, String title){
            if(str.trim().length() < length){
                msg.Warning("Bạn phải nhập ít nhất "+length+" kí tự cho "+title+"!");
                textFild.setBackground(Color.yellow);
                textFild.requestFocus();
                return false;
            }
            return true;
        }

        public static boolean isEmail(JTextField textField, String fieldName) {
            String text = textField.getText().trim();
            if (!text.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
                msg.Error("Bạn phải nhập đúng định dang email!");
                textField.setBackground(Color.yellow);
                textField.requestFocus();
                return false;
            }
            return true;
        }

        
        public static boolean isPhoneNumber(JTextField textField, String fieldName) {
        String text = textField.getText().trim();
        
        // Sử dụng biểu thức chính quy để kiểm tra số điện thoại
        if (!Pattern.matches("^\\d{10}$", text)) {
            msg.Warning("Số điện thoại không hợp lệ!");
            textField.setBackground(Color.yellow);
            textField.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean isDateDDMMYYYY(JTextField textField, String fieldName) {
        String text = textField.getText().trim();
        
        // Định dạng cho chuỗi ngày tháng "dd-MM-yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false); // Tắt tính linh hoạt của định dạng
        
        try {
            // Chuyển chuỗi thành kiểu Date
            Date date = dateFormat.parse(text);
            return true;
        } catch (ParseException e) {
            msg.Warning("Ngày tháng năm không hợp lệ! Sử dụng định dạng 'dd-MM-yyyy'");
            textField.setBackground(Color.yellow);
            textField.requestFocus();
            return false;
        }
    }
       
}
