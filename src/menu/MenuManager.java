package menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the restaurant menu items organized by category.
 */
public class MenuManager {
    private List<MenuItem> menuItems;

    public MenuManager() {
        menuItems = new ArrayList<>();

        // Appetizers
        menuItems.add(new MenuItem("Cheese Bread", 3.00, MenuItem.Category.APPETIZER));
        menuItems.add(new MenuItem("Mozzarella Sticks", 4.00, MenuItem.Category.APPETIZER));

        // Entrees
        menuItems.add(new MenuItem("Small Pizza", 7.00, MenuItem.Category.ENTREE));
        menuItems.add(new MenuItem("Medium Pizza", 11.00, MenuItem.Category.ENTREE));
        menuItems.add(new MenuItem("Large Pizza", 14.00, MenuItem.Category.ENTREE));

        // Drinks
        menuItems.add(new MenuItem("Bottle Water", 1.00, MenuItem.Category.DRINK));
        menuItems.add(new MenuItem("Fountain Drinks", 3.00, MenuItem.Category.DRINK));
    }

    public List<MenuItem> getAllItems() {
        return new ArrayList<>(menuItems);
    }

    public List<MenuItem> getItemsByCategory(MenuItem.Category category) {
        List<MenuItem> categoryItems = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.category() == category) {
                categoryItems.add(item);
            }
        }
        return categoryItems;
    }

    public MenuItem getItemByName(String name) {
        for (MenuItem item : menuItems) {
            if (item.name().equals(name)) {
                return item;
            }
        }
        return null;
    }
}

