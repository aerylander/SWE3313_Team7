package Main;

/**
 * Represents an item on a ticket with quantity.
 */
public class TicketItem {
    private MenuItem menuItem;
    private int quantity;

    public TicketItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public void decrementQuantity() {
        if (this.quantity > 0) {
            this.quantity--;
        }
    }

    public double getSubtotal() {
        return menuItem.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s x%d - $%.2f", menuItem.getName(), quantity, getSubtotal());
    }
}

