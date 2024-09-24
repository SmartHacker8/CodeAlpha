import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class EmployeeLeaveManagementSystem extends JFrame {
    // Store leave balances and leave requests
    private HashMap<String, Integer> leaveBalances = new HashMap<>();
    private HashMap<String, String> leaveRequests = new HashMap<>();

    // Constructor to create the GUI
    public EmployeeLeaveManagementSystem() {
        // Set up the frame
        setTitle("Employee Leave Management System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Creating the login panel
        JPanel loginPanel = new JPanel();
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(10);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");

        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);
        loginPanel.add(loginButton);
        add(loginPanel, BorderLayout.NORTH);

        // Center panel for displaying leave balances and requests
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Display leave balance
        JLabel leaveBalanceLabel = new JLabel("Leave Balance: ");
        JTextArea leaveBalanceArea = new JTextArea(5, 30);
        leaveBalanceArea.setEditable(false);
        JScrollPane leaveScrollPane = new JScrollPane(leaveBalanceArea);

        // Display leave requests
        JLabel leaveRequestLabel = new JLabel("Leave Requests: ");
        JTextArea leaveRequestArea = new JTextArea(5, 30);
        leaveRequestArea.setEditable(false);
        JScrollPane requestScrollPane = new JScrollPane(leaveRequestArea);

        centerPanel.add(leaveBalanceLabel);
        centerPanel.add(leaveScrollPane);
        centerPanel.add(leaveRequestLabel);
        centerPanel.add(requestScrollPane);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel for requesting and approving leaves
        JPanel bottomPanel = new JPanel();
        JButton requestLeaveButton = new JButton("Request Leave");
        JButton approveLeaveButton = new JButton("Approve Leave");
        JButton rejectLeaveButton = new JButton("Reject Leave");

        bottomPanel.add(requestLeaveButton);
        bottomPanel.add(approveLeaveButton);
        bottomPanel.add(rejectLeaveButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");

                    // Show leave balances after login
                    updateLeaveBalanceArea(leaveBalanceArea);
                    updateLeaveRequestArea(leaveRequestArea);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login!");
                }
            }
        });

        requestLeaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                if (!leaveRequests.containsKey(username)) {
                    leaveRequests.put(username, "Pending Approval");
                    updateLeaveRequestArea(leaveRequestArea);
                    JOptionPane.showMessageDialog(null, "Leave request submitted!");
                } else {
                    JOptionPane.showMessageDialog(null, "You already have a pending leave request.");
                }
            }
        });

        approveLeaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog("Enter the username to approve leave:");
                if (leaveRequests.containsKey(username) && leaveRequests.get(username).equals("Pending Approval")) {
                    leaveRequests.put(username, "Approved");
                    leaveBalances.put(username, leaveBalances.get(username) - 1);
                    updateLeaveBalanceArea(leaveBalanceArea);
                    updateLeaveRequestArea(leaveRequestArea);
                    JOptionPane.showMessageDialog(null, "Leave approved for " + username);
                } else {
                    JOptionPane.showMessageDialog(null, "No pending leave request for " + username);
                }
            }
        });

        rejectLeaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog("Enter the username to reject leave:");
                if (leaveRequests.containsKey(username) && leaveRequests.get(username).equals("Pending Approval")) {
                    leaveRequests.put(username, "Rejected");
                    updateLeaveRequestArea(leaveRequestArea);
                    JOptionPane.showMessageDialog(null, "Leave rejected for " + username);
                } else {
                    JOptionPane.showMessageDialog(null, "No pending leave request for " + username);
                }
            }
        });
    }

    // Method to validate login credentials (for simplicity)
    private boolean validateLogin(String username, String password) {
        // In a real application, this should check a database or a secure data source
        if (username.equals("admin") && password.equals("admin123")) {
            leaveBalances.put("employee1", 10);
            leaveBalances.put("employee2", 8);
            return true;
        }
        return false;
    }

    // Method to update the leave balance area
    private void updateLeaveBalanceArea(JTextArea leaveBalanceArea) {
        leaveBalanceArea.setText("");
        for (String employee : leaveBalances.keySet()) {
            leaveBalanceArea.append(employee + ": " + leaveBalances.get(employee) + " days\n");
        }
    }

    // Method to update the leave request area
    private void updateLeaveRequestArea(JTextArea leaveRequestArea) {
        leaveRequestArea.setText("");
        for (String employee : leaveRequests.keySet()) {
            leaveRequestArea.append(employee + ": " + leaveRequests.get(employee) + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmployeeLeaveManagementSystem().setVisible(true);
        });
    }
}
