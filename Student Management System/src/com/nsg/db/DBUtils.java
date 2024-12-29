package com.nsg.db;

import com.nsg.sms.StudentProfile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides utility methods to operate on a Student's Profile
 *
 * @author Nilesh Gawai
 * <p>
 * Created on 03/03/2024
 */
public class DBUtils {

    private static Connection connection = null;

    private static final String DB_URL = "jdbc:mysql://localhost/student_profiles";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    /**
     * Gets the MySQL Server database connection.
     *
     * @return Connection object.
     */
    public static Connection getConnection() {

        try {
            // Loading the JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static boolean addStudent(StudentProfile sp) {

        boolean success = false;

        try {
            Connection connection = DBUtils.getConnection();
            String insertQuery = "INSERT INTO students (first_name, last_name, domain, address) value (?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(insertQuery);

            ps.setString(1, sp.getFirstName());
            ps.setString(2, sp.getLastName());
            ps.setString(3, sp.getDomain());
            ps.setString(4, sp.getAddress());

            ps.execute();

            success = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing database connection: " + e.getMessage());
                }
            }
        }

        return success;
    }

    public static List<StudentProfile> showRecords() {
        int id;
        String firstName;
        String lastName;
        String domain;
        String address;
        StudentProfile profile;
        List<StudentProfile> students = new ArrayList<>();

        try {
            connection = DBUtils.getConnection();
            Statement stat = connection.createStatement();
            String sqlQuery = "SELECT * FROM students";
            ResultSet rs = stat.executeQuery(sqlQuery);

            // Move to last row
            rs.last();
            int size = rs.getRow();

            System.out.println("Total Records: " + size);

            // For iteration, move the database cursor before the first record.
            rs.beforeFirst();

            if (size == 0) {
                System.out.println("No Records Found! " +
                                   "Consider adding new students.");
            } else {
                while (rs.next()) {
                    id = rs.getInt("id");
                    firstName = rs.getString("first_name");
                    lastName = rs.getString("last_name");
                    domain = rs.getString("domain");
                    address = rs.getString("address");

                    profile = new StudentProfile(id, firstName, lastName, domain, address);

                    students.add(profile);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return students;
    }

    public static boolean deleteStudent(int studentId) {
        boolean success = false;
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            String deleteQuery = "DELETE FROM students WHERE id = " + studentId;
            PreparedStatement ps = conn.prepareStatement(deleteQuery);
            if (ps.executeUpdate() > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing database connection: " + e.getMessage());
                }
            }
        }
        return success;
    }

    public static boolean updateStudentInfo(String sqlQuery) {
        Connection conn;
        boolean updateFlag = false;
        PreparedStatement statement;

        try {
            conn = DBUtils.getConnection();
            statement = conn.prepareStatement(sqlQuery);
            statement.executeUpdate();
            if (statement.executeUpdate() > 0) {
                updateFlag = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return updateFlag;
    }
}
