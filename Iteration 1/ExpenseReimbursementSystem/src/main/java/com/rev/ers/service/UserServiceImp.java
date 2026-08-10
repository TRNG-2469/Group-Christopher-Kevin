package com.rev.ers.service;

import com.rev.ers.model.User;
import com.rev.ers.repo.DepartmentDAO;
import com.rev.ers.repo.UserDAO;

public class UserServiceImp implements UserService{
    private final UserDAO userDAO;
    private final DepartmentDAO departmentDAO;

    public UserServiceImp(UserDAO userDAO, DepartmentDAO departmentDAO) {
        this.userDAO = userDAO;
        this.departmentDAO = departmentDAO;
    }

    @Override
    public User searchByUsername(String username) {
        // Ensures username input is valid
        if(username == null || username.isEmpty()){
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        return userDAO.searchByUsername(username);
    }

    @Override
    public User authenticate(String username, String password) {
        // Ensures username and password inputs are valid
        if(username == null || password == null || username.isEmpty() || password.isEmpty()){
            throw new IllegalArgumentException("Username or password cannot be null or empty.");
        }
        return userDAO.authenticate(username, password);
    }

    @Override
    public User register(User user) {
        // Ensures user is not null
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        // Ensures username does not already exist and username input is valid
        if(searchByUsername(user.getUsername()) != null){
            throw new IllegalArgumentException("Username already exists.");
        }
        // Ensures password is not null or empty
        if(user.getPassword() == null || user.getPassword().isBlank()){
            throw new IllegalArgumentException("Username or password cannot be null or blank.");
        }
        // Ensures first and last name are not null or empty
        if(user.getFirstName() == null || user.getFirstName().isBlank()
                || user.getLastName() == null || user.getLastName().isBlank()){
            throw new IllegalArgumentException("First or last name cannot be null or blank.");
        }
        // Ensures department ID exists
        if(departmentDAO.findDepartmentById(user.getDepartment_id()) == null){
            throw new IllegalArgumentException("Department ID does not exist.");
        }
        return userDAO.register(user);
    }
}
