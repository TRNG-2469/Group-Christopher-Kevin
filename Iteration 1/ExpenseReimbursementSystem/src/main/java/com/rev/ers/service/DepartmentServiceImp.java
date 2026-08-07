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
    public Department findDepartmentById(int id) {
        return departmentDAO.findDepartmentById(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentDAO.findAll();
    }
}
