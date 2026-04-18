package menu;

/**
 * Represents a menu item with name, price, and category.
 */
public class MenuItem {
    public enum Category {
        APPETIZER, ENTREE, DRINK
    }

    private String name;
    private double price;
    private Category category;
    private boolean isHeader;

    public MenuItem(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.isHeader = false;
    }
    
    // Constructor for header items
    public MenuItem(String name) {
        this.name = name;
        this.price = 0;
        this.category = null;
        this.isHeader = true;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
    
    public boolean isHeader() {
        return isHeader;
    }

    @Override
    public String toString() {
        if (isHeader) {
            return name;
        }
        return String.format("%s - $%.2f", name, price);
    }
}
