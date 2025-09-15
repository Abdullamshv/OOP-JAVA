package apusystem.ui;

import apusystem.users.Supervisor;
import javax.swing.*;
import java.awt.*;

public class SupervisorDashboard extends JFrame {
    private Supervisor supervisor;

    public SupervisorDashboard(Supervisor supervisor) {
        this.supervisor = supervisor;
        initializeFrame();
        initializeComponents();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Supervisor Dashboard - " + supervisor.getUsername());
        setSize(700, 500);
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
        headerPanel.setBackground(new Color(0, 123, 255));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JLabel welcomeLabel = new JLabel("Welcome, " + supervisor.getUsername() + "!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel roleLabel = new JLabel("Supervisor Portal");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        roleLabel.setForeground(new Color(200, 200, 200));
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
        
        // Management tools section
        JPanel managementPanel = createManagementPanel();
        gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(managementPanel, gbc);
        
        // Student overview section
        JPanel studentPanel = createStudentOverviewPanel();
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(studentPanel, gbc);
        
        return contentPanel;
    }
    
    private JPanel createManagementPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("Management Tools");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(52, 58, 64));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        
        panel.add(Box.createVerticalStrut(20));
        
        JButton viewStudentsBtn = createActionButton("View Students", new Color(40, 167, 69));
        JButton scheduleMeetingBtn = createActionButton("Schedule Meeting", new Color(0, 123, 255));
        JButton provideFeedbackBtn = createActionButton("Provide Feedback", new Color(255, 193, 7));
        JButton viewReportsBtn = createActionButton("View Reports", new Color(108, 117, 125));
        
        panel.add(viewStudentsBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(scheduleMeetingBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(provideFeedbackBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(viewReportsBtn);
        
        return panel;
    }
    
    private JPanel createStudentOverviewPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("Student Overview");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(52, 58, 64));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        
        panel.add(Box.createVerticalStrut(20));
        
        // Sample student data
        String[] students = {
            "John Smith - Computer Science",
            "Sarah Johnson - Mathematics",
            "Mike Davis - Physics",
            "Emily Brown - Chemistry"
        };
        
        for (String student : students) {
            JPanel studentPanel = new JPanel(new BorderLayout());
            studentPanel.setBackground(new Color(248, 249, 250));
            studentPanel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
            
            JLabel studentLabel = new JLabel(student);
            studentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            studentLabel.setForeground(new Color(73, 80, 87));
            
            JButton viewBtn = new JButton("View");
            viewBtn.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            viewBtn.setBackground(new Color(0, 123, 255));
            viewBtn.setForeground(Color.WHITE);
            viewBtn.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            viewBtn.setFocusPainted(false);
            
            studentPanel.add(studentLabel, BorderLayout.CENTER);
            studentPanel.add(viewBtn, BorderLayout.EAST);
            
            panel.add(studentPanel);
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

