package menu;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the restaurant menu items organized by category.
 */
public class MenuManager {
    private final List<MenuItem> menuItems;

    public MenuManager() {
        menuItems = new ArrayList<>();

        // Appetizers
        menuItems.add(new MenuItem("Cheese Bread", 3.00, MenuItem.Category.APPETIZER));
        menuItems.add(new MenuItem("Mozzarella Sticks", 4.00, MenuItem.Category.APPETIZER));
        menuItems.add(new MenuItem("Pretzel Bites", 2.00, MenuItem.Category.APPETIZER));
        menuItems.add(new MenuItem("Focaccia", 2.00, MenuItem.Category.APPETIZER));

        menuItems.add(new MenuItem("Caesar Salad", 4.00, MenuItem.Category.SALAD));
        menuItems.add(new MenuItem("Caprese Salad", 4.00, MenuItem.Category.SALAD));
        menuItems.add(new MenuItem("House Salad", 4.00, MenuItem.Category.SALAD));
        menuItems.add(new MenuItem("Greek Salad", 4.00, MenuItem.Category.SALAD));

        // Entrees
        menuItems.add(new MenuItem("Small Pizza", 7.00, MenuItem.Category.ENTREE));
        menuItems.add(new MenuItem("Medium Pizza", 11.00, MenuItem.Category.ENTREE));
        menuItems.add(new MenuItem("Large Pizza", 14.00, MenuItem.Category.ENTREE));
        menuItems.add(new MenuItem("Extra Large Pizza", 16.00, MenuItem.Category.ENTREE));

        // Drinks
        menuItems.add(new MenuItem("Bottled Water", 1.00, MenuItem.Category.DRINK));
        menuItems.add(new MenuItem("Fountain Drink", 2.00, MenuItem.Category.DRINK));
        menuItems.add(new MenuItem("Cream Soda", 3.00, MenuItem.Category.DRINK));
        menuItems.add(new MenuItem("Lemonade", 2.00, MenuItem.Category.DRINK));

        menuItems.add(new MenuItem("Cannolis", 2.00, MenuItem.Category.DESSERT));
        menuItems.add(new MenuItem("Small Gelato", 3.00, MenuItem.Category.DESSERT));
        menuItems.add(new MenuItem("Regular Gelato", 4.00, MenuItem.Category.DESSERT));
        menuItems.add(new MenuItem("Tiramisu", 5.00, MenuItem.Category.DESSERT));
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
}

