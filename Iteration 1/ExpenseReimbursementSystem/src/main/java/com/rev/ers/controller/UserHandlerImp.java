package com.rev.ers.controller;

import com.rev.ers.model.User;
import com.rev.ers.service.UserService;
import io.javalin.http.Context;

public class UserHandlerImp implements UserHandler{
    private final UserService userService;

    public UserHandlerImp(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void authenticate(Context ctx) {
        User user = ctx.bodyAsClass(User.class);
        User authenticatedUser = userService.authenticate(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            ctx.status(200).json(authenticatedUser);
        } else {
            ctx.status(401).result("Invalid username or password.");
        }
    }

    @Override
    public void register(Context ctx) {
        User user = ctx.bodyAsClass(User.class);
        User registeredUser = userService.register(user);
        if (registeredUser != null) {
            ctx.status(201).json(registeredUser);
        } else {
            ctx.status(400).result("Failed to register user.");
        }
    }
}
