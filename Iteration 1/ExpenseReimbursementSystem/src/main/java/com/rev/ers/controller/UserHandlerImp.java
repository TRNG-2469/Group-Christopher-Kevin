package com.rev.ers.controller;

import com.rev.ers.service.UserService;

public class UserHandlerImp implements UserHandler{
    private final UserService userService;

    public UserHandlerImp(UserService userService) {
        this.userService = userService;
    }
}
