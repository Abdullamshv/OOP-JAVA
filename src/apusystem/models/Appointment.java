package apusystem.models;

public class Appointment {
    private String student;
    private String supervisor;
    private String date;
    private String time;
    private String status;

    public Appointment(String student, String supervisor, String date) {
        this.student = student;
        this.supervisor = supervisor;
        this.date = date;
        this.time = "09:00"; // Default time
        this.status = "Scheduled"; // Default status
    }
    
    public Appointment(String student, String supervisor, String date, String time, String status) {
        this.student = student;
        this.supervisor = supervisor;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getStudent() { return student; }
    public String getSupervisor() { return supervisor; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getStatus() { return status; }
    
    // Additional getter methods for compatibility
    public String getStudentUsername() { return student; }
    public String getSupervisorUsername() { return supervisor; }

    @Override
    public String toString() {
        return student + ";" + supervisor + ";" + date;
    }
}