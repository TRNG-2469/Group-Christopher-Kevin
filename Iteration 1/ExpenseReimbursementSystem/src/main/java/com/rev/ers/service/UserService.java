package com.rev.ers.service;

import com.rev.ers.model.User;

public interface UserService {
    public User authenticate(String username, String password);
    public void register(User user);
}
