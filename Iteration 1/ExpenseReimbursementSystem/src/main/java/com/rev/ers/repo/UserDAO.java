package com.rev.ers.repo;

import com.rev.ers.model.User;

public interface UserDAO {
    public User searchByUsername(String username);
    public String authenticate(String username);
    public User register(User user);
}
