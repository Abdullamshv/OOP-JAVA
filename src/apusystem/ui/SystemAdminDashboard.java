package apusystem.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import apusystem.models.SystemAdminModel;

public class SystemAdminDashboard extends JFrame {
    private SystemAdminModel adminModel;
    private JTable userTable;
    private DefaultTableModel tableModel;

    public SystemAdminDashboard(SystemAdminModel adminModel) {
        this.adminModel = adminModel;
        setupUI();
    }

    private void setupUI() {
        setTitle("System Admin Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Create table
        String[] columns = {"Role", "Username", "Password"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);

        // Create buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        JButton addButton = new JButton("Add User");
        JButton removeButton = new JButton("Remove User");
        JButton resetButton = new JButton("Reset Password");
        JButton refreshButton = new JButton("Refresh");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(refreshButton);

        // Add action listeners
        addButton.addActionListener(e -> showAddUserDialog());
        removeButton.addActionListener(e -> removeSelectedUser());
        resetButton.addActionListener(e -> resetSelectedUserPassword());
        refreshButton.addActionListener(e -> refreshUserTable());

        // Add components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

        refreshUserTable();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void refreshUserTable() {
        tableModel.setRowCount(0);
        String[][] users = SystemAdminModel.getAllUsers();
        for (String[] user : users) {
            tableModel.addRow(new String[]{user[0], user[1], "********"});
        }
    }

    private void showAddUserDialog() {
        String[] roles = {"Student", "Supervisor", "FacultyAdmin", "SystemAdmin"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Role:"));
        panel.add(roleBox);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(this, panel, 
            "Add New User", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String role = (String) roleBox.getSelectedItem();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (adminModel.addUser(role, username, password)) {
                JOptionPane.showMessageDialog(this, "User added successfully");
                refreshUserTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add user");
            }
        }
    }

    private void removeSelectedUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to remove");
            return;
        }

        String username = (String) userTable.getValueAt(selectedRow, 1);
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to remove user: " + username,
            "Confirm Remove", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (adminModel.removeUser(username)) {
                JOptionPane.showMessageDialog(this, "User removed successfully");
                refreshUserTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove user");
            }
        }
    }

    private void resetSelectedUserPassword() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user");
            return;
        }

        String username = (String) userTable.getValueAt(selectedRow, 1);
        JPasswordField passwordField = new JPasswordField();
        
        int result = JOptionPane.showConfirmDialog(this,
            new Object[]{"Enter new password for " + username + ":", passwordField},
            "Reset Password",
            JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String newPassword = new String(passwordField.getPassword());
            if (adminModel.updatePassword(username, newPassword)) {
                JOptionPane.showMessageDialog(this, "Password reset successful");
                refreshUserTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to reset password");
            }
        }
    }
}
