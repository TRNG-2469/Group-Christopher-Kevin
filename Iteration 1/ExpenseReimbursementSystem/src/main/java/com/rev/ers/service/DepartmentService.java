package com.rev.ers.service;

import com.rev.ers.model.Department;

import java.util.List;

public interface DepartmentService {
    Department queryDepartmentByDepartmentId(int id);
    List<Department> queryDepartments();
}
