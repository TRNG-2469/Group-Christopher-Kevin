package com.rev.ers.controller;

import com.rev.ers.model.Department;
import com.rev.ers.service.DepartmentService;
import io.javalin.http.Context;

import java.util.List;

public class DepartmentHandlerImp implements DepartmentHandler {
    private final DepartmentService departmentService;

    public DepartmentHandlerImp(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void findDepartmentById(Context ctx) {
        Department department = ctx.bodyAsClass(Department.class);
        Department foundDepartment = departmentService.findDepartmentById(department.getId());
        if (foundDepartment != null) {
            ctx.status(200).json(foundDepartment);
        } else {
            ctx.status(404).result("Department not found.");
        }
    }

    @Override
    public void findAll(Context ctx) {
        List<Department> departments = departmentService.findAll();
        ctx.status(200).json(departments);
    }
}
