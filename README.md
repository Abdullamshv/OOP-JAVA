# 🎓 APU System - University Management System

## 📋 Project Description

**APU System** is a comprehensive university management system developed in Java using Swing for the graphical interface. The system provides various portals for different user roles: students, supervisors, faculty administrators, and system administrators.

### 🎯 Main System Features:

- **User authentication** with different roles
- **Assignment management** of supervisors to students
- **Meeting scheduling** and consultations
- **Report generation** and statistics
- **User management** system
- **Search and filtering** of data

---

## 🏗️ Project Architecture

### 📁 Directory Structure

```
apusystem/
├── 📄 Main.java                          # Application entry point
├── 📂 bin/                               # Compiled .class files
├── 📂 data/                              # Data files (text)
│   ├── 📄 users.txt                     # User credentials
│   ├── 📄 appointments.txt              # Meeting records
│   └── 📄 assignments.txt               # Supervisor assignments
├── 📂 models/                            # Business logic models
│   ├── 📄 Appointment.java              # Appointment entity
│   ├── 📄 Feedback.java                 # Feedback entity
│   ├── 📄 FacultyAdminModel.java        # Faculty administrator model
│   └── 📄 SystemAdminModel.java         # System administrator model
├── 📂 services/                          # Service layer
│   ├── 📄 AppointmentService.java       # Meeting management service
│   ├── 📄 FileManager.java              # File operations manager
│   └── 📄 UserService.java              # Authentication service
├── 📂 ui/                                # User interface
│   ├── 📄 LoginFrame.java               # Login window
│   ├── 📄 StudentDashboard.java         # Student dashboard
│   ├── 📄 SupervisorDashboard.java      # Supervisor dashboard
│   ├── 📄 FacultyAdminDashboard.java    # Faculty administrator dashboard
│   └── 📄 SystemAdminDashboard.java     # System administrator dashboard
└── 📂 users/                             # User entities
    ├── 📄 User.java                     # Base abstract class
    ├── 📄 Student.java                  # Student entity
    ├── 📄 Supervisor.java               # Supervisor entity
    ├── 📄 FacultyAdmin.java             # Faculty administrator entity
    └── 📄 SystemAdmin.java              # System administrator entity
```

---

## 👥 User Roles and Capabilities

### 🎓 Student
**File**: `ui/StudentDashboard.java`

**Capabilities**:
- 📅 View schedule
- 📝 Book consultations
- 📊 View grades
- 📋 View recent activities

**Color scheme**: Blue interface

### 👨‍🏫 Supervisor
**File**: `ui/SupervisorDashboard.java`

**Capabilities**:
- 👥 View assigned students
- 📅 Schedule meetings
- 💬 Provide feedback
- 📈 View reports

**Color scheme**: Blue interface

### 🏛️ Faculty Administrator (Faculty Admin)
**File**: `ui/FacultyAdminDashboard.java` + `models/FacultyAdminModel.java`

**Capabilities**:
- 🔗 **Assign supervisors to students**
  - Select student from dropdown list
  - Select supervisor from dropdown list
  - Automatic reassignment on change
- 👀 **View all assignments**
  - Tabular view of all student-supervisor relationships
  - Real-time updates
- 🔍 **Search and filtering**
  - Search by intake (enrollment year)
  - Search by program
  - Dynamic result filtering
- 📊 **Report generation**
  - Comprehensive report with all statistics
  - Students per intake report
  - Supervisor workload report
  - Export in text format

**Color scheme**: Yellow-gold interface

### ⚙️ System Administrator (System Admin)
**File**: `ui/SystemAdminDashboard.java` + `models/SystemAdminModel.java`

**Capabilities**:
- ➕ Add new users of all roles
- 🗑️ Delete existing users
- 🔐 Reset user passwords
- 👥 View all system users
- 📋 Manage user table

**Color scheme**: Gray interface

---

## 🔧 System Components

### 📊 Models

#### `models/FacultyAdminModel.java` ⭐ **MAIN COMPONENT**
**Purpose**: Business logic for faculty administrator

**Key methods**:
```java
// Assignment management
public boolean assignSupervisorToStudent(String student, String supervisor)
public boolean removeStudentAssignment(String student)
public List<String[]> getSupervisorStudentAssignments()

// Search and filtering
public List<String[]> searchAssignmentsByIntake(String intake)
public List<String[]> searchAssignmentsByProgram(String program)

// Report generation
public Map<String, Integer> generateStudentsPerIntakeReport()
public Map<String, Integer> generateStudentsPerSupervisorReport()
public String generateComprehensiveReport()

// Utilities
public List<String> getAllStudents()
public List<String> getAllSupervisors()
```

#### `models/SystemAdminModel.java`
**Purpose**: Business logic for system administrator

**Key methods**:
```java
public static String[][] getAllUsers()
public boolean addUser(String role, String username, String password)
public boolean removeUser(String username)
public boolean updatePassword(String username, String newPassword)
```

