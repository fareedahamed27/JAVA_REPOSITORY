//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class ProfileCreationPage extends JFrame {
//    private JTextField emailField, phoneField, cityField, countryField, dobField, heightField, weightField, targetWeightField;
//    private JComboBox<String> genderBox, activityLevelBox, goalTypeBox;
//    private JButton saveProfileButton;
//
//    public ProfileCreationPage() {
//        setTitle("Create Your Profile");
//        setSize(500, 700);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // Header
//        JLabel headerLabel = new JLabel("Profile Setup", SwingConstants.CENTER);
//        headerLabel.setFont(new Font("Arial", Font.BOLD, 26));
//        headerLabel.setForeground(new Color(34, 167, 240));
//        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
//        add(headerLabel, BorderLayout.NORTH);
//
//        // Center Panel for form
//        JPanel formPanel = new JPanel();
//        formPanel.setLayout(new GridBagLayout());
//        formPanel.setBackground(Color.WHITE);
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        // Helper method to create styled labels
//        Font labelFont = new Font("Arial", Font.BOLD, 16);
//        Color labelColor = new Color(34, 167, 240);
//
//        // Create fields with labels
//        emailField = createStyledTextField("Email", labelFont, labelColor, gbc, formPanel);
//        phoneField = createStyledTextField("Phone", labelFont, labelColor, gbc, formPanel);
//        cityField = createStyledTextField("City", labelFont, labelColor, gbc, formPanel);
//        countryField = createStyledTextField("Country", labelFont, labelColor, gbc, formPanel);
//        dobField = createStyledTextField("Date of Birth (YYYY-MM-DD)", labelFont, labelColor, gbc, formPanel);
//        heightField = createStyledTextField("Height (cm)", labelFont, labelColor, gbc, formPanel);
//        weightField = createStyledTextField("Weight (kg)", labelFont, labelColor, gbc, formPanel);
//        targetWeightField = createStyledTextField("Target Weight (kg)", labelFont, labelColor, gbc, formPanel);
//
//        // Gender dropdown
//        JLabel genderLabel = new JLabel("Gender");
//        genderLabel.setFont(labelFont);
//        genderLabel.setForeground(labelColor);
//        gbc.gridx = 0;
//        gbc.gridy++;
//        formPanel.add(genderLabel, gbc);
//
//        genderBox = new JComboBox<>(new String[]{"M", "F", "Other"});
//        genderBox.setFont(new Font("Arial", Font.PLAIN, 14));
//        genderBox.setPreferredSize(new Dimension(200, 30));
//        gbc.gridx = 1;
//        formPanel.add(genderBox, gbc);
//
//        // Activity Level dropdown
//        JLabel activityLabel = new JLabel("Activity Level");
//        activityLabel.setFont(labelFont);
//        activityLabel.setForeground(labelColor);
//        gbc.gridx = 0;
//        gbc.gridy++;
//        formPanel.add(activityLabel, gbc);
//
//        activityLevelBox = new JComboBox<>(new String[]{"Low", "Medium", "High"});
//        activityLevelBox.setFont(new Font("Arial", Font.PLAIN, 14));
//        activityLevelBox.setPreferredSize(new Dimension(200, 30));
//        gbc.gridx = 1;
//        formPanel.add(activityLevelBox, gbc);
//
//        // Goal Type dropdown
//        JLabel goalLabel = new JLabel("Goal Type");
//        goalLabel.setFont(labelFont);
//        goalLabel.setForeground(labelColor);
//        gbc.gridx = 0;
//        gbc.gridy++;
//        formPanel.add(goalLabel, gbc);
//
//        goalTypeBox = new JComboBox<>(new String[]{"Gain", "Lose"});
//        goalTypeBox.setFont(new Font("Arial", Font.PLAIN, 14));
//        goalTypeBox.setPreferredSize(new Dimension(200, 30));
//        gbc.gridx = 1;
//        formPanel.add(goalTypeBox, gbc);
//
//        // Save Profile button
//        saveProfileButton = new JButton("Save Profile");
//        saveProfileButton.setFont(new Font("Arial", Font.BOLD, 18));
//        saveProfileButton.setBackground(new Color(34, 167, 240));
//        saveProfileButton.setForeground(Color.WHITE);
//        saveProfileButton.setFocusPainted(false);
//        saveProfileButton.setPreferredSize(new Dimension(200, 50));
//        saveProfileButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                saveProfile();
//            }
//        });
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        gbc.gridwidth = 2;
//        gbc.anchor = GridBagConstraints.CENTER;
//        formPanel.add(saveProfileButton, gbc);
//
//        add(formPanel, BorderLayout.CENTER);
//    }
//
//    private JTextField createStyledTextField(String placeholder, Font labelFont, Color labelColor, GridBagConstraints gbc, JPanel panel) {
//        JLabel label = new JLabel(placeholder);
//        label.setFont(labelFont);
//        label.setForeground(labelColor);
//        gbc.gridx = 0;
//        gbc.gridy++;
//        panel.add(label, gbc);
//
//        JTextField textField = new JTextField(20);
//        textField.setFont(new Font("Arial", Font.PLAIN, 14));
//        textField.setBorder(BorderFactory.createLineBorder(new Color(34, 167, 240), 2));
//        textField.setPreferredSize(new Dimension(250, 30));
//        gbc.gridx = 1;
//        panel.add(textField, gbc);
//
//        return textField;
//    }
//
//    private void saveProfile() {
//        // Save profile data here
//        // You can access data from fields like emailField.getText(), genderBox.getSelectedItem(), etc.
//        JOptionPane.showMessageDialog(this, "Profile saved successfully!");
//        // Redirect to Dashboard or next page
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new ProfileCreationPage().setVisible(true));
//    }
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class ProfileCreationPage extends JFrame {
    private JTextField emailField, phoneField, cityField, countryField, dobField, heightField, weightField, targetWeightField;
    private JComboBox<String> genderBox, activityLevelBox, goalTypeBox;
    private JButton saveProfileButton;
    private int userId = 1; // Replace with actual user ID as needed

    public ProfileCreationPage(int userId) {
        this.userId = userId;
        setTitle("Complete Your Profile");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Profile Setup", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 26));
        headerLabel.setForeground(new Color(34, 167, 240));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(headerLabel, BorderLayout.NORTH);

        // Center Panel for form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Helper method to create styled labels
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Color labelColor = new Color(34, 167, 240);

        // Create fields with labels
        emailField = createStyledTextField("Email", labelFont, labelColor, gbc, formPanel);
        phoneField = createStyledTextField("Phone", labelFont, labelColor, gbc, formPanel);
        cityField = createStyledTextField("City", labelFont, labelColor, gbc, formPanel);
        countryField = createStyledTextField("Country", labelFont, labelColor, gbc, formPanel);
        dobField = createStyledTextField("Date of Birth (YYYY-MM-DD)", labelFont, labelColor, gbc, formPanel);
        heightField = createStyledTextField("Height (cm)", labelFont, labelColor, gbc, formPanel);
        weightField = createStyledTextField("Weight (kg)", labelFont, labelColor, gbc, formPanel);
        targetWeightField = createStyledTextField("Target Weight (kg)", labelFont, labelColor, gbc, formPanel);

        // Gender dropdown
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(labelFont);
        genderLabel.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(genderLabel, gbc);

        genderBox = new JComboBox<>(new String[]{"M", "F", "Other"});
        genderBox.setFont(new Font("Arial", Font.PLAIN, 14));
        genderBox.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        formPanel.add(genderBox, gbc);

        // Activity Level dropdown
        JLabel activityLabel = new JLabel("Activity Level");
        activityLabel.setFont(labelFont);
        activityLabel.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(activityLabel, gbc);

        activityLevelBox = new JComboBox<>(new String[]{"Low", "Medium", "High"});
        activityLevelBox.setFont(new Font("Arial", Font.PLAIN, 14));
        activityLevelBox.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        formPanel.add(activityLevelBox, gbc);

        // Goal Type dropdown
        JLabel goalLabel = new JLabel("Goal Type");
        goalLabel.setFont(labelFont);
        goalLabel.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(goalLabel, gbc);

        goalTypeBox = new JComboBox<>(new String[]{"Gain", "Lose","Maintain"});
        goalTypeBox.setFont(new Font("Arial", Font.PLAIN, 14));
        goalTypeBox.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        formPanel.add(goalTypeBox, gbc);

        // Save Profile button
        saveProfileButton = new JButton("Save Profile");
        saveProfileButton.setFont(new Font("Arial", Font.BOLD, 18));
        saveProfileButton.setBackground(new Color(34, 167, 240));
        saveProfileButton.setForeground(Color.WHITE);
        saveProfileButton.setFocusPainted(false);
        saveProfileButton.setPreferredSize(new Dimension(200, 50));
        saveProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProfile();
            }
        });

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(saveProfileButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private JTextField createStyledTextField(String placeholder, Font labelFont, Color labelColor, GridBagConstraints gbc, JPanel panel) {
        JLabel label = new JLabel(placeholder);
        label.setFont(labelFont);
        label.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label, gbc);

        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(34, 167, 240), 2));
        textField.setPreferredSize(new Dimension(250, 30));
        gbc.gridx = 1;
        panel.add(textField, gbc);

        return textField;
    }

    private void saveProfile() {
        // Validate if fields are filled
        if (emailField.getText().isEmpty() || phoneField.getText().isEmpty() || cityField.getText().isEmpty() ||
                countryField.getText().isEmpty() || dobField.getText().isEmpty() || heightField.getText().isEmpty() ||
                weightField.getText().isEmpty() || targetWeightField.getText().isEmpty() ||
                genderBox.getSelectedItem() == null || activityLevelBox.getSelectedItem() == null || goalTypeBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please fill in all the details.", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!phoneField.getText().matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Phone number must be exactly 10 digits.", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String city = cityField.getText().trim();
            String country = countryField.getText().trim();
            Date dob = Date.valueOf(dobField.getText().trim()); // Parse date
            String gender = (String) genderBox.getSelectedItem();
            float height = Float.parseFloat(heightField.getText().trim());
            float weight = Float.parseFloat(weightField.getText().trim());
            String activityLevel = (String) activityLevelBox.getSelectedItem();
            String goalType = (String) goalTypeBox.getSelectedItem();
            float targetWeight = Float.parseFloat(targetWeightField.getText().trim());

            // Call DatabaseUtil to save the profile
            boolean isSuccess = DatabaseUtil.insertProfile(userId, email, phone, city, country, dob, gender, height, weight, activityLevel, goalType, targetWeight);

            if (isSuccess) {
                JOptionPane.showMessageDialog(this, "Profile saved successfully!");

                dispose();
                new StylishLoginPage().setVisible(true);
                // Proceed to dashboard or next step
            } else {
                JOptionPane.showMessageDialog(this, "Error saving profile. Please try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }


}
