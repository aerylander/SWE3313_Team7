# Quick Reference - Server View System

## Files Overview

| File | Purpose | Status |
|------|---------|--------|
| `Table.java` | Restaurant table data model | ✅ Complete |
| `TableManager.java` | Manages all tables | ✅ Complete |
| `ServerTableListPanel.java` | Main server screen (table list) | ✅ Complete |
| `ServerTicketCreationPanel.java` | Ticket creation screen | ✅ Placeholder |
| `ServerActiveTicketsPanel.java` | Active tickets display | ✅ Placeholder |
| `App.java` | Main application frame | ✅ Updated |
| `RoleSelectPanel.java` | Role selection | ✅ Updated |
| `EmployeeLoginPanel.java` | Employee login | ✅ Updated |
| `RoleManager.java` | Role management | ✅ Complete |
| `login.java` | PIN validation | ✓ Existing |
| `AdminLoginPanel.java` | Admin login | ✓ Existing |

## Screen IDs in CardLayout

Used with `app.showScreen(screenName)`:

- `"Employee"` - Employee Login screen
- `"Admin"` - Admin Login screen
- `"RoleSelect"` - Role Selection screen
- `"ServerTableList"` - **Server Table List (MAIN)**
- `"ServerTicketCreation"` - Ticket Creation screen
- `"ServerActiveTickets"` - Active Tickets screen

## Default PINs

- `1234`
- `5678`

## Server Role Properties

- **Maximum Users**: Unlimited
- **Default Screen**: ServerTableList
- **Number of Tables**: 10

## Key Classes & Methods

### ServerTableListPanel
```java
selectTable(tableNumber)      // Select a table
startNewTicket()              // Start ticket creation
viewActiveTickets()           // View all active tickets
logout()                      // Log out and release role
refreshTableList()            // Refresh table display
```

### TableManager
```java
getAllTables()                // Get all tables
getTable(tableNumber)         // Get specific table
setTableActiveTicket(num, hasTicket)  // Update table status
tableHasActiveTicket(num)     // Check table status
```

### App
```java
selectTable(tableNumber)      // Pass selected table to creation panel
showScreen(screenName)        // Switch between screens
getRoleManager()              // Get role manager
getTableManager()             // Get table manager
```

## User Flow Diagram

```
┌─────────────────────┐
│ Employee Login      │
│ PIN: 1234 or 5678   │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Role Select         │
│ Choose: Server      │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────────────────────┐
│ Server Table List (MAIN)            │
│ • Select Table                      │
│ • [Start New Ticket]                │
│ • [View Active Tickets]             │
│ • [Log Out]                         │
└──┬──────────────────────┬───────┬──┘
   │                      │       │
   ▼                      ▼       └──────────────┐
┌──────────────────┐  ┌────────────────┐        │
│ Ticket Creation  │  │ Active Tickets │        │
│ (Placeholder)    │  │ (Placeholder)  │        │
└────────┬─────────┘  └────────┬───────┘        │
         │                     │                 │
         └──────────┬──────────┘                 │
                    │                           │
                    ▼                           │
           [Back to Table List] ←───────────────┘
```

## Compilation & Execution

```bash
# Navigate to project directory
cd /Users/patrickpucylowski/Documents/GitHub/SWE3313_Team7

# Compile all Java files
javac -d class src/Main/*.java

# Run the application
java -cp class Main.App
```

## Testing Checklist

- [ ] Application starts with Employee Login screen
- [ ] PIN validation works (1234, 5678 accepted, others rejected)
- [ ] Role Select screen appears after valid login
- [ ] Selecting "Server" role navigates to Table List
- [ ] All 10 tables display in the list
- [ ] Can select a table from the list
- [ ] "Start New Ticket" button validates table selection
- [ ] "Start New Ticket" navigates to Ticket Creation screen
- [ ] Can cancel from Ticket Creation and return to Table List
- [ ] "View Active Tickets" navigates to Active Tickets screen
- [ ] Can return from Active Tickets to Table List
- [ ] "Log Out" button releases role and returns to login
- [ ] Can log in again with same PIN

## Known Limitations (Placeholders)

- TicketCreation screen is placeholder - needs full implementation
- ActiveTickets screen is placeholder - needs full implementation
- Tables don't persist between sessions
- No ticket data storage
- No kitchen integration
- No real ticket tracking

## Future Enhancements

See `SERVER_VIEW_IMPLEMENTATION.md` for detailed next steps.

---
**Quick Reference Version 1.0** | April 17, 2026

