package apusystem.ui;

import apusystem.models.Appointment;
import apusystem.services.AppointmentService;
import apusystem.users.Supervisor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageAppointmentsFrame extends JFrame {

    private Supervisor supervisor;
    private JTable table;

    public ManageAppointmentsFrame(Supervisor supervisor) {
        this.supervisor = supervisor;
        setTitle("Manage Appointments - " + supervisor.getUsername());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        loadAppointmentTable();
        add(createButtonPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadAppointmentTable() {
        List<Appointment> appointments
                = AppointmentService.getAllAppointments()
                        .stream()
                        .filter(a -> a.getSupervisorUsername().equals(supervisor.getUsername())
                        && a.getStatus().equalsIgnoreCase("pending"))
                        .toList();

        String[] columnNames = {"Student", "Date", "Time", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Appointment a : appointments) {
            model.addRow(new Object[]{
                a.getStudentUsername(),
                a.getDate(),
                a.getTime(),
                a.getStatus()
            });
        }

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        JButton approveBtn = new JButton("Approve");
        JButton rejectBtn = new JButton("Reject");

        approveBtn.addActionListener(e -> updateStatus("approved"));
        rejectBtn.addActionListener(e -> updateStatus("rejected"));

        panel.add(approveBtn);
        panel.add(rejectBtn);

        return panel;
    }

    private void updateStatus(String status) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an appointment first!");
            return;
        }

        String student = table.getValueAt(row, 0).toString();
        String date = table.getValueAt(row, 1).toString();
        String time = table.getValueAt(row, 2).toString();

        Appointment updated = new Appointment(student, supervisor.getUsername(), date, time, status);
        AppointmentService.updateAppointmentStatus(updated, status);

        JOptionPane.showMessageDialog(this, "Appointment " + status + "!");
        dispose();
        new ManageAppointmentsFrame(supervisor);
    }
}
