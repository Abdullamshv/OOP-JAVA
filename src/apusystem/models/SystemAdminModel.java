package apusystem.models;

import java.io.*;
import java.util.*;
import apusystem.users.User;

public class SystemAdminModel extends User {
    private static final String USERS_FILE = "data/users.txt";

    public SystemAdminModel(String username, String password) {
        super(username, password, "SystemAdmin");
    }

    public static String[][] getAllUsers() {
        List<String[]> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    users.add(new String[]{parts[0], parts[1], parts[2]});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users.toArray(new String[0][0]);
    }

    public boolean addUser(String role, String username, String password) {
        try (FileWriter fw = new FileWriter(USERS_FILE, true)) {
            fw.write(String.format("%s;%s;%s%n", role, username, password));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeUser(String username) {
        List<String> users = new ArrayList<>();
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3 && !parts[1].equals(username)) {
                    users.add(line);
                } else if (parts.length == 3) {
                    found = true;
                }
            }
            if (found) {
                try (FileWriter fw = new FileWriter(USERS_FILE)) {
                    for (String user : users) {
                        fw.write(user + System.lineSeparator());
                    }
                }
            }
            return found;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePassword(String username, String newPassword) {
        List<String> users = new ArrayList<>();
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3 && parts[1].equals(username)) {
                    users.add(parts[0] + ";" + parts[1] + ";" + newPassword);
                    found = true;
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
            }
            return found;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}