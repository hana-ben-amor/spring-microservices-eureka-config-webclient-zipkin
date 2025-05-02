package com.example.department_service.controller;

import com.example.department_service.client.EmployeeClient;
import com.example.department_service.model.Department;
import com.example.department_service.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {
    private static final Logger LOGGER= LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeClient employeeClient;
    @GetMapping
    public ResponseEntity<?> getDepartments()
    {
        LOGGER.info("Departments found");
        return ResponseEntity.ok(departmentRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long id)
    {
        LOGGER.info("Department found {} ",departmentRepository.findById(id));
        return ResponseEntity.ok(departmentRepository.findById(id));
    }
    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody Department dep){
        LOGGER.info("Department added ! {}",dep);
        return ResponseEntity.ok(departmentRepository.addDep(dep));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id)
    {
        LOGGER.info("Department found deleted with id : {} ",id);
        return ResponseEntity.ok(departmentRepository.removeDep(id));
    }

    @GetMapping("/with-employees")
    public ResponseEntity<?> getDepartmentsWithEmployees()
    {
        LOGGER.info("Departments found");
        List<Department> departmentList=
                departmentRepository.findAll();
        departmentList.forEach(department -> {
            department.setEmployees(employeeClient.findByDepId(department.getId()));
        });
        return ResponseEntity.ok(departmentList);
    }


}
