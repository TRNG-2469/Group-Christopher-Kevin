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

    }
}
