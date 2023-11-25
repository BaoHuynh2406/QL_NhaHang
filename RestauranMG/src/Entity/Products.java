
package Entity;

public class Products {
    private String ID_product;
    private String Name;
    private int Quantity;
    private String Unit;
    private double Price;

    public Products(String ID_product, String Name, int Quantity, String Unit, double Price) {
        this.ID_product = ID_product;
        this.Name = Name;
        this.Quantity = Quantity;
        this.Unit = Unit;
        this.Price = Price;
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

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }
    
    @Override
    public String toString(){
        return Name;
    }
}
