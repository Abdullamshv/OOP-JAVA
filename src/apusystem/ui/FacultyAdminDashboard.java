package apusystem.ui;

import apusystem.models.FacultyAdminModel;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class FacultyAdminDashboard extends JFrame {
    private FacultyAdminModel admin;

    public FacultyAdminDashboard(FacultyAdminModel admin) {
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
        
        JButton assignSupervisorBtn = createActionButton("Assign Supervisor", new Color(40, 167, 69));
        JButton viewAssignmentsBtn = createActionButton("View Assignments", new Color(0, 123, 255));
        JButton searchAssignmentsBtn = createActionButton("Search Assignments", new Color(255, 193, 7));
        JButton generateReportsBtn = createActionButton("Generate Reports", new Color(108, 117, 125));
        
        // Add action listeners
        assignSupervisorBtn.addActionListener(e -> showAssignSupervisorDialog());
        viewAssignmentsBtn.addActionListener(e -> showAssignmentsDialog());
        searchAssignmentsBtn.addActionListener(e -> showSearchDialog());
        generateReportsBtn.addActionListener(e -> showReportsDialog());
        
        panel.add(assignSupervisorBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(viewAssignmentsBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(searchAssignmentsBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(generateReportsBtn);
        
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
    
    // Dialog for assigning supervisor to student
    private void showAssignSupervisorDialog() {
        JDialog dialog = new JDialog(this, "Assign Supervisor to Student", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Student selection
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Select Student:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> studentCombo = new JComboBox<>();
        java.util.List<String> students = admin.getAllStudents();
        for (String student : students) {
            studentCombo.addItem(student);
        }
        panel.add(studentCombo, gbc);
        
        // Supervisor selection
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Select Supervisor:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> supervisorCombo = new JComboBox<>();
        java.util.List<String> supervisors = admin.getAllSupervisors();
        for (String supervisor : supervisors) {
            supervisorCombo.addItem(supervisor);
        }
        panel.add(supervisorCombo, gbc);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton assignBtn = new JButton("Assign");
        JButton cancelBtn = new JButton("Cancel");
        
        assignBtn.addActionListener(e -> {
            String student = (String) studentCombo.getSelectedItem();
            String supervisor = (String) supervisorCombo.getSelectedItem();
            if (admin.assignSupervisorToStudent(student, supervisor)) {
                JOptionPane.showMessageDialog(dialog, "Supervisor assigned successfully!");
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to assign supervisor!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelBtn.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(assignBtn);
        buttonPanel.add(cancelBtn);
        panel.add(buttonPanel, gbc);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    // Dialog for viewing assignments
    private void showAssignmentsDialog() {
        JDialog dialog = new JDialog(this, "Supervisor-Student Assignments", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        // Create table
        String[] columns = {"Student", "Supervisor"};
        java.util.List<String[]> assignments = admin.getSupervisorStudentAssignments();
        String[][] data = assignments.toArray(new String[0][0]);
        
        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Close button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> dialog.dispose());
        buttonPanel.add(closeBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    // Dialog for searching assignments
    private void showSearchDialog() {
        JDialog dialog = new JDialog(this, "Search Assignments", true);
        dialog.setSize(500, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Search type
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Search by:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> searchTypeCombo = new JComboBox<>(new String[]{"Intake", "Program"});
        panel.add(searchTypeCombo, gbc);
        
        // Search term
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Search term:"), gbc);
        gbc.gridx = 1;
        JTextField searchField = new JTextField(20);
        panel.add(searchField, gbc);
        
        // Results area
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        JTextArea resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        panel.add(scrollPane, gbc);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.NONE;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton searchBtn = new JButton("Search");
        JButton closeBtn = new JButton("Close");
        
        searchBtn.addActionListener(e -> {
            String searchType = (String) searchTypeCombo.getSelectedItem();
            String searchTerm = searchField.getText().trim();
            
            if (searchTerm.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter a search term!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            java.util.List<String[]> results;
            if ("Intake".equals(searchType)) {
                results = admin.searchAssignmentsByIntake(searchTerm);
            } else {
                results = admin.searchAssignmentsByProgram(searchTerm);
            }
            
            StringBuilder sb = new StringBuilder();
            if (results.isEmpty()) {
                sb.append("No assignments found for: ").append(searchTerm);
            } else {
                sb.append("Found ").append(results.size()).append(" assignment(s):\n\n");
                for (String[] assignment : results) {
                    sb.append(assignment[0]).append(" -> ").append(assignment[1]).append("\n");
                }
            }
            
            resultsArea.setText(sb.toString());
        });
        
        closeBtn.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(searchBtn);
        buttonPanel.add(closeBtn);
        panel.add(buttonPanel, gbc);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    // Dialog for generating reports
    private void showReportsDialog() {
        JDialog dialog = new JDialog(this, "Generate Reports", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        // Report type selection
        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel reportTypeLabel = new JLabel("Report Type:");
        JComboBox<String> reportTypeCombo = new JComboBox<>(new String[]{
            "Comprehensive Report", "Students per Intake", "Students per Supervisor"
        });
        topPanel.add(reportTypeLabel);
        topPanel.add(reportTypeCombo);
        
        // Results area
        JTextArea resultsArea = new JTextArea(15, 40);
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton generateBtn = new JButton("Generate Report");
        JButton closeBtn = new JButton("Close");
        
        generateBtn.addActionListener(e -> {
            String reportType = (String) reportTypeCombo.getSelectedItem();
            StringBuilder report = new StringBuilder();
            
            switch (reportType) {
                case "Comprehensive Report":
                    report.append(admin.generateComprehensiveReport());
                    break;
                case "Students per Intake":
                    Map<String, Integer> intakeReport = admin.generateStudentsPerIntakeReport();
                    report.append("STUDENTS PER INTAKE:\n\n");
                    for (Map.Entry<String, Integer> entry : intakeReport.entrySet()) {
                        report.append(String.format("%s: %d students\n", entry.getKey(), entry.getValue()));
                    }
                    break;
                case "Students per Supervisor":
                    Map<String, Integer> supervisorReport = admin.generateStudentsPerSupervisorReport();
                    report.append("STUDENTS PER SUPERVISOR:\n\n");
                    for (Map.Entry<String, Integer> entry : supervisorReport.entrySet()) {
                        report.append(String.format("%s: %d students\n", entry.getKey(), entry.getValue()));
                    }
                    break;
            }
            
            resultsArea.setText(report.toString());
        });
        
        closeBtn.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(generateBtn);
        buttonPanel.add(closeBtn);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
}
