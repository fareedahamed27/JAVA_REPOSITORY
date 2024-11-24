//import javax.swing.*;
//import javax.swing.border.TitledBorder;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class DashboardPage extends JFrame {
//    private int userId;
//    private JLabel bmiLabel, bmiCategoryLabel, calorieSuggestionLabel, workoutPlanLabel;
//    private JTextField emailField, phoneField, cityField, countryField;
//    private JButton saveProfileButton;
//    private Connection connection; // Assuming this is your DB connection
//
//    public DashboardPage(int userId, Connection connection) {
//        this.userId = userId;
//        this.connection = connection;
//        setTitle("User Dashboard");
//        setSize(800, 600);
//        setLayout(new GridLayout(2, 2, 10, 10)); // Four sections with gaps
//
//        JPanel profilePanel = createProfilePanel();
//        JPanel bmiPanel = createBMIPanel();
//        JPanel nutritionPanel = createNutritionPanel();
//        JPanel workoutPanel = createWorkoutPanel();
//
//        add(profilePanel);
//        add(bmiPanel);
//        add(nutritionPanel);
//        add(workoutPanel);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//    }
//
//    private JPanel createProfilePanel() {
//        JPanel panel = new JPanel();
//        panel.setBorder(new TitledBorder("Profile"));
//
//        emailField = new JTextField(20);
//        phoneField = new JTextField(20);
//        cityField = new JTextField(20);
//        countryField = new JTextField(20);
//        saveProfileButton = new JButton("Save Changes");
//
//        // Load user details
//        loadUserProfile();
//
//        saveProfileButton.addActionListener(e -> updateUserProfile());
//
//        panel.setLayout(new GridLayout(5, 2));
//        panel.add(new JLabel("Email:"));
//        panel.add(emailField);
//        panel.add(new JLabel("Phone:"));
//        panel.add(phoneField);
//        panel.add(new JLabel("City:"));
//        panel.add(cityField);
//        panel.add(new JLabel("Country:"));
//        panel.add(countryField);
//        panel.add(saveProfileButton);
//
//        return panel;
//    }
//
//    private void loadUserProfile() {
//        try {
//            String sql = "SELECT email, phone, city, country FROM Profile WHERE userId=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                emailField.setText(rs.getString("email"));
//                phoneField.setText(rs.getString("phone"));
//                cityField.setText(rs.getString("city"));
//                countryField.setText(rs.getString("country"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void updateUserProfile() {
//        try {
//            String sql = "UPDATE Profile SET email=?, phone=?, city=?, country=? WHERE userId=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setString(1, emailField.getText());
//            stmt.setString(2, phoneField.getText());
//            stmt.setString(3, cityField.getText());
//            stmt.setString(4, countryField.getText());
//            stmt.setInt(5, userId);
//            stmt.executeUpdate();
//            JOptionPane.showMessageDialog(this, "Profile updated successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error updating profile.");
//        }
//    }
//
//    private JPanel createBMIPanel() {
//        JPanel panel = new JPanel();
//        panel.setBorder(new TitledBorder("BMI"));
//
//        double bmi = calculateBMI();
//        String category = classifyBMI(bmi);
//
//        bmiLabel = new JLabel("BMI: " + String.format("%.2f", bmi));
//        bmiCategoryLabel = new JLabel("Category: " + category);
//
//        panel.setLayout(new GridLayout(2, 1));
//        panel.add(bmiLabel);
//        panel.add(bmiCategoryLabel);
//
//        return panel;
//    }
//
//    private double calculateBMI() {
//        double height = 0, weight = 0;
//        try {
//            String sql = "SELECT height, weight FROM Profile WHERE userId=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                height = rs.getDouble("height");
//                weight = rs.getDouble("weight");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return (weight / Math.pow(height / 100, 2));
//    }
//
//    private String classifyBMI(double bmi) {
//        if (bmi < 18.5) return "Underweight";
//        else if (bmi < 24.9) return "Normal weight";
//        else if (bmi < 29.9) return "Overweight";
//        else return "Obesity";
//    }
//
//    private JPanel createNutritionPanel() {
//        JPanel panel = new JPanel();
//        panel.setBorder(new TitledBorder("Nutrition Suggestions"));
//
//        String calorieSuggestion = getCalorieSuggestion();
//        calorieSuggestionLabel = new JLabel("Suggested Caloric Intake: " + calorieSuggestion);
//
//        panel.add(calorieSuggestionLabel);
//
//        return panel;
//    }
//
//    private String getCalorieSuggestion() {
//        String goal = "";
//        try {
//            String sql = "SELECT goalType FROM Profile WHERE userId=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                goal = rs.getString("goalType");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        switch (goal) {
//            case "Gain": return "2500-3000 kcal";
//            case "Lose": return "1500-2000 kcal";
//            default: return "2000-2500 kcal";
//        }
//    }
//
//    private JPanel createWorkoutPanel() {
//        JPanel panel = new JPanel();
//        panel.setBorder(new TitledBorder("Workout Plan"));
//
//        String workoutPlan = getWorkoutPlan();
//        workoutPlanLabel = new JLabel("Workout Plan: " + workoutPlan);
//
//        panel.add(workoutPlanLabel);
//
//        return panel;
//    }
//
//    private String getWorkoutPlan() {
//        double bmi = calculateBMI();
//        String goal = "";
//        try {
//            String sql = "SELECT goalType FROM Profile WHERE userId=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                goal = rs.getString("goalType");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        if ("Gain".equals(goal)) {
//            return "Strength training, weight lifting.";
//        } else if ("Lose".equals(goal)) {
//            return "Cardio and high-intensity interval training.";
//        } else {
//            return "Balanced mix of cardio and strength training.";
//        }
//    }
//
//    public static void main(String[] args) {
//        // For demonstration purposes, replace with actual user ID and connection
//        new DashboardPage(1, null);
//    }
//}
//import javax.swing.*;
//import javax.swing.border.TitledBorder;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.*;
//import java.text.SimpleDateFormat;
//
//public class DashboardPage extends JFrame {
//    private int userId;
//    private JLabel bmiLabel, bmiCategoryLabel, calorieSuggestionLabel, workoutPlanLabel;
//    private JTextField emailField, phoneField, cityField, countryField, activityLevelField, genderField, targetWeightField;
//    private JFormattedTextField dobField;
//    private JButton saveProfileButton;
//    private Connection connection;
//
//    public DashboardPage(int userId, Connection connection) {
//        this.userId = userId;
//        this.connection = connection;
//        setTitle("User Dashboard");
//        setSize(800, 600);
//        setLayout(new GridLayout(2, 2, 10, 10)); // Four sections with gaps
//
//        JPanel profilePanel = createProfilePanel();
//        JPanel bmiPanel = createBMIPanel();
//        JPanel nutritionPanel = createNutritionPanel();
//        JPanel workoutPanel = createWorkoutPanel();
//
//        add(profilePanel);
//        add(bmiPanel);
//        add(nutritionPanel);
//        add(workoutPanel);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//    }
//
//    private JPanel createProfilePanel() {
//        JPanel panel = new JPanel();
//        panel.setBorder(new TitledBorder("Profile"));
//
//        emailField = new JTextField(20);
//        phoneField = new JTextField(20);
//        cityField = new JTextField(20);
//        countryField = new JTextField(20);
//        activityLevelField = new JTextField(20);
//        genderField = new JTextField(1); // Only one character for gender
//        targetWeightField = new JTextField(5);
//
//        // Use a date format for the date of birth field
//        dobField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
//        dobField.setColumns(10);
//
//        saveProfileButton = new JButton("Save Changes");
//
//        // Load user details
//        loadUserProfile();
//
//        saveProfileButton.addActionListener(e -> updateUserProfile());
//
//        panel.setLayout(new GridLayout(7, 2)); // Adjusted to accommodate all fields
//        panel.add(new JLabel("Email:"));
//        panel.add(emailField);
//        panel.add(new JLabel("Phone:"));
//        panel.add(phoneField);
//        panel.add(new JLabel("City:"));
//        panel.add(cityField);
//        panel.add(new JLabel("Country:"));
//        panel.add(countryField);
//        panel.add(new JLabel("Activity Level:"));
//        panel.add(activityLevelField);
//        panel.add(new JLabel("Gender:"));
//        panel.add(genderField);
//        panel.add(new JLabel("DOB (YYYY-MM-DD):"));
//        panel.add(dobField);
//        panel.add(new JLabel("Target Weight (kg):"));
//        panel.add(targetWeightField);
//        panel.add(saveProfileButton);
//
//        return panel;
//    }
//
//    private void loadUserProfile() {
//        try {
//            String sql = "SELECT email, phone, city, country, dob, gender, activity_level, target_weight FROM Profile WHERE user_id=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                emailField.setText(rs.getString("email"));
//                phoneField.setText(rs.getString("phone"));
//                cityField.setText(rs.getString("city"));
//                countryField.setText(rs.getString("country"));
//                activityLevelField.setText(rs.getString("activity_level"));
//                genderField.setText(rs.getString("gender"));
//                dobField.setText(rs.getString("dob"));
//                targetWeightField.setText(String.valueOf(rs.getFloat("target_weight")));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void updateUserProfile() {
//        try {
//            String sql = "UPDATE Profile SET email=?, phone=?, city=?, country=?, activity_level=?, gender=?, dob=?, target_weight=? WHERE user_id=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setString(1, emailField.getText());
//            stmt.setString(2, phoneField.getText());
//            stmt.setString(3, cityField.getText());
//            stmt.setString(4, countryField.getText());
//            stmt.setString(5, activityLevelField.getText());
//            stmt.setString(6, genderField.getText());
//            stmt.setDate(7, Date.valueOf(dobField.getText())); // Ensure date format is correct
//            stmt.setFloat(8, Float.parseFloat(targetWeightField.getText()));
//            stmt.setInt(9, userId);
//            stmt.executeUpdate();
//            JOptionPane.showMessageDialog(this, "Profile updated successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error updating profile.");
//        }
//    }
//
//    private JPanel createBMIPanel() {
//        JPanel panel = new JPanel();
//        panel.setBorder(new TitledBorder("BMI"));
//
//        double bmi = calculateBMI();
//        String category = classifyBMI(bmi);
//
//        bmiLabel = new JLabel("BMI: " + String.format("%.2f", bmi));
//        bmiCategoryLabel = new JLabel("Category: " + category);
//
//        panel.setLayout(new GridLayout(2, 1));
//        panel.add(bmiLabel);
//        panel.add(bmiCategoryLabel);
//
//        return panel;
//    }
//
//    private double calculateBMI() {
//        double height = 0, weight = 0;
//        try {
//            String sql = "SELECT height_cm, weight_kg FROM Profile WHERE user_id=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                height = rs.getDouble("height_cm");
//                weight = rs.getDouble("weight_kg");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return (weight / Math.pow(height / 100, 2));
//    }
//
//    private String classifyBMI(double bmi) {
//        if (bmi < 18.5) return "Underweight";
//        else if (bmi < 24.9) return "Normal weight";
//        else if (bmi < 29.9) return "Overweight";
//        else return "Obesity";
//    }
//
//    private JPanel createNutritionPanel() {
//        JPanel panel = new JPanel();
//        panel.setBorder(new TitledBorder("Nutrition Suggestions"));
//
//        String calorieSuggestion = getCalorieSuggestion();
//        calorieSuggestionLabel = new JLabel("Suggested Caloric Intake: " + calorieSuggestion);
//
//        panel.add(calorieSuggestionLabel);
//
//        return panel;
//    }
//
//    private String getCalorieSuggestion() {
//        String goal = "";
//        try {
//            String sql = "SELECT goal_type FROM Profile WHERE user_id=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                goal = rs.getString("goal_type");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        switch (goal) {
//            case "Gain":
//                return "2500-3000 kcal";
//            case "Lose":
//                return "1500-2000 kcal";
//            default:
//                return "2000-2500 kcal";
//        }
//    }
//
//    private JPanel createWorkoutPanel() {
//        JPanel panel = new JPanel();
//        panel.setBorder(new TitledBorder("Workout Plan"));
//
//        String workoutPlan = getWorkoutPlan();
//        workoutPlanLabel = new JLabel("Workout Plan: " + workoutPlan);
//
//        panel.add(workoutPlanLabel);
//
//        return panel;
//    }
//
//    private String getWorkoutPlan() {
//        double bmi = calculateBMI();
//        String goal = "";
//        try {
//            String sql = "SELECT goal_type FROM Profile WHERE user_id=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                goal = rs.getString("goal_type");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        if ("Gain".equals(goal)) {
//            return "Strength training, weight lifting.";
//        } else if ("Lose".equals(goal)) {
//            return "Cardio and high-intensity interval training.";
//        } else {
//            return "Balanced mix of cardio and strength training.";
//        }
//    }
//
//    //    public static void main(String[] args) {
////        // For demonstration purposes, replace with actual user ID and connection
////        new DashboardPage(1, null);
////    }
////}
//    public static void main(String[] args) {
//        // Establish connection using DatabaseUtil
//        Connection connection = DatabaseUtil.connect();
//
//        if (connection != null) {
//            new DashboardPage(1, connection);
//        } else {
//            System.out.println("Failed to establish a connection with the database.");
//        }
//    }
//}
//import javax.swing.*;
//import javax.swing.border.LineBorder;
//import java.awt.*;
//import java.sql.*;
//import java.text.SimpleDateFormat;
//import javax.swing.border.TitledBorder;
//
//public class DashboardPage extends JFrame {
//    private int userId;
//    private JLabel bmiLabel, bmiCategoryLabel, calorieSuggestionLabel, workoutPlanLabel;
//    private JTextField emailField, phoneField, cityField, countryField, activityLevelField, genderField, targetWeightField;
//    private JFormattedTextField dobField;
//    private JButton saveProfileButton;
//    private Connection connection;
//
//    public DashboardPage(int userId, Connection connection) {
//        this.userId = userId;
//        this.connection = connection;
//        setTitle("User Dashboard");
//        setSize(900, 700);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // Main layout
//        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
//        mainPanel.setBackground(new Color(245, 245, 245));
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//        // Top Panel (Profile Info and Actions)
//        JPanel profilePanel = createProfilePanel();
//        profilePanel.setPreferredSize(new Dimension(400, 300));
//
//        // Panels for BMI, Nutrition, Workout
//        JPanel bmiPanel = createBMIPanel();
//        JPanel nutritionPanel = createNutritionPanel();
//        JPanel workoutPanel = createWorkoutPanel();
//
//        // Container for bottom panels
//        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 10, 0));
//        bottomPanel.add(bmiPanel);
//        bottomPanel.add(nutritionPanel);
//        bottomPanel.add(workoutPanel);
//
//        mainPanel.add(profilePanel, BorderLayout.NORTH);
//        mainPanel.add(bottomPanel, BorderLayout.CENTER);
//
//        add(mainPanel);
//        setVisible(true);
//    }
//
//    private JPanel createProfilePanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBackground(new Color(230, 230, 255));
//        panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(70, 130, 180), 2, true), "Profile",
//                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), new Color(70, 130, 180)));
//
//        // Input fields setup
//        emailField = createInputField("Email:");
//        phoneField = createInputField("Phone:");
//        cityField = createInputField("City:");
//        countryField = createInputField("Country:");
//        activityLevelField = createInputField("Activity Level:");
//        genderField = createInputField("Gender:");
//        targetWeightField = createInputField("Target Weight (kg):");
//        dobField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
//        dobField.setColumns(20);
//
//        // Save Button
//        saveProfileButton = new JButton("Save Changes");
//        saveProfileButton.setBackground(new Color(70, 130, 180));
//        saveProfileButton.setForeground(Color.WHITE);
//        saveProfileButton.addActionListener(e -> updateUserProfile());
//
//        // Load user details
//        loadUserProfile();
//
//        // Add components to panel
//        panel.add(emailField);
//        panel.add(phoneField);
//        panel.add(cityField);
//        panel.add(countryField);
//        panel.add(activityLevelField);
//        panel.add(genderField);
//        panel.add(new JLabel("DOB (YYYY-MM-DD):"));
//        panel.add(dobField);
//        panel.add(targetWeightField);
//        panel.add(Box.createVerticalStrut(10)); // Spacing
//        panel.add(saveProfileButton);
//
//        return panel;
//    }
//
//    private JTextField createInputField(String labelText) {
//        JPanel fieldPanel = new JPanel(new BorderLayout());
//        fieldPanel.setBackground(new Color(230, 230, 255));
//        JLabel label = new JLabel(labelText);
//        label.setFont(new Font("Arial", Font.PLAIN, 13));
//        JTextField textField = new JTextField(20);
//        textField.setFont(new Font("Arial", Font.PLAIN, 14));
//        fieldPanel.add(label, BorderLayout.WEST);
//        fieldPanel.add(textField, BorderLayout.CENTER);
//        return textField;
//    }
//
//    private JPanel createBMIPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBackground(Color.WHITE);
//        panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(60, 179, 113), 2, true), "BMI",
//                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), new Color(60, 179, 113)));
//
//        double bmi = calculateBMI();
//        String category = classifyBMI(bmi);
//
//        bmiLabel = new JLabel("BMI: " + String.format("%.2f", bmi));
//        bmiCategoryLabel = new JLabel("Category: " + category);
//
//        bmiLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//        bmiCategoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        panel.add(bmiLabel);
//        panel.add(bmiCategoryLabel);
//
//        return panel;
//    }
//
//    private JPanel createNutritionPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBackground(Color.WHITE);
//        panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(255, 165, 0), 2, true), "Nutrition Suggestions",
//                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), new Color(255, 165, 0)));
//
//        String calorieSuggestion = getCalorieSuggestion();
//        calorieSuggestionLabel = new JLabel("Suggested Caloric Intake: " + calorieSuggestion);
//
//        calorieSuggestionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        panel.add(calorieSuggestionLabel);
//
//        return panel;
//    }
//
//    private JPanel createWorkoutPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBackground(Color.WHITE);
//        panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(128, 128, 255), 2, true), "Workout Plan",
//                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), new Color(128, 128, 255)));
//
//        String workoutPlan = getWorkoutPlan();
//        workoutPlanLabel = new JLabel("Workout Plan: " + workoutPlan);
//
//        workoutPlanLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        panel.add(workoutPlanLabel);
//
//        return panel;
//    }
//
//    // Additional methods for loading, updating user data and calculations remain unchanged
//}
//private String getWorkoutPlan() {
//    double bmi = calculateBMI();
//    String goal = "";
//    try {
//        String sql = "SELECT goal_type FROM Profile WHERE user_id=?";
//        PreparedStatement stmt = connection.prepareStatement(sql);
//        stmt.setInt(1, userId);
//        ResultSet rs = stmt.executeQuery();
//        if (rs.next()) {
//            goal = rs.getString("goal_type");
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//
//    if ("Gain".equals(goal)) {
//        return "Strength training, weight lifting.";
//    } else if ("Lose".equals(goal)) {
//        return "Cardio and high-intensity interval training.";
//    } else {
//        return "Balanced mix of cardio and strength training.";
//    }
//}
//
////    public static void main(String[] args) {
////        // For demonstration purposes, replace with actual user ID and connection
////        new DashboardPage(1, null);
////    }
////}
//public static void main(String[] args) {
//    // Establish connection using DatabaseUtil
//    Connection connection = DatabaseUtil.connect();
//
//    if (connection != null) {
//        new DashboardPage(1, connection);
//    } else {
//        System.out.println("Failed to establish a connection with the database.");
//    }
//}
//}
//














