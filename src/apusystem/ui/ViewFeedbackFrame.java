package apusystem.ui;

import apusystem.models.Feedback;
import apusystem.services.FeedbackService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewFeedbackFrame extends JFrame {

    public ViewFeedbackFrame(String studentUsername) {
        setTitle("Supervisor Feedback");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initUI(studentUsername);
    }

    private void initUI(String studentUsername) {
        List<Feedback> feedbackList = FeedbackService.getFeedbackByStudent(studentUsername);

        if (feedbackList.isEmpty()) {
            add(new JLabel("No feedback yet."), BorderLayout.CENTER);
            return;
        }

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Feedback fb : feedbackList) {
            listModel.addElement("From: " + fb.getSupervisorUsername() + " — " + fb.getMessage());
        }

        JList<String> feedbackDisplay = new JList<>(listModel);
        add(new JScrollPane(feedbackDisplay), BorderLayout.CENTER);
    }
}
