package apusystem.services;

import apusystem.models.Feedback;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackService {

    private static final String FILE_PATH = "data/feedback.txt";

    public static void addFeedback(Feedback feedback) {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) file.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(feedback.getStudentUsername() + ";" +
                         feedback.getSupervisorUsername() + ";" +
                         feedback.getMessage());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing feedback: " + e.getMessage());
        }
    }

    public static List<Feedback> getFeedbackByStudent(String studentUsername) {
        List<Feedback> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3 && parts[0].equals(studentUsername)) {
                    list.add(new Feedback(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading feedback: " + e.getMessage());
        }

        return list;
    }
}
