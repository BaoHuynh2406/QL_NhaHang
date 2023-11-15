
package Entity;
public class OrderDetail {
    private int ID_OrderDetail;
    private int ID_Order;
    private int ID_Item;
    private int Quantity;
    private double Price;
    private double TotalPrice;

    public OrderDetail(int ID_OrderDetail, int ID_Order, int ID_Item, int Quantity, double Price, double TotalPrice) {
        this.ID_OrderDetail = ID_OrderDetail;
        this.ID_Order = ID_Order;
        this.ID_Item = ID_Item;
        this.Quantity = Quantity;
        this.Price = Price;
        this.TotalPrice = TotalPrice;
    }

    public int getID_Order() {
        return ID_Order;
    }

    public void setID_Order(int ID_Order) {
        this.ID_Order = ID_Order;
    }

    public int getID_Item() {
        return ID_Item;
    }

    public void setID_Item(int ID_Item) {
        this.ID_Item = ID_Item;
    }

    

    public int getID_OrderDetail() {
        return ID_OrderDetail;
    }

    public void setID_OrderDetail(int ID_OrderDetail) {
        this.ID_OrderDetail = ID_OrderDetail;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }
    @Override
    public String toString() {
        return "OrderDetail{" +
                "idOrderDetail=" + ID_OrderDetail +
                ", idOrder=" + ID_Order + 
                ", idItem=" + ID_Item + 
                ", quantity=" + Quantity +
                ", price=" + Price +
                ", totalPrice=" + TotalPrice +
                '}';
    }
}
