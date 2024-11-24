import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class HealthTrackerPage extends JFrame {

    private JTextField workoutDurationField;
    private JComboBox<String> workoutTypeComboBox;
    private JTextArea meditationArea;
    private JTextField weightField;
    private JButton logWorkoutButton, logWeightButton;
    private JLabel weightHistoryLabel, workoutSuggestionsLabel;
    private JPanel workoutPanel, meditationPanel, weightPanel;

    public HealthTrackerPage() {
        setTitle("Health Tracker");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create Tabs for Different Sections
        JTabbedPane tabbedPane = new JTabbedPane();

        // Workout Tab
        workoutPanel = createWorkoutPanel();
        tabbedPane.addTab("Workouts", workoutPanel);

        // Meditation Tab
        meditationPanel = createMeditationPanel();
        tabbedPane.addTab("Meditation & Mental Health", meditationPanel);

        // Weight Tab
        weightPanel = createWeightPanel();
        tabbedPane.addTab("Weight & Stats", weightPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    // Workout Panel
    private JPanel createWorkoutPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Workout Type ComboBox
        JLabel workoutTypeLabel = new JLabel("Select Workout Type:");
        workoutTypeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        workoutTypeComboBox = new JComboBox<>(new String[]{"Walking", "Cycling", "Jogging", "Running", "Swimming", "Other"});
        workoutTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 16));

        // Workout Duration Input
        JLabel workoutDurationLabel = new JLabel("Enter Duration (minutes):");
        workoutDurationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        workoutDurationField = new JTextField(10);
        workoutDurationField.setFont(new Font("Arial", Font.PLAIN, 16));

        // Log Workout Button
        logWorkoutButton = new JButton("Log Workout");
        logWorkoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWorkoutLog();
            }
        });

        // Adding Components to Panel
        panel.add(workoutTypeLabel);
        panel.add(workoutTypeComboBox);
        panel.add(workoutDurationLabel);
        panel.add(workoutDurationField);
        panel.add(logWorkoutButton);

        // Workout Suggestions
        workoutSuggestionsLabel = new JLabel("Suggested Workouts: Jogging, Cycling for 30 minutes.");
        workoutSuggestionsLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        panel.add(workoutSuggestionsLabel);

        return panel;
    }

    // Meditation Panel
    private JPanel createMeditationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Meditation Logging
        JLabel meditationLabel = new JLabel("Meditation Log (Notes):");
        meditationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        meditationArea = new JTextArea(5, 20);
        meditationArea.setFont(new Font("Arial", Font.PLAIN, 16));
        meditationArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        meditationArea.setLineWrap(true);
        meditationArea.setWrapStyleWord(true);

        // Add Components to Panel
        panel.add(meditationLabel);
        panel.add(new JScrollPane(meditationArea));

        return panel;
    }

    // Weight Panel
    private JPanel createWeightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Weight Input
        JLabel weightLabel = new JLabel("Enter Weight (kg):");
        weightLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        weightField = new JTextField(10);
        weightField.setFont(new Font("Arial", Font.PLAIN, 16));

        // Log Weight Button
        logWeightButton = new JButton("Log Weight");
        logWeightButton.setFont(new Font("Arial", Font.BOLD, 16));
        logWeightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWeightLog();
            }
        });

        // Adding Components to Panel
        panel.add(weightLabel);
        panel.add(weightField);
        panel.add(logWeightButton);

        // Weight History
        weightHistoryLabel = new JLabel("Weight History: 70 kg -> 71 kg -> 69.5 kg");
        weightHistoryLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        panel.add(weightHistoryLabel);

        return panel;
    }

    // Handle Workout Log
    private void handleWorkoutLog() {
        String workoutType = (String) workoutTypeComboBox.getSelectedItem();
        String durationStr = workoutDurationField.getText();
        if (!durationStr.isEmpty()) {
            try {
                int duration = Integer.parseInt(durationStr);
                // Calculate Calories Burned (simplified estimate)
                int caloriesBurned = calculateCaloriesBurned(workoutType, duration);
                JOptionPane.showMessageDialog(this, "Workout logged: " + caloriesBurned + " calories burned.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid duration.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a workout duration.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Calculate Calories Burned (basic estimation)
    private int calculateCaloriesBurned(String workoutType, int duration) {
        int caloriesPerMinute = 0;
        switch (workoutType) {
            case "Walking":
                caloriesPerMinute = 4;
                break;
            case "Cycling":
                caloriesPerMinute = 6;
                break;
            case "Jogging":
                caloriesPerMinute = 8;
                break;
            case "Running":
                caloriesPerMinute = 10;
                break;
            case "Swimming":
                caloriesPerMinute = 7;
                break;
            default:
                caloriesPerMinute = 5;
                break;
        }
        return caloriesPerMinute * duration;
    }

    // Handle Weight Log
    private void handleWeightLog() {
        String weightStr = weightField.getText();
        if (!weightStr.isEmpty()) {
            try {
                double weight = Double.parseDouble(weightStr);
                JOptionPane.showMessageDialog(this, "Weight logged: " + weight + " kg.");
                // Update weight history and stats in a real implementation
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid weight.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter your weight.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HealthTrackerPage().setVisible(true);
        });
    }
}
