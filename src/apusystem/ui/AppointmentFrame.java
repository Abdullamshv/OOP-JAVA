package apusystem.ui;

import apusystem.models.Appointment;
import apusystem.services.AppointmentService;
import apusystem.users.Student;

import javax.swing.*;
import java.awt.*;

public class AppointmentFrame extends JFrame {

    private Student student;

    public AppointmentFrame(Student student) {
        this.student = student;
        setTitle("Manage Appointments");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initialize();
        setVisible(true);
    }

    private void initialize() {
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton makeBtn = new JButton("Make Appointment");
        JButton rescheduleBtn = new JButton("Reschedule Appointment");
        JButton cancelBtn = new JButton("Cancel Appointment");
        JButton viewBtn = new JButton("View My Appointments");

        makeBtn.addActionListener(e -> makeAppointment());
        rescheduleBtn.addActionListener(e -> rescheduleAppointment());
        cancelBtn.addActionListener(e -> cancelAppointment());
        viewBtn.addActionListener(e -> viewAppointments());

        add(makeBtn);
        add(rescheduleBtn);
        add(cancelBtn);
        add(viewBtn);
    }

    private void makeAppointment() {
        String date = JOptionPane.showInputDialog(this, "Enter appointment date (YYYY-MM-DD):");
        String time = JOptionPane.showInputDialog(this, "Enter time (e.g. 14:00):");

        if (date != null && time != null) {
            Appointment appt = new Appointment(student.getUsername(), student.getSupervisorUsername(), date, time, "pending");
            AppointmentService.addAppointment(appt);
            JOptionPane.showMessageDialog(this, "Appointment request sent!");
        }
    }

    private void rescheduleAppointment() {
        var appointments = AppointmentService.getAppointmentsByStudent(student.getUsername());

        if (appointments.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No appointments to reschedule.");
            return;
        }

        String[] apptList = appointments.stream()
                .map(a -> a.getDate() + " " + a.getTime())
                .toArray(String[]::new);

        String selection = (String) JOptionPane.showInputDialog(
                this,
                "Select appointment to reschedule:",
                "Reschedule",
                JOptionPane.QUESTION_MESSAGE,
                null,
                apptList,
                apptList[0]
        );

        if (selection != null) {
            String[] parts = selection.split(" ");
            Appointment oldAppt = new Appointment(student.getUsername(), student.getSupervisorUsername(), parts[0], parts[1], "pending");

            String newDate = JOptionPane.showInputDialog(this, "Enter new date (YYYY-MM-DD):");
            String newTime = JOptionPane.showInputDialog(this, "Enter new time (e.g. 15:30):");

            if (newDate != null && newTime != null) {
                Appointment newAppt = new Appointment(student.getUsername(), student.getSupervisorUsername(), newDate, newTime, "pending");
                AppointmentService.updateAppointment(oldAppt, newAppt);
                JOptionPane.showMessageDialog(this, "Appointment rescheduled!");
            }
        }
    }

    private void cancelAppointment() {
        var appointments = AppointmentService.getAppointmentsByStudent(student.getUsername());

        if (appointments.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No appointments to cancel.");
            return;
        }

        String[] apptList = appointments.stream()
                .map(a -> a.getDate() + " " + a.getTime())
                .toArray(String[]::new);

        String selection = (String) JOptionPane.showInputDialog(
                this,
                "Select appointment to cancel:",
                "Cancel Appointment",
                JOptionPane.QUESTION_MESSAGE,
                null,
                apptList,
                apptList[0]
        );

        if (selection != null) {
            String[] parts = selection.split(" ");
            Appointment appt = new Appointment(student.getUsername(), student.getSupervisorUsername(), parts[0], parts[1], "cancelled");
            AppointmentService.removeAppointment(appt);
            JOptionPane.showMessageDialog(this, "Appointment cancelled.");
        }
    }

    private void viewAppointments() {
        var appointments = AppointmentService.getAppointmentsByStudent(student.getUsername());

        if (appointments.isEmpty()) {
            JOptionPane.showMessageDialog(this, "You have no appointments yet.");
            return;
        }

        StringBuilder list = new StringBuilder("Your Appointments:\n\n");
        for (Appointment appt : appointments) {
            list.append(appt.getDate()).append("  ").append(appt.getTime()).append("  [").append(appt.getStatus()).append("]\n");
        }

        JOptionPane.showMessageDialog(this, list.toString());
    }
}
