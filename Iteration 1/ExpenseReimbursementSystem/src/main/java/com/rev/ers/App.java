package com.rev.ers;

import com.rev.ers.controller.*;
import com.rev.ers.enums.Role;
import com.rev.ers.model.User;
import com.rev.ers.repo.*;
import com.rev.ers.service.*;

import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

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

        app.before(ctx -> {
            logger.info("Attempting request - Method: {} | Path: {}", ctx.method(), ctx.path());
        });

        app.get("/", ctx -> ctx.result("Hello World"));

        app.post("/register", userHandler::register);
        app.post("/login", userHandler::login);
        app.post("/logout", userHandler::logout);

        app.get("/departments/{id}", departmentHandler::findDepartmentById);
        app.get("/departments", departmentHandler::findAll);

        app.before("/reimbursements/*", ctx -> {
            User user = ctx.sessionAttribute("user");
            if (user == null) {
                ctx.status(401).result("You must be logged in.");
                ctx.skipRemainingHandlers();
            }
            if (user.getUserId() != Integer.parseInt(ctx.pathParam("userId"))) {
                ctx.status(403).result("You can only access your own reimbursements.");
                ctx.skipRemainingHandlers();
            }
        });

        app.post("/reimbursements", reimbursementHandler::createReimbursement);
        app.get("/reimbursements/{userId}", reimbursementHandler::queryReimbursementByAuthorId);
        app.patch("/reimbursements/{userId}", reimbursementHandler::updateReimbursement);

        app.before("/manager/*", ctx -> {
            User user = ctx.sessionAttribute("user");
            if (user == null) {
                ctx.status(401).result("You must be logged in.");
                ctx.skipRemainingHandlers();
                return;
            }
            if (user.getRole() != Role.MANAGER) {
                ctx.status(403).result("Manager access required.");
                ctx.skipRemainingHandlers();
            }
        });

        app.get("/manager/reimbursements", reimbursementHandler::queryReimbursements);
        app.patch("/manager/reimbursements/{reimbursementId}", reimbursementHandler::resolveReimbursement);

        app.after(ctx ->  {
            if(ctx.statusCode() == 200 || ctx.statusCode() == 201) {
                logger.info("Request succeeded with status code " + ctx.statusCode());
            } else {
                logger.warn("Request failed with status code " + ctx.statusCode() + ": " +
                        ctx.result());
            }
        });
    }
}