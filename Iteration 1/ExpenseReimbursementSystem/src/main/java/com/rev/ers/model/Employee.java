package com.rev.ers.model;

import com.rev.ers.enums.Role;

public class Employee extends User{
    private Employee() {
        super(0, "", "", "", "", Role.EMPLOYEE, 0);
    }

    public Employee(int userId, String username, String password, String firstName, String lastName, int departmentId) {
        super(userId, username, password, firstName, lastName, Role.EMPLOYEE, departmentId);
    }
}
