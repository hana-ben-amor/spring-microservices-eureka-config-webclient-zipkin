package com.example.department_service.model;

public record Employee(Long id,Long departmentId,String name,int age,String position) {
    @Override
    public Long id() {
        return id;
    }

    @Override
    public Long departmentId() {
        return departmentId;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int age() {
        return age;
    }

    @Override
    public String position() {
        return position;
    }
}
