# Role Select Screen Implementation

## Overview
Successfully created the Role Select screen for the Pizza Restaurant System. This screen appears when an Employee user with no assigned role logs in, allowing them to select from three available roles: Host, Server, or Kitchen.

## Components Created

### 1. RoleManager.java
A management class that handles:
- **Role Assignment**: Manages which users are assigned to which roles
- **Capacity Management**: Enforces maximum user limits for Host (2 max) and Kitchen (3 max) roles
- **User Tracking**: Keeps track of active users per role
- **Role Validation**: Checks if a role has reached capacity before assignment

**Key Methods:**
- `assignRole(String role)`: Attempts to assign a role, returns false if capacity reached
- `removeRole(String role)`: Removes a user from a role (called on logout)
- `isRoleFull(String role)`: Checks if a role is at capacity
- `getActiveUserCount(String role)`: Returns current active users in a role

### 2. RoleSelectPanel.java
The GUI panel displayed when a user logs in without a role. Features:
- **Three Role Buttons**: Host, Server, and Kitchen buttons for role selection
- **Capacity Display**: Shows current/max capacity for limited roles (Host and Kitchen)
- **Error Handling**: Displays error messages if user attempts to select a full role
- **Logout Option**: Allows users to return to the Employee Login screen
- **Role Validation**: Checks capacity before allowing role selection

**User Flow:**
1. User selects a role (Host, Server, or Kitchen)
2. System checks if role is available
3. If full: Error message shown, user prompted to select different role
4. If available: User assigned to role and transitions to role-specific view
5. Logout button returns user to Employee Login screen

### 3. Updated EmployeeLoginPanel.java
Enhanced with login validation logic:
- PIN validation using the existing `login` class
- Transition to Role Select screen on successful login
- Error handling for invalid PINs and non-numeric input
- PIN field cleared after login attempt

### 4. Updated App.java
Updated to include:
- RoleManager initialization with configurable limits (2 for Host, 3 for Kitchen)
- RoleSelectPanel added to the CardLayout panel stack
- RoleManager getter for access by other components

## Features Implemented

### ✅ Role Selection
- Users can choose from Host, Server, or Kitchen roles
- Server role has unlimited capacity
- Host and Kitchen have administrator-configured limits

### ✅ Capacity Management
- Host role limited to 2 concurrent users
- Kitchen role limited to 3 concurrent users
- Server role unlimited
- Real-time capacity display on screen

### ✅ Error Handling
- User-friendly error messages when role is full
- Prompts user to select alternative role
- Input validation for PIN entry

### ✅ State Management
- User's assigned role is tracked
- Role is released on logout
- Proper cleanup ensures accurate capacity tracking

### ✅ Navigation
- Automatic transition to role-specific views upon successful role selection
- Logout button returns to Employee Login screen
- Proper screen switching using CardLayout

## Usage

To test the system:
1. Compile: `javac -d class src/Main/*.java`
2. Run: `java -cp class Main.App`
3. Login with PIN (1234 or 5678)
4. Select a role
5. If role is full, select an alternative role
6. Click "Log Out" to return to login screen

## Future Integration Points

The Role Select panel references role-specific views:
- `HostView` - Implement host view functionality
- `ServerView` - Implement server view functionality
- `KitchenView` - Implement kitchen view functionality

These screens will be shown when `switchToRoleView()` is called and can be added to the App's CardLayout when implemented.

