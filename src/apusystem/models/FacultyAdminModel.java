package apusystem.models;

import apusystem.users.User;
import apusystem.services.FileManager;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class FacultyAdminModel extends User {
    private static final String ASSIGNMENTS_FILE = "data/assignments.txt";
    private static final String USERS_FILE = "data/users.txt";
    private static final String REPORT_FILE = "data/generated_report.txt";

    public FacultyAdminModel(String username, String password) {
        super(username, password, "FacultyAdmin");
    }

    // ✅ Assign supervisor to student using full names
    public boolean assignSupervisorToStudent(String studentFullName, String supervisorFullName) {
        if (studentFullName == null || supervisorFullName == null ||
            studentFullName.trim().isEmpty() || supervisorFullName.trim().isEmpty()) {
            return false;
        }

        removeStudentAssignment(studentFullName);
        String assignment = studentFullName + ";" + supervisorFullName;
        FileManager.appendToFile(ASSIGNMENTS_FILE, assignment);
        return true;
    }

    // ✅ Delete assignment
    public boolean deleteAssignment(String studentFullName, String supervisorFullName) {
        List<String> lines = FileManager.readFile(ASSIGNMENTS_FILE);
        List<String> updated = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length == 2 && parts[0].equals(studentFullName) && parts[1].equals(supervisorFullName)) {
                found = true;
                continue;
            }
            updated.add(line);
        }

        if (found) {
            try (FileWriter fw = new FileWriter(ASSIGNMENTS_FILE)) {
                for (String l : updated) fw.write(l + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return found;
    }

    // ✅ Remove previous assignment
    public boolean removeStudentAssignment(String studentFullName) {
        List<String> assignments = new ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(ASSIGNMENTS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(";");
                if (parts.length == 2 && !parts[0].equals(studentFullName)) {
                    assignments.add(line);
                } else if (parts.length == 2) {
                    found = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (found) {
            try (FileWriter fw = new FileWriter(ASSIGNMENTS_FILE)) {
                for (String a : assignments) fw.write(a + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return found;
    }

    // ✅ Get all supervisor-student pairs
    public List<String[]> getSupervisorStudentAssignments() {
        List<String[]> assignments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ASSIGNMENTS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(";");
                if (parts.length == 2) assignments.add(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    // ✅ Generate auto report
    public String generateComprehensiveReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== FACULTY ADMIN REPORT ===\n\n");

        report.append("All Assignments:\n");
        for (String[] a : getSupervisorStudentAssignments()) {
            report.append(String.format("  Student: %-20s | Supervisor: %s\n", a[0], a[1]));
        }

        report.append("\nReport generated on: ").append(new Date()).append("\n");
        return report.toString();
    }

    // ✅ Save manually written report
    public boolean saveCustomReport(String reportText) {
        if (reportText == null || reportText.trim().isEmpty()) return false;

        try (FileWriter fw = new FileWriter(REPORT_FILE, false)) {
            fw.write("=== CUSTOM FACULTY REPORT ===\n\n");
            fw.write(reportText + "\n");
            fw.write("\nSaved on: " + new Date());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
