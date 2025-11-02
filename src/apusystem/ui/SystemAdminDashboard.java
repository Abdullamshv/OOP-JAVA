package apusystem.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import apusystem.models.SystemAdminModel;

public class SystemAdminDashboard extends JFrame {
    private SystemAdminModel adminModel;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JLabel statsLabel;

    public SystemAdminDashboard(SystemAdminModel adminModel) {
        this.adminModel = adminModel;
        initializeFrame();
        initializeComponents();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("System Admin Dashboard - " + adminModel.getUsername());
        setSize(900, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel();
        JPanel contentPanel = createContentPanel();
        JPanel footerPanel = createFooterPanel();

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(108, 117, 125));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel welcomeLabel = new JLabel("Welcome, " + adminModel.getUsername() + "!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel roleLabel = new JLabel("System Administration Portal");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        roleLabel.setForeground(new Color(220, 220, 220));
        roleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(welcomeLabel, BorderLayout.CENTER);
        headerPanel.add(roleLabel, BorderLayout.SOUTH);

        return headerPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(248, 249, 250));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JPanel userPanel = createUserManagementPanel();
        gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 0.3;
        contentPanel.add(userPanel, gbc);

        JPanel systemPanel = createSystemOverviewPanel();
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 0.7;
        contentPanel.add(systemPanel, gbc);

        return contentPanel;
    }

    private JPanel createUserManagementPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("User Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(52, 58, 64));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(Box.createVerticalStrut(10));

        JButton addUserBtn = createActionButton("Add User", new Color(40, 167, 69));
        JButton removeUserBtn = createActionButton("Remove User", new Color(220, 53, 69));
        JButton resetPasswordBtn = createActionButton("Reset Password", new Color(255, 193, 7));
        JButton refreshBtn = createActionButton("Refresh Users", new Color(0, 123, 255));

        addUserBtn.addActionListener(e -> showAddUserDialog());
        removeUserBtn.addActionListener(e -> removeSelectedUser());
        resetPasswordBtn.addActionListener(e -> resetSelectedUserPassword());
        refreshBtn.addActionListener(e -> refreshUserTable());

        panel.add(addUserBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(removeUserBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(resetPasswordBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(refreshBtn);

        return panel;
    }

    private JPanel createSystemOverviewPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("System Overview");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(52, 58, 64));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(Box.createVerticalStrut(20));

        String[] columns = {"Role", "Username", "Password"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userTable = new JTable(tableModel);
        userTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        userTable.setRowHeight(25);
        userTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        userTable.getTableHeader().setBackground(new Color(248, 249, 250));

        userTable.setSelectionBackground(new Color(0, 123, 255, 50));
        userTable.setSelectionForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        scrollPane.setPreferredSize(new Dimension(400, 300));

        panel.add(scrollPane);

        refreshUserTable();

        return panel;
    }

    private JButton createActionButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 35));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });

        return button;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(new Color(248, 249, 250));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton logoutBtn = createStyledButton("Logout", new Color(220, 53, 69));
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new LoginFrame();
            }
        });

        footerPanel.add(logoutBtn);
        return footerPanel;
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });

        return button;
    }

    private void refreshUserTable() {
        tableModel.setRowCount(0);
        String[][] users = SystemAdminModel.getAllUsers();
        for (String[] user : users) {
            tableModel.addRow(user);
        }
    }

    private void showAddUserDialog() {
        JDialog dialog = new JDialog(this, "Add New User", true);
        dialog.setSize(450, 350);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(108, 117, 125));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("Add New User");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] roles = {"Student", "Supervisor", "FacultyAdmin", "SystemAdmin"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        roleBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        roleBox.setPreferredSize(new Dimension(250, 30));

        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        usernameField.setPreferredSize(new Dimension(250, 30));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        passwordField.setPreferredSize(new Dimension(250, 30));

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        confirmPasswordField.setPreferredSize(new Dimension(250, 30));

        gbc.gridx = 0; gbc.gridy = 0;
        contentPanel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(roleBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        contentPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        contentPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        contentPanel.add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(confirmPasswordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JButton addBtn = createStyledButton("Add User", new Color(40, 167, 69));
        JButton cancelBtn = createStyledButton("Cancel", new Color(108, 117, 125));

        addBtn.addActionListener(e -> {
            String role = (String) roleBox.getSelectedItem();
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill in all fields!",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (username.length() < 3) {
                JOptionPane.showMessageDialog(dialog, "Username must be at least 3 characters!",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (password.length() < 6) {
                JOptionPane.showMessageDialog(dialog, "Password must be at least 6 characters!",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(dialog, "Passwords do not match!",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (adminModel.addUser(role, username, password)) {
                JOptionPane.showMessageDialog(dialog,
                        "User '" + username + "' added successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshUserTable();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog,
                        "Failed to add user! Username may already exist.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

            java.util.Arrays.fill(passwordField.getPassword(), '0');
            java.util.Arrays.fill(confirmPasswordField.getPassword(), '0');
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(cancelBtn);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(addBtn);

        dialog.add(headerPanel, BorderLayout.NORTH);
        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void removeSelectedUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to remove",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String username = (String) userTable.getValueAt(selectedRow, 1);

        if (username.equals(adminModel.getUsername())) {
            JOptionPane.showMessageDialog(this, "You cannot remove yourself!",
                    "Operation Denied", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to remove user:\n'" + username + "'?",
                "Confirm Remove", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            if (adminModel.removeUser(username)) {
                JOptionPane.showMessageDialog(this,
                        "User '" + username + "' removed successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshUserTable();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to remove user!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetSelectedUserPassword() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String username = (String) userTable.getValueAt(selectedRow, 1);

        JDialog dialog = new JDialog(this, "Reset Password", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(108, 117, 125));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("Reset Password for: " + username);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        passwordField.setPreferredSize(new Dimension(220, 30));

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        confirmPasswordField.setPreferredSize(new Dimension(220, 30));

        gbc.gridx = 0; gbc.gridy = 0;
        contentPanel.add(new JLabel("New Password:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        contentPanel.add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(confirmPasswordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JButton resetBtn = createStyledButton("Reset Password", new Color(255, 193, 7));
        JButton cancelBtn = createStyledButton("Cancel", new Color(108, 117, 125));

        resetBtn.addActionListener(e -> {
            String newPassword = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter a new password!",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newPassword.length() < 6) {
                JOptionPane.showMessageDialog(dialog, "Password must be at least 6 characters!",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(dialog, "Passwords do not match!",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (adminModel.updatePassword(username, newPassword)) {
                JOptionPane.showMessageDialog(dialog,
                        "Password reset successfully for '" + username + "'!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog,
                        "Failed to reset password!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

            java.util.Arrays.fill(passwordField.getPassword(), '0');
            java.util.Arrays.fill(confirmPasswordField.getPassword(), '0');
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(cancelBtn);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(resetBtn);

        dialog.add(headerPanel, BorderLayout.NORTH);
        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
}
