package Main;

import javax.swing.*;
import java.awt.*;

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