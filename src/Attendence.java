import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Attendence extends JFrame {

    private static final double WEEKLY_BUDGET = 1000.0;
    private static final double MONTHLY_BUDGET=5000.0;
    private double weeklyCost = 0.0;
    private double monthlyCost=0.0;

    private JTextField nameField;
    private JComboBox<String> roleComboBox;
    private JComboBox<String> statusComboBox;
    private JTextField hoursField;
    private JTextArea attendanceLog;
    private JTextField weeklyCostField;
    private JTextField weeklybudgetField;
    private JTextField monthlycostField;
    private JTextField monthlybudgetField;
    private JLabel weeklyCostLabel;
    private JLabel weeklybudgetLabel;
    private JLabel monthlycostLabel;
    private JLabel monthlybudgetLabel;
    private JComboBox<String> rateBasis;

    ImageIcon image = new ImageIcon("C:\\Users\\amana\\OneDrive\\Desktop\\Paramount.jpeg");

    public Attendence() {
        setIconImage(image.getImage());
        setTitle("Construction Site Attendance");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // Change to GridBagLayout for flexibility
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("     Name:");
        nameField = new JTextField();

        JLabel roleLabel = new JLabel("     Role:");
        roleComboBox = new JComboBox<>(new String[]{"Site Engineer", "HVAC Supervisor", "Ductman", "Electrician", "Chilled Water", "Electrical Helper", "Electrical Supervisor", "Plumber Supervisor", "Maison", "Helper", "Store Keeper", "Engineer", "Mechanical Engineer", "Chilled Water supervisor", "Plumber Helper", "Electrical Foreman", "Safety Officer", "Welder"});

        JLabel hoursLabel = new JLabel("    Work Hours:");
        hoursField = new JTextField();

        JLabel statusLabel = new JLabel("    Status:");
        statusComboBox = new JComboBox<>(new String[]{"Attended", "Absent"});

        JLabel wageRateLabel = new JLabel("    Select Wage Basis:");
        rateBasis = new JComboBox<>(new String[]{"Weekly", "Monthly"});

        JButton markAttendanceButton = new JButton("    Mark Attendance");
        attendanceLog = new JTextArea(10, 30); // Set size for the text area
        attendanceLog.setEditable(true);

        JButton resetButton = new JButton(" Reset");
        resetButton.addActionListener(e -> {
            if (e.getSource() == resetButton) {
                nameField.setText("");
                hoursField.setText("");
            }
        });

        JButton calculateCost = new JButton(" Calculate Cost");
        calculateCost.addActionListener(e -> {
            if (e.getSource() == calculateCost) {
                String name = nameField.getText();
                String role = (String) roleComboBox.getSelectedItem();
                double hours = Double.parseDouble(hoursField.getText());

                double hourlyRate = getHourlyRate(role);
                double dailyWage = hours * hourlyRate;

                if (rateBasis.getSelectedItem().equals("Weekly")) {
                    weeklyCost += dailyWage;
                    weeklyCostField.setText(String.valueOf(weeklyCost));

                    if (weeklyCost > WEEKLY_BUDGET) {
                        weeklybudgetField.setForeground(Color.RED);
                    } else {
                        weeklybudgetField.setForeground(Color.GREEN);
                    }
                } else {
                    monthlyCost += dailyWage;
                    monthlycostField.setText(String.valueOf(monthlyCost));

                    if (monthlyCost > MONTHLY_BUDGET) {
                        monthlybudgetField.setForeground(Color.RED);
                    } else {
                        monthlybudgetField.setForeground(Color.GREEN);
                    }
                }
            }
        });

        weeklyCostLabel = new JLabel("    Weekly Labor Cost:");
        weeklyCostField = new JTextField();
        weeklyCostField.setEditable(false);

        weeklybudgetLabel = new JLabel("    Weekly Budget:");
        weeklybudgetField = new JTextField(String.valueOf(WEEKLY_BUDGET));
        weeklybudgetField.setEditable(false);

        monthlycostLabel = new JLabel("    Monthly Labor Cost:");
        monthlycostField = new JTextField();
        monthlycostField.setEditable(false);

        monthlybudgetLabel = new JLabel("    Monthly Budget:");
        monthlybudgetField = new JTextField(String.valueOf(MONTHLY_BUDGET));
        monthlybudgetField.setEditable(false);

        // Layout Configuration

        // Row 1: Name
        gbc.gridx = 0; gbc.gridy = 0;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        // Row 2: Role
        gbc.gridx = 0; gbc.gridy = 1;
        add(roleLabel, gbc);
        gbc.gridx = 1;
        add(roleComboBox, gbc);

        // Row 3: Hours
        gbc.gridx = 0; gbc.gridy = 2;
        add(hoursLabel, gbc);
        gbc.gridx = 1;
        add(hoursField, gbc);

        // Row 4: Status
        gbc.gridx = 0; gbc.gridy = 3;
        add(statusLabel, gbc);
        gbc.gridx = 1;
        add(statusComboBox, gbc);

        // Row 5: Wage Rate
        gbc.gridx = 0; gbc.gridy = 4;
        add(wageRateLabel, gbc);
        gbc.gridx = 1;
        add(rateBasis, gbc);

        // Row 6: Buttons
        gbc.gridx = 0; gbc.gridy = 5;
        add(markAttendanceButton, gbc);
        gbc.gridx = 1;
        add(resetButton, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(calculateCost, gbc);

        // Row 7: Attendance Log
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(new JScrollPane(attendanceLog), gbc);

        // Row 8: Weekly and Monthly Cost
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 8;
        add(weeklyCostLabel, gbc);
        gbc.gridx = 1;
        add(weeklyCostField, gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        add(weeklybudgetLabel, gbc);
        gbc.gridx = 1;
        add(weeklybudgetField, gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        add(monthlycostLabel, gbc);
        gbc.gridx = 1;
        add(monthlycostField, gbc);

        gbc.gridx = 0; gbc.gridy = 11;
        add(monthlybudgetLabel, gbc);
        gbc.gridx = 1;
        add(monthlybudgetField, gbc);
    }

    private double getHourlyRate(String role) {
        switch (role) {
            case "Site Engineer":
                return 25.0;
            case "Electrician", "Electrical Foreman":
                return 20.0;
            case "HVAC Supervisor", "Electrical Supervisor":
                return 23.0;
            case "Ductman", "Store Keeper":
                return 16.0;
            case "Chilled Water", "Helper", "Electrical Helper", "Plumber Helper":
                return 18.0;
            case "Plumber Supervisor", "Safety Officer":
                return 25.0;
            case "Maison", "Welder":
                return 19.0;
            case "Engineer":
                return 22.0;
            case "Mechanical Engineer":
                return 23.0;
            case "Chilled Water Supervisor":
                return 23.0;
            default:
                return 0.0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Attendence());

    }
}
