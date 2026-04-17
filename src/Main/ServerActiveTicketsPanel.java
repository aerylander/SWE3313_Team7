package Main;

import javax.swing.*;
import java.awt.*;

/**
 * The Active Tickets screen shows all active tickets across all tables.
 * This is a placeholder implementation.
 */
public class ServerActiveTicketsPanel extends JPanel {
    private App mainApp;
    
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
        
        // Placeholder content area
        JPanel contentArea = new JPanel();
        contentArea.setOpaque(false);
        contentArea.setLayout(new BoxLayout(contentArea, BoxLayout.Y_AXIS));
        contentArea.add(new JLabel("List of active tickets will appear here"));
        gbc.gridy = 1;
        gbc.weighty = 0.7;
        add(contentArea, gbc);
        
        // Navigation buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        
        JButton backBtn = new JButton("Back to Tables");
        backBtn.addActionListener(e -> backToTableList());
        buttonPanel.add(backBtn);
        
        JButton logOutBtn = new JButton("Log Out");
        logOutBtn.addActionListener(e -> logout());
        buttonPanel.add(logOutBtn);
        
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gbc);
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

