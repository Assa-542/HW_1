package task4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDirectory {
    private List<Employee> employees;

    public EmployeeDirectory() {
        this.employees = new ArrayList<>();
    }


    public void addEmployee(Employee employee) {
        employees.add(employee);
    }


    public List<Employee> findEmployeesByExperience(int experience) {
        return employees.stream()
                .filter(e -> e.getExperience() == experience)
                .collect(Collectors.toList());
    }

    public List<String> getPhoneNumbersByName(String name) {
        return employees.stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .map(Employee::getPhoneNumber)
                .collect(Collectors.toList());
    }

    public Employee findEmployeeById(String employeeId) {
        return employees.stream()
                .filter(e -> e.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "EmployeeDirectory{" +
                "employees=" + employees +
                '}';
    }
}

