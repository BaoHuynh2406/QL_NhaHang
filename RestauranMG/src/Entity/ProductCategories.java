
package Entity;
public class ProductCategories {
    private int ID_Categories;
    private String CategoryName;

    public ProductCategories() {
    }

    public ProductCategories(int ID_Categories, String CategoryName) {
        this.ID_Categories = ID_Categories;
        this.CategoryName = CategoryName;
    }

    public int getID_Categories() {
        return ID_Categories;
    }

    public void setID_Categories(int ID_Categories) {
        this.ID_Categories = ID_Categories;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }
    
    @Override
    public String toString() {
        return CategoryName;
    }
}
