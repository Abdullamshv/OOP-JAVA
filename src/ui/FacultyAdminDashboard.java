package apusystem.ui;

import apusystem.users.FacultyAdmin;
import javax.swing.*;
import java.awt.*;

public class FacultyAdminDashboard extends JFrame {
    private FacultyAdmin admin;

    public FacultyAdminDashboard(FacultyAdmin admin) {
        this.admin = admin;
        initializeFrame();
        initializeComponents();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Faculty Admin Dashboard - " + admin.getUsername());
        setSize(800, 600);
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
        headerPanel.setBackground(new Color(255, 193, 7));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JLabel welcomeLabel = new JLabel("Welcome, " + admin.getUsername() + "!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(52, 58, 64));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel roleLabel = new JLabel("Faculty Administration Portal");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        roleLabel.setForeground(new Color(73, 80, 87));
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
        
        // Administration tools section
        JPanel adminPanel = createAdminPanel();
        gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(adminPanel, gbc);
        
        // Faculty overview section
        JPanel facultyPanel = createFacultyOverviewPanel();
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(facultyPanel, gbc);
        
        return contentPanel;
    }
    
    private JPanel createAdminPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("Administration Tools");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(52, 58, 64));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        
        panel.add(Box.createVerticalStrut(20));
        
        JButton manageFacultyBtn = createActionButton("Manage Faculty", new Color(40, 167, 69));
        JButton viewReportsBtn = createActionButton("View Reports", new Color(0, 123, 255));
        JButton manageCoursesBtn = createActionButton("Manage Courses", new Color(255, 193, 7));
        JButton systemSettingsBtn = createActionButton("System Settings", new Color(108, 117, 125));
        
        panel.add(manageFacultyBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(viewReportsBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(manageCoursesBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(systemSettingsBtn);
        
        return panel;
    }
    
    private JPanel createFacultyOverviewPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("Faculty Overview");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(52, 58, 64));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        
        panel.add(Box.createVerticalStrut(20));
        
        // Sample faculty data
        String[] faculty = {
            "Dr. Smith - Computer Science",
            "Prof. Johnson - Mathematics",
            "Dr. Davis - Physics",
            "Prof. Brown - Chemistry"
        };
        
        for (String member : faculty) {
            JPanel facultyPanel = new JPanel(new BorderLayout());
            facultyPanel.setBackground(new Color(248, 249, 250));
            facultyPanel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
            
            JLabel facultyLabel = new JLabel(member);
            facultyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            facultyLabel.setForeground(new Color(73, 80, 87));
            
            JButton manageBtn = new JButton("Manage");
            manageBtn.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            manageBtn.setBackground(new Color(255, 193, 7));
            manageBtn.setForeground(new Color(52, 58, 64));
            manageBtn.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            manageBtn.setFocusPainted(false);
            
            facultyPanel.add(facultyLabel, BorderLayout.CENTER);
            facultyPanel.add(manageBtn, BorderLayout.EAST);
            
            panel.add(facultyPanel);
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
