package apusystem.ui;

import apusystem.users.SystemAdmin;
import javax.swing.*;

public class SystemAdminDashboard extends JFrame {
    public SystemAdminDashboard(SystemAdmin admin) {
        setTitle("System Admin Dashboard");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Welcome, " + admin.getUsername()));
        setVisible(true);
    }
}
