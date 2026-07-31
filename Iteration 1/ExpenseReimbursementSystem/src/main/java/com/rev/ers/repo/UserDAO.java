package com.rev.ers.repo;

import com.rev.ers.model.User;

public interface UserDAO {
    public User authenticate(String username, String password);
    public void register(User user);
}
