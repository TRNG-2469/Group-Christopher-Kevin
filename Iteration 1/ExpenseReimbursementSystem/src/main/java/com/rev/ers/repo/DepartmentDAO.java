package com.rev.ers.repo;

import com.rev.ers.model.Department;

import java.util.List;

public interface DepartmentDAO {
    Department findDepartmentById(int id);
    List<Department> findAll();
}
