import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        // Database URL with your PostgreSQL database name
        String url = "jdbc:postgresql://localhost:5432/FitnessTracker";
        // Replace with your actual PostgreSQL username and password
        String user = "FAREED";
        String password = "Bismillah";

        // Establishing connection
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
}
