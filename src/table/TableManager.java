package table;

import ticket.TicketManager;
import java.util.ArrayList;
import java.util.List;

import static table.Table.status;

/**
 * Manages the restaurant's tables and their statuses.
 */
public class TableManager {
    private List<Table> tables;
    private TicketManager ticketManager;
    
    public TableManager(int numberOfTables, TicketManager ticketManager) {
        this.ticketManager = ticketManager;
        this.tables = new ArrayList<>();
        for (int i = 1; i <= numberOfTables; i++) {
            tables.add(new Table(i));
        }
    }
    
    /**
     * Gets all tables.
     */
    public List<Table> getAllTables() {
        // Update table statuses based on active tickets
        /*for (Table table : tables) {
            table.setTableStatus(ticketManager.tableHasActiveTickets(table.getTableNumber()));
        }*/
        return new ArrayList<>(tables);
    }
    
    /**
     * Gets a specific table by number.
     */
    public Table getTable(int tableNumber) {
        for (Table table : tables) {
            if (table.getTableNumber() == tableNumber) {
                // Update status before returning
                // table.setTableStatus(ticketManager.tableHasActiveTickets(tableNumber));
                return table;
            }
        }
        return null;
    }
    
    /**
     * Sets the active ticket status for a table.
     */
    public void setTableStatus(int tableNumber, status s) {
        Table table = getTable(tableNumber);
        if (table != null) {
            table.setTableStatus(s);
        }
    }
    
    /**
     * Checks if a table has an active ticket.
     */
    public boolean tableHasActiveTicket(int tableNumber) {
        return ticketManager.tableHasActiveTickets(tableNumber);
    }
    
    /**
     * Gets the total number of tables.
     */
    public int getTotalTableCount() {
        return tables.size();
    }
}
