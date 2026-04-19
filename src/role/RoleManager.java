package role;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages user roles and tracks active users per role.
 * Enforces maximum limits for Host and Kitchen roles.
 */
public class RoleManager {
    public static final String ROLE_HOST = "Host";
    public static final String ROLE_SERVER = "Server";
    public static final String ROLE_KITCHEN = "Kitchen";
    
    private int maxHostUsers;
    private int maxKitchenUsers;
    
    // Track active users by role
    private Map<String, Integer> activeUserCount;
    
    public RoleManager(int maxHostUsers, int maxKitchenUsers) {
        this.maxHostUsers = maxHostUsers;
        this.maxKitchenUsers = maxKitchenUsers;
        this.activeUserCount = new HashMap<>();
        this.activeUserCount.put(ROLE_HOST, 0);
        this.activeUserCount.put(ROLE_SERVER, 0);
        this.activeUserCount.put(ROLE_KITCHEN, 0);
    }
    
    /**
     * Attempts to assign a role to a user.
     * Returns true if successful, false if the role limit is reached.
     */
    public boolean assignRole(String role) {
        if (role.equals(ROLE_HOST)) {
            if (activeUserCount.get(ROLE_HOST) >= maxHostUsers) {
                return false;
            }
            activeUserCount.put(ROLE_HOST, activeUserCount.get(ROLE_HOST));
            return true;
        } else if (role.equals(ROLE_KITCHEN)) {
            if (activeUserCount.get(ROLE_KITCHEN) >= maxKitchenUsers) {
                return false;
            }
            activeUserCount.put(ROLE_KITCHEN, activeUserCount.get(ROLE_KITCHEN) + 1);
            return true;
        } else if (role.equals(ROLE_SERVER)) {
            // Server role has no limit
            int updateCount = getActiveUserCount(ROLE_SERVER);
            updateCount++;
            activeUserCount.put(ROLE_SERVER, updateCount);
            return true;
        }
        return false;
    }
    
    /**
     * Removes a user from a role (called when user logs out).
     */
    public void removeRole(String role) {
        if (activeUserCount.containsKey(role)) {
            int count = activeUserCount.get(role);
            if (count > 0) {
                activeUserCount.put(role, count - 1);
            }
        }
    }
    
    /**
     * Checks if a role has reached its maximum capacity.
     */
    public boolean isRoleFull(String role) {
        if (role.equals(ROLE_HOST)) {
            return activeUserCount.get(ROLE_HOST) >= maxHostUsers;
        } else if (role.equals(ROLE_KITCHEN)) {
            return activeUserCount.get(ROLE_KITCHEN) >= maxKitchenUsers;
        }
        return false; // Server has no limit
    }
    
    /**
     * Gets the current count of active users in a role.
     */
    public int getActiveUserCount(String role) {
        return activeUserCount.get(role);
    }
    
    /**
     * Gets the maximum limit for a role.
     */
    public int getMaxLimit(String role) {
        if (role.equals(ROLE_HOST)) {
            return maxHostUsers;
        } else if (role.equals(ROLE_KITCHEN)) {
            return maxKitchenUsers;
        }
        return -1; // No limit for Server
    }
}

