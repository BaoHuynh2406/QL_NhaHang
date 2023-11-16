
package Entity;

public class Employees {
    private int ID_Employee;
    private String FullName;
    private String Password;
    private String PhoneNumber;
    private String Email;
    private String Address;
    private String ID_role;
    private String Photo;

    public Employees(int ID_Employee, String FullName, String Password, String PhoneNumber, String Email, String Address, String ID_role, String Photo) {
        this.ID_Employee = ID_Employee;
        this.FullName = FullName;
        this.Password = Password;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.Address = Address;
        this.ID_role = ID_role;
        this.Photo = Photo;
    }

    public Employees() {
    }
    
    public int getID_Employee() {
        return ID_Employee;
    }

    public void setID_Employee(int ID_Employee) {
        this.ID_Employee = ID_Employee;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getID_role() {
        return ID_role;
    }

    public void setID_role(String ID_role) {
        this.ID_role = ID_role;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + ID_Employee +
                ", fullName='" + FullName + '\'' +
                ", password='" + Password + '\'' +
                ", phoneNumber='" + PhoneNumber + '\'' +
                ", email='" + Email + '\'' +
                ", address='" + Address + '\'' +
                ", idRole='" + ID_role + '\'' +
                ", photo='" + Photo + '\'' +
                '}';
    }
}
