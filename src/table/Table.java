package table;

/**
 * Represents a restaurant table with a number and current status.
 */
public class Table {
    private int tableNumber;
    private boolean hasActiveTicket;
    
    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.hasActiveTicket = false;
    }
    
    public int getTableNumber() {
        return tableNumber;
    }
    
    public boolean hasActiveTicket() {
        return hasActiveTicket;
    }
    
    public void setHasActiveTicket(boolean hasTicket) {
        this.hasActiveTicket = hasTicket;
    }
    
    @Override
    public String toString() {
        return "Table " + tableNumber + (hasActiveTicket ? " (Active)" : "");
    }
}

