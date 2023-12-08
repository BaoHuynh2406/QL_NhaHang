package Utils;

import javax.swing.*;

public class msg {
    public static void Info(String mess) {
        JOptionPane pane = new JOptionPane(mess, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog("Thông báo");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public static void Error(String mess) {
        JOptionPane pane = new JOptionPane(mess, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = pane.createDialog("Lỗi");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public static void Warning(String mess) {
        JOptionPane pane = new JOptionPane(mess, JOptionPane.WARNING_MESSAGE);
        JDialog dialog = pane.createDialog("Cảnh báo");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public static void Question(String mess) {
        JOptionPane pane = new JOptionPane(mess, JOptionPane.QUESTION_MESSAGE);
        JDialog dialog = pane.createDialog("Bạn có chắc?");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public static int Yes_no(String mess, String title) {
        JOptionPane pane = new JOptionPane(mess, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
        JDialog dialog = pane.createDialog(title);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        Object selectedValue = pane.getValue();
        if (selectedValue instanceof Integer) {
            return ((Integer) selectedValue);
        }
        return JOptionPane.CLOSED_OPTION;
    }

    public static boolean Yes_no(String mess) {
        int choice = JOptionPane.showConfirmDialog(null, mess, "Bạn có chắc", JOptionPane.YES_NO_OPTION);
        return choice == JOptionPane.YES_OPTION;
    }

    public static int InputNum(String mess) {
        String numS = JOptionPane.showInputDialog(mess);
        try {
            return Integer.valueOf(numS);
        } catch (Exception e) {
            return 0;
        }
    }
}
