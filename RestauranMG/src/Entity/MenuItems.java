
package Entity;
public class MenuItems {
    private int ID_Item;
    private String ItemName;
    private int ID_Category;
    private int Price;
    private String Photo;
    private boolean Available;

    public MenuItems(int ID_Item, String ItemName, int ID_Category, int Price, String Photo, boolean Available) {
        this.ID_Item = ID_Item;
        this.ItemName = ItemName;
        this.ID_Category = ID_Category;
        this.Price = Price;
        this.Photo = Photo;
        this.Available = Available;
    }

  

    
    public MenuItems() {
    }

    public boolean isAvailable() {
        return Available;
    }

    public void setAvailable(boolean Available) {
        this.Available = Available;
    }
    
    
    public int getID_Category() {
        return ID_Category;
    }

    public void setID_Category(int ID_Category) {
        this.ID_Category = ID_Category;
    }

    public int getID_Item() {
        return ID_Item;
    }

    public void setID_Item(int ID_Item) {
        this.ID_Item = ID_Item;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }
    @Override
    public String toString() {
        return "MenuItem{" +
                "idItem=" + ID_Item +
                ", itemName='" + ItemName + '\'' +
                ", idCategory=" + ID_Category +
                ", price=" + Price +
                ", photo='" + Photo + '\'' +
                '}';
    }
}
