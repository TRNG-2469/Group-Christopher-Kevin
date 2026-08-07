package com.rev.ers.repo;

import com.rev.ers.enums.Status;
import com.rev.ers.enums.Type;
import com.rev.ers.model.Department;
import com.rev.ers.model.Reimbursement;
import com.rev.ers.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImp implements DepartmentDAO{
    public DepartmentDAOImp() {};

    @Override
    public Department findDepartmentById(int id) {
        String sql = "SELECT * FROM departments WHERE id = ?;";

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, id);

            ResultSet result = prep.executeQuery();
            if(result.next()) {
                int deptId = result.getInt(1);
                String name = result.getString(2);

                return new Department(deptId, name);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        String sql = "SELECT * FROM departments;";
        List<Department> allDepts = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);

            ResultSet result = prep.executeQuery();
            while(result.next()){
                int deptId = result.getInt(1);
                String name = result.getString(2);

                Department department = new Department(deptId, name);
                allDepts.add(department);
            }
            return allDepts;

        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
