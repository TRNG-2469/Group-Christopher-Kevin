package com.rev.ers.service;

import com.rev.ers.model.User;
import com.rev.ers.repo.DepartmentDAO;
import com.rev.ers.repo.UserDAO;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImp implements UserService{
    private final UserDAO userDAO;
    private final DepartmentDAO departmentDAO;

    public UserServiceImp(UserDAO userDAO, DepartmentDAO departmentDAO) {
        this.userDAO = userDAO;
        this.departmentDAO = departmentDAO;
    }

    @Override
    public User searchByUsername(String username) {
        if(username == null || username.isEmpty()){
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        return userDAO.searchByUsername(username);
    }

    @Override
    public User login(String username, String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        User user = searchByUsername(username);
        if(user == null) {
            return null;
        }
        String storedPassword = user.getPassword();
        if(storedPassword == null) {
            return null;
        }
        if(BCrypt.checkpw(password, storedPassword)) {
            return user;
        }
        return null;
    }

    @Override
    public User register(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        if(searchByUsername(user.getUsername()) != null){
            throw new IllegalArgumentException("Username already exists.");
        }
        if(user.getPassword() == null || user.getPassword().isBlank()){
            throw new IllegalArgumentException("Password cannot be null or blank.");
        }
        if(user.getFirstName() == null || user.getFirstName().isBlank() || user.getLastName() == null || user.getLastName().isBlank()){
            throw new IllegalArgumentException("First or last name cannot be null or blank.");
        }
        if(departmentDAO.queryDepartmentByDepartmentId(user.getDepartmentId()) == null){
            throw new IllegalArgumentException("Department ID does not exist.");
        }
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return userDAO.register(user);
    }
}
