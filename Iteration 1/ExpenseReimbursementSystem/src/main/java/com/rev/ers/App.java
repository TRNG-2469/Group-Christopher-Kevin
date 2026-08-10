package com.rev.ers;

import com.rev.ers.controller.*;
import com.rev.ers.repo.*;
import com.rev.ers.service.*;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImp();
        DepartmentDAO departmentDAO = new DepartmentDAOImp();
        ReimbursementDAO reimbursementDAO = new ReimbursementDAOImp();

        UserService userService = new UserServiceImp(userDAO, departmentDAO);
        DepartmentService departmentService = new DepartmentServiceImp(departmentDAO);
        ReimbursementService reimbursementService = new ReimbursementServiceImp(reimbursementDAO);

        UserHandler userHandler = new UserHandlerImp(userService);
        DepartmentHandler departmentHandler = new DepartmentHandlerImp(departmentService);
        ReimbursementHandler reimbursementHandler = new ReimbursementHandlerImp(reimbursementService);

        Javalin app = Javalin.create().start(7000);

        app.get("/", ctx -> ctx.result("Hello World"));

        app.post("/register", userHandler::register);
        app.post("/login", userHandler::authenticate);

        app.get("/departments/{id}", departmentHandler::findDepartmentById);
        app.get("/departments", departmentHandler::findAll);

        app.post("/reimbursements", reimbursementHandler::create);
        app.put("/reimbursements/{id}", reimbursementHandler::update);
        app.get("/reimbursements", reimbursementHandler::findAll);
        app.get("/reimbursements/{id}", reimbursementHandler::findByAuthor);
    }
}