package com.rev.ers.service;

import com.rev.ers.model.User;

public interface UserService {
    public User searchByUsername(String username);
    public boolean authenticate(String username, String password);
    public User register(User user);
}