// version 3

//import javax.swing.*;
//import javax.swing.border.LineBorder;
//import javax.swing.border.TitledBorder;
//import java.awt.*;
//import java.sql.*;
//import java.text.SimpleDateFormat;
//
//public class DashboardPage extends JFrame {
//    private int userId;
//    private JLabel bmiLabel, bmiCategoryLabel, calorieSuggestionLabel, workoutPlanLabel;
//    private JTextField emailField, phoneField, cityField, countryField, activityLevelField, genderField, targetWeightField;
//    private JFormattedTextField dobField;
//    private JButton saveProfileButton;
//    private Connection connection;
//
//    public DashboardPage(int userId, Connection connection) {
//        this.userId = userId;
//        this.connection = connection;
//        setTitle("User Dashboard");
//        setSize(900, 700);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // Main layout
//        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
//        mainPanel.setBackground(new Color(245, 245, 245));
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//        // Top Panel (Profile Info and Actions)
//        JPanel profilePanel = createProfilePanel();
//        profilePanel.setPreferredSize(new Dimension(400, 300));
//
//        // Panels for BMI, Nutrition, Workout
//        JPanel bmiPanel = createBMIPanel();
//        JPanel nutritionPanel = createNutritionPanel();
//        JPanel workoutPanel = createWorkoutPanel();
//
//        // Container for bottom panels
//        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 10, 0));
//        bottomPanel.add(bmiPanel);
//        bottomPanel.add(nutritionPanel);
//        bottomPanel.add(workoutPanel);
//
//        mainPanel.add(profilePanel, BorderLayout.NORTH);
//        mainPanel.add(bottomPanel, BorderLayout.CENTER);
//
//        add(mainPanel);
//        setVisible(true);
//    }
//
//    private JPanel createProfilePanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBackground(new Color(230, 230, 255));
//        panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(70, 130, 180), 2, true), "Profile",
//                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), new Color(70, 130, 180)));
//
//        // Input fields setup
//        emailField = createInputField("Email:");
//        phoneField = createInputField("Phone:");
//        cityField = createInputField("City:");
//        countryField = createInputField("Country:");
//        activityLevelField = createInputField("Activity Level:");
//        genderField = createInputField("Gender:");
//        targetWeightField = createInputField("Target Weight (kg):");
//        dobField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
//        dobField.setColumns(20);
//
//        // Save Button
//        saveProfileButton = new JButton("Save Changes");
//        saveProfileButton.setBackground(new Color(70, 130, 180));
//        saveProfileButton.setForeground(Color.WHITE);
//        saveProfileButton.addActionListener(e -> updateUserProfile());
//
//        // Load user details
//        loadUserProfile();
//
//        // Add components to panel
//        panel.add(emailField);
//        panel.add(phoneField);
//        panel.add(cityField);
//        panel.add(countryField);
//        panel.add(activityLevelField);
//        panel.add(genderField);
//        panel.add(new JLabel("DOB (YYYY-MM-DD):"));
//        panel.add(dobField);
//        panel.add(targetWeightField);
//        panel.add(Box.createVerticalStrut(10)); // Spacing
//        panel.add(saveProfileButton);
//
//        return panel;
//    }
//
//    private JTextField createInputField(String labelText) {
//        JLabel label = new JLabel(labelText);
//        label.setFont(new Font("Arial", Font.PLAIN, 13));
//        JTextField textField = new JTextField(20);
//        textField.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        JPanel fieldPanel = new JPanel(new BorderLayout());
//        fieldPanel.setBackground(new Color(230, 230, 255));
//        fieldPanel.add(label, BorderLayout.WEST);
//        fieldPanel.add(textField, BorderLayout.CENTER);
//
//        return textField;
//    }
//
//    private JPanel createBMIPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBackground(Color.WHITE);
//        panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(60, 179, 113), 2, true), "BMI",
//                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), new Color(60, 179, 113)));
//
//        double bmi = calculateBMI();
//        String category = classifyBMI(bmi);
//
//        bmiLabel = new JLabel("BMI: " + String.format("%.2f", bmi));
//        bmiCategoryLabel = new JLabel("Category: " + category);
//
//        bmiLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//        bmiCategoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        panel.add(bmiLabel);
//        panel.add(bmiCategoryLabel);
//
//        return panel;
//    }
//
//    private JPanel createNutritionPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBackground(Color.WHITE);
//        panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(255, 165, 0), 2, true), "Nutrition Suggestions",
//                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), new Color(255, 165, 0)));
//
//        String calorieSuggestion = getCalorieSuggestion();
//        calorieSuggestionLabel = new JLabel("Suggested Caloric Intake: " + calorieSuggestion);
//
//        calorieSuggestionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        panel.add(calorieSuggestionLabel);
//
//        return panel;
//    }
//
//    private JPanel createWorkoutPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBackground(Color.WHITE);
//        panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(128, 128, 255), 2, true), "Workout Plan",
//                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), new Color(128, 128, 255)));
//
//        String workoutPlan = getWorkoutPlan();
//        workoutPlanLabel = new JLabel("Workout Plan: " + workoutPlan);
//
//        workoutPlanLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        panel.add(workoutPlanLabel);
//
//        return panel;
//    }
//
//    private double calculateBMI() {
//        // Placeholder for BMI calculation
//        return 24.5;  // Example BMI, should be calculated based on height and weight from the profile
//    }
//
//    private String classifyBMI(double bmi) {
//        if (bmi < 18.5) return "Underweight";
//        else if (bmi < 24.9) return "Normal weight";
//        else if (bmi < 29.9) return "Overweight";
//        else return "Obesity";
//    }
//
//    private void loadUserProfile() {
//        try {
//            String sql = "SELECT email, phone, city, country, dob, activity_level, gender, target_weight FROM Profile WHERE user_id=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                emailField.setText(rs.getString("email"));
//                phoneField.setText(rs.getString("phone"));
//                cityField.setText(rs.getString("city"));
//                countryField.setText(rs.getString("country"));
//                dobField.setText(rs.getString("dob"));
//                activityLevelField.setText(rs.getString("activity_level"));
//                genderField.setText(rs.getString("gender"));
//                targetWeightField.setText(rs.getString("target_weight"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void updateUserProfile() {
//        try {
//            String sql = "UPDATE Profile SET email=?, phone=?, city=?, country=?, dob=?, activity_level=?, gender=?, target_weight=? WHERE user_id=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setString(1, emailField.getText());
//            stmt.setString(2, phoneField.getText());
//            stmt.setString(3, cityField.getText());
//            stmt.setString(4, countryField.getText());
//            stmt.setString(5, dobField.getText());
//            stmt.setString(6, activityLevelField.getText());
//            stmt.setString(7, genderField.getText());
//            stmt.setString(8, targetWeightField.getText());
//            stmt.setInt(9, userId);
//            stmt.executeUpdate();
//            JOptionPane.showMessageDialog(this, "Profile updated successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error updating profile.");
//        }
//    }
//
//    private String getCalorieSuggestion() {
//        return "2000-2500 calories per day";  // Placeholder
//    }
//
//    private String getWorkoutPlan() {
//        double bmi = calculateBMI();
//        String goal = "";
//        try {
//            String sql = "SELECT goal_type FROM Profile WHERE user_id=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                goal = rs.getString("goal_type");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if ("gain".equalsIgnoreCase(goal)) return "Strength training with progressive overload";
//        else if ("lose".equalsIgnoreCase(goal)) return "HIIT and cardio-focused workouts";
//        else return "Balanced training with a mix of strength and cardio";
//    }
//
//    public static void main(String[] args) {
//        // Establish connection using DatabaseUtil
//        Connection connection = DatabaseUtil.connect();
//
//        if (connection != null) {
//            new DashboardPage(1, connection);
//        } else {
//            System.out.println("Failed to establish a connection with the database.");
//        }
//    }}
////version  4
//import javax.swing.*;
//import javax.swing.border.LineBorder;
//import javax.swing.border.TitledBorder;
//import java.awt.*;
//import java.sql.*;
//import java.text.SimpleDateFormat;
//
//public class DashboardPage extends JFrame {
//    private int userId;
//    private JLabel bmiLabel, bmiCategoryLabel, calorieSuggestionLabel, workoutPlanLabel;
//    private JTextField emailField, phoneField, cityField, countryField, activityLevelField, genderField, targetWeightField;
//    private JFormattedTextField dobField;
//    private JButton saveProfileButton, profileUpdateButton;
//    private Connection connection;
//
//    public DashboardPage(int userId, Connection connection) {
//        this.userId = userId;
//        this.connection = connection;
//        setTitle("User Dashboard");
//        setSize(900, 700);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // Main layout
//        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
//        mainPanel.setBackground(new Color(245, 245, 245));
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//        // Top Panel (Profile Info and Actions)
//        JPanel profilePanel = createProfilePanel();
//        profilePanel.setPreferredSize(new Dimension(400, 300));
//
//        // Panels for BMI, Nutrition, Workout
//        JPanel bmiPanel = createBMIPanel();
//        JPanel nutritionPanel = createNutritionPanel();
//        JPanel workoutPanel = createWorkoutPanel();
//
//        // Container for bottom panels
//        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 10, 0));
//        bottomPanel.add(bmiPanel);
//        bottomPanel.add(nutritionPanel);
//        bottomPanel.add(workoutPanel);
//
//        mainPanel.add(profilePanel, BorderLayout.NORTH);
//        mainPanel.add(bottomPanel, BorderLayout.CENTER);
//
//        add(mainPanel);
//        setVisible(true);
//    }
//
//    private JPanel createProfilePanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBackground(new Color(230, 230, 255));
//        panel.setBorder(BorderFactory.createTitledBorder(
//                new LineBorder(new Color(70, 130, 180), 2, true), "Profile",
//                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), new Color(70, 130, 180)
//        ));
//
//        // Input fields setup
//        emailField = createInputField("Email:");
//        phoneField = createInputField("Phone:");
//        cityField = createInputField("City:");
//        countryField = createInputField("Country:");
//        activityLevelField = createInputField("Activity Level:");
//        genderField = createInputField("Gender:");
//        targetWeightField = createInputField("Target Weight (kg):");
//        dobField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
//        dobField.setColumns(20);
//
//        // Save Button
//        saveProfileButton = new JButton("Save Changes");
//        saveProfileButton.setBackground(new Color(70, 130, 180));
//        saveProfileButton.setForeground(Color.WHITE);
//        saveProfileButton.addActionListener(e -> updateUserProfile());
//
//        // Profile Update Button
//        profileUpdateButton = new JButton("Update Profile");
//        profileUpdateButton.setBackground(new Color(58, 134, 255));
//        profileUpdateButton.setForeground(Color.WHITE);
//        profileUpdateButton.setFont(new Font("Arial", Font.BOLD, 14));
//        profileUpdateButton.addActionListener(e -> openProfileUpdateForm(userId));
//
//        // Load user details
//        loadUserProfile();
//
//        // Add components to panel
//        panel.add(emailField);
//        panel.add(phoneField);
//        panel.add(cityField);
//        panel.add(countryField);
//        panel.add(activityLevelField);
//        panel.add(genderField);
//        panel.add(new JLabel("DOB (YYYY-MM-DD):"));
//        panel.add(dobField);
//        panel.add(targetWeightField);
//        panel.add(Box.createVerticalStrut(10)); // Spacing
//        panel.add(saveProfileButton);
//        panel.add(profileUpdateButton); // Added Update Profile Button
//
//        return panel;
//    }
//
//    private JTextField createInputField(String labelText) {
//        JLabel label = new JLabel(labelText);
//        label.setFont(new Font("Arial", Font.PLAIN, 13));
//        JTextField textField = new JTextField(20);
//        textField.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        JPanel fieldPanel = new JPanel(new BorderLayout());
//        fieldPanel.setBackground(new Color(230, 230, 255));
//        fieldPanel.add(label, BorderLayout.WEST);
//        fieldPanel.add(textField, BorderLayout.CENTER);
//
//        return textField;
//    }
//
//    private JPanel createBMIPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBackground(Color.WHITE);
//        panel.setBorder(BorderFactory.createTitledBorder(
//                new LineBorder(new Color(60, 179, 113), 2, true), "BMI",
//                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), new Color(60, 179, 113)
//        ));
//
//        double bmi = calculateBMI();
//        String category = classifyBMI(bmi);
//
//        bmiLabel = new JLabel("BMI: " + String.format("%.2f", bmi));
//        bmiCategoryLabel = new JLabel("Category: " + category);
//
//        bmiLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//        bmiCategoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        panel.add(bmiLabel);
//        panel.add(bmiCategoryLabel);
//
//        return panel;
//    }
//
//    private JPanel createNutritionPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBackground(Color.WHITE);
//        panel.setBorder(BorderFactory.createTitledBorder(
//                new LineBorder(new Color(255, 165, 0), 2, true), "Nutrition Suggestions",
//                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), new Color(255, 165, 0)
//        ));
//
//        String calorieSuggestion = getCalorieSuggestion();
//        calorieSuggestionLabel = new JLabel("Suggested Caloric Intake: " + calorieSuggestion);
//        calorieSuggestionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        panel.add(calorieSuggestionLabel);
//
//        return panel;
//    }
//
//    private JPanel createWorkoutPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBackground(Color.WHITE);
//        panel.setBorder(BorderFactory.createTitledBorder(
//                new LineBorder(new Color(128, 128, 255), 2, true), "Workout Plan",
//                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), new Color(128, 128, 255)
//        ));
//
//        String workoutPlan = getWorkoutPlan();
//        workoutPlanLabel = new JLabel("Workout Plan: " + workoutPlan);
//        workoutPlanLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        panel.add(workoutPlanLabel);
//
//        return panel;
//    }
//
//    private double calculateBMI() {
//        // Placeholder for BMI calculation
//        return 24.5;  // Example BMI, should be calculated based on height and weight from the profile
//    }
//
//    private String classifyBMI(double bmi) {
//        if (bmi < 18.5) return "Underweight";
//        else if (bmi < 24.9) return "Normal weight";
//        else if (bmi < 29.9) return "Overweight";
//        else return "Obesity";
//    }
//
//    private void loadUserProfile() {
//        try {
//            String sql = "SELECT email, phone, city, country, dob, activity_level, gender, target_weight FROM Profile WHERE user_id=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                emailField.setText(rs.getString("email"));
//                phoneField.setText(rs.getString("phone"));
//                cityField.setText(rs.getString("city"));
//                countryField.setText(rs.getString("country"));
//                dobField.setText(rs.getString("dob"));
//                activityLevelField.setText(rs.getString("activity_level"));
//                genderField.setText(rs.getString("gender"));
//                targetWeightField.setText(rs.getString("target_weight"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void updateUserProfile() {
//        try {
//            String sql = "UPDATE Profile SET email=?, phone=?, city=?, country=?, dob=?, activity_level=?, gender=?, target_weight=? WHERE user_id=?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setString(1, emailField.getText());
//            stmt.setString(2, phoneField.getText());
//            stmt.setString(3, cityField.getText());
//            stmt.setString(4, countryField.getText());
//            stmt.setString(5, dobField.getText());
//            stmt.setString(6, activityLevelField.getText());
//            stmt.setString(7, genderField.getText());
//            stmt.setString(8, targetWeightField.getText());
//            stmt.setInt(9, userId);
//            stmt.executeUpdate();
//            JOptionPane.showMessageDialog(this, "Profile updated successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void openProfileUpdateForm(int userId) {
//        // Code to open Profile Update page, passing userId for reference
//        // Example: new ProfileUpdateForm(userId).setVisible(true);
//    }
//
//    private String getCalorieSuggestion() {
//        // Placeholder for personalized calorie suggestion
//        return "2200 kcal";  // Example value
//    }
//
//    private String getWorkoutPlan() {
//        // Placeholder for personalized workout plan
//        return "Moderate Intensity 5x/week";  // Example value
//    }
//}
// version 5
/*import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class DashboardPage extends JFrame {
    private int userId;
    private Connection connection;

    public DashboardPage(int userId, Connection connection) {
        this.userId = userId;
        this.connection = connection;
        setTitle("Dashboard - MyFitnessApp");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        // Sidebar panel
        JPanel sidebarPanel = createSidebarPanel();
        sidebarPanel.setPreferredSize(new Dimension(250, getHeight()));

        // Dashboard content panel
        JPanel contentPanel = createContentPanel();

        // Add panels to main panel
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createSidebarPanel() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(60, 63, 65));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel logoLabel = new JLabel("MyFitnessApp");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 22));
        logoLabel.setForeground(new Color(200, 200, 200));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(logoLabel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30))); // Space below logo

        // Sidebar buttons with icons
        JButton profileButton = createSidebarButton("Profile", "user_icon.png");
        JButton bmiButton = createSidebarButton("BMI", "bmi_icon.png");
        JButton nutritionButton = createSidebarButton("Nutrition", "nutrition_icon.png");
        JButton workoutButton = createSidebarButton("Workout", "workout_icon.png");

        sidebar.add(profileButton);
        sidebar.add(bmiButton);
        sidebar.add(nutritionButton);
        sidebar.add(workoutButton);

        return sidebar;
    }

    private JButton createSidebarButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(iconPath));
        button.setMaximumSize(new Dimension(200, 50));
        button.setBackground(new Color(80, 80, 80));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(15);
        button.setBorder(new EmptyBorder(10, 20, 10, 10));
        button.setFocusPainted(false);
        return button;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(240, 240, 240));

        JPanel profilePanel = createCardPanel("User Profile", new Color(60, 179, 113));
        JPanel bmiPanel = createCardPanel("BMI Calculation", new Color(30, 144, 255));
        JPanel nutritionPanel = createCardPanel("Nutrition Suggestions", new Color(255, 140, 0));
        JPanel workoutPanel = createCardPanel("Workout Plan", new Color(128, 0, 128));

        // Arrange content panels in a grid
        contentPanel.add(profilePanel);
        contentPanel.add(bmiPanel);
        contentPanel.add(nutritionPanel);
        contentPanel.add(workoutPanel);

        return contentPanel;
    }

    private JPanel createCardPanel(String title, Color color) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2, true),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(color);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea contentArea = new JTextArea("Placeholder for " + title.toLowerCase() + " content.");
        contentArea.setFont(new Font("Arial", Font.PLAIN, 14));
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setEditable(false);
        contentArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentArea.setBackground(new Color(245, 245, 245));

        cardPanel.add(titleLabel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        cardPanel.add(contentArea);

        return cardPanel;
    }

    private void loadUserProfile() {
        // Code to load user profile data from the database and set content in profile panel
        String query = "SELECT * FROM Profile WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Retrieve profile data and update the profile panel
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String dob = rs.getString("dob");
                String gender = rs.getString("gender");
                double height = rs.getDouble("height");
                double weight = rs.getDouble("weight");
                String activityLevel = rs.getString("activity_level");
                String goalType = rs.getString("goal_type");
                double targetWeight = rs.getDouble("target_weight");

                // Use the data to populate the profile panel
                // For example, updating the contentArea of the profile card with user details
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void calculateAndDisplayBMI() {
        // Code to calculate BMI and display in BMI panel
        String query = "SELECT height, weight FROM Profile WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double height = rs.getDouble("height");
                double weight = rs.getDouble("weight");

                double bmi = weight / (height * height);
                String bmiCategory = getBMICategory(bmi);

                // Update the BMI panel with the calculated BMI and category
                // For example, setting the content of the BMI panel with the BMI value
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }

    private void displayNutritionSuggestions() {
        // Code to display personalized nutrition suggestions based on the user's goal
        String query = "SELECT goal_type FROM Profile WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String goalType = rs.getString("goal_type");

                // Based on goal type, display nutrition suggestions
                String nutritionSuggestions = getNutritionSuggestions(goalType);

                // Update the nutrition panel with the suggestions
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getNutritionSuggestions(String goalType) {
        if ("gain".equalsIgnoreCase(goalType)) {
            return "High-protein diet recommended.";
        } else if ("lose".equalsIgnoreCase(goalType)) {
            return "Calorie deficit diet recommended.";
        } else {
            return "Balanced diet recommended.";
        }
    }

    private void displayWorkoutPlan() {
        // Code to display personalized workout plan based on user's profile
        String query = "SELECT activity_level FROM Profile WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String activityLevel = rs.getString("activity_level");

                // Based on activity level, display workout plan
                String workoutPlan = getWorkoutPlan(activityLevel);

                // Update the workout panel with the workout plan
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getWorkoutPlan(String activityLevel) {
        if ("low".equalsIgnoreCase(activityLevel)) {
            return "Light workout plan.";
        } else if ("medium".equalsIgnoreCase(activityLevel)) {
            return "Moderate workout plan.";
        } else {
            return "Intense workout plan.";
        }
    }

    public static void main(String[] args) {
        // Example usage with a dummy user ID and database connection
        int userId = 1;
        Connection connection = null; // Replace with actual database connection
        new DashboardPage(userId, connection);
    }
}*/
//version 6

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

