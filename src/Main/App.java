package Main;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    // CardLayout is the "engine" that swaps the views
    private CardLayout cardLayout;
    private JPanel mainContainer;

    public App() {
        // Basic window setup
        setTitle("J's Pizza Restaurant System");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers the window

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // Initialize and add the panels to the card stack
        // These names ("Employee", "Admin") are used to switch between them
        mainContainer.add(new EmployeeLoginPanel(this), "Employee");
        mainContainer.add(new AdminLoginPanel(this), "Admin");

        add(mainContainer);
        setVisible(true);
    }

    // Method used by the buttons in other panels to request a screen change
    public void showScreen(String screenName) {
        cardLayout.show(mainContainer, screenName);
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> new App());
    }
}