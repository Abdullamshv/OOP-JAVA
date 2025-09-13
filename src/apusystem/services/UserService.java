package apusystem.services;

import apusystem.users.*;
import java.util.*;

public class UserService {
    private static final String FILE_PATH = "data/users.txt";

    public static User login(String username, String password) {
        List<String> users = FileManager.readFile(FILE_PATH);
        for (String line : users) {
            String[] parts = line.split(";");
            if (parts.length == 3) {
                String role = parts[0];
                String user = parts[1];
                String pass = parts[2];

                if (user.equals(username) && pass.equals(password)) {
                    return createUser(role, user, pass);
                }
            }
        }
        return null;
    }

    public static void register(User user) {
        FileManager.appendToFile(FILE_PATH, user.toString());
    }

    private static User createUser(String role, String username, String password) {
        return switch (role) {
            case "Student" -> new Student(username, password);
            case "Supervisor" -> new Supervisor(username, password);
            case "FacultyAdmin" -> new FacultyAdmin(username, password);
            case "SystemAdmin" -> new SystemAdmin(username, password);
            default -> null;
        };
    }

    public static List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        List<String> users = FileManager.readFile(FILE_PATH);
        for (String line : users) {
            String[] parts = line.split(";");
            if (parts.length == 3) {
                result.add(createUser(parts[0], parts[1], parts[2]));
            }
        }
        return result;
    }
}
