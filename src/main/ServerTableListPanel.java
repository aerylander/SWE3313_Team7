package main;

import javax.swing.*;
import java.awt.*;

import static main.RoleManager.ROLE_SERVER;

/**
 * The Server Table List screen shows all available tables for a server.
 * Servers can select a table to create a new ticket or view active tickets.
 */
public class ServerTableListPanel extends JPanel {
    private App mainApp;
    private TableManager tableManager;
    private int selectedTableNumber;
    private JList<Table> tableList;
    private DefaultListModel<Table> listModel;
    
    public ServerTableListPanel(App mainApp, TableManager tableManager) {
        this.mainApp = mainApp;
        this.tableManager = tableManager;
        this.selectedTableNumber = -1;
        
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 220)); // Beige background
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        
        // Row 0: Title
        JLabel title = new JLabel("Server - Select a Table");
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(title, gbc);
        
        // Row 1: Instructions
        JLabel instructions = new JLabel("Select a table to start a new ticket or view active tickets. The number of servers is: " + mainApp.getNumberOfRole(ROLE_SERVER));
        instructions.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        gbc.gridy = 1;
        add(instructions, gbc);
        
        // Row 2: Table List
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.7;
        
        listModel = new DefaultListModel<>();
        for (Table table : tableManager.getAllTables()) {
            listModel.addElement(table);
        }
        tableList = new JList<>(listModel);
        tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableList.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tableList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Table selectedTable = tableList.getSelectedValue();
                if (selectedTable != null) {
                    selectedTableNumber = selectedTable.getTableNumber();
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tableList);
        add(scrollPane, gbc);
        
        // Row 3: Action Buttons
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        
        JButton startTicketBtn = new JButton("Start New Ticket");
        startTicketBtn.addActionListener(e -> startNewTicket());
        buttonPanel.add(startTicketBtn);
        
        JButton viewActiveBtn = new JButton("View Active Tickets");
        viewActiveBtn.addActionListener(e -> viewActiveTickets());
        buttonPanel.add(viewActiveBtn);
        
        add(buttonPanel, gbc);
        
        // Row 4: Log Out Button
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JButton logOutBtn = new JButton("Log Out");
        logOutBtn.addActionListener(e -> logout());
        add(logOutBtn, gbc);
    }
    
    /**
     * Handles starting a new ticket for the selected table.
     */
    private void startNewTicket() {
        if (selectedTableNumber == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a table first.", 
                "No Table Selected", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Pass the selected table to the TicketCreation panel
        mainApp.selectTable(selectedTableNumber);
        mainApp.showScreen("ServerTicketCreation");
    }
    
    /**
     * Navigates to the Active Tickets screen.
     */
    private void viewActiveTickets() {
        mainApp.showScreen("ServerActiveTickets");
    }
    
    /**
     * Logs out the user and returns to the Employee Login screen.
     */
    private void logout() {
        // Release the Server role
        mainApp.getRoleManager().removeRole(ROLE_SERVER);
        // Return to Employee Login screen
        mainApp.showScreen("Employee");
    }
    
    /**
     * Refreshes the table list display.
     */
    public void refreshTableList() {
        listModel.clear();
        for (Table table : tableManager.getAllTables()) {
            listModel.addElement(table);
        }
        selectedTableNumber = -1;
        tableList.clearSelection();
    }
}

