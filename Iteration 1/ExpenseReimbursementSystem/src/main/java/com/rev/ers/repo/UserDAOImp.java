package com.rev.ers.repo;

import com.rev.ers.model.Employee;
import com.rev.ers.model.Manager;
import com.rev.ers.model.User;
import com.rev.ers.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImp implements UserDAO {

    public User authenticate(String username, String password){
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, username);
            prep.setString(2, password);

            try (ResultSet result = prep.executeQuery()){
                while(result.next()){
                    if(result.getString(6).equals("manager")){
                        return new Manager(result.getInt(1), // user id
                            result.getString(2), // username
                            result.getString(3), // password
                            result.getString(4), // first name
                            result.getString(5), // last name
                            result.getInt(7)); // dept id
                    } else {
                        return new Employee(result.getInt(1), // user id
                            result.getString(2), // username
                            result.getString(3), // password
                            result.getString(4), // first name
                            result.getString(5), // last name
                            result.getInt(7)); // dept id
                    }
                }
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void register(User user) {
        String sql = "INSERT INTO users VALUES(?, ?, ?, ?, ?, ?)";

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, user.getUsername());
            prep.setString(2, user.getPassword());
            prep.setString(3, user.getFirstName());
            prep.setString(4, user.getLastName());
            prep.setString(5, user.getRole().getDbValue());
            prep.setInt(6, user.getDepartment_id());

            prep.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
