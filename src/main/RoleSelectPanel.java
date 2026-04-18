package main;

import javax.swing.*;
import java.awt.*;

/**
 * The Role Select Panel is shown when an Employee user with no assigned role logs in.
 * It allows the user to select from Host, Server, or Kitchen roles.
 * Host and Kitchen roles have maximum user limits enforced by RoleManager.
 */
public class RoleSelectPanel extends JPanel {
    private App mainApp;
    private RoleManager roleManager;
    private String currentUserRole; // Tracks the role selected by this user
    
    public RoleSelectPanel(App mainApp, RoleManager roleManager) {
        this.mainApp = mainApp;
        this.roleManager = roleManager;
        this.currentUserRole = null;
        
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 220)); // Beige background
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        
        // Row 0: Title
        JLabel title = new JLabel("Role Select");
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(title, gbc);
        
        // Row 1: Instructions
        JLabel instructions = new JLabel("Please select a role to begin:");
        instructions.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        gbc.gridy = 1;
        add(instructions, gbc);
        
        // Row 2: Role buttons (horizontal layout)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        
        // Host Button
        JButton hostBtn = new JButton("Host");
        hostBtn.setPreferredSize(new Dimension(100, 40));
        hostBtn.addActionListener(e -> selectRole(RoleManager.ROLE_HOST));
        buttonPanel.add(hostBtn);
        
        // Server Button
        JButton serverBtn = new JButton("Server");
        serverBtn.setPreferredSize(new Dimension(100, 40));
        serverBtn.addActionListener(e -> selectRole(RoleManager.ROLE_SERVER));
        buttonPanel.add(serverBtn);
        
        // Kitchen Button
        JButton kitchenBtn = new JButton("Kitchen");
        kitchenBtn.setPreferredSize(new Dimension(100, 40));
        kitchenBtn.addActionListener(e -> selectRole(RoleManager.ROLE_KITCHEN));
        buttonPanel.add(kitchenBtn);
        
        gbc.gridy = 2;
        add(buttonPanel, gbc);
        
        // Row 3: Status/Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        
        JLabel hostInfo = new JLabel("Host: " + roleManager.getActiveUserCount(RoleManager.ROLE_HOST) + 
                                      "/" + roleManager.getMaxLimit(RoleManager.ROLE_HOST));
        JLabel kitchenInfo = new JLabel("Kitchen: " + roleManager.getActiveUserCount(RoleManager.ROLE_KITCHEN) + 
                                         "/" + roleManager.getMaxLimit(RoleManager.ROLE_KITCHEN));
        
        infoPanel.add(hostInfo);
        infoPanel.add(kitchenInfo);
        gbc.gridy = 3;
        add(infoPanel, gbc);
        
        // Row 4: Log Out Button
        JButton logOutBtn = new JButton("Log Out");
        logOutBtn.addActionListener(e -> logout());
        gbc.gridy = 4;
        add(logOutBtn, gbc);
    }
    
    /**
     * Handles role selection logic.
     */
    private void selectRole(String role) {
        // Check if the role is available (not full)
        if (roleManager.isRoleFull(role)) {
            showError("Role Full", 
                      "The " + role + " role has reached its maximum capacity.\n" +
                      "Please select a different role.");
            return;
        }
        
        // Assign the role
        if (roleManager.assignRole(role)) {
            currentUserRole = role;
            // Transition to the appropriate view for this role
            switchToRoleView(role);
        } else {
            showError("Error", "Failed to assign role. Please try again.");
        }
    }
    
    /**
     * Switches to the appropriate panel for the selected role.
     * This is a placeholder - you'll need to implement role-specific panels.
     */
    private void switchToRoleView(String role) {
        switch (role) {
            case RoleManager.ROLE_HOST:
                mainApp.showScreen("HostView");
                break;
            case RoleManager.ROLE_SERVER:
                mainApp.showScreen("ServerTableList");
                break;
            case RoleManager.ROLE_KITCHEN:
                mainApp.showScreen("KitchenView");
                break;
        }
    }
    
    /**
     * Logs out the user and returns them to the Employee Login screen.
     */
    private void logout() {
        // Release the role if one was assigned
        if (currentUserRole != null) {
            roleManager.removeRole(currentUserRole);
            currentUserRole = null;
        }
        // Return to Employee Login screen
        mainApp.showScreen("Employee");
    }
    
    /**
     * Shows an error dialog to the user.
     */
    private void showError(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
}

