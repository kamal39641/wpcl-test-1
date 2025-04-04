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
            System.out.println(employees.get(new Random().nextInt(employees.size())).trim());
            System.out.println(Constants.DATA_LOADED);

        } else if (action.startsWith("+")) {
            System.out.println("Loading data ...");
            appendEmployee(action.substring(1).trim());
            System.out.println(Constants.DATA_LOADED);

        } else if (action.startsWith("?")) {
            System.out.println("Loading data ...");
            String searchName = action.substring(1).trim();
            List<String> employees = readEmployeeList();

            boolean found = employees.stream()
                    .anyMatch(e -> e.trim().equalsIgnoreCase(searchName));

            if (found) {
                System.out.println("✅ Employee found: " + searchName);
            } else {
                System.out.println("❌ Employee not found: " + searchName);
            }

            System.out.println(Constants.DATA_LOADED);

        } else if (action.equals("c")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployeeList();
            System.out.println(employees.size() + " word(s) found " + String.join(",", employees).length());
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
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            return new ArrayList<>(Arrays.asList(reader.readLine().split(",")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
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
