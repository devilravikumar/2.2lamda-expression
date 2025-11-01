import java.io.*;
import java.util.*;

// ========================== PART A ===============================
// Autoboxing and Unboxing Example
class AutoboxingExample {
    public static void runAutoboxingExample() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter integers separated by space:");
        String input = sc.nextLine();

        String[] numbers = input.split(" ");
        List<Integer> intList = new ArrayList<>();

        // Autoboxing (String -> int -> Integer)
        for (String num : numbers) {
            Integer value = Integer.parseInt(num); // parse string
            intList.add(value); // autoboxing to Integer
        }

        // Sum using Unboxing
        int sum = 0;
        for (Integer n : intList) {
            sum += n; // unboxing from Integer to int
        }

        System.out.println("Sum of all integers: " + sum);
        System.out.println("--------------------------------------------------");
    }
}

// ========================== PART B ===============================
// Serialization and Deserialization of Student Object
class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    int studentID;
    String name;
    String grade;

    Student(int studentID, String name, String grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    void display() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Grade: " + grade);
    }
}

class SerializationExample {
    public static void runSerializationExample() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student ID:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Student Name:");
        String name = sc.nextLine();
        System.out.println("Enter Student Grade:");
        String grade = sc.nextLine();

        Student s = new Student(id, name, grade);

        // Serialization
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            oos.writeObject(s);
            System.out.println("Student object serialized successfully.");
        } catch (IOException e) {
            System.out.println("Error during serialization: " + e.getMessage());
        }

        // Deserialization
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student deserializedStudent = (Student) ois.readObject();
            System.out.println("\nDeserialized Student Object:");
            deserializedStudent.display();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error during deserialization: " + e.getMessage());
        }

        System.out.println("--------------------------------------------------");
    }
}

// ========================== PART C ===============================
// Menu-Based Employee Management System Using File Handling
class Employee {
    String name;
    int id;
    String designation;
    double salary;

    Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + designation + "," + salary;
    }

    static Employee fromString(String data) {
        String[] parts = data.split(",");
        return new Employee(parts[1], Integer.parseInt(parts[0]), parts[2], Double.parseDouble(parts[3]));
    }
}

class EmployeeFileHandling {
    private static final String FILE_NAME = "employees.txt";

    public static void addEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Employee ID:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Employee Name:");
        String name = sc.nextLine();
        System.out.println("Enter Designation:");
        String designation = sc.nextLine();
        System.out.println("Enter Salary:");
        double salary = sc.nextDouble();

        Employee emp = new Employee(name, id, designation, salary);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(emp.toString());
            bw.newLine();
            System.out.println("Employee added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void displayEmployees() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\nAll Employee Records:");
            while ((line = br.readLine()) != null) {
                Employee emp = Employee.fromString(line);
                System.out.println("--------------------------------------");
                System.out.println("ID: " + emp.id);
                System.out.println("Name: " + emp.name);
                System.out.println("Designation: " + emp.designation);
                System.out.println("Salary: " + emp.salary);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No employee records found yet.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        System.out.println("--------------------------------------------------");
    }

    public static void runMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== Employee Management Menu =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting Application...");
                    break;
                default:
                    System.out.println("Invalid Choice! Try Again.");
            }
        } while (choice != 3);
    }
}

// ========================== MAIN DRIVER ===============================
public class CombinedJavaFeatures {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Autoboxing and Unboxing Example");
            System.out.println("2. Serialization and Deserialization");
            System.out.println("3. Employee Management Using File Handling");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    AutoboxingExample.runAutoboxingExample();
                    break;
                case 2:
                    SerializationExample.runSerializationExample();
                    break;
                case 3:
                    EmployeeFileHandling.runMenu();
                    break;
                case 4:
                    System.out.println("Exiting Program... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);
    }
}
