package UI.Model;

public class Model_Card_for_Table {

    private String name;
    private double total;
    private int guestNum;
    private TableType type;

    public Model_Card_for_Table() {
    }

    public Model_Card_for_Table(String name, double total, int guestNum, TableType type) {
        this.name = name;
        this.total = total;
        this.guestNum = guestNum;
        this.type = type;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getGuestNum() {
        return guestNum;
    }

    public void setGuestNum(int guestNum) {
        this.guestNum = guestNum;
    }

    public TableType getType() {
        return type;
    }

    public void setType(TableType type) {
        this.type = type;
    }

   
    
    public static enum TableType {
        NULL, NOTNULL
    }
}
