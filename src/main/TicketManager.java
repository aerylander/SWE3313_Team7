package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages active tickets in the restaurant system.
 */
public class TicketManager {
    private List<Ticket> activeTickets;

    public TicketManager() {
        this.activeTickets = new ArrayList<>();
    }

    /**
     * Adds a new ticket to the active tickets list.
     */
    public void addTicket(Ticket ticket) {
        activeTickets.add(ticket);
    }

    /**
     * Removes a ticket from the active tickets list.
     */
    public void removeTicket(Ticket ticket) {
        activeTickets.remove(ticket);
    }

    /**
     * Gets all active tickets.
     */
    public List<Ticket> getActiveTickets() {
        return new ArrayList<>(activeTickets);
    }

    /**
     * Gets tickets for a specific table.
     */
    public List<Ticket> getTicketsForTable(int tableNumber) {
        List<Ticket> tableTickets = new ArrayList<>();
        for (Ticket ticket : activeTickets) {
            if (ticket.getTableNumber() == tableNumber) {
                tableTickets.add(ticket);
            }
        }
        return tableTickets;
    }

    /**
     * Checks if a table has any active tickets.
     */
    public boolean tableHasActiveTickets(int tableNumber) {
        return !getTicketsForTable(tableNumber).isEmpty();
    }

    /**
     * Gets the total number of active tickets.
     */
    public int getActiveTicketCount() {
        return activeTickets.size();
    }

    /**
     * Clears all active tickets (for testing/reset purposes).
     */
    public void clearAllTickets() {
        activeTickets.clear();
    }
}

