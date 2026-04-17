package Main;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the restaurant's tables and their statuses.
 */
public class TableManager {
    private List<Table> tables;
    
    public TableManager(int numberOfTables) {
        this.tables = new ArrayList<>();
        for (int i = 1; i <= numberOfTables; i++) {
            tables.add(new Table(i));
        }
    }
    
    /**
     * Gets all tables.
     */
    public List<Table> getAllTables() {
        return new ArrayList<>(tables);
    }
    
    /**
     * Gets a specific table by number.
     */
    public Table getTable(int tableNumber) {
        for (Table table : tables) {
            if (table.getTableNumber() == tableNumber) {
                return table;
            }
        }
        return null;
    }
    
    /**
     * Sets the active ticket status for a table.
     */
    public void setTableActiveTicket(int tableNumber, boolean hasTicket) {
        Table table = getTable(tableNumber);
        if (table != null) {
            table.setHasActiveTicket(hasTicket);
        }
    }
    
    /**
     * Checks if a table has an active ticket.
     */
    public boolean tableHasActiveTicket(int tableNumber) {
        Table table = getTable(tableNumber);
        return table != null && table.hasActiveTicket();
    }
    
    /**
     * Gets the total number of tables.
     */
    public int getTotalTableCount() {
        return tables.size();
    }
}

