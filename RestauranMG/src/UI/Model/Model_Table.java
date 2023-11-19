package UI.Model;

public class Model_Table {

    private String name;
    private int total;
    private int guestNum;
    private TableType type;

    public Model_Table() {
    }

    public Model_Table(String name, int total, int guestNum, TableType type) {
        this.name = name;
        this.total = total;
        this.guestNum = guestNum;
        this.type = type;
    }

    public Model_Table(String name) {
        this.name = name;
        this.type = Model_Table.TableType.NULL;
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

    public void setTotal(int total) {
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
