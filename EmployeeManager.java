import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(Constants.ERROR_NO_ARGUMENT);
            return;
        }

        String action = args[0];

        if (action.equals("l")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployeeList();
            for (String employee : employees) {
                System.out.println(employee.trim());
            }
            System.out.println(Constants.DATA_LOADED);

        } else if (action.equals("s")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployeeList();
            Random random = new Random();
            int randomIndex = random.nextInt(employees.size());
            System.out.println(employees.get(randomIndex).trim());
            System.out.println(Constants.DATA_LOADED);

        } else if (action.startsWith("+")) {
            System.out.println("Loading data ...");
            String employeeName = action.substring(1).trim();
            appendEmployee(employeeName);
            System.out.println(Constants.DATA_LOADED);

        } else if (action.startsWith("?")) {
            System.out.println("Loading data ...");
            String searchName = action.substring(1).trim();
            List<String> employees = readEmployeeList();
            boolean found = employees.stream().anyMatch(e -> e.trim().equals(searchName));
            if (found) {
                System.out.println("Employee found!");
            }
            System.out.println(Constants.DATA_LOADED);

        } else if (action.equals("c")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployeeList();
            int wordCount = employees.size();
            int charCount = String.join(",", employees).length();
            System.out.println(wordCount + " word(s) found " + charCount);
            System.out.println(Constants.DATA_LOADED);

        } else if (action.startsWith("u")) {
            System.out.println("Loading data ...");
            String employeeToUpdate = action.substring(1).trim();
            List<String> employees = readEmployeeList();
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).trim().equals(employeeToUpdate)) {
                    employees.set(i, "Updated");
                }
            }
            writeEmployeeList(employees);
            System.out.println(Constants.DATA_UPDATED);

        } else if (action.startsWith("d")) {
            System.out.println("Loading data ...");
            String employeeToDelete = action.substring(1).trim();
            List<String> employees = readEmployeeList();
            employees.removeIf(e -> e.trim().equals(employeeToDelete));
            writeEmployeeList(employees);
            System.out.println(Constants.DATA_DELETED);

        } else {
            System.out.println(Constants.INVALID_ARGUMENT);
        }
    }

    private static List<String> readEmployeeList() {
        List<String> employeeList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            String line = reader.readLine();
            if (line != null) {
                String[] employeeArray = line.split(",");
                employeeList = new ArrayList<>(Arrays.asList(employeeArray));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return employeeList;
    }

    private static void writeEmployeeList(List<String> employeeList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employeeList));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void appendEmployee(String employeeName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
            writer.write(", " + employeeName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
