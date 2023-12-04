
package Entity;
public class MenuCategories {
    private int ID_Category;
    private String CategoryName;

    public MenuCategories(int ID_Category, String CategoryName) {
        this.ID_Category = ID_Category;
        this.CategoryName = CategoryName;
    }

    public MenuCategories() {
    }
    
    public int getID_Category() {
        return ID_Category;
    }

    public void setID_Category(int ID_Category) {
        this.ID_Category = ID_Category;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }
    
    @Override
    public String toString() {
        return this.CategoryName;
    }
}
