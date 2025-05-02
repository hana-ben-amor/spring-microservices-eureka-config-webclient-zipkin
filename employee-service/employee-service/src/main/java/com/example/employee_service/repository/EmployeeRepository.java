package com.example.employee_service.repository;

import com.example.employee_service.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {
    public List<Employee> employees= new ArrayList<>();

    public Employee addEmployee(Employee emp) {
        employees.add(emp);
        return emp;
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Optional<Employee> findById(Long id) {
        return employees.stream()
                .filter(emp -> emp.getId() != null && emp.getId().equals(id))
                .findFirst();
    }

    public Employee removeById(Long id) {
        Optional<Employee> toRemove = findById(id);
        toRemove.ifPresent(employees::remove);
        return toRemove.orElse(null);
    }
    public List<Employee> findByDepartmentId(Long departmentId) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getDepartmentId() != null && emp.getDepartmentId().equals(departmentId)) {
                result.add(emp);
            }
        }
        return result;
    }

}