public class DashboardPage extends JFrame {
    private int userId;
    private Connection connection;

    // Declare JTextArea for each panel's content
    private JTextArea profileContentArea, bmiContentArea, nutritionContentArea, workoutContentArea;

    public DashboardPage(int userId, Connection connection) {
        this.userId = userId;
        this.connection = connection;
        setTitle("Dashboard - MyFitnessApp");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        // Sidebar panel
        JPanel sidebarPanel = createSidebarPanel();
        sidebarPanel.setPreferredSize(new Dimension(250, getHeight()));

        // Dashboard content panel
        JPanel contentPanel = createContentPanel();

        // Add panels to main panel
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);

        // Load user data into panels
        loadUserProfile();
        calculateAndDisplayBMI();
        displayNutritionSuggestions();
        displayWorkoutPlan();
    }
    private JPanel createSidebarPanel() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(60, 63, 65));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel logoLabel = new JLabel("<html>Welcome to<br>MyFitnessApp</html>");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 22));
        logoLabel.setForeground(new Color(200, 200, 200));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(logoLabel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30))); // Space below logo

        // Add all sidebar buttons
        sidebar.add(createSidebarButton("Profile", "user_icon.png"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createSidebarButton("BMI", "bmi_icon.png"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createSidebarButton("Nutrition", "nutrition_icon.png"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createSidebarButton("Workout", "workout_icon.png"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer before Update Profile button

        // Profile Update Button
        JButton updateProfileButton = createSidebarActionButton("Update Profile", e -> openProfileUpdatePage());
        sidebar.add(updateProfileButton);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer

        // Logout Button
        JButton logoutButton = createSidebarActionButton("Logout", e -> {
            new StylishLoginPage().setVisible(true); // Open login page
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(sidebar);
            if (topFrame != null) {
                topFrame.dispose(); // Close the dashboard window
            }
        });
        sidebar.add(logoutButton);

        return sidebar;
    }

    // Helper function to style and create action buttons
    private JButton createSidebarActionButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(200, 50));
        button.setBackground(new Color(80, 80, 80));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(15);
        button.setBorder(new EmptyBorder(10, 20, 10, 10));
        button.setFocusPainted(false);
        button.addActionListener(action);
        return button;
    }


    private JButton createSidebarButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(iconPath));
        button.setMaximumSize(new Dimension(200, 50));
        button.setBackground(new Color(80, 80, 80));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(15);
        button.setBorder(new EmptyBorder(10, 20, 10, 10));
        button.setFocusPainted(false);
        return button;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(240, 240, 240));

        JPanel profilePanel = createCardPanel("User Profile", new Color(60, 179, 113));
        profileContentArea = (JTextArea) profilePanel.getComponent(2);  // Assigning the text area for updating
        JPanel bmiPanel = createCardPanel("BMI Calculation", new Color(30, 144, 255));
        bmiContentArea = (JTextArea) bmiPanel.getComponent(2);
        JPanel nutritionPanel = createCardPanel("Nutrition Suggestions", new Color(255, 140, 0));
        nutritionContentArea = (JTextArea) nutritionPanel.getComponent(2);
        JPanel workoutPanel = createCardPanel("Workout Plan", new Color(128, 0, 128));
        workoutContentArea = (JTextArea) workoutPanel.getComponent(2);

        contentPanel.add(profilePanel);
        contentPanel.add(bmiPanel);
        contentPanel.add(nutritionPanel);
        contentPanel.add(workoutPanel);

        return contentPanel;
    }

    private JPanel createCardPanel(String title, Color color) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2, true),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(color);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea contentArea = new JTextArea("Loading " + title.toLowerCase() + "...");
        contentArea.setFont(new Font("Arial", Font.PLAIN, 14));
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setEditable(false);
        contentArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentArea.setBackground(new Color(245, 245, 245));

        cardPanel.add(titleLabel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        cardPanel.add(contentArea);

        return cardPanel;
    }


