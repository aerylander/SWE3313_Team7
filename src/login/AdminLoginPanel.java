package login;

import javax.swing.*;
import java.awt.*;

public class AdminLoginPanel extends JPanel {
    public AdminLoginPanel(App mainApp) {
        setLayout(new GridBagLayout());
        setBackground(new Color(255, 140, 0)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Tighter spacing for form fields
        gbc.gridx = 0;

        // Row 0: Title
        JLabel title = new JLabel("Admin Login");
        title.setFont(new Font("Times New Roman", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across label/field columns
        add(title, gbc);

        // Reset gridwidth for form fields
        gbc.gridwidth = 1;

        // Row 1: Username
        gbc.gridy = 1; gbc.gridx = 0;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        add(userLabel, gbc);
        
        gbc.gridx = 1;
        JTextField userField = new JTextField(15);
        add(userField, gbc);

        // Row 2: Password
        gbc.gridy = 2; gbc.gridx = 0;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        add(passLabel, gbc);
        
        gbc.gridx = 1;
        JPasswordField passField = new JPasswordField(15);
        add(passField, gbc);

        // Row 3: OK Button (Centered below fields)
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        JButton okBtn = new JButton("OK");
        add(okBtn, gbc);

        // Row 4: Navigation
        JButton goToEmployee = new JButton("Go to Employee Login");
        goToEmployee.addActionListener(e -> mainApp.showScreen("Employee"));
        gbc.gridy = 4;
        add(goToEmployee, gbc);
    }
}