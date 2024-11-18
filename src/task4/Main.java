package task4;

public class Main {
    public static void main(String[] args) {
        EmployeeDirectory directory = new EmployeeDirectory();

        directory.addEmployee(new Employee("001", "1234567890", "John Doe", 5));
        directory.addEmployee(new Employee("002", "0987654321", "Jane Smith", 3));
        directory.addEmployee(new Employee("003", "1122334455", "Jim Beam", 5));
        directory.addEmployee(new Employee("004", "2233445566", "Jill White", 2));

        System.out.println("Employees with 5 years of experience: " + directory.findEmployeesByExperience(5));

        System.out.println("Phone numbers of employees named John Doe: " + directory.getPhoneNumbersByName("John Doe"));

        System.out.println("Employee with ID 003: " + directory.findEmployeeById("003"));

        System.out.println("Employee Directory: " + directory);
    }
}

