package apusystem.ui;

import apusystem.services.UserService;
import apusystem.users.*;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Система управления университетом - Вход");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Имя пользователя:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Пароль:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginBtn = new JButton("Войти");
        loginBtn.addActionListener(e -> login());
        add(loginBtn);

        JButton clearBtn = new JButton("Очистить");
        clearBtn.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
        });
        add(clearBtn);

        // Добавляем обработчик Enter для полей
        usernameField.addActionListener(e -> passwordField.requestFocus());
        passwordField.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Пожалуйста, заполните все поля", 
                "Ошибка входа", JOptionPane.ERROR_MESSAGE);
            return;
        }

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
            JOptionPane.showMessageDialog(this, "Неверные учетные данные", 
                "Ошибка входа", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
            passwordField.requestFocus();
        }
    }
}
