package apusystem.models;

import java.io.*;
import java.util.*;
import apusystem.users.User;

public class SystemAdminModel extends User {
    // ✅ ИСПРАВЛЕНО: Правильный путь к файлу
    private static final String USERS_FILE = "data/users.txt";

    public SystemAdminModel(String username, String password) {
        super(username, password, "SystemAdmin");
    }

    // ✅ ИСПРАВЛЕНО: Метод больше не возвращает реальные пароли
    public static String[][] getAllUsers() {
        List<String[]> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    // ✅ БЕЗОПАСНОСТЬ: Заменяем пароль на маску
                    users.add(new String[]{parts[0], parts[1], "********"});
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Error reading users file: " + e.getMessage());
            e.printStackTrace();
        }
        return users.toArray(new String[0][0]);
    }

    // ✅ УЛУЧШЕНО: Добавлена валидация
    public boolean addUser(String role, String username, String password) {
        // Проверка на пустые значения
        if (role == null || username == null || password == null || 
            role.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty()) {
            System.err.println("❌ Cannot add user: Empty fields");
            return false;
        }
        
        // Проверка на существующего пользователя
        if (userExists(username)) {
            System.err.println("❌ User already exists: " + username);
            return false;
        }
        
        // Проверка валидности роли
        String[] validRoles = {"Student", "Supervisor", "FacultyAdmin", "SystemAdmin"};
        if (!Arrays.asList(validRoles).contains(role)) {
            System.err.println("❌ Invalid role: " + role);
            return false;
        }
        
        try (FileWriter fw = new FileWriter(USERS_FILE, true)) {
            fw.write(String.format("%s;%s;%s%n", role.trim(), username.trim(), password.trim()));
            System.out.println("✅ User added successfully: " + username);
            return true;
        } catch (IOException e) {
            System.err.println("❌ Error adding user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ✅ УЛУЧШЕНО: Добавлена валидация и логирование
    public boolean removeUser(String username) {
        if (username == null || username.trim().isEmpty()) {
            System.err.println("❌ Cannot remove user: Empty username");
            return false;
        }
        
        List<String> users = new ArrayList<>();
        boolean found = false;
        
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(";");
                if (parts.length == 3 && !parts[1].trim().equals(username.trim())) {
                    users.add(line);
                } else if (parts.length == 3) {
                    found = true;
                    System.out.println("🗑️ Removing user: " + username);
                }
            }
            
            if (found) {
                try (FileWriter fw = new FileWriter(USERS_FILE)) {
                    for (String user : users) {
                        fw.write(user + System.lineSeparator());
                    }
                }
                System.out.println("✅ User removed successfully: " + username);
            } else {
                System.err.println("❌ User not found: " + username);
            }
            
            return found;
        } catch (IOException e) {
            System.err.println("❌ Error removing user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ✅ УЛУЧШЕНО: Добавлена валидация пароля
    public boolean updatePassword(String username, String newPassword) {
        if (username == null || newPassword == null || 
            username.trim().isEmpty() || newPassword.trim().isEmpty()) {
            System.err.println("❌ Cannot update password: Empty fields");
            return false;
        }
        
        // Минимальная длина пароля
        if (newPassword.trim().length() < 6) {
            System.err.println("❌ Password too short (minimum 6 characters)");
            return false;
        }
        
        List<String> users = new ArrayList<>();
        boolean found = false;
        
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(";");
                if (parts.length == 3 && parts[1].trim().equals(username.trim())) {
                    users.add(parts[0] + ";" + parts[1] + ";" + newPassword.trim());
                    found = true;
                    System.out.println("🔑 Updating password for user: " + username);
                } else {
                    users.add(line);
                }
            }
            
            if (found) {
                try (FileWriter fw = new FileWriter(USERS_FILE)) {
                    for (String user : users) {
                        fw.write(user + System.lineSeparator());
                    }
                }
                System.out.println("✅ Password updated successfully for: " + username);
            } else {
                System.err.println("❌ User not found: " + username);
            }
            
            return found;
        } catch (IOException e) {
            System.err.println("❌ Error updating password: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // ✅ НОВЫЙ МЕТОД: Проверка существования пользователя
    private boolean userExists(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(";");
                if (parts.length == 3 && parts[1].trim().equals(username.trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Error checking user existence: " + e.getMessage());
        }
        return false;
    }
    
    // ✅ НОВЫЙ МЕТОД: Получение статистики
    public Map<String, Integer> getUserStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("Student", 0);
        stats.put("Supervisor", 0);
        stats.put("FacultyAdmin", 0);
        stats.put("SystemAdmin", 0);
        
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String role = parts[0].trim();
                    stats.put(role, stats.getOrDefault(role, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Error getting statistics: " + e.getMessage());
        }
        
        return stats;
    }
}