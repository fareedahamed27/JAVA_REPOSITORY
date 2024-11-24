//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class SignUpForm extends JFrame {
//    private JTextField usernameField;
//    private JPasswordField passwordField;
//    private JPasswordField confirmPasswordField;
//    private JButton signUpButton;
//    private JLabel titleLabel;
//
//    public SignUpForm() {
//        setTitle("Create New Account");
//        setSize(400, 500);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // Title Label
//        titleLabel = new JLabel("Create Your Account", SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        titleLabel.setForeground(new Color(58, 134, 255));
//        titleLabel.setPreferredSize(new Dimension(400, 60));
//        add(titleLabel, BorderLayout.NORTH);
//
//        // Center Panel for the form
//        JPanel formPanel = new JPanel();
//        formPanel.setLayout(new GridBagLayout());
//        formPanel.setBackground(new Color(245, 245, 245));
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        // Username Label and Field
//        JLabel usernameLabel = new JLabel("Username");
//        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
//        usernameLabel.setForeground(new Color(58, 134, 255));
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        formPanel.add(usernameLabel, gbc);
//
//        usernameField = createStyledTextField();
//        gbc.gridx = 1;
//        formPanel.add(usernameField, gbc);
//
//        // Password Label and Field
//        JLabel passwordLabel = new JLabel("Password");
//        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
//        passwordLabel.setForeground(new Color(58, 134, 255));
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        formPanel.add(passwordLabel, gbc);
//
//        passwordField = createStyledPasswordField();
//        gbc.gridx = 1;
//        formPanel.add(passwordField, gbc);
//
//        // Confirm Password Label and Field
//        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
//        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
//        confirmPasswordLabel.setForeground(new Color(58, 134, 255));
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        formPanel.add(confirmPasswordLabel, gbc);
//
//        confirmPasswordField = createStyledPasswordField();
//        gbc.gridx = 1;
//        formPanel.add(confirmPasswordField, gbc);
//
//        // Sign Up Button
//        signUpButton = new JButton("Sign Up");
//        signUpButton.setFont(new Font("Arial", Font.BOLD, 18));
//        signUpButton.setBackground(new Color(58, 134, 255));
//        signUpButton.setForeground(Color.WHITE);
//        signUpButton.setFocusPainted(false);
//        signUpButton.setPreferredSize(new Dimension(150, 40));
//        signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        signUpButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                handleSignUp();
//            }
//        });
//
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        gbc.gridwidth = 2;
//        formPanel.add(signUpButton, gbc);
//
//        add(formPanel, BorderLayout.CENTER);
//    }
//
//    private JTextField createStyledTextField() {
//        JTextField textField = new JTextField(15);
//        textField.setFont(new Font("Arial", Font.PLAIN, 16));
//        textField.setForeground(new Color(58, 134, 255));
//        textField.setBackground(Color.WHITE);
//        textField.setBorder(BorderFactory.createLineBorder(new Color(58, 134, 255), 2));
//        textField.setPreferredSize(new Dimension(250, 40));
//        return textField;
//    }
//
//    private JPasswordField createStyledPasswordField() {
//        JPasswordField passwordField = new JPasswordField(15);
//        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
//        passwordField.setForeground(new Color(58, 134, 255));
//        passwordField.setBackground(Color.WHITE);
//        passwordField.setBorder(BorderFactory.createLineBorder(new Color(58, 134, 255), 2));
//        passwordField.setPreferredSize(new Dimension(250, 40));
//        return passwordField;
//    }
//
////    private void handleSignUp() {
////        // Extract input from fields
////        String username = usernameField.getText();
////        String password = new String(passwordField.getPassword());
////        String confirmPassword = new String(confirmPasswordField.getPassword());
////
////        // Basic validation (you can expand this to include stronger checks)
////        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
////            JOptionPane.showMessageDialog(this, "All fields are required.");
////            return;
////        }
////        if (!password.equals(confirmPassword)) {
////            JOptionPane.showMessageDialog(this, "Passwords do not match.");
////            return;
////        }
////
////        // (Implement database insertion here to save the new user's information)
////        JOptionPane.showMessageDialog(this, "Account created successfully!");
////
////        // Optionally, redirect back to the login page after successful signup
////        new StylishLoginPage().setVisible(true);
////        dispose(); // Close this form
////    }
//
//    private void handleSignUp() {
//        // Extract input from fields
//        String username = usernameField.getText();
//        String password = new String(passwordField.getPassword());
//        String confirmPassword = new String(confirmPasswordField.getPassword());
//
//        // Basic validation
//        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        if (!password.equals(confirmPassword)) {
//            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        // Attempt to add the user to the database
////        boolean success = DatabaseUtil.insertUser(username, password);
////        if (success) {
////            JOptionPane.showMessageDialog(this, "Account created successfully!");
////            new StylishLoginPage().setVisible(true); // Redirect to the login page
////            dispose(); // Close sign-up form
////        } else {
////            JOptionPane.showMessageDialog(this, "Username already exists or database error.", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
////        }
//
//        boolean success = DatabaseUtil.insertUser(username, password);
//        if (success) {
//            JOptionPane.showMessageDialog(this, "Account created successfully!");
//
//            // Pass the user ID (or other necessary details) to the ProfileCreationPage, if needed.
//            ProfileCreationPage profilePage = new ProfileCreationPage();
//            profilePage.setVisible(true); // Open ProfileCreationPage
//            dispose(); // Close sign-up form
//
//        } else {
//            JOptionPane.showMessageDialog(this, "Username already exists or database error.", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
//        }
//
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new SignUpForm().setVisible(true);
//        });
//    }
//}
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class SignUpForm extends JFrame {
//    private JTextField usernameField;
//    private JPasswordField passwordField;
//    private JPasswordField confirmPasswordField;
//    private JButton signUpButton;
//    private JLabel titleLabel;
//
//    public SignUpForm() {
//        setTitle("Create New Account");
//        setSize(400, 500);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // Title Label
//        titleLabel = new JLabel("Create Your Account", SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        titleLabel.setForeground(new Color(58, 134, 255));
//        titleLabel.setPreferredSize(new Dimension(400, 60));
//        add(titleLabel, BorderLayout.NORTH);
//
//        // Center Panel for the form
//        JPanel formPanel = new JPanel();
//        formPanel.setLayout(new GridBagLayout());
//        formPanel.setBackground(new Color(245, 245, 245));
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        // Username Label and Field
//        JLabel usernameLabel = new JLabel("Username");
//        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
//        usernameLabel.setForeground(new Color(58, 134, 255));
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        formPanel.add(usernameLabel, gbc);
//
//        usernameField = createStyledTextField();
//        gbc.gridx = 1;
//        formPanel.add(usernameField, gbc);
//
//        // Password Label and Field
//        JLabel passwordLabel = new JLabel("Password");
//        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
//        passwordLabel.setForeground(new Color(58, 134, 255));
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        formPanel.add(passwordLabel, gbc);
//
//        passwordField = createStyledPasswordField();
//        gbc.gridx = 1;
//        formPanel.add(passwordField, gbc);
//
//        // Confirm Password Label and Field
//        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
//        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
//        confirmPasswordLabel.setForeground(new Color(58, 134, 255));
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        formPanel.add(confirmPasswordLabel, gbc);
//
//        confirmPasswordField = createStyledPasswordField();
//        gbc.gridx = 1;
//        formPanel.add(confirmPasswordField, gbc);
//
//        // Sign Up Button
//        signUpButton = new JButton("Sign Up");
//        signUpButton.setFont(new Font("Arial", Font.BOLD, 18));
//        signUpButton.setBackground(new Color(58, 134, 255));
//        signUpButton.setForeground(Color.WHITE);
//        signUpButton.setFocusPainted(false);
//        signUpButton.setPreferredSize(new Dimension(150, 40));
//        signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        signUpButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                handleSignUp();
//            }
//        });
//
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        gbc.gridwidth = 2;
//        formPanel.add(signUpButton, gbc);
//
//        add(formPanel, BorderLayout.CENTER);
//    }
//
//    private JTextField createStyledTextField() {
//        JTextField textField = new JTextField(15);
//        textField.setFont(new Font("Arial", Font.PLAIN, 16));
//        textField.setForeground(new Color(58, 134, 255));
//        textField.setBackground(Color.WHITE);
//        textField.setBorder(BorderFactory.createLineBorder(new Color(58, 134, 255), 2));
//        textField.setPreferredSize(new Dimension(250, 40));
//        return textField;
//    }
//
//    private JPasswordField createStyledPasswordField() {
//        JPasswordField passwordField = new JPasswordField(15);
//        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
//        passwordField.setForeground(new Color(58, 134, 255));
//        passwordField.setBackground(Color.WHITE);
//        passwordField.setBorder(BorderFactory.createLineBorder(new Color(58, 134, 255), 2));
//        passwordField.setPreferredSize(new Dimension(250, 40));
//        return passwordField;
//    }
//
////    private void handleSignUp() {
////        // Extract input from fields
////        String username = usernameField.getText();
////        String password = new String(passwordField.getPassword());
////        String confirmPassword = new String(confirmPasswordField.getPassword());
////
////        // Basic validation (you can expand this to include stronger checks)
////        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
////            JOptionPane.showMessageDialog(this, "All fields are required.");
////            return;
////        }
////        if (!password.equals(confirmPassword)) {
////            JOptionPane.showMessageDialog(this, "Passwords do not match.");
////            return;
////        }
////
////        // (Implement database insertion here to save the new user's information)
////        JOptionPane.showMessageDialog(this, "Account created successfully!");
////
////        // Optionally, redirect back to the login page after successful signup
////        new StylishLoginPage().setVisible(true);
////        dispose(); // Close this form
////    }
//
//    private void handleSignUp() {
//        // Extract input from fields
//        String username = usernameField.getText();
//        String password = new String(passwordField.getPassword());
//        String confirmPassword = new String(confirmPasswordField.getPassword());
//
//        // Basic validation
//        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        if (!password.equals(confirmPassword)) {
//            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//
//        Integer userId = DatabaseUtil.insertUser(username, password);
//        if (userId != null) {
//            JOptionPane.showMessageDialog(this, "Account created successfully!");
//
//            // Pass the user ID (or other necessary details) to the ProfileCreationPage, if needed.
//            ProfileCreationPage profilePage = new ProfileCreationPage(userId);
//            profilePage.setVisible(true); // Open ProfileCreationPage
//            dispose(); // Close sign-up form
//
//        } else {
//            JOptionPane.showMessageDialog(this, "Username already exists or database error.", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
//        }
//
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new SignUpForm().setVisible(true);
//        });
//    }
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton signUpButton;
    private JLabel titleLabel;
    private JCheckBox showPasswordCheckbox;

    public SignUpForm() {
        setTitle("Create New Account");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title Label
        titleLabel = new JLabel("Create Your Account", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(58, 134, 255));
        titleLabel.setPreferredSize(new Dimension(400, 60));
        add(titleLabel, BorderLayout.NORTH);

        // Center Panel for the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setForeground(new Color(58, 134, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        usernameField = createStyledTextField();
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setForeground(new Color(58, 134, 255));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        passwordField = createStyledPasswordField();
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Confirm Password Label and Field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        confirmPasswordLabel.setForeground(new Color(58, 134, 255));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(confirmPasswordLabel, gbc);

        confirmPasswordField = createStyledPasswordField();
        gbc.gridx = 1;
        formPanel.add(confirmPasswordField, gbc);

        // Show/Hide Password Checkbox
        showPasswordCheckbox = new JCheckBox("Show Password");
        showPasswordCheckbox.setFont(new Font("Arial", Font.PLAIN, 14));
        showPasswordCheckbox.setForeground(new Color(58, 134, 255));
        showPasswordCheckbox.setBackground(new Color(245, 245, 245));
        showPasswordCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean showPassword = showPasswordCheckbox.isSelected();
                passwordField.setEchoChar(showPassword ? '\0' : '*');
                confirmPasswordField.setEchoChar(showPassword ? '\0' : '*');
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(showPasswordCheckbox, gbc);

        // Sign Up Button
        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 18));
        signUpButton.setBackground(new Color(58, 134, 255));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.setPreferredSize(new Dimension(150, 40));
        signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignUp();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(signUpButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setForeground(new Color(58, 134, 255));
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(new Color(58, 134, 255), 2));
        textField.setPreferredSize(new Dimension(250, 40));
        return textField;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setForeground(new Color(58, 134, 255));
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(58, 134, 255), 2));
        passwordField.setPreferredSize(new Dimension(250, 40));
        passwordField.setEchoChar('*'); // Default masking character
        return passwordField;
    }

    private void handleSignUp() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Integer userId = DatabaseUtil.insertUser(username, password);
        if (userId != null) {
            JOptionPane.showMessageDialog(this, "Account created successfully!");

            ProfileCreationPage profilePage = new ProfileCreationPage(userId);
            profilePage.setVisible(true);
            dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Username already exists or database error.", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SignUpForm().setVisible(true);
        });
    }
}
