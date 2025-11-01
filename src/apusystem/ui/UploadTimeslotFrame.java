package apusystem.ui;

import apusystem.models.AvailableTimeSlot;
import apusystem.services.AvailableTimeService;
import apusystem.users.Supervisor;

import javax.swing.*;
import java.awt.*;

public class UploadTimeslotFrame extends JFrame {

    private Supervisor supervisor;
    private JTextField dateField, timeField;

    public UploadTimeslotFrame(Supervisor supervisor) {
        this.supervisor = supervisor;
        setTitle("Upload Available Timeslot");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        add(dateField);

        add(new JLabel("Time (HH:MM):"));
        timeField = new JTextField();
        add(timeField);

        JButton uploadBtn = new JButton("Upload Timeslot");
        uploadBtn.addActionListener(e -> uploadTimeslot());
        add(uploadBtn);
    }

    private void uploadTimeslot() {
        String date = dateField.getText().trim();
        String time = timeField.getText().trim();

        if (date.isEmpty() || time.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        // Save timeslot
        AvailableTimeSlot slot = new AvailableTimeSlot(supervisor.getUsername(), date, time);
        AvailableTimeService.addTimeSlot(slot);

        JOptionPane.showMessageDialog(this, "Timeslot uploaded successfully!");
        dispose();
    }
}
