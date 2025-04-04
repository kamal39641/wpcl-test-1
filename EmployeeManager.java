import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        // Check if there are arguments passed
        if (args.length == 0) {
            System.out.println(Constants.ERROR_NO_ARGUMENT);
            return;
        }

        String action = args[0]; // First argument determines the action to perform

        // Load and display employee data
        if (action.equals("l")) {
            System.out.println("Loading data ...");
            List<String> employeeList = readEmployeeList(); // Read employee data
            for (String employee : employeeList) {
                System.out.println(employee.trim()); // Display each employee
            }
            System.out.println(Constants.DATA_LOADED);

        // Select and display a random employee
        } else if (action.equals("s")) {
            System.out.println("Loading data ...");
            List<String> employeeList = readEmployeeList();
            String randomEmployee = employeeList.get(new Random().nextInt(employeeList.size())).trim();
            System.out.println(randomEmployee); // Display random employee
            System.out.println(Constants.DATA_LOADED);

        // Append a new employee to the list
        } else if (action.startsWith("+")) {
            System.out.println("Loading data ...");
            appendEmployee(action.substring(1).trim()); // Append new employee to file
            System.out.println(Constants.DATA_LOADED);

        // Search for an employee by name
        } else if (action.startsWith("?")) {
            System.out.println("Loading data ...");
            String searchName = action.substring(1).trim(); // Name to search for
            List<String> employeeList = readEmployeeList();

            boolean found = employeeList.stream()
                    .anyMatch(e -> e.trim().equalsIgnoreCase(searchName)); // Check if employee exists

            if (found) {
                System.out.println("✅ Employee found: " + searchName);
            } else {
                System.out.println("❌ Employee not found: " + searchName);
            }

            System.out.println(Constants.DATA_LOADED);

        // Count the number of employees and characters
        } else if (action.equals("c")) {
            System.out.println("Loading data ...");
            List<String> employeeList = readEmployeeList();
            int wordCount = employeeList.size(); // Count of employees
            int charCount = String.join(",", employeeList).length(); // Total character count

            System.out.println(wordCount + " word(s) found, " + charCount + " characters.");
            System.out.println(Constants.DATA_LOADED);

        // Update the details of an employee
        } else if (action.startsWith("u")) {
            System.out.println("Loading data ...");
            String employeeToUpdate = action.substring(1).trim(); // Employee to update
            List<String> employeeList = readEmployeeList();

            for (int i = 0; i < employeeList.size(); i++) {
                if (employeeList.get(i).trim().equals(employeeToUpdate)) {
                    employeeList.set(i, "Updated"); // Update employee name
                }
            }
            writeEmployeeList(employeeList); // Write updated list to file
            System.out.println(Constants.DATA_UPDATED);

        // Delete an employee from the list
        } else if (action.startsWith("d")) {
            System.out.println("Loading data ...");
            String employeeToDelete = action.substring(1).trim(); // Employee to delete
            List<String> employeeList = readEmployeeList();
            employeeList.removeIf(e -> e.trim().equals(employeeToDelete)); // Remove the employee
            writeEmployeeList(employeeList); // Write updated list to file
            System.out.println(Constants.DATA_DELETED);

        // Invalid argument case
        } else {
            System.out.println(Constants.INVALID_ARGUMENT);
            System.out.println("Valid arguments are: l, s, +<name>, ?<name>, c, u<name>, d<name>");
        }
    }

    // Reads the employee list from the file and returns as a List
    private static List<String> readEmployeeList() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            return new ArrayList<>(Arrays.asList(reader.readLine().split(","))); // Split employees by comma
        } catch (Exception ex) {
            ex.printStackTrace(); // Print error if reading fails
        }
        return new ArrayList<>();
    }

    // Writes the updated employee list to the file
    private static void writeEmployeeList(List<String> employeeList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employeeList)); // Join the list as a comma-separated string
        } catch (Exception ex) {
            ex.printStackTrace(); // Print error if writing fails
        }
    }

    // Appends a new employee to the file
    private static void appendEmployee(String employeeName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
            writer.write(", " + employeeName); // Append new employee with a comma
        } catch (Exception ex) {
            ex.printStackTrace(); // Print error if appending fails
        }
    }
}