#### `models/Appointment.java`
**Purpose**: Meeting/consultation entity

**Fields**:
- `student` - student name
- `supervisor` - supervisor name
- `date` - meeting date

#### `models/Feedback.java`
**Purpose**: Feedback entity

**Fields**:
- `student` - student name
- `supervisor` - supervisor name
- `comment` - comment

### 🛠️ Services

#### `services/UserService.java`
**Purpose**: Authentication and user management

**Key methods**:
```java
public static User login(String username, String password)
public static boolean register(User user)
public static List<User> getAllUsers()
private static User createUser(String role, String username, String password)
```

**Features**:
- Automatically creates correct user type on login
- For FacultyAdmin returns `FacultyAdminModel` (not base `FacultyAdmin`)

#### `services/FileManager.java`
**Purpose**: File operations management

**Methods**:
```java
public static List<String> readFile(String path)
public static void writeFile(String path, List<String> lines)
public static void appendToFile(String path, String line)
```

#### `services/AppointmentService.java`
**Purpose**: Meeting management

**Methods**:
```java
public static boolean addAppointment(Appointment appointment)
public static List<Appointment> getAppointments()
```

### 🖥️ User Interface (UI)

#### `ui/LoginFrame.java`
**Purpose**: Login window

**Features**:
- Full-screen mode
- Modern design with gradients
- Enter key handling for login
- Routing to correct panels by roles

#### `ui/FacultyAdminDashboard.java` ⭐ **MAIN INTERFACE**
**Purpose**: Faculty administrator dashboard

**Components**:
- **Header**: Welcome message and role
- **4 main buttons**:
  1. 🟢 **Assign Supervisor** - supervisor assignment
  2. 🔵 **View Assignments** - view assignments
  3. 🟡 **Search Assignments** - search assignments
  4. ⚫ **Generate Reports** - generate reports

**Dialogs**:
- `showAssignSupervisorDialog()` - assignment dialog with dropdown lists
- `showAssignmentsDialog()` - table of all assignments
- `showSearchDialog()` - search interface with filters
- `showReportsDialog()` - report generator

#### `ui/StudentDashboard.java`
**Purpose**: Student dashboard

**Components**:
- Quick actions (schedule, booking, grades)
- Recent activities
- Blue interface

#### `ui/SupervisorDashboard.java`
**Purpose**: Supervisor dashboard

**Components**:
- Management tools
- Student overview
- Blue interface

#### `ui/SystemAdminDashboard.java`
**Purpose**: System administrator dashboard

**Components**:
- User table
- Management buttons (add, delete, reset password)
- Gray interface

### 👤 User Entities (Users)

#### `users/User.java`
**Purpose**: Base abstract class for all users

**Fields**:
- `username` - username
- `password` - password
- `role` - user role

#### `users/Student.java`, `users/Supervisor.java`, `users/FacultyAdmin.java`, `users/SystemAdmin.java`
**Purpose**: Concrete user implementations

**Features**:
- Inherit from `User`
- Contain only constructors
- Business logic moved to models

---

## 💾 Data Storage

### 📄 File Format

#### `data/users.txt`
**Format**: `Role;Username;Password`
```
Student;student1;password123
Supervisor;supervisor1;password123
FacultyAdmin;faculty1;password123
SystemAdmin;admin1;password123
```

#### `data/assignments.txt`
**Format**: `StudentName;SupervisorName`
```
student1;supervisor1
student2;supervisor1
student3;supervisor2
```

#### `data/appointments.txt`
**Format**: `StudentName;SupervisorName;Date`
```
student1;supervisor1;2024-03-15
```

---

## 🚀 Running the Project

### 📋 Requirements
- **Java JDK 8 or higher**
- **Windows/Linux/macOS** with Java support
- **Command line** or **IDE** (Eclipse, IntelliJ IDEA, NetBeans)

### 🔧 Method 1: Through Command Line

#### Step 1: Navigate to project directory
```bash
cd D:\Coding\OOP-JAVA\src\apusystem
```

#### Step 2: Compilation
```bash
javac -d bin -sourcepath . Main.java models/*.java services/*.java ui/*.java users/*.java
```

#### Step 3: Run
```bash
java -cp bin apusystem.Main
```

### 🔧 Method 2: Through batch file (Windows)

#### Create `compile.bat` file:
```batch
@echo off
echo Compiling APU System...
javac -d bin -sourcepath . Main.java models/*.java services/*.java ui/*.java users/*.java
if %errorlevel% equ 0 (
    echo Compilation successful!
    echo Running application...
    java -cp bin apusystem.Main
) else (
    echo Compilation failed!
    pause
)
```

#### Run:
```bash
compile.bat
```

### 🔧 Method 3: Through IDE

1. **Open project** in your IDE (Eclipse, IntelliJ IDEA, NetBeans)
2. **Set source folder** to `src/apusystem`
3. **Run** `Main.java`

