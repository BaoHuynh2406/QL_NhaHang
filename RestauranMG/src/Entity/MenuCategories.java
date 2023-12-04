
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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        MenuCategories other = (MenuCategories) obj;

        // So sánh cả ID_Category và CategoryName
        return this.ID_Category == other.ID_Category &&
                this.CategoryName.equals(other.CategoryName);
    }
}
