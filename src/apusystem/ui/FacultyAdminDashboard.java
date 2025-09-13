package apusystem.ui;

import apusystem.users.FacultyAdmin;
import javax.swing.*;

public class FacultyAdminDashboard extends JFrame {
    public FacultyAdminDashboard(FacultyAdmin admin) {
        setTitle("Faculty Admin Dashboard");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Welcome, " + admin.getUsername()));
        setVisible(true);
    }
}
