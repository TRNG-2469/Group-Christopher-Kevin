package com.rev.ers.model;

import com.rev.ers.enums.Role;

public class Manager extends User{
    private Manager() {
        super(0, "", "", "", "", Role.MANAGER, 0);
    }

    public Manager(int userId, String username, String password, String firstName, String lastName, int departmentId) {
        super(userId, username, password, firstName, lastName, Role.MANAGER, departmentId);
    }
}
