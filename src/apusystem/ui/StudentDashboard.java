package apusystem.ui;

import apusystem.users.Student;
import javax.swing.*;

public class StudentDashboard extends JFrame {
    public StudentDashboard(Student student) {
        setTitle("Student Dashboard");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Welcome, " + student.getUsername()));
        setVisible(true);
    }
}
