//import java.sql.*;
//
//public class DatabaseUtil {
//    private static final String DB_URL = "jdbc:postgresql://localhost:5432/FitnessTracker"; // Replace with your DB name
//    private static final String USER = "FAREED"; // PostgreSQL username
//    private static final String PASSWORD = "Bismillah"; // PostgreSQL password
//
//    public static Connection connect() {
//        try {
//            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
//        } catch (SQLException e) {
//            System.out.println("Connection failed: " + e.getMessage());
//            return null;
//        }
//    }
//
//    // Method to validate login credentials
//    public static boolean validateLogin(String username, String password) {
//        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
//        try (Connection conn = connect();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//            ResultSet rs = stmt.executeQuery();
//            return rs.next(); // Returns true if a match is found
//        } catch (SQLException e) {
//            System.out.println("Login validation error: " + e.getMessage());
//            return false;
//        }
//    }
//
//    // Method to insert new user during sign-up
////    public static boolean insertUser(String username, String password) {
////        String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
////        try (Connection conn = connect();
////             PreparedStatement stmt = conn.prepareStatement(query)) {
////            stmt.setString(1, username);
////            stmt.setString(2, password);
////            int rowsAffected = stmt.executeUpdate();
////            return rowsAffected > 0; // Return true if insertion is successful
////        } catch (SQLException e) {
////            System.out.println("Error inserting user: " + e.getMessage());
////            return false;
////        }
////    }
////}
//// Method to insert new user during sign-up with duplicate handling
//    public static boolean insertUser(String username, String password) {
//        String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
//        try (Connection conn = connect();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//            int rowsAffected = stmt.executeUpdate();
//            return rowsAffected > 0; // Return true if insertion is successful
//        } catch (SQLException e) {
//            if (e.getSQLState().equals("23505")) { // PostgreSQL unique constraint violation code
//                System.out.println("Error: Username already exists.");
//            } else {
//                System.out.println("Error inserting user: " + e.getMessage());
//            }
//            return false;
//        }
//    }
//
//
//        public static boolean insertProfile(int userId, String email, String phone, String city, String country,
//                                            Date dob, String gender, float height, float weight,
//                                            String activityLevel, String goalType, float targetWeight) {
//            String query = "INSERT INTO Profile (user_id, email, phone, city, country, dob, gender, height_cm, weight_kg, activity_level, goal_type, target_weight) " +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//            try (Connection conn = connect();
//                 PreparedStatement stmt = conn.prepareStatement(query)) {
//                stmt.setInt(1, userId);
//                stmt.setString(2, email);
//                stmt.setString(3, phone);
//                stmt.setString(4, city);
//                stmt.setString(5, country);
//                stmt.setDate(6, dob);  // java.sql.Date format
//                stmt.setString(7, gender);
//                stmt.setFloat(8, height);
//                stmt.setFloat(9, weight);
//                stmt.setString(10, activityLevel);
//                stmt.setString(11, goalType);
//                stmt.setFloat(12, targetWeight);
//
//                int rowsAffected = stmt.executeUpdate();
//                return rowsAffected > 0; // Returns true if insertion was successful
//            } catch (SQLException e) {
//                System.out.println("Error inserting profile: " + e.getMessage());
//                return false;
//            }
//        }
//    }
//
//
//
//
//
import java.sql.*;
import java.util.*;
import java.sql.Date;
public class DatabaseUtil {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/FitnessTracker"; // Replace with your DB name
    private static final String USER = "FAREED"; // PostgreSQL username
    private static final String PASSWORD = "Bismillah"; // PostgreSQL password

    // Method to connect to the database
    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    public static Integer insertUser(String username, String password) {
        String query = "INSERT INTO Users (username, password) VALUES (?, ?) RETURNING user_id";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set username and password parameters
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute the query and retrieve the generated user_id
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id"); // Return the user_id if insertion was successful
            }
        } catch (SQLException e) {
            // Check for unique constraint violation (username already exists)
            if ("23505".equals(e.getSQLState())) { // PostgreSQL unique constraint violation code
                System.out.println("Error: Username already exists.");
            } else {
                System.out.println("Error inserting user: " + e.getMessage());
            }
        }
        return null; // Return null if insertion fails
    }

