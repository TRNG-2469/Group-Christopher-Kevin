package com.rev.ers.repo;

import com.rev.ers.model.Department;

import java.util.List;

public interface DepartmentDAO {
    Department queryDepartmentByDepartmentId(int departmentId);
    List<Department> queryDepartments();
}
