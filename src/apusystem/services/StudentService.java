package apusystem.services;

import apusystem.users.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private static final String FILE_PATH = "data/students.txt";

    public static List<Student> getStudentsBySupervisor(String supervisorUsername) {
        List<Student> students = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[2].equals(supervisorUsername)) {
                    students.add(new Student(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading students: " + e.getMessage());
        }

        return students;
    }
}
