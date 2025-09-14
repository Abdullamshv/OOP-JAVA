package apusystem.services;

import apusystem.models.Appointment;
import java.util.*;

public class AppointmentService {
    private static final String FILE_PATH = "data/appointments.txt";

    public static boolean addAppointment(Appointment appointment) {
        if (appointment == null || appointment.getStudent() == null || 
            appointment.getSupervisor() == null || appointment.getDate() == null) {
            return false;
        }
        
        FileManager.appendToFile(FILE_PATH, appointment.toString());
        return true;
    }

    public static List<Appointment> getAppointments() {
        List<Appointment> list = new ArrayList<>();
        List<String> lines = FileManager.readFile(FILE_PATH);
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            
            String[] parts = line.split(";");
            if (parts.length == 3) {
                String student = parts[0].trim();
                String supervisor = parts[1].trim();
                String date = parts[2].trim();
                
                if (!student.isEmpty() && !supervisor.isEmpty() && !date.isEmpty()) {
                    list.add(new Appointment(student, supervisor, date));
                }
            }
        }
        return list;
    }
}
