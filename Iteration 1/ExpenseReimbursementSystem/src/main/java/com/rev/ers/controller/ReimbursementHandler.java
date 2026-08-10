package com.rev.ers.controller;

import io.javalin.http.Context;

public interface ReimbursementHandler {
    void create(Context ctx);
    void update(Context ctx);

    void findByAuthor(Context ctx);
    void findAll(Context ctx);
}
