package com.rev.ers.utils;

import com.rev.ers.model.Employee;
import com.rev.ers.model.Manager;
import com.rev.ers.model.User;

public class UserFactory {
    public static User createUser(int id, String username, String password, String firstName, String lastName, String role, int deptId) {
        return switch(role.toLowerCase()) {
            case "manager" -> new Manager(id, username, password, firstName, lastName, deptId);
            case "employee" -> new Employee(id, username, password, firstName, lastName, deptId);
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };
    }
}