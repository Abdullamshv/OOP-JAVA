package apusystem.ui;

import apusystem.users.Supervisor;
import javax.swing.*;
import java.awt.*;

public class SupervisorDashboard extends JFrame {
    public SupervisorDashboard(Supervisor supervisor) {
        setTitle("Supervisor Dashboard - " + supervisor.getUsername());
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());
        
        JLabel welcomeLabel = new JLabel("Добро пожаловать, " + supervisor.getUsername() + "!");
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

