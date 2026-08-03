package com.rev.ers;

import com.rev.ers.controller.*;
import com.rev.ers.repo.*;
import com.rev.ers.service.*;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.result("Hello World"));

        UserDAO userDAO = new UserDAOImp();
        UserService userService = new UserServiceImp(userDAO);
        UserHandler userHandler = new UserHandlerImp(userService);

        ReimbursementDAO ReimbursementDAO = new ReimbursementDAOImp();
        ReimbursementService ReimbursementService = new ReimbursementServiceImp(ReimbursementDAO);
        ReimbursementHandler ReimbursementHandler = new ReimbursementHandlerImp(ReimbursementService);
    }
}