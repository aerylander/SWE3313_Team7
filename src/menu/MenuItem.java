package menu;
// Represents a menu item with name, price, and category.
public record MenuItem(String name, double price, Category category) {
    public enum Category {
        APPETIZER, SALAD, ENTREE, DRINK, DESSERT
    }

    // Constructor for header items
    public MenuItem(String name) {
        this(name, 0, null);
    }

    @Override
    public String toString() {
        String data;
        if (this.price == 0 && this.category == null) {
            data = this.name;
        } else {
            data = String.format("%s - $%.2f", this.name, this.price);
        }
        return data;
    }
}
