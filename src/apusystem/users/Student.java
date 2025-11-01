package apusystem.users;

public class Student extends User {
    private String supervisorUsername;
    
    public Student(String username, String password) {
        super(username, password, "Student");
        this.supervisorUsername = null; // Will be set when assigned
    }
    
    public String getSupervisorUsername() {
        return supervisorUsername;
    }
    
    public void setSupervisorUsername(String supervisorUsername) {
        this.supervisorUsername = supervisorUsername;
    }
}
