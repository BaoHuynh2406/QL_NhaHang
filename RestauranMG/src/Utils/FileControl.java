
package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;



public class FileControl {
        public static void writeFile(Object obj, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();
            fileOut.close();
            System.out.println("Write file to " + fileName);
             } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
        public static Object readFile(String fileName) {
        Object obj = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            obj = objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Read file " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error when read file "+ fileName);
        }
        return obj;
    }
    
    public static boolean deleteFile(String fileName) {
        File fileToDelete = new File(fileName);

        // Kiểm tra xem tệp tồn tại trước khi xóa
        if (fileToDelete.exists()) {
            // Thực hiện xóa tệp
            if (fileToDelete.delete()) {
                System.out.println("Delete file " + fileName);
                return true;
            } else {
                System.out.println("Error delete file " + fileName);
                return false;
            }
        } else {
            System.out.println("file " + fileName + " don't exits");
            return false;
        }
    }
}
