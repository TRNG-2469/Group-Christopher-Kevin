package com.rev.ers.repo;

import com.rev.ers.model.User;
import com.rev.ers.utils.ConnectionFactory;
import com.rev.ers.utils.UserFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImp implements UserDAO {
    @Override
    public User searchByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, username);

            try (ResultSet result = prep.executeQuery()){
                if (result.next()) {
                    return UserFactory.createUser(result.getInt("id"), result.getString("username"),
                            result.getString("password"), result.getString("first_name"), result.getString("last_name"),
                            result.getString("role"), result.getInt("department_id"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public User authenticate(String username, String password){
        String sql = "SELECT id, username, first_name, last_name, role, department FROM users WHERE username = ? AND password = ?";
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, username);
            prep.setString(2, password);
            try (ResultSet result = prep.executeQuery()){
                if (result.next()) {
                    return UserFactory.createUser(result.getInt("id"), result.getString("username"),
                            result.getString("password"), result.getString("first_name"), result.getString("last_name"),
                            result.getString("role"), result.getInt("department_id"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User register(User user) {
        String sql = "INSERT INTO users (username, password, first_name, last_name, role, deptartment_id) VALUES(?, ?, ?, ?, ?, ?)";
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, user.getUsername());
            prep.setString(2, user.getPassword());
            prep.setString(3, user.getFirstName());
            prep.setString(4, user.getLastName());
            prep.setString(5, user.getRole().getDbValue());
            prep.setInt(6, user.getDepartment_id());
            prep.executeUpdate();
            return user;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
