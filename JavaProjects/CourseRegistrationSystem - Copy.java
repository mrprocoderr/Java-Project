import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    List<Student> registeredStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }
}

class Student {
    int studentID;
    String name;
    List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}


public class CourseRegistrationSystem {
    public static void main(String[] args) {
        List<Course> courseDatabase = new ArrayList<>();
        List<Student> studentDatabase = new ArrayList<>();

        Course course1 = new Course("CSE101", "Introduction to Programming", "Learn basics of programming.", 50, "Mon/Wed 10:00 AM");
        Course course2 = new Course("MAT202", "Linear Algebra", "Study matrices and linear equations.", 40, "Tue/Thu 2:00 PM");
        courseDatabase.add(course1);
        courseDatabase.add(course2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Display Course Listing");
            System.out.println("2. Register Student for Course");
            System.out.println("3. Drop Course for Student");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (choice == 1) {
                System.out.println("Available Courses:");
                for (Course course : courseDatabase) {
                    int availableSlots = course.capacity - course.registeredStudents.size();
                    System.out.println("Course Code: " + course.code);
                    System.out.println("Title: " + course.title);
                    System.out.println("Description: " + course.description);
                    System.out.println("Available Slots: " + availableSlots);
                    System.out.println("Schedule: " + course.schedule);
                    System.out.println();
                }
            } else if (choice == 2) {
                // Student registration logic
                System.out.print("Enter student ID: ");
                int studentID = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                System.out.print("Enter student name: ");
                String studentName = scanner.nextLine();

                Student student = new Student(studentID, studentName);
                studentDatabase.add(student);

                System.out.println("Available Courses:");
                for (int i = 0; i < courseDatabase.size(); i++) {
                    Course course = courseDatabase.get(i);
                    int availableSlots = course.capacity - course.registeredStudents.size();
                    System.out.println((i + 1) + ". Course Code: " + course.code + " (" + availableSlots + " slots available)");
                }

                System.out.print("Enter the course number to register: ");
                int courseNumber = scanner.nextInt();
                if (courseNumber >= 1 && courseNumber <= courseDatabase.size()) {
                    Course selectedCourse = courseDatabase.get(courseNumber - 1);
                    if (selectedCourse.registeredStudents.size() < selectedCourse.capacity) {
                        selectedCourse.registeredStudents.add(student);
                        student.registeredCourses.add(selectedCourse);
                        System.out.println("Registration successful.");
                    } else {
                        System.out.println("Course is already full.");
                    }
                } else {
                    System.out.println("Invalid course number.");
                }

            }  else if (choice == 3) {
                // Course removal logic
                System.out.print("Enter student ID: ");
                int studentID = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                Student selectedStudent = null;
                for (Student student : studentDatabase) {
                    if (student.studentID == studentID) {
                        selectedStudent = student;
                        break;
                    }
                }

                if (selectedStudent != null) {
                    System.out.println("Registered Courses:");
                    List<Course> registeredCourses = selectedStudent.registeredCourses;
                    for (int i = 0; i < registeredCourses.size(); i++) {
                        Course course = registeredCourses.get(i);
                        System.out.println((i + 1) + ". Course Code: " + course.code);
                    }

                    System.out.print("Enter the course number to drop: ");
                    int courseNumber = scanner.nextInt();
                    if (courseNumber >= 1 && courseNumber <= registeredCourses.size()) {
                        Course courseToDrop = registeredCourses.get(courseNumber - 1);
                        courseToDrop.registeredStudents.remove(selectedStudent);
                        selectedStudent.registeredCourses.remove(courseToDrop);
                        System.out.println("Course dropped successfully.");
                    } else {
                        System.out.println("Invalid course number.");
                    }
                } else {
                    System.out.println("Student not found.");
                }
            } else if (choice == 4) {
                System.out.println("Exiting the program.");
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        scanner.close();
    }
}


