// Singleton class for managing database connection
class DatabaseManager {

    // The only instance of DatabaseManager
    private static DatabaseManager instance;

    // Database connection
    private String connection;

    // Private constructor
    // Prevents creating DatabaseManager objects from outside
    private DatabaseManager() {
        connection = "Smart Serve Database";
        System.out.println("DatabaseManager created.");
    }

    // Returns the single DatabaseManager instance
    public static DatabaseManager getInstance() {

        if (instance == null) {
            instance = new DatabaseManager();
        }

        return instance;
    }

    // Get database connection
    public String getConnection() {
        return connection;
    }

    // Example database operation
    public void saveOrder(int orderId) {
        System.out.println(
            "Order " + orderId + " saved to database."
        );
    }

    // Example database operation
    public void updateOrderStatus(int orderId, String status) {
        System.out.println(
            "Order " + orderId +
            " status updated to: " + status
        );
    }
}


// Order Module
class OrderModule {

    public void createOrder() {

        DatabaseManager db = DatabaseManager.getInstance();

        db.saveOrder(101);
    }
}


// Kitchen Module
class KitchenModule {

    public void prepareOrder() {

        DatabaseManager db = DatabaseManager.getInstance();

        db.updateOrderStatus(101, "Preparing");
    }
}


// Billing Module
class BillingModule {

    public void generateBill() {

        DatabaseManager db = DatabaseManager.getInstance();

        System.out.println(
            "Bill generated using: " +
            db.getConnection()
        );
    }
}


// Admin Module
class AdminModule {

    public void viewReports() {

        DatabaseManager db = DatabaseManager.getInstance();

        System.out.println(
            "Admin accessing database: " +
            db.getConnection()
        );
    }
}


// Main class
public class SmartServe {

    public static void main(String[] args) {

        OrderModule orderModule = new OrderModule();
        KitchenModule kitchenModule = new KitchenModule();
        BillingModule billingModule = new BillingModule();
        AdminModule adminModule = new AdminModule();

        // Order module uses DatabaseManager
        orderModule.createOrder();

        // Kitchen module uses the SAME DatabaseManager
        kitchenModule.prepareOrder();

        // Billing module uses the SAME DatabaseManager
        billingModule.generateBill();

        // Admin module uses the SAME DatabaseManager
        adminModule.viewReports();

        // Check Singleton
        DatabaseManager db1 = DatabaseManager.getInstance();
        DatabaseManager db2 = DatabaseManager.getInstance();

        if (db1 == db2) {
            System.out.println(
                "\nBoth db1 and db2 refer to the SAME object."
            );
        }
    }
}