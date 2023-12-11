package Utils;

import Entity.Employees;

public class Auth {

    // đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
    public static Employees user = null;
    
    // xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
    public static void clear(){
        Auth.user = null;
    }
    // kiểm tra xem đăng nhập hay chưa
    public static boolean isLogin() {
        return Auth.user != null;
    }
    public static String getRole(){
        return user.getID_role();
    }
    
    public static boolean isMG(){
        if(Auth.user.getID_role().equals("MG")){
            return true;
        }
        
        return false;
    }
    
    public static boolean isSV(){
        if(Auth.user.getID_role().equals("SV")){
            return true;
        }
        
        return false;
    }
}
