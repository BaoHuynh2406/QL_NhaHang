
package Utils;
import java.sql.*;



public class jdbc {
        //Giao thức kết nối
        private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        private static String dbUrl = "jdbc:sqlserver://localhost;database=EduSysData;encrypt=false";
        private static String user = "sa";
        private static String pass = "123";
        private static Connection cnn = null;
        
        static {
            try{
                Class.forName(driver);
                
            }catch(ClassNotFoundException e){
                System.out.println("Driver not found");
            }
        }
        
        //Hàm connect
        public static Connection getConnection() {
        if (cnn == null) {
            try {
                cnn = (Connection) DriverManager.getConnection(dbUrl, user, pass);
                System.out.println("Connection success to DataBase");
            } catch (SQLException e) {
                System.out.println("Connection error: " + e.getMessage());
            }
        }
        return cnn;
    }
        
       //Hàm thực hiện truy vẫn
        public static PreparedStatement getStmt (String sql, Object... args) throws SQLException {
            //Kết nối
                Connection connection = DriverManager.getConnection (dbUrl, user, pass);
                PreparedStatement pstmt = null;
           //Phân biệt câu truy vấn Proc và Bình thường
                if(sql.trim() .startsWith("{")){
                    pstmt = connection. prepareCall (sql) ;
                }else {
                    pstmt = connection.prepareStatement (sql);
                }
          //Gán các giá trị tham số vào câu truy vấn
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject (i + 1, args[i]);
                }
                
                //Trả về câu truy vấn chưa run
                return pstmt;
        }
        
        //Thực hiên update
        public static int update(String sql, Object... args) {
            try {
                PreparedStatement stmt = getStmt(sql, args);
                try {
                    return stmt.executeUpdate();
                } finally {
                    stmt.getConnection().close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new RuntimeException();
            }
    }
        
        //Câu lệnh truy vấn trả về tập giá trị
        public static ResultSet query(String sql, Object... args) {
        try{
             PreparedStatement stmt = getStmt(sql, args);
                return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        
        //Trả về một đối tượng duy nhất
         public static Object value(String sql, Object... args) {
        try {
            //Dùng lại Hàm query
             ResultSet rs = query(sql, args);
             //Lấy giá trị đầu tiên, tránh trường hợp trả về nhiều giá trị
            if (rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