private void loadUserProfile() {
    String userQuery = "SELECT username FROM Users WHERE user_id = ?";
    String profileQuery = "SELECT gender, dob FROM Profile WHERE user_id = ?";

    try (PreparedStatement userStmt = connection.prepareStatement(userQuery);
         PreparedStatement profileStmt = connection.prepareStatement(profileQuery)) {

        // Set the user_id for both queries
        userStmt.setInt(1, userId);
        profileStmt.setInt(1, userId);

        // Execute the user query to get the username
        ResultSet userRs = userStmt.executeQuery();
        String username = "";
        if (userRs.next()) {
            username = userRs.getString("username");
        }

        // Execute the profile query to get the gender and dob
        ResultSet profileRs = profileStmt.executeQuery();
        String gender = "";
        String dob = "";
        if (profileRs.next()) {
            gender = profileRs.getString("gender");
            dob = profileRs.getString("dob");
        }

        // Calculate age from dob
        int age = calculateAge(dob);

        // Build the profile info string with the required fields: Name, Gender, Age
        String profileInfo = "Name: " + username + "\n"
                + "Gender: " + gender + "\n"
                + "Age: " + age + " years";

        // Set the profile information to the profile content area
        profileContentArea.setText(profileInfo);



    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private int calculateAge(String dob) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = sdf.parse(dob);
            Calendar birthCalendar = Calendar.getInstance();
            birthCalendar.setTime(birthDate);

            int birthYear = birthCalendar.get(Calendar.YEAR);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int age = currentYear - birthYear;

            // Check if the birthday has passed this year, if not subtract 1 from age
            int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
            int birthMonth = birthCalendar.get(Calendar.MONTH);
            if (currentMonth < birthMonth || (currentMonth == birthMonth && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH))) {
                age--;
            }
            return age;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void openProfileUpdatePage() {
        // Implement the logic to open the profile update page
        Connection connection = DatabaseUtil.connect();
        // You can use a new frame or dialog to open the update form
        ProfileUpdatePage updatePage = new ProfileUpdatePage(userId,connection);  // Assuming you have a ProfileUpdatePage class
        updatePage.setVisible(true);
    }


    private void calculateAndDisplayBMI() {
        String query = "SELECT height_cm, weight_kg FROM Profile WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double height = rs.getDouble("height_cm");
                double weight = rs.getDouble("weight_kg");
                height =height/100;
                double bmi = weight / (height * height);
                String bmiCategory = getBMICategory(bmi);

                bmiContentArea.setText("BMI: " + new DecimalFormat("#.##").format(bmi) + "\nCategory: " + bmiCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 24.9) return "Normal weight";
        else if (bmi < 29.9) return "Overweight";
        else return "Obesity";
    }

    private void displayNutritionSuggestions() {
        String query = "SELECT goal_type FROM Profile WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String goalType = rs.getString("goal_type");
                nutritionContentArea.setText(getNutritionSuggestions(goalType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

private String getNutritionSuggestions(String goalType) {
    StringBuilder nutritionPlan = new StringBuilder();

    if (goalType.equalsIgnoreCase("gain")) {
        nutritionPlan.append("Goal: Weight Gain\n\n");
        nutritionPlan.append("Meal Plan:\n");
        nutritionPlan.append("Breakfast: Poha with peanuts and vegetables\n");
        nutritionPlan.append(" - 2 slices of whole wheat bread with butter\n");
        nutritionPlan.append("Lunch: Brown rice with dal and vegetable curry\n");
        nutritionPlan.append(" - A serving of chicken curry or paneer tikka\n");
        nutritionPlan.append("Snack: Greek yogurt with honey and nuts\n");
        nutritionPlan.append("Dinner: Grilled fish with quinoa and vegetables\n");
        nutritionPlan.append("Recommended Drinks: Whole milk, protein shakes\n");
        nutritionPlan.append("Macronutrients: 60% Carbs, 30% Protein, 10% Fats\n");
    } else if (goalType.equalsIgnoreCase("lose")) {
        nutritionPlan.append("Goal: Weight Loss\n\n");
        nutritionPlan.append("Meal Plan:\n");
        nutritionPlan.append("Breakfast: Oats porridge with almonds and chia seeds\n");
        nutritionPlan.append(" - 1 boiled egg\n");
        nutritionPlan.append("Lunch: Mixed vegetable salad with grilled chicken or tofu\n");
        nutritionPlan.append("Snack: Apple with peanut butter\n");
        nutritionPlan.append("Dinner: Baked fish with sautéed spinach\n");
        nutritionPlan.append("Recommended Drinks: Water, green tea\n");
        nutritionPlan.append("Macronutrients: 40% Carbs, 40% Protein, 20% Fats\n");
    } else {
        nutritionPlan.append("Goal: Maintain Weight\n\n");
        nutritionPlan.append("Meal Plan:\n");
        nutritionPlan.append("Breakfast: Rava upma with vegetables\n");
        nutritionPlan.append("Lunch: Whole wheat chapati with dal and vegetable sabzi\n");
        nutritionPlan.append("Snack: Mixed nuts with green tea\n");
        nutritionPlan.append("Dinner: Vegetable pulao with raita\n");
        nutritionPlan.append("Recommended Drinks: Water, buttermilk\n");
        nutritionPlan.append("Macronutrients: 40% Carbs, 30% Protein, 30% Fats\n");
    }

    return nutritionPlan.toString();
}


    private void displayWorkoutPlan() {
        String query = "SELECT activity_level, goal_type FROM Profile WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String activityLevel = rs.getString("activity_level");
                String goalType = rs.getString("goal_type");
                workoutContentArea.setText(getWorkoutPlan(activityLevel, goalType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


private String getWorkoutPlan(String activityLevel, String goalType) {
    StringBuilder workoutPlan = new StringBuilder();

    if (goalType.equalsIgnoreCase("gain")) {
        workoutPlan.append("Goal: Weight Gain\n\n");
        if (activityLevel.equalsIgnoreCase("high")) {
            workoutPlan.append("Day 1: Full Body Strength\n - Squats: 4x8-10, Deadlifts: 4x6-8\n");
            workoutPlan.append(" - Bench Press: 4x8-10, Rows: 3x10\n");
            workoutPlan.append(" - Overhead Press: 3x8\n");
            workoutPlan.append("Day 2: Cardio + Core\n - HIIT: 30 min, Plank: 3x1 min\n");
            workoutPlan.append(" - Russian Twists: 3x15, Leg Raises: 3x15\n");
            workoutPlan.append("Day 3: Upper Body Strength\n - Pull-ups: 4x8, Bicep Curls: 4x10\n");
            workoutPlan.append(" - Dips: 3x10, Shoulder Press: 4x8\n");
        } else {
            workoutPlan.append("Day 1: Full Body Strength\n - Squats: 3x8-10, Push-ups: 3x15\n");
            workoutPlan.append(" - Dumbbell Rows: 3x10\n");
            workoutPlan.append("Day 2: Cardio\n - Jogging: 30 min\n");
            workoutPlan.append("Day 3: Upper Body Strength\n - Dumbbell Bench Press: 3x10\n");
            workoutPlan.append(" - Triceps Dips: 3x10, Dumbbell Rows: 3x10\n");
        }
    } else if (goalType.equalsIgnoreCase("lose")) {
        workoutPlan.append("Goal: Weight Loss\n\n");
        if (activityLevel.equalsIgnoreCase("high")) {
            workoutPlan.append("Day 1: HIIT\n - Sprint Intervals: 30 sec sprint, 30 sec rest, 10 rounds\n");
            workoutPlan.append(" - Jump Squats: 3x15, Mountain Climbers: 3x20\n");
            workoutPlan.append("Day 2: Core\n - Plank: 3x1 min, Bicycle Crunches: 3x20\n");
            workoutPlan.append(" - Leg Raises: 3x15\n");
            workoutPlan.append("Day 3: Cardio\n - Jogging: 45 min\n");
        } else {
            workoutPlan.append("Day 1: Cardio + Full Body\n - Walk: 30 min, Squats: 3x10\n");
            workoutPlan.append(" - Push-ups: 3x10\n");
            workoutPlan.append("Day 2: Cardio\n - Cycling: 30 min\n");
        }
    } else {
        workoutPlan.append("Goal: Maintain Weight\n\n");
        workoutPlan.append("Day 1: Full Body Strength\n - Push-ups: 3x12, Squats: 3x12\n");
        workoutPlan.append(" - Lunges: 3x12\n");
        workoutPlan.append("Day 2: Cardio\n - Jogging: 30 min\n");
        workoutPlan.append("Day 3: Yoga\n - Sun Salutations: 10 rounds\n");
        workoutPlan.append(" - Downward Dog: 3x1 min hold\n");
    }

    return workoutPlan.toString();
}


}
