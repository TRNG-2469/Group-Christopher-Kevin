package com.rev.ers.controller;

import com.rev.ers.model.User;
import io.javalin.http.Context;

public interface UserHandler {

    public void login(Context ctx);

    public void register(Context ctx);
}
