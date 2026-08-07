package com.rev.ers.service;

import com.rev.ers.model.Department;

import java.util.List;

public interface DepartmentService {
    Department findDepartmentById(int id);
    List<Department> findAll();
}
