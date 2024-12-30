package hw_29;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBConnector.initialize();

        EmployeeDAO dao = new EmployeeDAO();

        Scanner sc = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.println("DB menu:\n");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. View All Employees");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter employee name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter employee age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter employee position: ");
                    String position = sc.nextLine();
                    System.out.println("Enter employee salary: ");
                    double salary = sc.nextDouble();

                    dao.add(new Employee(0, name, age, position, salary));
                 break;
                case 2:
                    System.out.println("Enter employee name: ");
                    String newName = sc.nextLine();
                    System.out.println("Enter employee age: ");
                    int newAge = sc.nextInt();
                    System.out.println("Enter employee position: ");
                    String newPosition = sc.nextLine();
                    System.out.println("Enter employee salary: ");
                    double newSalary = sc.nextDouble();

                    dao.update(new Employee(0, newName, newAge, newPosition, newSalary));
                    break;
                case 3:
                    System.out.println("Enter employee ID to be deleted: ");
                    int id = sc.nextInt();

                    dao.delete(id);
                 break;
                case 4:
                    System.out.println("Info about all employees: ");
                    dao.view();
                 break;
                case 5:
                    quit = true;
                 break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
