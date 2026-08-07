package com.rev.ers.controller;

import com.rev.ers.model.Department;
import io.javalin.http.Context;

import java.util.List;

public interface DepartmentHandler {
    void findDepartmentById(Context ctx);
    void findAll(Context ctx);
}
