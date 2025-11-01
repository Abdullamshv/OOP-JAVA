package apusystem.data;

import apusystem.users.Supervisor;
import java.io.*;

public class DataManager {

    public static Supervisor getSupervisorByUsername(String username) {
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("src/apusystem/data/supervisors.txt") // ← вот здесь путь
            );
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    reader.close();
                    return new Supervisor(parts[0], parts[1]);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading supervisors.txt");
        }
        return null;
    }

}
