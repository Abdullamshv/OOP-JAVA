package apusystem.ui;

import apusystem.models.AvailableTimeSlot;
import apusystem.services.AvailableTimeService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewTimeslotsFrame extends JFrame {

    public ViewTimeslotsFrame(String supervisorUsername) {
        setTitle("Available Timeslots");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initUI(supervisorUsername);
    }

    private void initUI(String supervisorUsername) {
        List<AvailableTimeSlot> slots = AvailableTimeService.getTimeSlotsBySupervisor(supervisorUsername);

        if (slots.isEmpty()) {
            add(new JLabel("No available timeslots from this supervisor."), BorderLayout.CENTER);
            return;
        }

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (AvailableTimeSlot slot : slots) {
            listModel.addElement(slot.getDate() + " at " + slot.getTime());
        }

        JList<String> slotList = new JList<>(listModel);
        add(new JScrollPane(slotList), BorderLayout.CENTER);
    }
}
