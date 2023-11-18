
package Utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encryption {
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hashPassword("admin123"));
    }
}
