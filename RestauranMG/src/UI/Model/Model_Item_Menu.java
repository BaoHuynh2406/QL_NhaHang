package UI.Model;

public class Model_Item_Menu {

    private String name;
    private int ID;
    private int coast;
    private String path;
    private MenuType type;

    public Model_Item_Menu() {
    }

    public int getCoast() {
        return coast;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public Model_Item_Menu(String name, int ID, int coast, String path, MenuType type) {
        this.name = name;
        this.ID = ID;
        this.coast = coast;
        this.path = path;
        this.type = type;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
   
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public static enum MenuType {
        UNAVAiLABLE, AVAiLABLE
    }
}
