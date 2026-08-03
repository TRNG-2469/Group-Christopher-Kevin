package com.rev.ers.service;

import com.rev.ers.model.User;
import com.rev.ers.repo.UserDAO;
import com.rev.ers.repo.UserDAOImp;

public class UserServiceImp implements UserService{
    @Override
    public User authenticate(String username, String password) {
        UserDAO DAO = new UserDAOImp();
        if(username == null || password == null || username.isEmpty() || password.isEmpty()){
            throw new IllegalArgumentException("Username or password cannot be null or empty.");
        }

        return DAO.authenticate(username, password);
    }

    @Override
    public void register(User user) {
        UserDAO DAO = new UserDAOImp();
        if(user.getUsername() == null || user.getUsername().isEmpty()
                || user.getPassword() == null || user.getPassword().isEmpty()){
            throw new IllegalArgumentException("Username or password cannot be null or empty.");

        } else if(user.getFirstName() == null || user.getFirstName().isEmpty()
                || user.getLastName() == null || user.getLastName().isEmpty()){
            throw new IllegalArgumentException("First or last name cannot be null or empty.");

        } else if(user.getDepartment_id() > 0){
            throw new IllegalArgumentException("Department ID cannot be negative.");
        }

        DAO.register(user);
    }
}
