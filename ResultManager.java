import java.util.ArrayList;
import java.util.Scanner;

// Main Class
public class ResultManager {

    // -------- Custom Exception Class --------
    static class InvalidMarksException extends Exception {
        public InvalidMarksException(String message) {
            super(message);
        }
    }

    // -------- Student Class --------
    static class Student {
        int rollNumber;
        String studentName;
        int[] marks = new int[3];

        public Student(int rollNumber, String studentName, int[] marks) throws InvalidMarksException {
            this.rollNumber = rollNumber;
            this.studentName = studentName;
            this.marks = marks;
            validateMarks();  
        }

        // Validate marks
        public void validateMarks() throws InvalidMarksException {
            for (int mark : marks) {
                if (mark < 0 || mark > 100) {
                    throw new InvalidMarksException("Marks must be between 0 and 100!");
                }
            }
        }

        // Calculate average
        public double calculateAverage() {
            int sum = 0;
            for (int mark : marks) {
                sum += mark;
            }
            return sum / 3.0;
        }

        // Display result
        public void displayResult() {
            System.out.println("\nRoll Number: " + rollNumber);
            System.out.println("Student Name: " + studentName);
            System.out.print("Marks: ");
            for (int mark : marks) {
                System.out.print(mark + " ");
            }

            double avg = calculateAverage();
            System.out.println("\nAverage: " + avg);

            if (avg >= 40) {
                System.out.println("Result: PASS");
            } else {
                System.out.println("Result: FAIL");
            }
        }
    }

    // -------- Main Logic --------
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        try {
            do {
                System.out.println("\n===== Student Result Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. Show All Students");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        addStudent(sc);
                        break;
                    case 2:
                        showAllStudents();
                        break;
                    case 3:
                        System.out.println("Exiting program...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 3);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
            System.out.println("Scanner closed. Program finished.");
        }
    }

    // -------- Add Student Method --------
    static void addStudent(Scanner sc) {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter marks for Subject " + (i + 1) + ": ");
                marks[i] = sc.nextInt();
            }

            Student s = new Student(roll, name, marks);
            students.add(s);
            System.out.println("Student added successfully");

        } catch (InvalidMarksException e) {
            System.out.println("Invalid Marks Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General Exception: " + e.getMessage());
        }
    }

    // -------- Display Students --------
    static void showAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found!");
        } else {
            for (Student s : students) {
                s.displayResult();
            }
        }
    }
}
