package com.rev.ers.model;

import com.rev.ers.enums.Role;

public class Employee extends User{
    private Employee() {
        super(0, "", "", "", "", Role.EMPLOYEE, 0);
    }

    public Employee(int user_id, String username, String password, String firstName, String lastName, int department_id) {
        super(user_id, username, password, firstName, lastName, Role.EMPLOYEE, department_id);
    }
}
