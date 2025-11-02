package apusystem.ui;

import apusystem.models.FacultyAdminModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FacultyAdminDashboard extends JFrame {
    private FacultyAdminModel model;
    private JTable assignmentsTable;
    private DefaultTableModel tableModel;

    public FacultyAdminDashboard(FacultyAdminModel model) {
        this.model = model;
        setTitle("Faculty Admin Dashboard");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Assign Supervisor", createAssignPanel());
        tabs.addTab("Assignments", createAssignmentsPanel());
        tabs.addTab("Generate Report", createReportPanel());

        add(tabs, BorderLayout.CENTER);
        setVisible(true);
    }

    // =========================
    // ASSIGN SUPERVISOR PANEL
    // =========================
    private JPanel createAssignPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Assign Supervisor to Student"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel studentLabel = new JLabel("Student Full Name:");
        JTextField studentField = new JTextField(20);

        JLabel supervisorLabel = new JLabel("Supervisor Full Name:");
        JTextField supervisorField = new JTextField(20);

        JButton assignBtn = new JButton("Assign");
        assignBtn.addActionListener(e -> {
            String student = studentField.getText().trim();
            String supervisor = supervisorField.getText().trim();
            if (model.assignSupervisorToStudent(student, supervisor)) {
                JOptionPane.showMessageDialog(this, "✅ Assigned successfully!");
                studentField.setText("");
                supervisorField.setText("");
                refreshAssignmentsTable();
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Assignment failed. Please check input.");
            }
        });

        gbc.gridx = 0; gbc.gridy = 0; panel.add(studentLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(studentField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(supervisorLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(supervisorField, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(assignBtn, gbc);

        return panel;
    }

    // =========================
    // ASSIGNMENTS PANEL
    // =========================
    private JPanel createAssignmentsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Assignments Overview"));

        tableModel = new DefaultTableModel(new String[]{"Student", "Supervisor"}, 0);
        assignmentsTable = new JTable(tableModel);
        refreshAssignmentsTable();

        JButton deleteBtn = new JButton("Delete Selected");
        deleteBtn.addActionListener(e -> {
            int row = assignmentsTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "⚠️ Please select an assignment to delete.");
                return;
            }
            String student = (String) tableModel.getValueAt(row, 0);
            String supervisor = (String) tableModel.getValueAt(row, 1);

            if (model.deleteAssignment(student, supervisor)) {
                JOptionPane.showMessageDialog(this, "✅ Assignment deleted.");
                refreshAssignmentsTable();
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Could not delete assignment.");
            }
        });

        panel.add(new JScrollPane(assignmentsTable), BorderLayout.CENTER);
        panel.add(deleteBtn, BorderLayout.SOUTH);
        return panel;
    }

    private void refreshAssignmentsTable() {
        tableModel.setRowCount(0);
        List<String[]> data = model.getSupervisorStudentAssignments();
        for (String[] pair : data) {
            tableModel.addRow(pair);
        }
    }

    // =========================
    // REPORT PANEL
    // =========================
    private JPanel createReportPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Generate Report"));

        JTextArea reportArea = new JTextArea();
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(reportArea);

        JButton autoGenBtn = new JButton("Generate Auto Report");
        autoGenBtn.addActionListener(e -> reportArea.setText(model.generateComprehensiveReport()));

        JButton saveReportBtn = new JButton("Save Custom Report");
        saveReportBtn.addActionListener(e -> {
            if (model.saveCustomReport(reportArea.getText())) {
                JOptionPane.showMessageDialog(this, "✅ Report saved successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Report not saved (empty?).");
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(autoGenBtn);
        btnPanel.add(saveReportBtn);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    // =========================
    // MAIN (for testing)
    // =========================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FacultyAdminModel model = new FacultyAdminModel("admin", "1234");
            new FacultyAdminDashboard(model);
        });
    }
}
