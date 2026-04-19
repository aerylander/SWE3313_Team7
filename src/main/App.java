package main;

import login.AdminLoginPanel;
import login.EmployeeLoginPanel;
import menu.MenuManager;
import server.ServerTableListPanel;
import server.ServerTicketCreationPanel;
import server.ServerActiveTicketsPanel;
import table.TableManager;
import ticket.TicketManager;
import role.RoleSelectPanel;
import role.RoleManager;
import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    // CardLayout is the "engine" that swaps the views
    private final CardLayout cardLayout;
    private final JPanel mainContainer;
    private final RoleManager roleManager;
    private final ServerTicketCreationPanel serverTicketCreationPanel;
    private final TicketManager ticketManager;

    public App() {
        // Basic window setup
        setTitle("J's Pizza Restaurant System");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers the window

        // Initialize the RoleManager with max limits for Host and Kitchen
        // These can be configured by administrators
        roleManager = new RoleManager(2, 3); // 2 max hosts, 3 max kitchen staff

        ticketManager = new TicketManager();
        MenuManager menuManager = new MenuManager();

        // Initialize the TableManager with the restaurant's tables
        TableManager tableManager = new TableManager(30, ticketManager); // 10 tables in the restaurant

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // Initialize and add the panels to the card stack
        // These names are used to switch between them
        mainContainer.add(new EmployeeLoginPanel(this), "Employee");
        mainContainer.add(new AdminLoginPanel(this), "Admin");
        mainContainer.add(new RoleSelectPanel(this, roleManager), "RoleSelect");
        
        // Server view screens
        ServerTableListPanel serverTableListPanel = new ServerTableListPanel(this, tableManager);
        serverTicketCreationPanel = new ServerTicketCreationPanel(this, menuManager, ticketManager);
        mainContainer.add(serverTableListPanel, "ServerTableList");
        mainContainer.add(serverTicketCreationPanel, "ServerTicketCreation");
        mainContainer.add(new ServerActiveTicketsPanel(this), "ServerActiveTickets");

        add(mainContainer);
        setVisible(true);
    }

    // Method to pass RoleManager's activeUserCount values to other modules.
    public int getNumberOfRole(String role){
        return roleManager.getActiveUserCount(role);
    }

    // Method to pass ticketManager to other modules.
    public TicketManager getTicketManager(){
        return ticketManager;
    }

    // Method used by the buttons in other panels to request a screen change
    public void showScreen(String screenName) {
        cardLayout.show(mainContainer, screenName);
    }

    // Getter for RoleManager (if needed by other panels)
    public RoleManager getRoleManager() {
        return roleManager;
    }

    // Sets the selected table for ticket creation
    public void selectTable(int tableNumber) {
        serverTicketCreationPanel.setSelectedTable(tableNumber);
    }

    static void main(String[]args) {
        // Run the GUI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(App::new);
    }
    public void refreshServerTableList(){
        //This method has no content. It should call TableManager and refresh the statuses for the Server Table List.
    }
}