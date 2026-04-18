package main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The Active Tickets screen shows all active tickets across all tables.
 * Displays ticket details including table number, items, and totals.
 */
public class ServerActiveTicketsPanel extends JPanel {
    private App mainApp;
    private DefaultListModel<String> ticketListModel;
    private JList<String> ticketList;
    
    public ServerActiveTicketsPanel(App mainApp) {
        this.mainApp = mainApp;
        
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 220));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        
        // Title
        JLabel title = new JLabel("Active Tickets");
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        gbc.gridy = 0;
        add(title, gbc);
        
        // Ticket list
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.8;
        
        ticketListModel = new DefaultListModel<>();
        ticketList = new JList<>(ticketListModel);
        ticketList.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(ticketList);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        add(scrollPane, gbc);
        
        // Refresh button
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshTickets());
        add(refreshBtn, gbc);
        
        // Navigation buttons
        gbc.gridy = 3;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        
        JButton backBtn = new JButton("Back to Tables");
        backBtn.addActionListener(e -> backToTableList());
        buttonPanel.add(backBtn);
        
        JButton logOutBtn = new JButton("Log Out");
        logOutBtn.addActionListener(e -> logout());
        buttonPanel.add(logOutBtn);
        
        add(buttonPanel, gbc);
        
        // Initial refresh
        refreshTickets();
    }
    
    /**
     * Refreshes the display of active tickets.
     */
    private void refreshTickets() {
        ticketListModel.clear();
        List<Ticket> activeTickets = mainApp.getTicketManager().getActiveTickets();
        
        if (activeTickets.isEmpty()) {
            ticketListModel.addElement("No active tickets");
        } else {
            for (Ticket ticket : activeTickets) {
                ticketListModel.addElement(ticket.toString());
                
                // Add ticket details
                for (TicketItem item : ticket.getItems()) {
                    ticketListModel.addElement("  " + item.toString());
                }
                
                if (!ticket.getNotes().isEmpty()) {
                    ticketListModel.addElement("  Notes: " + ticket.getNotes());
                }
                
                ticketListModel.addElement(""); // Empty line between tickets
            }
        }
    }
    
    /**
     * Returns to the table list screen.
     */
    private void backToTableList() {
        mainApp.showScreen("ServerTableList");
    }
    
    /**
     * Logs out the user and returns to the Employee Login screen.
     */
    private void logout() {
        // Release the Server role
        mainApp.getRoleManager().removeRole(RoleManager.ROLE_SERVER);
        // Return to Employee Login screen
        mainApp.showScreen("Employee");
    }
}
