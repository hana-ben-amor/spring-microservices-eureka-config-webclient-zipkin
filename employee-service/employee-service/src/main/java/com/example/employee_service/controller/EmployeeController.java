package com.example.employee_service.controller;

import com.example.employee_service.model.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee emp) {
        return repository.addEmployee(emp);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Employee not found"));
    }

    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable Long id) {
        Employee removed = repository.removeById(id);
        if (removed == null) {
            throw new IllegalStateException("Employee not found for deletion");
        }
        return removed;
    }

    @GetMapping("/department/{dep_id}")
    public List<Employee> findByDepId(@PathVariable Long dep_id)
    {
        return repository.findByDepartmentId(dep_id);
    }
}
