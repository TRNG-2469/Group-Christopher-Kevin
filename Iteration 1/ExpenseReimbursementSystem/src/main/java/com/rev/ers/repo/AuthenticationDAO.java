package com.rev.ers.repo;

public interface AuthenticationDAO {
    public boolean authenticate(String username, String password);
}
