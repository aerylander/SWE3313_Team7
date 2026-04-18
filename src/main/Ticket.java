package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a complete ticket with items, notes, and table information.
 */
public class Ticket {
    private int tableNumber;
    private List<TicketItem> items;
    private String notes;
    private long timestamp;

    public Ticket(int tableNumber) {
        this.tableNumber = tableNumber;
        this.items = new ArrayList<>();
        this.notes = "";
        this.timestamp = System.currentTimeMillis();
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public List<TicketItem> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(MenuItem menuItem) {
        // Check if item already exists, if so increment quantity
        for (TicketItem item : items) {
            if (item.getMenuItem().equals(menuItem)) {
                item.incrementQuantity();
                return;
            }
        }
        // Item doesn't exist, add new
        items.add(new TicketItem(menuItem, 1));
    }

    public void removeItem(TicketItem ticketItem) {
        items.remove(ticketItem);
    }

    public void updateItemQuantity(TicketItem ticketItem, int quantity) {
        if (quantity <= 0) {
            items.remove(ticketItem);
        } else {
            ticketItem.setQuantity(quantity);
        }
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getTotal() {
        return items.stream().mapToDouble(TicketItem::getSubtotal).sum();
    }

    public int getItemCount() {
        return items.stream().mapToInt(TicketItem::getQuantity).sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("Table %d - %d items - $%.2f", tableNumber, getItemCount(), getTotal());
    }
}

