package apusystem.models;

public class Appointment {
    private String student;
    private String supervisor;
    private String date;

    public Appointment(String student, String supervisor, String date) {
        this.student = student;
        this.supervisor = supervisor;
        this.date = date;
    }

    public String getStudent() { return student; }
    public String getSupervisor() { return supervisor; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return student + ";" + supervisor + ";" + date;
    }
}
