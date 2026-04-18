# Server View Implementation

## Overview
Successfully created the Server View system for the Pizza Restaurant System. This includes the Server Table List screen and related components for table and ticket management.

## Components Created

### 1. Table.java
A data model representing a single restaurant table.

**Properties:**
- `tableNumber`: Unique identifier for the table
- `hasActiveTicket`: Boolean flag indicating if table has an active ticket

**Key Methods:**
- `getTableNumber()`: Returns the table number
- `hasActiveTicket()`: Returns whether table has active ticket
- `setHasActiveTicket(boolean)`: Updates ticket status
- `toString()`: Returns display string (e.g., "Table 1 (Active)")

### 2. TableManager.java
Manages all restaurant tables and their states.

**Key Methods:**
- `getAllTables()`: Returns a list of all tables
- `getTable(int tableNumber)`: Gets a specific table
- `setTableActiveTicket(int, boolean)`: Updates a table's ticket status
- `tableHasActiveTicket(int)`: Checks if a specific table has active ticket
- `getTotalTableCount()`: Returns number of tables

**Initialization:**
- Created with the number of tables (default: 10 tables in App.java)

### 3. ServerTableListPanel.java
The main Server view screen displaying all available tables.

**Features:**
- **Table List Display**: Shows all tables with JList component
- **Table Selection**: Single-selection mode for selecting one table at a time
- **Visual Feedback**: Displays table status (active/inactive)
- **Two Action Buttons**:
  - "Start New Ticket": Creates a new ticket for selected table
  - "View Active Tickets": Shows all active tickets across all tables
- **Log Out Button**: Releases Server role and returns to Employee Login

**User Flow:**
1. Server selects a table from the list
2. Clicks either "Start New Ticket" or "View Active Tickets"
3. Can log out at any time, releasing the Server role

**Methods:**
- `startNewTicket()`: Validates table selection, passes to TicketCreation panel
- `viewActiveTickets()`: Navigates to Active Tickets screen
- `logout()`: Releases role and returns to login
- `refreshTableList()`: Updates table display

### 4. ServerTicketCreationPanel.java
Screen for creating a new ticket for a selected table.

**Features:**
- Displays selected table number
- Placeholder area for ticket creation interface
- "Save Ticket" button to save the ticket
- "Cancel" button to return to table list

**Methods:**
- `setSelectedTable(int)`: Updates the selected table
- `saveTicket()`: Saves the ticket and returns to table list
- `cancel()`: Cancels without saving and returns to table list

### 5. ServerActiveTicketsPanel.java
Screen displaying all active tickets.

**Features:**
- Placeholder area for ticket list
- "Back to Tables" button to return to table list
- "Log Out" button to logout

**Methods:**
- `backToTableList()`: Returns to ServerTableListPanel
- `logout()`: Releases role and returns to login

## Updated Components

### App.java
Updated to include:
- `TableManager` initialization with 10 tables
- `ServerTableListPanel`, `ServerTicketCreationPanel`, and `ServerActiveTicketsPanel` added to CardLayout
- `selectTable(int)` method to set selected table for ticket creation
- `getTableManager()` getter for table management

### RoleSelectPanel.java
Updated to:
- Navigate to "ServerTableList" when Server role is selected (instead of placeholder "ServerView")

### EmployeeLoginPanel.java
(Previously updated)
- Validates PIN and transitions to Role Select screen on successful login

## Screen Navigation Flow

```
Employee Login
     ↓ (PIN: 1234 or 5678)
Role Select
     ↓ (Select Server)
Server Table List
     ├→ Start New Ticket → Server Ticket Creation → Back to Table List
     ├→ View Active Tickets → Server Active Tickets → Back to Table List
     └→ Log Out → Employee Login
```

## Usage

### To Test:
1. Compile: `javac -d class src/main/*.java`
2. Run: `java -cp class main.App`
3. Enter PIN: `1234` or `5678`
4. Select "Server" role
5. View the Server Table List with all 10 tables
6. Click to select a table
7. Click "Start New Ticket" or "View Active Tickets"
8. Use "Log Out" to return to login

## Features Implemented

✅ **Server Table List Screen**
- Displays all restaurant tables
- Single table selection
- Table status display (active/inactive)

✅ **Ticket Creation Access**
- Start new ticket button for selected table
- Validates table selection before proceeding
- Passes selected table to creation screen

✅ **Active Tickets Access**
- Direct access to view all active tickets
- Accessible from table list

✅ **Navigation & Logout**
- Log out button with proper role cleanup
- Exits back to Employee Login screen
- Back buttons maintain proper screen hierarchy

## Future Enhancement Points

1. **Ticket Creation Screen**: Implement full ticket creation interface with menu items, quantities, special instructions
2. **Active Tickets Display**: Show actual ticket list with details, status, and modification options
3. **Table Status Real-time Updates**: Auto-refresh table status based on ticket states
4. **Server Assignments**: Track which server created which tickets
5. **Persistence**: Save tickets to database/file system
6. **Order Tracking**: Integration with kitchen display system

