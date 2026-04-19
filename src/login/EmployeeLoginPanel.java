package login;

import javax.swing.*;
import java.awt.*;
import main.App;

public class EmployeeLoginPanel extends JPanel {
    public EmployeeLoginPanel(App mainApp) {
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 220)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        // Row 0: Title
        JLabel title = new JLabel("J's Pizza Restaurant Employee Login");
        title.setFont(new Font("Times New Roman", Font.BOLD, 20));
        gbc.gridy = 0;
        add(title, gbc);

        // Row 1: PIN Input Area (Using a sub-panel for horizontal alignment)
        JPanel pinPanel = new JPanel();
        pinPanel.setOpaque(false);
        pinPanel.add(new JLabel("Enter PIN:"));
        
        JPasswordField pinField = new JPasswordField(4); // Masked for security
        JButton loginBtn = new JButton("Login");
        
        loginBtn.addActionListener(e -> {
            String pin = new String(pinField.getPassword());
            login loginValidator = new login();
            try {
                int pinInt = Integer.parseInt(pin);
                if (loginValidator.validatePin(pinInt)) {
                    // PIN is valid, transition to Role Select screen
                    mainApp.showScreen("RoleSelect");
                    pinField.setText(""); // Clear field for next use
                } else {
                    JOptionPane.showMessageDialog(pinPanel, 
                        "Invalid PIN. Please try again.", 
                        "Login Failed", 
                        JOptionPane.ERROR_MESSAGE);
                    pinField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(pinPanel, 
                    "PIN must be a number.", 
                    "Invalid Input", 
                    JOptionPane.ERROR_MESSAGE);
                pinField.setText("");
            }
        });
        
        pinPanel.add(pinField);
        pinPanel.add(loginBtn);
        
        gbc.gridy = 1;
        add(pinPanel, gbc);

        // Row 2: Navigation
        JButton goToAdmin = new JButton("Go to Admin Login");
        goToAdmin.addActionListener(e -> mainApp.showScreen("Admin"));
        gbc.gridy = 2;
        add(goToAdmin, gbc);
    }
}