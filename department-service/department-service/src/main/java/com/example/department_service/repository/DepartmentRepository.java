package com.example.department_service.repository;

import com.example.department_service.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository {

    private List<Department> departments=
            new ArrayList<>();


    public Department addDep(Department dep){
        departments.add(dep);
        return dep;
    }

    public List<Department> findAll()
    {
        return departments;
    }


    public List<Department> removeDep(Long id) {
        departments.removeIf(dep -> dep.getId()==id);
        return departments;
    }

    public Department findById(long id){
        return departments
                .stream()
                .filter(dep-> dep.getId()==id)
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("Department not found..."));
    }

}
