package Utils;

import java.text.DecimalFormat;

public class fNum {

    public static String parseString(int n) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
        String formattedNumber = decimalFormat.format(n);
        return formattedNumber;

    }

    public static String parseString(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
        String formattedNumber = decimalFormat.format(number);
        return formattedNumber;
    }

    public static int parseInt(String formattedNumber) {
        return Integer.parseInt(formattedNumber.replace(",", ""));
    }
}
