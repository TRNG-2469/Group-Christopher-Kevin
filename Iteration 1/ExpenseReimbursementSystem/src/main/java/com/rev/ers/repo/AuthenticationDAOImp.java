package com.rev.ers.repo;

import com.rev.ers.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDAOImp implements AuthenticationDAO{

    public boolean authenticate(String username, String password){
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, username);
            prep.setString(2, password);

            try (ResultSet result = prep.executeQuery()){
                return result.next();
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
