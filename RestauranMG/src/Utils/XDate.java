package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XDate {
    static SimpleDateFormat formater = new SimpleDateFormat();
    
    public static String convertToDMY(Date date){
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return outputDateFormat.format(date);
    }
    public static Date toDate(String date, String pattern){
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String toString(Date date, String pattern) {
        formater.applyPattern(pattern);
        return formater.format(date);
    }

    public static Date now(){
        return new Date();
    }
    public static Date convertToYMD(String inputDate) {
        try {
            // Định dạng cho chuỗi ngày tháng năm đầu vào (dd-MM-yyyy)
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            
            // Định dạng cho chuỗi ngày tháng năm mới (yyyy-MM-dd)
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            // Chuyển đổi chuỗi đầu vào thành kiểu Date
            Date date = inputDateFormat.parse(inputDate);
            
            // Chuyển đổi kiểu Date thành chuỗi mới (yyyy-MM-dd)
            String formattedDate = outputDateFormat.format(date);
            
            // Chuyển chuỗi mới thành kiểu Date
            return outputDateFormat.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi
        }
    }

    public static Date addDays(Date date, long days){
        date.setTime(date.getTime() + days * 24 *60 * 60 * 1000);
        return date;
    }
}
