package apusystem.ui;

import apusystem.users.Student;
import java.awt.*;
import javax.swing.*;

public class StudentDashboard extends JFrame {

    private Student student;

    public StudentDashboard(Student student) {
        this.student = student;

        System.out.println("[DEBUG] Student supervisor = " + student.getSupervisorUsername());

        initializeFrame();
        initializeComponents();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Student Dashboard - " + student.getUsername());
        setSize(600, 400);
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
        headerPanel.setBackground(new Color(52, 58, 64));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel welcomeLabel = new JLabel("Welcome, " + student.getUsername() + "!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel roleLabel = new JLabel("Student Portal");
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

        JPanel quickActionsPanel = createQuickActionsPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(quickActionsPanel, gbc);

        JPanel activitiesPanel = createActivitiesPanel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(activitiesPanel, gbc);

        return contentPanel;
    }

    private JPanel createQuickActionsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(0, 20, 10, 20)
        ));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Quick Actions");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(52, 58, 64));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(Box.createVerticalStrut(20));

        JButton viewScheduleBtn = createActionButton("View assigned supervisor", new Color(40, 167, 69));
        viewScheduleBtn.addActionListener(e -> {
            String supervisor = student.getSupervisorUsername();
            if (supervisor == null || supervisor.equals("none") || supervisor.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No supervisor assigned yet.",
                        "Supervisor Info",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Your assigned supervisor is: " + supervisor,
                        "Supervisor Info",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JButton bookAppointmentBtn = createActionButton("Make, reschedule or cancel", new Color(0, 123, 255));
        bookAppointmentBtn.addActionListener(e -> {
            new AppointmentFrame(student); // откроем окно записи на встречу
        });
        JButton viewSupervisorBtn = createActionButton("View supervisor’s feedback", new Color(108, 117, 125));
        viewSupervisorBtn.addActionListener(e -> {
            new ViewFeedbackFrame(student.getUsername()).setVisible(true);
        });

        JButton viewTimeslotsBtn = createActionButton("View available timeslots", new Color(255, 193, 7));
        viewTimeslotsBtn.addActionListener(e -> {
            String sup = student.getSupervisorUsername();
            if (sup == null || sup.isBlank() || sup.equals("none")) {
                JOptionPane.showMessageDialog(this,
                        "No supervisor assigned yet.",
                        "No Supervisor",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            new ViewTimeslotsFrame(sup).setVisible(true);
        });

        panel.add(viewScheduleBtn);       // View assigned supervisor
        panel.add(Box.createVerticalStrut(10));

        panel.add(bookAppointmentBtn);    // Make, reschedule or cancel appointment
        panel.add(Box.createVerticalStrut(10));

        panel.add(viewSupervisorBtn);     // View supervisor’s feedback
        panel.add(Box.createVerticalStrut(10));

        panel.add(viewTimeslotsBtn);

        return panel;
    }

    private JPanel createActivitiesPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Recent Activities");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(52, 58, 64));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(Box.createVerticalStrut(20));

        String[] activities = {
            "Appointment scheduled with Dr.Jhon",
            "Grade posted for Mathematics",
            "New assignment uploaded",
            "Feedback received from supervisor"
        };

        for (String activity : activities) {
            JLabel activityLabel = new JLabel("• " + activity);
            activityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            activityLabel.setForeground(new Color(73, 80, 87));
            activityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(activityLabel);
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