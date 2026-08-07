package com.rev.ers.repo;

import com.rev.ers.model.User;

public interface UserDAO {
    public User searchByUsername(String username);
    public User authenticate(String username, String password);
    public User register(User user);
}
