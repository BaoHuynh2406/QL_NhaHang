
package Entity;
public class PurchaseOrdersDetail {
    private int ID_POD;
    private int ID_PurchaseOrder;
    private String ID_Product;
    private double Quantity;
    private double Price;

    public PurchaseOrdersDetail(int ID_POD, int ID_PurchaseOrder, String ID_Product, double Quantity, double Price) {
        this.ID_POD = ID_POD;
        this.ID_PurchaseOrder = ID_PurchaseOrder;
        this.ID_Product = ID_Product;
        this.Quantity = Quantity;
        this.Price = Price;
    }

    public PurchaseOrdersDetail() {
    }
    
    public void setID_PurchaseOrder(int ID_PurchaseOrder) {
        this.ID_PurchaseOrder = ID_PurchaseOrder;
    }

    public int getID_PurchaseOrder() {
        return ID_PurchaseOrder;
    }
    
    public String getID_Product() {
        return ID_Product;
    }

    public void setID_Product(String ID_Product) {
        this.ID_Product = ID_Product;
    }

    public int getID_POD() {
        return ID_POD;
    }

    public void setID_POD(int ID_POD) {
        this.ID_POD = ID_POD;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double Quantity) {
        this.Quantity = Quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }
    
    @Override
    public String toString() {
        return "PurchaseOrderDetail{" +
                "idPOD=" + ID_POD +
                ", idPurchaseOrder=" + ID_PurchaseOrder +
                ", idProduct='" + ID_Product + '\'' +
                ", quantity=" + Quantity +
                ", price=" + Price +
                '}';
    }
}
