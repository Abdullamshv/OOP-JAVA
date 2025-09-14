package apusystem.ui;

import apusystem.users.FacultyAdmin;
import javax.swing.*;
import java.awt.*;

public class FacultyAdminDashboard extends JFrame {
    public FacultyAdminDashboard(FacultyAdmin admin) {
        setTitle("Faculty Admin Dashboard - " + admin.getUsername());
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());
        
        JLabel welcomeLabel = new JLabel("Добро пожаловать, " + admin.getUsername() + "!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel, BorderLayout.CENTER);
        
        JButton logoutBtn = new JButton("Выйти");
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });
        add(logoutBtn, BorderLayout.SOUTH);
        
        setVisible(true);
    }
}
