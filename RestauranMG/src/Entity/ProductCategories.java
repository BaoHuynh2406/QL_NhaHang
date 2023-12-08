
package Entity;

import java.util.Objects;

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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategories that = (ProductCategories) o;
        return ID_Categories == that.ID_Categories &&
                Objects.equals(CategoryName, that.CategoryName);
    }
}