---

## 🔐 Test Credentials

| Role | Username | Password | Description |
|------|----------|----------|-------------|
| 🎓 Student | `student1` | `password123` | Main test student |
| 🎓 Student | `student2` | `password123` | Additional student |
| 🎓 Student | `student3` | `password123` | Additional student |
| 👨‍🏫 Supervisor | `supervisor1` | `password123` | Main supervisor |
| 👨‍🏫 Supervisor | `supervisor2` | `password123` | Additional supervisor |
| 🏛️ Faculty Admin | `faculty1` | `password123` | **MAIN USER** |
| ⚙️ System Admin | `admin1` | `password123` | System administrator |

---

## 🎯 Main Functionality (Faculty Admin)

### 1. 🔗 Assign Supervisors
1. Login as `faculty1` / `password123`
2. Click **"Assign Supervisor"** (green button)
3. Select student from dropdown list
4. Select supervisor from dropdown list
5. Click **"Assign"**

**Result**: Relationship is saved to `data/assignments.txt`

### 2. 👀 View Assignments
1. Click **"View Assignments"** (blue button)
2. Table with all assignments opens
3. Columns: Student | Supervisor

### 3. 🔍 Search Assignments
1. Click **"Search Assignments"** (yellow button)
2. Select search type: Intake or Program
3. Enter search query
4. Click **"Search"**

### 4. 📊 Generate Reports
1. Click **"Generate Reports"** (gray button)
2. Select report type:
   - **Comprehensive Report** - full report
   - **Students per Intake** - students by enrollment year
   - **Students per Supervisor** - students by supervisors
3. Click **"Generate Report"**

---

## 🔄 Data Flow

### Login:
```
User enters credentials
    ↓
LoginFrame → UserService.login()
    ↓
UserService.createUser() → Creates correct user type
    ↓
For FacultyAdmin: FacultyAdminModel
    ↓
LoginFrame → FacultyAdminDashboard(model)
```

### Supervisor Assignment:
```
User clicks "Assign Supervisor"
    ↓
FacultyAdminDashboard.showAssignSupervisorDialog()
    ↓
FacultyAdminModel.assignSupervisorToStudent()
    ↓
FileManager.appendToFile() → data/assignments.txt
```

### View Assignments:
```
User clicks "View Assignments"
    ↓
FacultyAdminDashboard.showAssignmentsDialog()
    ↓
FacultyAdminModel.getSupervisorStudentAssignments()
    ↓
Read data/assignments.txt → Display in table
```

---

## 🛠️ Technical Details

### Architectural Principles:
- **Separation of Concerns**: UI, business logic and data are separated
- **Inheritance**: All users inherit from base `User` class
- **Encapsulation**: Data is hidden, access through methods
- **Polymorphism**: Different user types are handled uniformly

### Design Patterns:
- **MVC (Model-View-Controller)**: Models, UI and services are separated
- **Singleton**: FileManager for file operations
- **Factory**: UserService creates users by roles

### Error Handling:
- Try-catch blocks for file operations
- Input data validation
- User existence check before assignment

---

## 📈 Extension Possibilities

### Planned Improvements:
1. **Database**: Replace files with MySQL/PostgreSQL
2. **Report Export**: PDF, Excel, CSV
3. **Email Notifications**: Assignment notifications
4. **Calendar**: Integration with calendar system
5. **Audit**: Logging of all changes
6. **Bulk Operations**: Assign multiple students at once
7. **Statistics**: Charts and diagrams
8. **Mobile Version**: Android/iOS application

---

## 🐛 Troubleshooting

### Problem: Compilation errors
**Solution**:
- Check that all .java files are in place
- Ensure package declarations match directory structure
- Check imports

### Problem: Empty dropdown lists
**Solution**:
- Check that `data/users.txt` contains Student and Supervisor records
- Ensure correct format: `Role;Name;Password`

### Problem: Assignments not saving
**Solution**:
- Check write permissions for `data/assignments.txt`
- Ensure file exists and is accessible

### Problem: Login not working
**Solution**:
- Check username: `faculty1` (case sensitive)
- Check password: `password123`
- Ensure `data/users.txt` contains the record

---

## 📞 Support

### For Developers:
- **Source Code**: Fully documented
- **Comments**: In Russian and English
- **Structure**: Logical and clear

### For Users:
- **Interface**: Intuitive
- **Hints**: Built-in error messages
- **Documentation**: Detailed instructions

---

## 🎉 Conclusion

**APU System** is a fully functional university management system with a focus on supervisor assignment management. The system is built on object-oriented programming principles and provides a modern user interface.

### Key Achievements:
✅ **Full functionality** for all user roles  
✅ **Modern UI** with color coding  
✅ **Reliable data storage** in file system  
✅ **Scalable architecture** for future improvements  
✅ **Detailed documentation** for developers and users  

**System is ready for use!** 🚀

---

*Created with ❤️ for efficient university process management*