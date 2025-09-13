package apusystem.ui;

import apusystem.services.UserService;
import apusystem.users.*;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> login());
        add(loginBtn);

        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user = UserService.login(username, password);

        if (user != null) {
            dispose();
            switch (user.getRole()) {
                case "Student" -> new StudentDashboard((Student) user);
                case "Supervisor" -> new SupervisorDashboard((Supervisor) user);
                case "FacultyAdmin" -> new FacultyAdminDashboard((FacultyAdmin) user);
                case "SystemAdmin" -> new SystemAdminDashboard((SystemAdmin) user);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials");
        }
    }
}
