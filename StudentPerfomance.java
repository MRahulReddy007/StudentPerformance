
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
    }
}