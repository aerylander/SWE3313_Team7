package main;

import javax.swing.*;
import java.awt.*;

/**
 * The Ticket Creation screen allows servers to create a new ticket for a selected table.
 * Features menu items organized by category, quantity controls, notes, and ticket confirmation.
 */
public class ServerTicketCreationPanel extends JPanel {
    private App mainApp;
    private MenuManager menuManager;
    private TicketManager ticketManager;
    private int selectedTableNumber;
    private Ticket currentTicket;
    
    // UI Components
    private DefaultListModel<MenuItem> menuListModel;
    private JList<MenuItem> menuList;
    private DefaultListModel<TicketItem> ticketListModel;
    private JList<TicketItem> ticketList;
    private JTextArea notesArea;
    private JButton confirmBtn;
    private JButton cancelBtn;
    private JLabel totalLabel;

    public ServerTicketCreationPanel(App mainApp, MenuManager menuManager, TicketManager ticketManager) {
        this.mainApp = mainApp;
        this.menuManager = menuManager;
        this.ticketManager = ticketManager;
        this.selectedTableNumber = -1;
        this.currentTicket = null;
        
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 220));
        
        initializeComponents();
        setupLayout();
    }
    
    private void initializeComponents() {
        // Menu list
        menuListModel = new DefaultListModel<>();
        menuList = new JList<>(menuListModel);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        
        // Ticket list
        ticketListModel = new DefaultListModel<>();
        ticketList = new JList<>(ticketListModel);
        ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ticketList.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        
        // Notes area
        notesArea = new JTextArea(3, 20);
        notesArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        
        // Buttons
        confirmBtn = new JButton("Confirm Ticket");
        confirmBtn.setEnabled(false);
        confirmBtn.addActionListener(e -> confirmTicket());
        
        cancelBtn = new JButton("Cancel Ticket");
        cancelBtn.addActionListener(e -> cancelTicket());
        
        // Total label
        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        // Populate menu
        populateMenu();
    }
    
    private void setupLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Title
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel title = new JLabel("Create Ticket - Table " + selectedTableNumber);
        title.setFont(new Font("Times New Roman", Font.BOLD, 18));
        add(title, gbc);
        
        // Menu section
        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Menu Items"), gbc);
        
        gbc.gridy = 2; gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.4; gbc.weighty = 0.6;
        JScrollPane menuScroll = new JScrollPane(menuList);
        menuScroll.setPreferredSize(new Dimension(200, 200));
        add(menuScroll, gbc);
        
        // Add to ticket button
        gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0; gbc.weighty = 0;
        JButton addBtn = new JButton("Add [+]");
        addBtn.addActionListener(e -> addItemToTicket());
        add(addBtn, gbc);
        
        // Ticket section
        gbc.gridy = 1; gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Current Ticket"), gbc);
        
        gbc.gridy = 2; gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.4; gbc.weighty = 0.6;
        JScrollPane ticketScroll = new JScrollPane(ticketList);
        ticketScroll.setPreferredSize(new Dimension(200, 200));
        add(ticketScroll, gbc);
        
        // Quantity control buttons
        gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0; gbc.weighty = 0;
        JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        JButton plusBtn = new JButton("+");
        plusBtn.addActionListener(e -> increaseQuantity());
        JButton minusBtn = new JButton("-");
        minusBtn.addActionListener(e -> decreaseQuantity());
        qtyPanel.add(minusBtn);
        qtyPanel.add(plusBtn);
        add(qtyPanel, gbc);
        
        // Notes section
        gbc.gridy = 4; gbc.gridx = 0; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1; gbc.weighty = 0;
        add(new JLabel("Notes:"), gbc);
        
        gbc.gridy = 5; gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.2;
        JScrollPane notesScroll = new JScrollPane(notesArea);
        notesScroll.setPreferredSize(new Dimension(400, 60));
        add(notesScroll, gbc);
        
        // Total and buttons
        gbc.gridy = 6; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        add(totalLabel, gbc);
        
        gbc.gridy = 7;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(confirmBtn);
        buttonPanel.add(cancelBtn);
        add(buttonPanel, gbc);
    }
    
    private void populateMenu() {
        menuListModel.clear();
        
        // Add appetizers
        menuListModel.addElement(new MenuItem("=== APPETIZERS ==="));
        for (MenuItem item : menuManager.getItemsByCategory(MenuItem.Category.APPETIZER)) {
            menuListModel.addElement(item);
        }
        
        // Add entrees
        menuListModel.addElement(new MenuItem("=== ENTREES ==="));
        for (MenuItem item : menuManager.getItemsByCategory(MenuItem.Category.ENTREE)) {
            menuListModel.addElement(item);
        }
        
        // Add drinks
        menuListModel.addElement(new MenuItem("=== DRINKS ==="));
        for (MenuItem item : menuManager.getItemsByCategory(MenuItem.Category.DRINK)) {
            menuListModel.addElement(item);
        }
    }
    
    /**
     * Updates the panel with the selected table number.
     */
    public void setSelectedTable(int tableNumber) {
        this.selectedTableNumber = tableNumber;
        this.currentTicket = new Ticket(tableNumber);
        updateDisplay();
    }
    
    private void addItemToTicket() {
        MenuItem selectedItem = menuList.getSelectedValue();
        if (selectedItem != null && selectedItem.getCategory() != null) {
            currentTicket.addItem(selectedItem);
            updateDisplay();
        }
    }
    
    private void increaseQuantity() {
        TicketItem selectedItem = ticketList.getSelectedValue();
        if (selectedItem != null) {
            selectedItem.incrementQuantity();
            updateDisplay();
        }
    }
    
    private void decreaseQuantity() {
        TicketItem selectedItem = ticketList.getSelectedValue();
        if (selectedItem != null) {
            selectedItem.decrementQuantity();
            if (selectedItem.getQuantity() == 0) {
                currentTicket.removeItem(selectedItem);
            }
            updateDisplay();
        }
    }
    
    private void updateDisplay() {
        // Update ticket list
        ticketListModel.clear();
        for (TicketItem item : currentTicket.getItems()) {
            ticketListModel.addElement(item);
        }
        
        // Update total
        totalLabel.setText(String.format("Total: $%.2f", currentTicket.getTotal()));
        
        // Update confirm button
        confirmBtn.setEnabled(!currentTicket.isEmpty());
        
        // Update notes
        notesArea.setText(currentTicket.getNotes());
        
        // Update title
        if (getComponentCount() > 0 && getComponent(0) instanceof JLabel) {
            ((JLabel) getComponent(0)).setText("Create Ticket - Table " + selectedTableNumber);
        }
    }
    
    private void confirmTicket() {
        if (currentTicket != null && !currentTicket.isEmpty()) {
            // Save notes
            currentTicket.setNotes(notesArea.getText());
            
            // Add to active tickets
            ticketManager.addTicket(currentTicket);
            
            // ! The method called here does nothing right now. Refresh table list to show active status.
            mainApp.refreshServerTableList();
            
            JOptionPane.showMessageDialog(this, 
                "Ticket confirmed for Table " + selectedTableNumber + "!\n" +
                "Total: $" + String.format("%.2f", currentTicket.getTotal()), 
                "Ticket Confirmed", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Return to table list
            mainApp.showScreen("ServerTableList");
        }
    }
    
    private void cancelTicket() {
        int result = JOptionPane.showConfirmDialog(this, 
            "Cancel this ticket? All items will be lost.", 
            "Cancel Ticket", 
            JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            // Return to table list
            mainApp.showScreen("ServerTableList");
        }
    }
}
