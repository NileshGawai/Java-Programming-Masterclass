package com.nsg.sms;

import com.nsg.db.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Nilesh Gawai
 * Created on 03/03/2024
 */
public class StudentProfile {

    private static final Scanner consoleInputReader = new Scanner(System.in);

    private int studentId;
    private String firstName;
    private String lastName;
    private String domain;
    private String address;

    public StudentProfile(int studentId, String firstName, String lastName, String domain, String address) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.domain = domain;
        this.address = address;
    }

    public StudentProfile(String firstName, String lastName, String domain, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.domain = domain;
        this.address = address;
    }

    private static boolean addNewStudent() {
        boolean success;

        // Enter firstName, lastName, domain, address
        System.out.print("Enter First Name: ");
        String firstName = consoleInputReader.next();

        System.out.print("Enter Last Name: ");
        String lastName = consoleInputReader.next();

        System.out.print("Enter Domain: ");
        String domain = consoleInputReader.next();

        System.out.print("Enter Address: ");
        String address = consoleInputReader.useDelimiter("\\n").next();

        StudentProfile sp = new StudentProfile(firstName, lastName, domain, address);

        success = DBUtils.addStudent(sp);

        return success;
    }

    private static void displayProfiles() {

        List<StudentProfile> studentList;
        studentList = DBUtils.showRecords();
        studentList.forEach(System.out::println);
        System.out.println("*".repeat(80) + "\n");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDomain() {
        return domain;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return String.format("%-4d%-10s%-10s%-25s%-25s", studentId, firstName, lastName, domain, address);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Student Management System".toUpperCase());
        displayMainMenu();
    }

    private static void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("""
                         MAIN MENU
                         1. Add New Student
                         2. Display Students
                         3. Delete Student
                         4. Update Student
                         5. Exit
                        \s
                         Select your choice:\s"""
                );

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        if (addNewStudent())
                            System.out.println("Congratulations! The student was successfully enrolled.\n");
                        else System.out.println("Could not add student!\n");
                    }
                    case 2 -> displayProfiles();
                    case 3 -> {
                        if (deleteProfile()) System.out.println("The student was deleted successfully.");
                        else System.out.println("Error deleting the student.");
                    }
                    case 4 -> {
                        if (updateProfile()) {
                            System.out.println("Details updated successfully.");
                        } else {
                            System.out.println("Update: Error(s) Encountered!");
                        }
                    }
                    case 5 -> exit();
                    default -> System.out.println("Please, provide correct option: (1-5)\n");
                }
            }
        } catch (Exception e) {
            System.err.println("Please, select a number from the menu!\nExiting...");
        }
    }

    private static void exit() {
        System.out.println("Thank you for using the system...\n\n");
        System.exit(0);
    }

    private static boolean updateProfile() {
        // Get the studentId to update.
        System.out.print("Enter student id to update: ");
        int studentId = consoleInputReader.nextInt();

        boolean updateSuccess = false;

        if (isValidID(studentId)) {
            int choice;
            String newFName;
            String newLName;
            String newDomain;
            String newAddress;
            Scanner localScanner = new Scanner(System.in);

            String updateQuery = "UPDATE students SET ";

            try {
                System.out.println("""
                        What do you want to update?
                        \t1. First Name
                        \t2. Last Name
                        \t3. Domain
                        \t4. Address
                        \t5. All
                        \t6. Cancel Update
                        \s
                        Enter option:\s""");

                choice = localScanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter new first name: ");
                        newFName = localScanner.next();
                        updateQuery += "first_name=" + "\"" + newFName + "\"" + " WHERE id=" + studentId;
                        updateSuccess = DBUtils.updateStudentInfo(updateQuery);
                    }
                    case 2 -> {
                        System.out.println("Enter new last name: ");
                        newLName = localScanner.next();
                        updateQuery += "last_name=" + "\"" + newLName + "\"" + " WHERE id=" + studentId;
                        updateSuccess = DBUtils.updateStudentInfo(updateQuery);
                    }
                    case 3 -> {
                        System.out.println("Enter new domain name: ");
                        newDomain = localScanner.next();
                        updateQuery += "domain=" + "\"" + newDomain + "\"" + " WHERE id=" + studentId;
                        updateSuccess = DBUtils.updateStudentInfo(updateQuery);
                    }
                    case 4 -> {
                        System.out.println("Enter new address: ");
                        newAddress = localScanner.useDelimiter("\\n").next();
                        updateQuery += "address=" + "\"" + newAddress + "\"" + " WHERE id=" + studentId;
                        updateSuccess = DBUtils.updateStudentInfo(updateQuery);
                    }
                    case 5 -> {
                        System.out.println("Enter details one-by-one: ");
                        System.out.println("First Name: ");
                        newFName = localScanner.next();
                        System.out.println("Last Name: ");
                        newLName = localScanner.next();
                        System.out.println("Domain: ");
                        newDomain = localScanner.next();
                        System.out.println("Address: ");
                        newAddress = localScanner.next();

                        updateQuery += "first_name=" + "\"" + newFName + "\"" + ", last_name=" + "\"" + newLName + "\"" + ", domain=" + "\"" + newDomain + "\"" + ", address=" + "\"" + newAddress + "\"" + " WHERE id=" + studentId;

                        updateSuccess = DBUtils.updateStudentInfo(updateQuery);
                    }
                    case 6 -> System.out.println("Update Cancelled.\n");
                    default -> System.out.println("Invalid choice!\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("Cannot update that student! (Student Id=" + studentId + ") doesn't exist");
        }

        return updateSuccess;
    }

    private static boolean deleteProfile() {

        boolean status = false;
        System.out.print("Enter student id to delete: ");
        int studentId = consoleInputReader.nextInt();
        if (isValidID(studentId)) {
            status = DBUtils.deleteStudent(studentId);
        } else {
            System.out.println("The student with id=" + studentId + " doesn't exist!\n");
        }
        return status;
    }

    /**
     * Checks whether the specified Student ID exists in the database.
     *
     * @param studentId The id of the student to be deleted.
     * @return True if the Student ID exists in the database or false.
     */
    private static boolean isValidID(int studentId) {

        boolean isValid = false;
        List<Integer> studentIdList = new ArrayList<>();

        try {
            Connection conn = DBUtils.getConnection();
            Statement statement = conn.createStatement();
            String query = "SELECT id FROM students";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                studentIdList.add(rs.getInt("id"));
            }

            if (studentIdList.contains(studentId)) {
                isValid = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isValid;
    }
}
