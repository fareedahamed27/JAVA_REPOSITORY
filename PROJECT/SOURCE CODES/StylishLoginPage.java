import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java. sql. Connection;
public class StylishLoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;

    public StylishLoginPage() {
        setTitle("Login");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Label
        JLabel headerLabel = new JLabel("Welcome to FitTracker", SwingConstants.CENTER);
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

        // Username Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setForeground(new Color(58, 134, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(58, 134, 255)));
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Password Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setForeground(new Color(58, 134, 255));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(58, 134, 255)));
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(58, 134, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(loginButton, gbc);

        // "Create Account" Button
        createAccountButton = new JButton("Create New Account");
        createAccountButton.setFont(new Font("Arial", Font.PLAIN, 14));
        createAccountButton.setForeground(new Color(58, 134, 255));
        createAccountButton.setContentAreaFilled(false);
        createAccountButton.setBorderPainted(false);
        gbc.gridy = 3;
        formPanel.add(createAccountButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Action Listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignUpForm();
            }
        });
    }

//    private void handleLogin() {
//        // Handle login functionality here
//        String username = usernameField.getText();
//        String password = new String(passwordField.getPassword());
//
//        // (Implement database validation here)
//        JOptionPane.showMessageDialog(this, "Login successful for " + username);
//        // Open dashboard or next screen after login
//    }
//
//    private void openSignUpForm() {
//        // Open the SignUpForm for new user registration
//        new SignUpForm().setVisible(true);
//        dispose(); // Close login form
//    }
//
//    private void handleLogin() {
//        String username = usernameField.getText();
//        String password = new String(passwordField.getPassword());
//
//        if (DatabaseUtil.validateLogin(username, password)) {
//            JOptionPane.showMessageDialog(this, "Login successful for " + username);
//            // Proceed to the dashboard or main application
//        } else {
//            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
//        }
//    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        Connection connection = DatabaseUtil.connect();  // Use DatabaseUtil to get connection
        if (connection != null) {
            int userId = DatabaseUtil.validateLoginGetUserId(connection,username, password);
            if (userId != -1) {
                JOptionPane.showMessageDialog(this, "Login successful for " + username);
                openDashboard(userId, connection);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Database connection failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openDashboard(int userId, Connection connection) {
        new DashboardPage(userId, connection).setVisible(true);
        dispose(); // Close the login page
    }



    private void openSignUpForm() {
        new SignUpForm().setVisible(true);
        dispose(); // Close login form
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StylishLoginPage().setVisible(true);
        });
    }
}
