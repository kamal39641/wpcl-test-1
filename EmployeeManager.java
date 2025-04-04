import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: No arguments provided. Please provide a valid argument.");
            return;
        }

        String action = args[0];

        if (action.equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employeeArray = line.split(",");
                for (String employee : employeeArray) {
                    System.out.println(employee);
                }
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (action.equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employeeArray = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employeeArray.length);
                System.out.println(employeeArray[randomIndex]);
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (action.startsWith("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt", true));
                String employeeName = action.substring(1);
                writer.write(", " + employeeName);
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (action.startsWith("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employeeArray = line.split(",");
                String searchName = action.substring(1).trim();
                boolean found = false;
                for (String employee : employeeArray) {
                    if (employee.trim().equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (action.equals("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                char[] characters = line.toCharArray();
                int wordCount = 1; // Assuming there's at least one word
                for (char character : characters) {
                    if (character == ',') {
                        wordCount++;
                    }
                }
                System.out.println(wordCount + " word(s) found " + characters.length);
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (action.startsWith("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employeeArray = line.split(",");
                String employeeToUpdate = action.substring(1).trim();
                for (int i = 0; i < employeeArray.length; i++) {
                    if (employeeArray[i].trim().equals(employeeToUpdate)) {
                        employeeArray[i] = "Updated";
                    }
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
                writer.write(String.join(",", employeeArray));
                writer.close();
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Updated.");
        } else if (action.startsWith("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employeeArray = line.split(",");
                String employeeToDelete = action.substring(1).trim();
                List<String> employeeList = new ArrayList<>(Arrays.asList(employeeArray));
                employeeList.removeIf(employee -> employee.trim().equals(employeeToDelete));
                BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
                writer.write(String.join(",", employeeList));
                writer.close();
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Deleted.");
        } else {
            System.out.println("Invalid argument provided. Please try again.");
        }
    }
}
