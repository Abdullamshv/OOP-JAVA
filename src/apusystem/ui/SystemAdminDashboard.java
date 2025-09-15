package apusystem.ui;

import apusystem.users.SystemAdmin;
import javax.swing.*;
import java.awt.*;

public class SystemAdminDashboard extends JFrame {
    private SystemAdmin admin;

    public SystemAdminDashboard(SystemAdmin admin) {
        this.admin = admin;
        initializeFrame();
        initializeComponents();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("System Admin Dashboard - " + admin.getUsername());
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Use default look and feel
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());
        
        // Header panel
        JPanel headerPanel = createHeaderPanel();
        
        // Main content panel
        JPanel contentPanel = createContentPanel();
        
        // Footer panel
        JPanel footerPanel = createFooterPanel();
        
        // Assemble the frame
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(220, 53, 69));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JLabel welcomeLabel = new JLabel("Welcome, " + admin.getUsername() + "!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel roleLabel = new JLabel("System Administration Portal");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        roleLabel.setForeground(new Color(255, 200, 200));
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
        
        // System management section
        JPanel systemPanel = createSystemPanel();
        gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(systemPanel, gbc);
        
        // User management section
        JPanel userPanel = createUserManagementPanel();
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(userPanel, gbc);
        
        // System status section
        JPanel statusPanel = createSystemStatusPanel();
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(statusPanel, gbc);
        
        return contentPanel;
    }
    
    private JPanel createSystemPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("System Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(52, 58, 64));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        
        panel.add(Box.createVerticalStrut(20));
        
        JButton systemSettingsBtn = createActionButton("System Settings", new Color(40, 167, 69));
        JButton backupRestoreBtn = createActionButton("Backup & Restore", new Color(0, 123, 255));
        JButton securityBtn = createActionButton("Security Settings", new Color(255, 193, 7));
        JButton maintenanceBtn = createActionButton("System Maintenance", new Color(108, 117, 125));
        
        panel.add(systemSettingsBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(backupRestoreBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(securityBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(maintenanceBtn);
        
        return panel;
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
        
        panel.add(Box.createVerticalStrut(20));
        
        JButton manageUsersBtn = createActionButton("Manage Users", new Color(40, 167, 69));
        JButton userRolesBtn = createActionButton("User Roles", new Color(0, 123, 255));
        JButton auditLogBtn = createActionButton("Audit Log", new Color(255, 193, 7));
        JButton permissionsBtn = createActionButton("Permissions", new Color(108, 117, 125));
        
        panel.add(manageUsersBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(userRolesBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(auditLogBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(permissionsBtn);
        
        return panel;
    }
    
    private JPanel createSystemStatusPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("System Status");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(52, 58, 64));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        
        panel.add(Box.createVerticalStrut(20));
        
        // System status indicators
        String[] statusItems = {
            "Database: Online",
            "File System: Healthy",
            "User Sessions: 15 active",
            "Last Backup: 2 hours ago"
        };
        
        for (String item : statusItems) {
            JPanel statusPanel = new JPanel(new BorderLayout());
            statusPanel.setBackground(new Color(248, 249, 250));
            statusPanel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
            
            JLabel statusLabel = new JLabel(item);
            statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            statusLabel.setForeground(new Color(73, 80, 87));
            
            JLabel statusIcon = new JLabel("●");
            statusIcon.setFont(new Font("Segoe UI", Font.BOLD, 16));
            statusIcon.setForeground(new Color(40, 167, 69));
            
            statusPanel.add(statusLabel, BorderLayout.CENTER);
            statusPanel.add(statusIcon, BorderLayout.EAST);
            
            panel.add(statusPanel);
            panel.add(Box.createVerticalStrut(8));
        }
        
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
        
        // Add hover effect
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
            dispose();
            new LoginFrame();
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
        
        // Add hover effect
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
}
