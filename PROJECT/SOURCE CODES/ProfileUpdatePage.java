////import javax.swing.*;
////import java.awt.*;
////import java.awt.event.ActionEvent;
////import java.awt.event.ActionListener;
////import java.sql.Connection;
//import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////import java.sql.SQLException;
////
////public class ProfileUpdatePage extends JFrame {
////    private JTextField emailField, phoneField, cityField, countryField, heightField, weightField, targetWeightField;
////    private JComboBox<String> activityLevelBox, goalTypeBox, genderBox;
////    private JButton updateButton;
////    private int userId;
////    private Connection connection;
////
////    public ProfileUpdatePage(int userId, Connection connection) {
////        this.userId = userId;
////        this.connection = connection;
////
////        setTitle("Update Profile");
////        setSize(400, 600);
////        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
////        setLocationRelativeTo(null);
////        setLayout(new BorderLayout());
////
////        // Header Label
////        JLabel headerLabel = new JLabel("Update Profile", SwingConstants.CENTER);
////        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
////        headerLabel.setForeground(new Color(58, 134, 255));
////        add(headerLabel, BorderLayout.NORTH);
////
////        // Center Panel for form
////        JPanel formPanel = new JPanel();
////        formPanel.setLayout(new GridBagLayout());
////        formPanel.setBackground(Color.WHITE);
////
////        GridBagConstraints gbc = new GridBagConstraints();
////        gbc.insets = new Insets(10, 10, 10, 10);
////        gbc.fill = GridBagConstraints.HORIZONTAL;
////
////        // Load current user profile data
////        loadUserProfileData(formPanel, gbc);
////
////        // Update Button
////        updateButton = new JButton("Update Profile");
////        updateButton.setFont(new Font("Arial", Font.BOLD, 16));
////        updateButton.setBackground(new Color(58, 134, 255));
////        updateButton.setForeground(Color.WHITE);
////        updateButton.setFocusPainted(false);
////        gbc.gridx = 0;
////        gbc.gridy = 10;
////        gbc.gridwidth = 2;
////        formPanel.add(updateButton, gbc);
////
////        add(formPanel, BorderLayout.CENTER);
////
////        // Action Listeners
////        updateButton.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                updateProfile();
////            }
////        });
////    }
////
////    private void loadUserProfileData(JPanel formPanel, GridBagConstraints gbc) {
////        String query = "SELECT email, phone, city, country, gender, height_cm, weight_kg, activity_level, goal_type, target_weight FROM Profile WHERE user_id = ?";
////        try (PreparedStatement stmt = connection.prepareStatement(query)) {
////            stmt.setInt(1, userId);
////            ResultSet rs = stmt.executeQuery();
////
////            if (rs.next()) {
////                // Email Field
////                addLabelAndField(formPanel, gbc, "Email:", emailField = new JTextField(rs.getString("email")), 0, 0);
////                // Phone Field
////                addLabelAndField(formPanel, gbc, "Phone:", phoneField = new JTextField(rs.getString("phone")), 0, 1);
////                // City Field
////                addLabelAndField(formPanel, gbc, "City:", cityField = new JTextField(rs.getString("city")), 0, 2);
////                // Country Field
////                addLabelAndField(formPanel, gbc, "Country:", countryField = new JTextField(rs.getString("country")), 0, 3);
////                // Gender Combo Box
////                addLabelAndComboBox(formPanel, gbc, "Gender:", genderBox = new JComboBox<>(new String[]{"M", "F"}), 0, 4, rs.getString("gender"));
////                // Height Field
////                addLabelAndField(formPanel, gbc, "Height (cm):", heightField = new JTextField(String.valueOf(rs.getDouble("height_cm"))), 0, 5);
////                // Weight Field
////                addLabelAndField(formPanel, gbc, "Weight (kg):", weightField = new JTextField(String.valueOf(rs.getDouble("weight_kg"))), 0, 6);
////                // Activity Level Combo Box
////                addLabelAndComboBox(formPanel, gbc, "Activity Level:", activityLevelBox = new JComboBox<>(new String[]{"Low", "Medium", "High"}), 0, 7, rs.getString("activity_level"));
////                // Goal Type Combo Box
////                addLabelAndComboBox(formPanel, gbc, "Goal Type:", goalTypeBox = new JComboBox<>(new String[]{"Gain", "Lose", "Maintain"}), 0, 8, rs.getString("goal_type"));
////                // Target Weight Field
////                addLabelAndField(formPanel, gbc, "Target Weight (kg):", targetWeightField = new JTextField(String.valueOf(rs.getDouble("target_weight"))), 0, 9);
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////    }
////
////    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String label, JTextField field, int x, int y) {
////        JLabel jLabel = new JLabel(label);
////        jLabel.setFont(new Font("Arial", Font.PLAIN, 16));
////        jLabel.setForeground(new Color(58, 134, 255));
////        gbc.gridx = x;
////        gbc.gridy = y;
////        panel.add(jLabel, gbc);
////
////        field.setFont(new Font("Arial", Font.PLAIN, 14));
////        field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(58, 134, 255)));
////        gbc.gridx = x + 1;
////        panel.add(field, gbc);
////    }
////
////    private void addLabelAndComboBox(JPanel panel, GridBagConstraints gbc, String label, JComboBox<String> comboBox, int x, int y, String selectedValue) {
////        JLabel jLabel = new JLabel(label);
////        jLabel.setFont(new Font("Arial", Font.PLAIN, 16));
////        jLabel.setForeground(new Color(58, 134, 255));
////        gbc.gridx = x;
////        gbc.gridy = y;
////        panel.add(jLabel, gbc);
////
////        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
////        comboBox.setBackground(Color.WHITE);
////        comboBox.setForeground(new Color(58, 134, 255));
////        comboBox.setSelectedItem(selectedValue);
////        gbc.gridx = x + 1;
////        panel.add(comboBox, gbc);
////    }
////
////    private void updateProfile() {
////        String email = emailField.getText();
////        String phone = phoneField.getText();
////        String city = cityField.getText();
////        String country = countryField.getText();
////        String gender = (String) genderBox.getSelectedItem();
////        double height = Double.parseDouble(heightField.getText());
////        double weight = Double.parseDouble(weightField.getText());
////        String activityLevel = (String) activityLevelBox.getSelectedItem();
////        String goalType = (String) goalTypeBox.getSelectedItem();
////        double targetWeight = Double.parseDouble(targetWeightField.getText());
////
////        String updateQuery = "UPDATE Profile SET email = ?, phone = ?, city = ?, country = ?, gender = ?, height_cm = ?, weight_kg = ?, activity_level = ?, goal_type = ?, target_weight = ? WHERE user_id = ?";
////        try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
////            stmt.setString(1, email);
////            stmt.setString(2, phone);
////            stmt.setString(3, city);
////            stmt.setString(4, country);
////            stmt.setString(5, gender);
////            stmt.setDouble(6, height);
////            stmt.setDouble(7, weight);
////            stmt.setString(8, activityLevel);
////            stmt.setString(9, goalType);
////            stmt.setDouble(10, targetWeight);
////            stmt.setInt(11, userId);
////
////            int rowsUpdated = stmt.executeUpdate();
////            if (rowsUpdated > 0) {
////                JOptionPane.showMessageDialog(this, "Profile updated successfully.");
////            } else {
////                JOptionPane.showMessageDialog(this, "Error updating profile.");
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////    }
////
////    public static void main(String[] args) {
////        // Use a mock userId and database connection for testing
////        int userId = 1;  // Example user ID
////        Connection connection = DatabaseUtil.connect();  // Example database connection
////        if (connection != null) {
////            new ProfileUpdatePage(userId, connection).setVisible(true);
////        } else {
////            JOptionPane.showMessageDialog(null, "Database connection failed.");
////        }
////    }
////}
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class ProfileUpdatePage extends JFrame {
//    private JTextField emailField, phoneField, cityField, countryField, heightField, weightField, targetWeightField;
//    private JComboBox<String> activityLevelBox, goalTypeBox, genderBox;
//    private JButton updateButton, deleteButton;
//    private int userId;
//    private Connection connection;
//
//    public ProfileUpdatePage(int userId, Connection connection) {
//        this.userId = userId;
//        this.connection = connection;
//
//        setTitle("Update Profile");
//        setSize(400, 600);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // Header Label
//        JLabel headerLabel = new JLabel("Update Profile", SwingConstants.CENTER);
//        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        headerLabel.setForeground(new Color(58, 134, 255));
//        add(headerLabel, BorderLayout.NORTH);
//
//        // Center Panel for form
//        JPanel formPanel = new JPanel();
//        formPanel.setLayout(new GridBagLayout());
//        formPanel.setBackground(Color.WHITE);
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        // Load current user profile data
//        loadUserProfileData(formPanel, gbc);
//
//        // Update Button
//        updateButton = new JButton("Update Profile");
//        updateButton.setFont(new Font("Arial", Font.BOLD, 16));
//        updateButton.setBackground(new Color(58, 134, 255));
//        updateButton.setForeground(Color.WHITE);
//        updateButton.setFocusPainted(false);
//        gbc.gridx = 0;
//        gbc.gridy = 10;
//        gbc.gridwidth = 2;
//        formPanel.add(updateButton, gbc);
//
//        // Delete Profile Button
//        deleteButton = new JButton("Delete Profile");
//        deleteButton.setFont(new Font("Arial", Font.BOLD, 16));
//        deleteButton.setBackground(new Color(255, 58, 58));
//        deleteButton.setForeground(Color.WHITE);
//        deleteButton.setFocusPainted(false);
//        gbc.gridy = 11;
//        formPanel.add(deleteButton, gbc);
//
//        add(formPanel, BorderLayout.CENTER);
//
//        // Action Listeners
//        updateButton.addActionListener(e -> updateProfile());
//        deleteButton.addActionListener(e -> confirmAndDeleteProfile());
//    }
//
//    private void loadUserProfileData(JPanel formPanel, GridBagConstraints gbc) {
//        String query = "SELECT email, phone, city, country, gender, height_cm, weight_kg, activity_level, goal_type, target_weight FROM Profile WHERE user_id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                // Email Field
//                addLabelAndField(formPanel, gbc, "Email:", emailField = new JTextField(rs.getString("email")), 0, 0);
//                // Phone Field
//                addLabelAndField(formPanel, gbc, "Phone:", phoneField = new JTextField(rs.getString("phone")), 0, 1);
//                // City Field
//                addLabelAndField(formPanel, gbc, "City:", cityField = new JTextField(rs.getString("city")), 0, 2);
//                // Country Field
//                addLabelAndField(formPanel, gbc, "Country:", countryField = new JTextField(rs.getString("country")), 0, 3);
//                // Gender Combo Box
//                addLabelAndComboBox(formPanel, gbc, "Gender:", genderBox = new JComboBox<>(new String[]{"M", "F"}), 0, 4, rs.getString("gender"));
//                // Height Field
//                addLabelAndField(formPanel, gbc, "Height (cm):", heightField = new JTextField(String.valueOf(rs.getDouble("height_cm"))), 0, 5);
//                // Weight Field
//                addLabelAndField(formPanel, gbc, "Weight (kg):", weightField = new JTextField(String.valueOf(rs.getDouble("weight_kg"))), 0, 6);
//                // Activity Level Combo Box
//                addLabelAndComboBox(formPanel, gbc, "Activity Level:", activityLevelBox = new JComboBox<>(new String[]{"Low", "Medium", "High"}), 0, 7, rs.getString("activity_level"));
//                // Goal Type Combo Box
//                addLabelAndComboBox(formPanel, gbc, "Goal Type:", goalTypeBox = new JComboBox<>(new String[]{"Gain", "Lose", "Maintain"}), 0, 8, rs.getString("goal_type"));
//                // Target Weight Field
//                addLabelAndField(formPanel, gbc, "Target Weight (kg):", targetWeightField = new JTextField(String.valueOf(rs.getDouble("target_weight"))), 0, 9);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String label, JTextField field, int x, int y) {
//        JLabel jLabel = new JLabel(label);
//        jLabel.setFont(new Font("Arial", Font.PLAIN, 16));
//        jLabel.setForeground(new Color(58, 134, 255));
//        gbc.gridx = x;
//        gbc.gridy = y;
//        panel.add(jLabel, gbc);
//
//        field.setFont(new Font("Arial", Font.PLAIN, 14));
//        field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(58, 134, 255)));
//        gbc.gridx = x + 1;
//        panel.add(field, gbc);
//    }
//
//    private void addLabelAndComboBox(JPanel panel, GridBagConstraints gbc, String label, JComboBox<String> comboBox, int x, int y, String selectedValue) {
//        JLabel jLabel = new JLabel(label);
//        jLabel.setFont(new Font("Arial", Font.PLAIN, 16));
//        jLabel.setForeground(new Color(58, 134, 255));
//        gbc.gridx = x;
//        gbc.gridy = y;
//        panel.add(jLabel, gbc);
//
//        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
//        comboBox.setBackground(Color.WHITE);
//        comboBox.setForeground(new Color(58, 134, 255));
//        comboBox.setSelectedItem(selectedValue);
//        gbc.gridx = x + 1;
//        panel.add(comboBox, gbc);
//    }
//
//    private void updateProfile() {
//        // Update profile code here
//    }
//
//    private void confirmAndDeleteProfile() {
//        int confirm = JOptionPane.showConfirmDialog(this,
//                "Are you sure you want to delete your profile? All your details will be lost.",
//                "Confirm Delete",
//                JOptionPane.YES_NO_OPTION);
//
//        if (confirm == JOptionPane.YES_OPTION) {
//            deleteProfile();
//        }
//    }
//
//    private void deleteProfile() {
//        String deleteQuery = "DELETE FROM Profile WHERE user_id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(deleteQuery)) {
//            stmt.setInt(1, userId);
//            int rowsDeleted = stmt.executeUpdate();
//            if (rowsDeleted > 0) {
//                JOptionPane.showMessageDialog(this, "Profile deleted successfully.");
//                dispose(); // Close the ProfileUpdatePage
//                new StylishLoginPage().setVisible(true); // Redirect to login page
//            } else {
//                JOptionPane.showMessageDialog(this, "Error deleting profile.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        int userId = 1; // Example user ID
//        Connection connection = DatabaseUtil.connect(); // Example database connection
//        if (connection != null) {
//            new ProfileUpdatePage(userId, connection).setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "Database connection failed.");
//        }
//    }
//}
//
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileUpdatePage extends JFrame {
    private JTextField emailField, phoneField, cityField, countryField, heightField, weightField, targetWeightField;
    private JComboBox<String> activityLevelBox, goalTypeBox, genderBox;
    private JButton updateButton, deleteProfileButton;
    private int userId;
    private Connection connection;

    public ProfileUpdatePage(int userId, Connection connection) {
        this.userId = userId;
        this.connection = connection;

        setTitle("Update Profile");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Label
        JLabel headerLabel = new JLabel("Update Profile", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(58, 134, 255));
        add(headerLabel, BorderLayout.NORTH);

        // Center Panel for form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Load current user profile data
        loadUserProfileData(formPanel, gbc);

        // Update Button
        updateButton = new JButton("Update Profile");
        updateButton.setFont(new Font("Arial", Font.BOLD, 16));
        updateButton.setBackground(new Color(58, 134, 255));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        formPanel.add(updateButton, gbc);

        // Add Delete Button
        deleteProfileButton = new JButton("Delete Profile");
        deleteProfileButton.setFont(new Font("Arial", Font.BOLD, 16));
        deleteProfileButton.setBackground(Color.RED);
        deleteProfileButton.setForeground(Color.WHITE);
        deleteProfileButton.setFocusPainted(false);
        gbc.gridy = 11;
        formPanel.add(deleteProfileButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Action Listeners
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Update button clicked"); // Debug statement
                updateProfile();
            }
        });

        deleteProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete your profile? All your details will be lost.",
                        "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteProfile();
                }
            }
        });
    }

    private void loadUserProfileData(JPanel formPanel, GridBagConstraints gbc) {
        String query = "SELECT email, phone, city, country, gender, height_cm, weight_kg, activity_level, goal_type, target_weight FROM Profile WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Email Field
                addLabelAndField(formPanel, gbc, "Email:", emailField = new JTextField(rs.getString("email")), 0, 0);
                // Phone Field
                addLabelAndField(formPanel, gbc, "Phone:", phoneField = new JTextField(rs.getString("phone")), 0, 1);
                // City Field
                addLabelAndField(formPanel, gbc, "City:", cityField = new JTextField(rs.getString("city")), 0, 2);
                // Country Field
                addLabelAndField(formPanel, gbc, "Country:", countryField = new JTextField(rs.getString("country")), 0, 3);
                // Gender Combo Box
                addLabelAndComboBox(formPanel, gbc, "Gender:", genderBox = new JComboBox<>(new String[]{"M", "F"}), 0, 4, rs.getString("gender"));
                // Height Field
                addLabelAndField(formPanel, gbc, "Height (cm):", heightField = new JTextField(String.valueOf(rs.getDouble("height_cm"))), 0, 5);
                // Weight Field
                addLabelAndField(formPanel, gbc, "Weight (kg):", weightField = new JTextField(String.valueOf(rs.getDouble("weight_kg"))), 0, 6);
                // Activity Level Combo Box
                addLabelAndComboBox(formPanel, gbc, "Activity Level:", activityLevelBox = new JComboBox<>(new String[]{"Low", "Medium", "High"}), 0, 7, rs.getString("activity_level"));
                // Goal Type Combo Box
                addLabelAndComboBox(formPanel, gbc, "Goal Type:", goalTypeBox = new JComboBox<>(new String[]{"Gain", "Lose", "Maintain"}), 0, 8, rs.getString("goal_type"));
                // Target Weight Field
                addLabelAndField(formPanel, gbc, "Target Weight (kg):", targetWeightField = new JTextField(String.valueOf(rs.getDouble("target_weight"))), 0, 9);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateProfile() {
        try {
            System.out.println("Attempting to update profile..."); // Debug statement
            String updateQuery = "UPDATE Profile SET email = ?, phone = ?, city = ?, country = ?, gender = ?, height_cm = ?, weight_kg = ?, activity_level = ?, goal_type = ?, target_weight = ? WHERE user_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
                stmt.setString(1, emailField.getText());
                stmt.setString(2, phoneField.getText());
                stmt.setString(3, cityField.getText());
                stmt.setString(4, countryField.getText());
                stmt.setString(5, (String) genderBox.getSelectedItem());
                stmt.setDouble(6, Double.parseDouble(heightField.getText()));
                stmt.setDouble(7, Double.parseDouble(weightField.getText()));
                stmt.setString(8, (String) activityLevelBox.getSelectedItem());
                stmt.setString(9, (String) goalTypeBox.getSelectedItem());
                stmt.setDouble(10, Double.parseDouble(targetWeightField.getText()));
                stmt.setInt(11, userId);

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Profile updated successfully.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error updating profile.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteProfile() {
        String deleteProfileQuery = "DELETE FROM Profile WHERE user_id = ?";
        String deleteUserQuery = "DELETE FROM Users WHERE user_id = ?";

        try (PreparedStatement profileStmt = connection.prepareStatement(deleteProfileQuery);
             PreparedStatement userStmt = connection.prepareStatement(deleteUserQuery)) {

            // Set the user ID for deletion
            profileStmt.setInt(1, userId);
            userStmt.setInt(1, userId);

            // Delete from Profile table
            int profileRowsDeleted = profileStmt.executeUpdate();

            // Delete from Users table
            int userRowsDeleted = userStmt.executeUpdate();

            // Check if both deletions were successful
            if (profileRowsDeleted > 0 && userRowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Account deleted successfully.");

                // Open the login page and close the dashboard
                new StylishLoginPage().setVisible(true); // Assuming StylishLoginPage constructor accepts the connection
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting account.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while deleting the account.");
        }
    }

    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField textField, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = x + 1;
        gbc.gridy = y;
        panel.add(textField, gbc);
    }
    private void addLabelAndComboBox(JPanel panel, GridBagConstraints gbc, String labelText, JComboBox<String> comboBox, int x, int y, String selectedValue) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = x + 1;
        gbc.gridy = y;
        panel.add(comboBox, gbc);

        if (selectedValue != null) {
            comboBox.setSelectedItem(selectedValue);
        }
    }


}
