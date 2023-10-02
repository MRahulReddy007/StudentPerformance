
/**
 * @author (Rahul Reddy 24263201)
 * @version (30/09/2023)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;




public class StudentPerfomance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> studentList = null;
        //This function creates Menu
        while(true){
        System.out.println("Menu:");
        System.out.println("1. Read the data from the given file");
        System.out.println("2. Find total marks and print data");
        System.out.println("3. Display Students with total marks below a threshold");
        System.out.println("4. Print top 5 Student with higest and lowest marks");
        System.out.println("5. Stop");
        
        System.out.print("Enter Your Choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); //Concume the newline character
        
        switch(choice){//Created Switch to choose/create menu functions
            case 1:
                System.out.print("Enter the file name(Use: students_grade_2022.csv): ");
                String filename = scanner.nextLine();
                studentList = readStudentData(filename);
                break;
            case 2:
                while (true) {
                    if (studentList != null) {
                        calculateTotalMarksAndDisplay(studentList);
                        break;  // Exit the loop if the operation is successful
                    } else {
                        System.out.println("Please read student data first (option 1).");
                        System.out.print("Press Enter to return to the main menu...");
                        scanner.nextLine();  // Consume the newline character
                        scanner.nextLine();  // Wait for user input
                    break;  // Exit the loop
                    }
                }
                break;  // Break added to exit the switch statement
            case 3:
                if(studentList != null){
                    System.out.print("Please Enter the Threshold: ");
                    double threshold = scanner.nextDouble();
                    printStudentsBelowThreshold(studentList, threshold);
                }else{
                    System.out.println("Please read student data first (option 1).");
                }
                // Prompt to return to the main menu
                System.out.print("Press Enter to return to the main menu...");
                scanner.nextLine();  // Consume the newline character
                scanner.nextLine();  // Wait for user input
                break;  // Break added to exit the switch statement
            case 4:
                if(studentList != null){
                    printTopAndBottomStudents(studentList);
                }else{
                    System.out.println("Please read student data first (option 1).");
                }
                // Prompt to return to the main menu
                System.out.print("Press Enter to return to the main menu...");
                scanner.nextLine();  // Consume the newline character
                scanner.nextLine();  // Wait for user input
                break;  // Break added to exit the switch statement
            case 5:
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                break;
            }//end of switch   
        }
    }    public static ArrayList<Student> readStudentData(String filename) {
    ArrayList<Student> studentList = new ArrayList<>();

    try (Scanner scanner = new Scanner(new File(filename))) {
        if (scanner.hasNextLine()) {
            String unitName = scanner.nextLine().trim();
            scanner.nextLine();  // Skip header line

            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                String lastName = parts[0].trim();
                String firstName = parts[1].trim();
                String studentID = parts[2].trim();

                double[] marks = new double[3];
                for (int i = 0; i < 3; i++) {
                    marks[i] = (i + 3 < parts.length && !parts[i + 3].trim().isEmpty()) ?
                                Double.parseDouble(parts[i + 3].trim()) : -1.0;
                }

                studentList.add(new Student(unitName, studentID, lastName + ", " + firstName, marks));
            }

            System.out.println("File found. Reading data from " + filename + "... Successful");
        } else {
            System.out.println("Empty file: " + filename);
        }
    } catch (FileNotFoundException e) {
        System.err.println("File not found: " + filename);
    }

    return studentList;
}
    //Functional Requirement 2: Print Student data and calculated total marks of student
    public static void calculateTotalMarksAndDisplay(ArrayList<Student> studentList) {
    for (Student student : studentList) {
        double totalMarks = Arrays.stream(student.getMarks())
                                  .filter(mark -> mark != -1.0)
                                  .sum();

        System.out.println("Name: " + student.getName() +
                ", Student ID: " + student.getStudentID() +
                ", Marks: " + Arrays.toString(student.getMarks()) +
                ", Total Mark: " + totalMarks);
    }
}
    //Functional Requirement 3:  Displays the list of students with total marks less than a certain threshold
    public static void printStudentsBelowThreshold(ArrayList<Student> studentList, double threshold) {
    studentList.stream()
               .filter(student -> Arrays.stream(student.getMarks())
                                       .filter(mark -> mark != -1.0)
                                       .sum() < threshold)
               .forEach(student -> System.out.println("Name: " + student.getName() +
                                                      ", Student ID: " + student.getStudentID() +
                                                      ", Marks: " + Arrays.toString(student.getMarks()) +
                                                      ", Total Mark: " + Arrays.stream(student.getMarks())
                                                                              .filter(mark -> mark != -1.0)
                                                                              .sum()));
}


    //Functional Requirement 4: Displaying to 5 Students with highest and lowest marks
        public static void printTopAndBottomStudents(ArrayList<Student> studentList) {
    // Calculate total marks for each student
    studentList.forEach(student -> {
        double totalMarks = Arrays.stream(student.getMarks())
                                 .filter(mark -> mark != -1.0)
                                 .sum();
        student.setTotalMarks(totalMarks);
    });
    

}
}