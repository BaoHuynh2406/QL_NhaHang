
package Entity;
public class Tables {
    private int ID_Table;
    private String TableName;
    private int ID_Area;
    private boolean IsOccupied;

    public Tables(int ID_Table, String TableName, int ID_Area, boolean IsOccupied) {
        this.ID_Table = ID_Table;
        this.TableName = TableName;
        this.ID_Area = ID_Area;
        this.IsOccupied = IsOccupied;
    }

    public int getID_Area() {
        return ID_Area;
    }

    public void setID_Area(int ID_Area) {
        this.ID_Area = ID_Area;
    }

    

    public int getID_Table() {
        return ID_Table;
    }

    public void setID_Table(int ID_Table) {
        this.ID_Table = ID_Table;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

    public boolean isIsOccupied() {
        return IsOccupied;
    }

    public void setIsOccupied(boolean IsOccupied) {
        this.IsOccupied = IsOccupied;
    }
    
    @Override
    public String toString() {
        return "Table{" +
                "idTable=" + ID_Table +
                ", tableName='" + TableName + '\'' +
                ", idArea=" + ID_Area +
                ", isOccupied=" + IsOccupied +
                '}';
    }
}
