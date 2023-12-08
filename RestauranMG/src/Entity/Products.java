
package Entity;

public class Products {
    private String ID_product;
    private String Name;
    private double Quantity;
    private String Unit;
    private int Price;
    public int ID_Catory;

    public Products(String ID_product, String Name, double Quantity, String Unit, int Price) {
        this.ID_product = ID_product;
        this.Name = Name;
        this.Quantity = Quantity;
        this.Unit = Unit;
        this.Price = Price;
    }

    public Products(String ID_product, String Name, double Quantity, String Unit, int Price, int ID_Catory) {
        this.ID_product = ID_product;
        this.Name = Name;
        this.Quantity = Quantity;
        this.Unit = Unit;
        this.Price = Price;
        this.ID_Catory = ID_Catory;
    }
    
    
    public Products() {
    }
    
    public String getID_product() {
        return ID_product;
    }

    public void setID_product(String ID_product) {
        this.ID_product = ID_product;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double Quantity) {
        this.Quantity = Quantity;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }
    
    @Override
    public String toString(){
        return Name;
    }
}
