
package Utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.String.format;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 * Thư viện `IMG` chứa các phương thức hỗ trợ xử lý ảnh và biểu tượng (icon) cho ứng dụng Java Swing.
 */

public class IMG {
    
    /**
     * Đường dẫn mặc định cho biểu tượng ứng dụng.
     */
    private static final String DEFAULT_ICON_PATH = "/Icon/logo.png";

   /**
     * Thiết lập biểu tượng cho ứng dụng.
     *
     * @return Biểu tượng ứng dụng.
     */
    
    public static Image setAppIcon() {
        URL url = IMG.class.getResource(DEFAULT_ICON_PATH);
        return new ImageIcon(url).getImage();
    }
    
    /**
     * Thiết lập biểu tượng cho ứng dụng với đường dẫn cụ thể.
     *
     * @param name Đường dẫn của biểu tượng.
     * @return Biểu tượng ứng dụng.
     */
   public static Image setAppIcon(String name) {
        URL url = IMG.class.getResource(name);
        return new ImageIcon(url).getImage();
    }
    
   /**
     * Thiết lập biểu tượng với đường dẫn cụ thể.
     *
     * @param name Tên biểu tượng.
     * @return Biểu tượng được thiết lập.
     */
   public static ImageIcon setIcon(String name){
       return new ImageIcon("src/Icon/"+name);
   } 
   
   
   /**
     * Thiết lập ảnh đại diện cho một người dùng.
     *
     * @param name Tên tệp ảnh đại diện.
     * @return Biểu tượng ảnh đại diện đã được điều chỉnh kích thước.
     */
    public static ImageIcon setAvatar(String name) {
            String path = "src/IMG/" + name;
            return resize(path, "src/IMG/Avatar.png", 256, 250);
    }

   
   
   /**
     * Thiết lập ảnh đại diện cho chuyên đề.
     *
     * @param name Tên tệp logo
     * @return Biểu tượng ảnh đại diện đã được điều chỉnh kích thước.
     */
   public static ImageIcon setLogo(String name){
       String path = "src/IMG/"+name;
       return resize(path,"src/IMG/null.png", 256, 256);
   }
   
   
   
      /**
     * Thay đổi kích thước của một ảnh ImageIcon.
     *
     * @param path   Đường dẫn tới tệp ảnh.
     * @param width  Chiều rộng mới.
     * @param height Chiều cao mới.
     * @return Biểu tượng ảnh đã được thay đổi kích thước.
     */
   
    public static ImageIcon resize(String path, int width, int height){
        try {
            // Đọc ảnh từ đường dẫn
            File imageFile = new File(path);
            if (!imageFile.exists()) {
                System.out.println("Image not found: " + path);
                imageFile = new File("src/IMG/null.png");
            }

            BufferedImage originalImg = ImageIO.read(imageFile);

            // Thay đổi kích thước ảnh
            Image scaledImage = originalImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Tạo ImageIcon từ ảnh đã thay đổi kích thước
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            return scaledIcon;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null; // Trả về null nếu có lỗi
        }
    }
    
    /**
     * Thay đổi kích thước của một ảnh ImageIcon và cung cấp đường dẫn mặc định.
     *
     * @param path         Đường dẫn tới tệp ảnh.
     * @param default_path Đường dẫn mặc định nếu không tìm thấy ảnh.
     * @param width        Chiều rộng mới.
     * @param height       Chiều cao mới.
     * @return Biểu tượng ảnh đã được thay đổi kích thước.
     */
    public static ImageIcon resize(String path, String default_path, int width, int height){
        try {
            // Đọc ảnh từ đường dẫn
            File imageFile = new File(path);
            if (!imageFile.exists()) {
                System.out.println("Image not found: " + path);
                imageFile = new File(default_path);
            }

            BufferedImage originalImg = ImageIO.read(imageFile);

            // Thay đổi kích thước ảnh
            Image scaledImage = originalImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Tạo ImageIcon từ ảnh đã thay đổi kích thước
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            return scaledIcon;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null; // Trả về null nếu có lỗi
        }
    }
   
    /**
     * Sao chép tệp từ một đường dẫn đến một vị trí đích.
     *
     * @param path Đường dẫn tới tệp nguồn.
     * @param to   Đường dẫn đích.
     * @return true nếu sao chép thành công, false nếu có lỗi.
     */
    public static boolean save(String path, String to) {
        try {
            File sourceFile = new File(path);
            File destinationDir = new File(to);

            // Kiểm tra xem thư mục đích đã tồn tại chưa. Nếu chưa, tạo nó.
            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }

            File destinationFile = new File(destinationDir, sourceFile.getName());

            // Sao chép tệp ảnh từ vị trí đầu vào vào thư mục đích
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("Save file " + sourceFile.getName() + " successfully.");
            return true; // Trả về true nếu sao chép thành công
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Save file Error.");
            return false; // Trả về false nếu có lỗi
        }
    }
    
    
    
}
