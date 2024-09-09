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

    private static final Scanner SCANNER = new Scanner(System.in);

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
        String firstName = SCANNER.next();

        System.out.print("Enter Last Name: ");
        String lastName = SCANNER.next();

        System.out.print("Enter Domain: ");
        String domain = SCANNER.next();

        System.out.print("Enter Address: ");
        String address = SCANNER.useDelimiter("\\n").next();

        StudentProfile sp = new StudentProfile(firstName, lastName, domain, address);

        success = DBUtils.addStudent(sp);

        return success;
    }

    private static void displayProfiles() {
        List<StudentProfile> studentList;
        studentList = DBUtils.showRecords();
        studentList.forEach(System.out::println);
        System.out.println("-".repeat(100) + "\n");
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%-4d%-10s%-10s%-25s%-25s", studentId, firstName, lastName, domain, address);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Student Management System".toUpperCase());
        System.out.println("-".repeat(50));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("""
                    1. Add New Student
                    2. Display Students
                    3. Delete Student
                    4. Update Student
                    5. Exit.
                    
                    Select your choice:
                    """);

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    boolean flag = addNewStudent();
                    if (!flag) {
                        System.out.println("Could not create profile [Something happened]!\n");
                    }
                }
                case 2 -> displayProfiles();
                case 3 -> deleteProfile();
                case 4 -> updateProfile();
                case 5 -> System.exit(0);
                default -> System.out.println("Please, provide correct option...!\n");
            }
        }
    }

    private static void updateProfile() {
        System.out.print("Enter student id to update: ");
        int studentId = SCANNER.nextInt();
        DBUtils.updateStudentInfo(studentId);
    }

    private static void deleteProfile() {
        System.out.print("Enter student id to delete: ");
        int studentId = SCANNER.nextInt();
        if (isValidID(studentId)) DBUtils.deleteStudent(studentId);
        else System.out.println("The student with id=" + studentId + " doesn't exist");
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
