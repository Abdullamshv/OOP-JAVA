package apusystem.models;

public class Feedback {
    private String student;
    private String supervisor;
    private String comment;

    public Feedback(String student, String supervisor, String comment) {
        this.student = student;
        this.supervisor = supervisor;
        this.comment = comment;
    }

    public String getStudent() { return student; }
    public String getSupervisor() { return supervisor; }
    public String getComment() { return comment; }

    @Override
    public String toString() {
        return student + ";" + supervisor + ";" + comment;
    }
}