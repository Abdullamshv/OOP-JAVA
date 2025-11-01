package apusystem.ui;

import apusystem.models.Feedback;
import apusystem.services.FeedbackService;
import apusystem.users.Supervisor;

import javax.swing.*;
import java.awt.*;

public class AddFeedbackFrame extends JFrame {

    private Supervisor supervisor;
    private JTextField studentField;
    private JTextArea feedbackArea;

    public AddFeedbackFrame(Supervisor supervisor) {
        this.supervisor = supervisor;

        setTitle("Add Feedback");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initUI();
        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.add(new JLabel("Student Username:"));
        studentField = new JTextField(15);
        top.add(studentField);
        add(top, BorderLayout.NORTH);

        feedbackArea = new JTextArea();
        feedbackArea.setBorder(BorderFactory.createTitledBorder("Feedback Message"));
        add(new JScrollPane(feedbackArea), BorderLayout.CENTER);

        JButton submitBtn = new JButton("Submit");
        submitBtn.addActionListener(e -> submitFeedback());
        add(submitBtn, BorderLayout.SOUTH);
    }

    private void submitFeedback() {
        String studentUser = studentField.getText().trim();
        String msg = feedbackArea.getText().trim();

        if (studentUser.isEmpty() || msg.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        Feedback feedback = new Feedback(studentUser, supervisor.getUsername(), msg);
        FeedbackService.addFeedback(feedback);

        JOptionPane.showMessageDialog(this, "Feedback submitted!");
        dispose();
    }
}
