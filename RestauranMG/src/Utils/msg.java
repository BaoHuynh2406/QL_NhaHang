
package Utils;

import javax.swing.JOptionPane;


public class msg {
    public static void Info(String mess){
        JOptionPane.showMessageDialog(null, mess, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void Error(String mess){
        JOptionPane.showMessageDialog(null, mess, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void Warning(String mess){
        JOptionPane.showMessageDialog(null, mess, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void Question(String mess){
        JOptionPane.showMessageDialog(null, mess, "Bạn có chắc?", JOptionPane.QUESTION_MESSAGE);
    }
    
    public static int Yes_no(String mess, String title){
        return JOptionPane.showConfirmDialog(null, mess, title, JOptionPane.YES_NO_OPTION);
    }
    
    public static boolean Yes_no(String mess) {
    int choice = JOptionPane.showConfirmDialog(null, mess, "Bạn có chắc", JOptionPane.YES_NO_OPTION);
    return choice == JOptionPane.YES_OPTION;
    }
    
    public static int InputNum(String mess){
        String numS = JOptionPane.showInputDialog(mess);
        try {
            return Integer.valueOf(numS);
        } catch (Exception e) {
            return 0;
        }
    }

    
    
 
}
