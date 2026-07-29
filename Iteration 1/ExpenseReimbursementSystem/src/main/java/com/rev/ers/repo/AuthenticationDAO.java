package com.rev.ers.repo;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDAO {
    private final Connection connection;

    public AuthenticationDAO(Connection connection){
        this.connection = connection;
    }

    public boolean authenticate(String username, String password){
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet result = statement.executeQuery()){
                return result.next();
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
