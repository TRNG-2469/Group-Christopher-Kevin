package com.rev.ers.service;

import com.rev.ers.model.User;
import com.rev.ers.repo.UserDAO;
import com.rev.ers.repo.UserDAOImp;

public class UserServiceImp implements UserService{
    private final UserDAO userDAO;

    public UserServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User authenticate(String username, String password) {
        if(username == null || password == null || username.isEmpty() || password.isEmpty()){
            throw new IllegalArgumentException("Username or password cannot be null or empty.");
        }

        return userDAO.authenticate(username, password);
    }

    @Override
    public User register(User user) {
        if(user.getUsername() == null || user.getUsername().isEmpty()
                || user.getPassword() == null || user.getPassword().isEmpty()){
            throw new IllegalArgumentException("Username or password cannot be null or empty.");

        } else if(user.getFirstName() == null || user.getFirstName().isEmpty()
                || user.getLastName() == null || user.getLastName().isEmpty()){
            throw new IllegalArgumentException("First or last name cannot be null or empty.");

        } else if(user.getDepartment_id() > 0){
            throw new IllegalArgumentException("Department ID cannot be negative.");
        }

        return userDAO.register(user);
    }
}
