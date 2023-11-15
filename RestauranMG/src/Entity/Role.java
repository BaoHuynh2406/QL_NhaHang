
package Entity;


public class Role {
    private String ID_role;
    private String RoleNam;

    public Role(String ID_role, String RoleNam) {
        this.ID_role = ID_role;
        this.RoleNam = RoleNam;
    }

    public String getID_role() {
        return ID_role;
    }

    public void setID_role(String ID_role) {
        this.ID_role = ID_role;
    }

    public String getRoleNam() {
        return RoleNam;
    }

    public void setRoleNam(String RoleNam) {
        this.RoleNam = RoleNam;
    }
    
    @Override
    public String toString(){
        return "Role{" +
                "idRole='" + ID_role + '\'' +
                ", roleName='" + RoleNam + '\'' +
                '}';
    }
}
