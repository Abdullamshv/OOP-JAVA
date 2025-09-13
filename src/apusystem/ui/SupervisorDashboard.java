package apusystem.ui;

import apusystem.users.Supervisor;
import javax.swing.*;

public class SupervisorDashboard extends JFrame {
    public SupervisorDashboard(Supervisor supervisor) {
        setTitle("Supervisor Dashboard");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Welcome, " + supervisor.getUsername()));
        setVisible(true);
    }
}

