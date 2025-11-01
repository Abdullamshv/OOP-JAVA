package apusystem.ui;

import apusystem.services.StudentService;
import apusystem.users.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewAssignedStudentsFrame extends JFrame {

    public ViewAssignedStudentsFrame(String supervisorUsername) {
        setTitle("Assigned Students");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initUI(supervisorUsername);
    }

    private void initUI(String supervisorUsername) {
        List<Student> students = StudentService.getStudentsBySupervisor(supervisorUsername);

        if (students.isEmpty()) {
            add(new JLabel("No students assigned to you."), BorderLayout.CENTER);
            return;
        }

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Student s : students) {
            model.addElement(s.getUsername());
        }

        JList<String> list = new JList<>(model);
        add(new JScrollPane(list), BorderLayout.CENTER);
    }
}
