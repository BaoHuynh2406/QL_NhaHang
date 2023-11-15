
package Entity;
public class MenuItemDetail {
    private int ID_MIT;
    private int ID_Item;
    private String ID_Product;
    private int Quantity;

    public MenuItemDetail(int ID_MIT, int ID_Item, String ID_Product, int Quantity) {
        this.ID_MIT = ID_MIT;
        this.ID_Item = ID_Item;
        this.ID_Product = ID_Product;
        this.Quantity = Quantity;
    }

    public int getID_Item() {
        return ID_Item;
    }

    public void setID_Item(int ID_Item) {
        this.ID_Item = ID_Item;
    }

    public String getID_Product() {
        return ID_Product;
    }

    public void setID_Product(String ID_Product) {
        this.ID_Product = ID_Product;
    }

    

    public int getID_MIT() {
        return ID_MIT;
    }

    public void setID_MIT(int ID_MIT) {
        this.ID_MIT = ID_MIT;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    
   @Override
    public String toString() {
        return "MenuItemDetail{" +
                "idMIT=" + ID_MIT +
                ", idItem=" + ID_Item + 
                ", idProduct='" + ID_Product + '\'' +
                ", quantity=" + Quantity +
                '}';
    }
}
