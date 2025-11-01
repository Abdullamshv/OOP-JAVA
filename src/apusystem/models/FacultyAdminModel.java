package apusystem.models;

import apusystem.users.User;
import apusystem.services.FileManager;
import java.util.*;
import java.io.*;

public class FacultyAdminModel extends User {
    private static final String ASSIGNMENTS_FILE = "data/assignments.txt";
    private static final String USERS_FILE = "data/users.txt";
    
    public FacultyAdminModel(String username, String password) {
        super(username, password, "FacultyAdmin");
    }
    
    // Assign or reassign supervisor to student
    public boolean assignSupervisorToStudent(String studentUsername, String supervisorUsername) {
        if (studentUsername == null || supervisorUsername == null || 
            studentUsername.trim().isEmpty() || supervisorUsername.trim().isEmpty()) {
            return false;
        }
        
        // Verify that both student and supervisor exist
        if (!userExists(studentUsername, "Student") || !userExists(supervisorUsername, "Supervisor")) {
            return false;
        }
        
        // Remove existing assignment for this student
        removeStudentAssignment(studentUsername);
        
        // Add new assignment
        String assignment = studentUsername + ";" + supervisorUsername;
        FileManager.appendToFile(ASSIGNMENTS_FILE, assignment);
        return true;
    }
    
    // Remove supervisor assignment from student
    public boolean removeStudentAssignment(String studentUsername) {
        List<String> assignments = new ArrayList<>();
        boolean found = false;
        
        try (BufferedReader br = new BufferedReader(new FileReader(ASSIGNMENTS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(";");
                if (parts.length == 2 && !parts[0].equals(studentUsername)) {
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
                for (String assignment : assignments) {
                    fw.write(assignment + System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        
        return found;
    }
    
    // Get all supervisor-student assignments
    public List<String[]> getSupervisorStudentAssignments() {
        List<String[]> assignments = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(ASSIGNMENTS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    assignments.add(new String[]{parts[0].trim(), parts[1].trim()});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return assignments;
    }
    
    // Search assignments by intake (assuming intake is part of username or stored separately)
    public List<String[]> searchAssignmentsByIntake(String intake) {
        List<String[]> allAssignments = getSupervisorStudentAssignments();
        List<String[]> filteredAssignments = new ArrayList<>();
        
        for (String[] assignment : allAssignments) {
            String student = assignment[0];
            // Simple intake filtering based on username pattern (adjust as needed)
            if (student.toLowerCase().contains(intake.toLowerCase())) {
                filteredAssignments.add(assignment);
            }
        }
        
        return filteredAssignments;
    }
    
    // Search assignments by program (assuming program is part of username or stored separately)
    public List<String[]> searchAssignmentsByProgram(String program) {
        List<String[]> allAssignments = getSupervisorStudentAssignments();
        List<String[]> filteredAssignments = new ArrayList<>();
        
        for (String[] assignment : allAssignments) {
            String student = assignment[0];
            // Simple program filtering based on username pattern (adjust as needed)
            if (student.toLowerCase().contains(program.toLowerCase())) {
                filteredAssignments.add(assignment);
            }
        }
        
        return filteredAssignments;
    }
    
    // Generate report: students per intake
    public Map<String, Integer> generateStudentsPerIntakeReport() {
        Map<String, Integer> report = new HashMap<>();
        List<String[]> assignments = getSupervisorStudentAssignments();
        
        for (String[] assignment : assignments) {
            String student = assignment[0];
            // Extract intake from username (adjust pattern as needed)
            String intake = extractIntakeFromUsername(student);
            report.put(intake, report.getOrDefault(intake, 0) + 1);
        }
        
        return report;
    }
    
    // Generate report: students per supervisor
    public Map<String, Integer> generateStudentsPerSupervisorReport() {
        Map<String, Integer> report = new HashMap<>();
        List<String[]> assignments = getSupervisorStudentAssignments();
        
        for (String[] assignment : assignments) {
            String supervisor = assignment[1];
            report.put(supervisor, report.getOrDefault(supervisor, 0) + 1);
        }
        
        return report;
    }
    
    // Generate comprehensive report
    public String generateComprehensiveReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== FACULTY ADMINISTRATION REPORT ===\n\n");
        
        // Students per intake
        report.append("STUDENTS PER INTAKE:\n");
        Map<String, Integer> intakeReport = generateStudentsPerIntakeReport();
        for (Map.Entry<String, Integer> entry : intakeReport.entrySet()) {
            report.append(String.format("  %s: %d students\n", entry.getKey(), entry.getValue()));
        }
        
        report.append("\nSTUDENTS PER SUPERVISOR:\n");
        Map<String, Integer> supervisorReport = generateStudentsPerSupervisorReport();
        for (Map.Entry<String, Integer> entry : supervisorReport.entrySet()) {
            report.append(String.format("  %s: %d students\n", entry.getKey(), entry.getValue()));
        }
        
        report.append("\nALL ASSIGNMENTS:\n");
        List<String[]> assignments = getSupervisorStudentAssignments();
        for (String[] assignment : assignments) {
            report.append(String.format("  %s -> %s\n", assignment[0], assignment[1]));
        }
        
        return report.toString();
    }
    
    // Helper method to check if user exists
    private boolean userExists(String username, String role) {
        List<String> users = FileManager.readFile(USERS_FILE);
        for (String line : users) {
            if (line.trim().isEmpty()) continue;
            
            String[] parts = line.split(";");
            if (parts.length == 3 && parts[0].equals(role) && parts[1].equals(username)) {
                return true;
            }
        }
        return false;
    }
    
    // Helper method to extract intake from username (customize as needed)
    private String extractIntakeFromUsername(String username) {
        // Simple implementation - adjust based on your username pattern
        // For example, if usernames are like "student2023_001", this would return "2023"
        if (username.length() >= 4) {
            String year = username.substring(username.length() - 7, username.length() - 3);
            if (year.matches("\\d{4}")) {
                return year;
            }
        }
        return "Unknown";
    }
    
    // Get all students
    public List<String> getAllStudents() {
        List<String> students = new ArrayList<>();
        List<String> users = FileManager.readFile(USERS_FILE);
        
        for (String line : users) {
            if (line.trim().isEmpty()) continue;
            
            String[] parts = line.split(";");
            if (parts.length == 3 && parts[0].equals("Student")) {
                students.add(parts[1]);
            }
        }
        
        return students;
    }
    
    // Get all supervisors
    public List<String> getAllSupervisors() {
        List<String> supervisors = new ArrayList<>();
        List<String> users = FileManager.readFile(USERS_FILE);
        
        for (String line : users) {
            if (line.trim().isEmpty()) continue;
            
            String[] parts = line.split(";");
            if (parts.length == 3 && parts[0].equals("Supervisor")) {
                supervisors.add(parts[1]);
            }
        }
        
        return supervisors;
    }
}
