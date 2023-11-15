
package Entity;

import java.util.Date;

public class Orders {
    private int ID_Order;
    private int ID_Table;
    private int ID_Employee;
    private Date OrderDate;
    private int NumberOfGuests;
    private boolean IsPaid;

    public Orders(int ID_Order, int ID_Table, int ID_Employee, Date OrderDate, int NumberOfGuests, boolean IsPaid) {
        this.ID_Order = ID_Order;
        this.ID_Table = ID_Table;
        this.ID_Employee = ID_Employee;
        this.OrderDate = OrderDate;
        this.NumberOfGuests = NumberOfGuests;
        this.IsPaid = IsPaid;
    }

    public int getID_Table() {
        return ID_Table;
    }

    public void setID_Table(int ID_Table) {
        this.ID_Table = ID_Table;
    }

    public int getID_Employee() {
        return ID_Employee;
    }

    public void setID_Employee(int ID_Employee) {
        this.ID_Employee = ID_Employee;
    }

    

    public int getID_Order() {
        return ID_Order;
    }

    public void setID_Order(int ID_Order) {
        this.ID_Order = ID_Order;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    public int getNumberOfGuests() {
        return NumberOfGuests;
    }

    public void setNumberOfGuests(int NumberOfGuests) {
        this.NumberOfGuests = NumberOfGuests;
    }

    public boolean isIsPaid() {
        return IsPaid;
    }

    public void setIsPaid(boolean IsPaid) {
        this.IsPaid = IsPaid;
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + ID_Order +
                ", idTable=" + ID_Table +
                ", idEmployee=" + ID_Employee +
                ", orderDate=" + OrderDate +
                ", numberOfGuests=" + NumberOfGuests +
                ", isPaid=" + IsPaid +
                '}';
    }
}
