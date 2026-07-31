package com.rev.ers.model;

import com.rev.ers.enums.Role;

public class Manager extends User{
    private Manager() {
        super(0, "", "", "", "", Role.MANAGER, 0);
    }

    public Manager(int user_id, String username, String password, String firstName, String lastName, int department_id) {
        super(user_id, username, password, firstName, lastName, Role.MANAGER, department_id);
    }
}
