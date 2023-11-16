
package Entity;

import java.util.Date;

public class PurchaseOrders {
    private int ID_PurchaseOrder;
    private Date OrderDate;
    private int ID_Employee;

    public PurchaseOrders(int ID_PurchaseOrder, Date OrderDate, int ID_Emplyee) {
        this.ID_PurchaseOrder = ID_PurchaseOrder;
        this.OrderDate = OrderDate;
        this.ID_Employee = ID_Emplyee;
    }

    public PurchaseOrders() {
    }
    
    public int getID_PurchaseOrder() {
        return ID_PurchaseOrder;
    }

    public void setID_PurchaseOrder(int ID_PurchaseOrder) {
        this.ID_PurchaseOrder = ID_PurchaseOrder;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    public int getID_Employee() {
        return ID_Employee;
    }

    public void setID_Employee(int ID_Employee) {
        this.ID_Employee = ID_Employee;
    }
    
    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "idPurchaseOrder=" + ID_PurchaseOrder +
                ", orderDate=" + OrderDate +
                ", idEmplyee=" + ID_Employee +
                '}';
    }
}
