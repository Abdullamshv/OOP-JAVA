package apusystem.ui;

import apusystem.users.Student;
import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends JFrame {
    public StudentDashboard(Student student) {
        setTitle("Student Dashboard - " + student.getUsername());
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());
        
        JLabel welcomeLabel = new JLabel("Добро пожаловать, " + student.getUsername() + "!");
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
