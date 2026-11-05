
import java.util.*;

public class RBAC {
    static Map<String, String> userRoles = new HashMap<>();
    static Map<String, Set<String>> rolePermissions = new HashMap<>();

    // Assign a user to a role
    static void assignRole(String user, String role) {
        userRoles.put(user, role);
    }

    // Assign a permission to a role
    static void assignPermission(String role, String permission) {
        rolePermissions.putIfAbsent(role, new HashSet<>());
        rolePermissions.get(role).add(permission);
    }

    // Check whether a user has a permission
    static boolean hasPermission(String user, String permission) {
        String role = userRoles.get(user);

        if (role == null) {
            return false;
        }

        Set<String> permissions = rolePermissions.get(role);

        return permissions != null &&
               permissions.contains(permission);
    }

    public static void main(String[] args) {

        // Define roles and their permissions
        rolePermissions.put("Admin", new HashSet<>(
            Arrays.asList("Create", "View", "Update", "Delete")
        ));

        rolePermissions.put("Manager", new HashSet<>(
            Arrays.asList("View", "Update")
        ));

        rolePermissions.put("Customer", new HashSet<>(
            Arrays.asList("View")
        ));

        // Assign users to roles
        assignRole("Ahmad", "Admin");
        assignRole("Omar", "Manager");
        assignRole("Ali", "Customer");

        // Test access requests
        System.out.println("Ahmad - Delete: " +
            hasPermission("Ahmad", "Delete"));

        System.out.println("Omar - Update: " +
            hasPermission("Omar", "Update"));

        System.out.println("Ali - Delete: " +
            hasPermission("Ali", "Delete"));

        // Grant a new permission to a role
        assignPermission("Customer", "Update");

        System.out.println("Ali - Update after permission granted: " +
            hasPermission("Ali", "Update"));
    }
}
