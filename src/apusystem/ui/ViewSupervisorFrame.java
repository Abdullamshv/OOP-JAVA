package apusystem.ui;

import apusystem.data.DataManager;
import apusystem.users.Supervisor;
import java.awt.*;
import javax.swing.*;

public class ViewSupervisorFrame extends JFrame {

    public ViewSupervisorFrame(String supervisorUsername) {
        Supervisor supervisor = DataManager.getSupervisorByUsername(supervisorUsername);

        setTitle("Assigned Supervisor");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        if (supervisor != null) {
            add(new JLabel("Supervisor Username: " + supervisor.getUsername(), SwingConstants.CENTER));
            add(new JLabel("Email: " + supervisor.getUsername() + "@apu.edu.my", SwingConstants.CENTER)); // заглушка
            add(new JLabel("Department: Computing School", SwingConstants.CENTER)); // заглушка
        } else {
            add(new JLabel("No supervisor assigned!", SwingConstants.CENTER));
        }

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> dispose());
        add(closeBtn);

        setVisible(true);
    }
}
