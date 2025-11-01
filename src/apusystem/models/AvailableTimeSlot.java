package apusystem.models;

public class AvailableTimeSlot {

    private String supervisorUsername;
    private String date;
    private String time;

    public AvailableTimeSlot(String supervisorUsername, String date, String time) {
        this.supervisorUsername = supervisorUsername;
        this.date = date;
        this.time = time;
    }

    public String getSupervisorUsername() {
        return supervisorUsername;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return supervisorUsername + ";" + date + ";" + time;
    }
}