public static int validateLoginGetUserId(Connection conn, String username, String password) {
    int userId = -1;
    String query = "SELECT user_id FROM Users WHERE username = ? AND password = ?";

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            userId = rs.getInt("user_id");  // Adjusted to match column name in the database
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return userId;
}




    // Method to insert a new profile for a user
    public static boolean insertProfile(int userId, String email, String phone, String city, String country,
                                        Date dob, String gender, float height, float weight,
                                        String activityLevel, String goalType, float targetWeight) {
        String query = "INSERT INTO Profile (user_id, email, phone, city, country, dob, gender, height_cm, weight_kg, activity_level, goal_type, target_weight) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, city);
            stmt.setString(5, country);
            stmt.setDate(6, dob);  // java.sql.Date format
            stmt.setString(7, gender);
            stmt.setFloat(8, height);
            stmt.setFloat(9, weight);
            stmt.setString(10, activityLevel);
            stmt.setString(11, goalType);
            stmt.setFloat(12, targetWeight);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if insertion was successful
        } catch (SQLException e) {
            System.out.println("Error inserting profile: " + e.getMessage());
            return false;
        }
    }

    // Method to fetch user profile by userId
    public static UserProfile getUserProfile(int userId) {
        String query = "SELECT * FROM Profile WHERE user_id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String city = rs.getString("city");
                String country = rs.getString("country");
                Date dob = rs.getDate("dob");
                String gender = rs.getString("gender");
                float height = rs.getFloat("height_cm");
                float weight = rs.getFloat("weight_kg");
                String activityLevel = rs.getString("activity_level");
                String goalType = rs.getString("goal_type");
                float targetWeight = rs.getFloat("target_weight");

                return new UserProfile(userId, email, phone, city, country, dob, gender, height, weight, activityLevel, goalType, targetWeight);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user profile: " + e.getMessage());
        }
        return null;
    }

    // Method to update user profile
    public static boolean updateUserProfile(int userId, String email, String phone, String city, String country,
                                            Date dob, String gender, float height, float weight,
                                            String activityLevel, String goalType, float targetWeight) {
        String query = "UPDATE Profile SET email = ?, phone = ?, city = ?, country = ?, dob = ?, gender = ?, " +
                "height_cm = ?, weight_kg = ?, activity_level = ?, goal_type = ?, target_weight = ? WHERE user_id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, phone);
            stmt.setString(3, city);
            stmt.setString(4, country);
            stmt.setDate(5, dob);
            stmt.setString(6, gender);
            stmt.setFloat(7, height);
            stmt.setFloat(8, weight);
            stmt.setString(9, activityLevel);
            stmt.setString(10, goalType);
            stmt.setFloat(11, targetWeight);
            stmt.setInt(12, userId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if update was successful
        } catch (SQLException e) {
            System.out.println("Error updating user profile: " + e.getMessage());
        }
        return false;
    }

    // Method to calculate BMI
    public static float calculateBMI(float height, float weight) {
        float heightInMeters = height / 100; // Convert height from cm to meters
        return weight / (heightInMeters * heightInMeters);
    }

    // Method to provide nutrition suggestion based on goal type (gain/lose/maintain)
    public static String getNutritionSuggestion(String goalType) {
        if (goalType.equalsIgnoreCase("gain")) {
            return "Suggested Calorie Intake: 2500-3000 kcal/day. Focus on high-protein and calorie-dense foods.";
        } else if (goalType.equalsIgnoreCase("lose")) {
            return "Suggested Calorie Intake: 1500-2000 kcal/day. Focus on low-calorie and nutrient-dense foods.";
        } else if (goalType.equalsIgnoreCase("maintain")) {
            return "Suggested Calorie Intake: 2000-2500 kcal/day. A balanced diet of carbs, proteins, and fats.";
        }
        return "No goal specified.";
    }

    // Method to provide workout plan based on goal type
    public static String getWorkoutPlan(String goalType) {
        if (goalType.equalsIgnoreCase("gain")) {
            return "Workout Plan: Focus on strength training, weight lifting, and compound exercises.";
        } else if (goalType.equalsIgnoreCase("lose")) {
            return "Workout Plan: Focus on cardio, HIIT (High-Intensity Interval Training), and fat-burning exercises.";
        } else if (goalType.equalsIgnoreCase("maintain")) {
            return "Workout Plan: A balanced mix of cardio and strength training.";
        }
        return "No goal specified.";
    }
}
