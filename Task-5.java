import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
        } else {
            System.out.println("Course is full. Cannot enroll more students.");
        }
    }

    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        } else {
            System.out.println("No students enrolled in this course.");
        }
    }
}

class Student {
    private int studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println("Successfully registered for course: " + course.getTitle());
        } else {
            System.out.println("Already registered for this course.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
            System.out.println("Successfully dropped course: " + course.getTitle());
        } else {
            System.out.println("Not registered for this course.");
        }
    }
}

class RegistrationSystem {
    private List<Course> courseDatabase;
    private List<Student> studentDatabase;

    public RegistrationSystem() {
        this.courseDatabase = new ArrayList<>();
        this.studentDatabase = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courseDatabase.add(course);
    }

    public void addStudent(Student student) {
        studentDatabase.add(student);
    }

    public void displayAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDatabase) {
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity() + ", Enrolled Students: " + course.getEnrolledStudents());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("-------------------------------");
        }
    }

    public void displayStudentRegistrationStatus(Student student) {
        System.out.println("\nRegistration Status for Student: " + student.getName());
        List<Course> registeredCourses = student.getRegisteredCourses();
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println("Course Code: " + course.getCourseCode() +
                        ", Title: " + course.getTitle() +
                        ", Enrolled Students: " + course.getEnrolledStudents());
            }
        }
    }
}

public class Task5 {
    public static void main(String[] args) {
        RegistrationSystem registrationSystem = new RegistrationSystem();

        // Adding sample courses
        Course course1 = new Course("CSE101", "Introduction to Programming", "Basic programming concepts", 50, "Mon, Wed, Fri 10:00 AM");
        Course course2 = new Course("MAT201", "Linear Algebra", "Introduction to linear algebra", 40, "Tue, Thu 2:00 PM");
        Course course3 = new Course("ENG301", "Advanced Writing", "Advanced writing skills", 30, "Mon, Wed 3:30 PM");

        registrationSystem.addCourse(course1);
        registrationSystem.addCourse(course2);
        registrationSystem.addCourse(course3);

        // Adding sample students
        Student student1 = new Student(1001, "John Doe");
        Student student2 = new Student(1002, "Jane Smith");

        registrationSystem.addStudent(student1);
        registrationSystem.addStudent(student2);

        // Display available courses
        registrationSystem.displayAvailableCourses();

        // Student 1 registers for courses
        registrationSystem.displayStudentRegistrationStatus(student1);
        student1.registerForCourse(course1);
        student1.registerForCourse(course2);
        student1.registerForCourse(course3);
        registrationSystem.displayStudentRegistrationStatus(student1);

        // Student 2 registers for courses
        registrationSystem.displayStudentRegistrationStatus(student2);
        student2.registerForCourse(course1);
        student2.registerForCourse(course3);
        registrationSystem.displayStudentRegistrationStatus(student2);

        // Student 1 drops a course
        registrationSystem.displayStudentRegistrationStatus(student1);
        student1.dropCourse(course2);
        registrationSystem.displayStudentRegistrationStatus(student1);
    }
}
