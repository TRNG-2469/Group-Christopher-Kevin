package com.rev.ers.service;

import com.rev.ers.model.Department;
import com.rev.ers.repo.DepartmentDAO;

import java.util.List;


public class DepartmentServiceImp implements DepartmentService {
    private final DepartmentDAO departmentDAO;

    public DepartmentServiceImp(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Override
    public Department queryDepartmentByDepartmentId(int departmentId) {
        if(departmentId <= 0){
            throw new IllegalArgumentException("Department ID cannot be negative or zero.");
        }
        return departmentDAO.queryDepartmentByDepartmentId(departmentId);
    }

    @Override
    public List<Department> queryDepartments() {
        return departmentDAO.queryDepartments();
    }
}
