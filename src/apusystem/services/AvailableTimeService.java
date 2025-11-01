package apusystem.services;

import apusystem.models.AvailableTimeSlot;
import java.io.*;
import java.util.*;

public class AvailableTimeService {

    private static final String FILE_PATH = "data/timeslots.txt";

    // Добавить новый свободный слот
    public static void addTimeSlot(AvailableTimeSlot slot) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(slot.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to available_times.txt: " + e.getMessage());
        }
    }

    // Получить все доступные слоты
    public static List<AvailableTimeSlot> getAllTimeSlots() {
        List<AvailableTimeSlot> slots = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(";");
                if (parts.length == 3) {
                    slots.add(new AvailableTimeSlot(parts[0], parts[1], parts[2]));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("timeslots.txt not found.");
        }
        return slots;
    }

    // Получить слоты только конкретного супервизора
    public static List<AvailableTimeSlot> getSlotsBySupervisor(String supervisorUsername) {
        List<AvailableTimeSlot> result = new ArrayList<>();
        for (AvailableTimeSlot slot : getAllTimeSlots()) {
            if (slot.getSupervisorUsername().equals(supervisorUsername)) {
                result.add(slot);
            }
        }
        return result;
    }

    public static List<AvailableTimeSlot> getTimeSlotsBySupervisor(String supervisorUsername) {
        List<AvailableTimeSlot> slots = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return slots;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3 && parts[0].equals(supervisorUsername)) {
                    slots.add(new AvailableTimeSlot(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading timeslots: " + e.getMessage());
        }

        return slots;
    }
}
