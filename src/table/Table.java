package table;

/**
 * Represents a restaurant table with a number and current status.
 */
public class Table {
    public enum status {CLEAN, DIRTY, OCCUPIED}
    final private int tableNumber;
    private status tableStatus;
    
    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.tableStatus = status.CLEAN;
    }
    
    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableStatus(status s) {
        this.tableStatus = s;
    }

    public status getTableStatus() {
        return this.tableStatus;
    }
    
    @Override
    public String toString() {
        return "Table " + tableNumber + (" (Active)");
    }
}

