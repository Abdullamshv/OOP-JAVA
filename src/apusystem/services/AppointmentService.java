package apusystem.services;

import apusystem.models.Appointment;
import java.util.*;

public class AppointmentService {
    private static final String FILE_PATH = "data/appointments.txt";

    public static void addAppointment(Appointment appointment) {
        FileManager.appendToFile(FILE_PATH, appointment.toString());
    }

    public static List<Appointment> getAppointments() {
        List<Appointment> list = new ArrayList<>();
        List<String> lines = FileManager.readFile(FILE_PATH);
        for (String line : lines) {
            String[] parts = line.split(";");
            if (parts.length == 3) {
                list.add(new Appointment(parts[0], parts[1], parts[2]));
            }
        }
        return list;
    }
}
