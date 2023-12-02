
package UI.Model;

public class Model_Mon_Da_Goi {
    private int id;
    private String name;
    private int price;
    private int sl;
    private int total;
    private ItemType type;
    public int ID_Order;
    
    public static enum ItemType{
        ChuaGoi, DaGoi, TieuDe
    }

    public Model_Mon_Da_Goi(int id, String name, int price, int sl, ItemType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sl = sl;
        this.total = price*sl;
        this.type = type;
    }

    public Model_Mon_Da_Goi(ItemType type) {
        this.type = type;
    }

    public Model_Mon_Da_Goi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getTotal() {
        total = price*sl;
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
    
    
    
}
