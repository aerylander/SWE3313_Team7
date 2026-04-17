package Main;

import javax.swing.*;
import java.awt.*;

/**
 * The Ticket Creation screen allows servers to create a new ticket for a selected table.
 * This is a placeholder implementation.
 */
public class ServerTicketCreationPanel extends JPanel {
    private App mainApp;
    private int selectedTableNumber;
    
    public ServerTicketCreationPanel(App mainApp) {
        this.mainApp = mainApp;
        this.selectedTableNumber = -1;
        
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 220));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        
        // Title
        JLabel title = new JLabel("Create New Ticket");
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        gbc.gridy = 0;
        add(title, gbc);
        
        // Table info
        JLabel tableInfo = new JLabel("Table: " + selectedTableNumber);
        tableInfo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gbc.gridy = 1;
        add(tableInfo, gbc);
        
        // Placeholder content area
        JPanel contentArea = new JPanel();
        contentArea.setOpaque(false);
        contentArea.setLayout(new BoxLayout(contentArea, BoxLayout.Y_AXIS));
        contentArea.add(new JLabel("Ticket creation interface will go here"));
        gbc.gridy = 2;
        gbc.weighty = 0.7;
        add(contentArea, gbc);
        
        // Navigation buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        
        JButton saveBtn = new JButton("Save Ticket");
        saveBtn.addActionListener(e -> saveTicket());
        buttonPanel.add(saveBtn);
        
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> cancel());
        buttonPanel.add(cancelBtn);
        
        gbc.gridy = 3;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gbc);
    }
    
    /**
     * Updates the panel with the selected table number.
     */
    public void setSelectedTable(int tableNumber) {
        this.selectedTableNumber = tableNumber;
    }
    
    /**
     * Handles saving the ticket.
     */
    private void saveTicket() {
        JOptionPane.showMessageDialog(this, 
            "Ticket saved for Table " + selectedTableNumber, 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
        // Return to table list
        mainApp.showScreen("ServerTableList");
    }
    
    /**
     * Cancels ticket creation and returns to table list.
     */
    private void cancel() {
        mainApp.showScreen("ServerTableList");
    }
}

