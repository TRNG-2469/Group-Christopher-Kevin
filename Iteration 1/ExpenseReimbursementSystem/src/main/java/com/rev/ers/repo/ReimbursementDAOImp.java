package com.rev.ers.repo;

import com.rev.ers.enums.Status;
import com.rev.ers.enums.Type;
import com.rev.ers.model.Reimbursement;
import com.rev.ers.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAOImp implements ReimbursementDAO{
    @Override
    public void create(Reimbursement reimbursement) {
        String sql = "INSERT INTO reimbursement VALUES(?, ?, ?, ?, ?, ?)";
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setDouble(1, reimbursement.getAmount());
            prep.setString(2, reimbursement.getDescription());
            prep.setString(3, reimbursement.getType().getDbValue());
            prep.setString(4, reimbursement.getStatus().getDbValue());
            prep.setInt(5, reimbursement.getAuthor_id());
            prep.setInt(6, reimbursement.getResolver_id());
            prep.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Database error", e);
        }
    }

    @Override
    public void update(Reimbursement reimbursement) {
        String sql = "UPDATE reimbursement SET amount = ?, description = ?, type = ?, status = ?, resolver = ?;";
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setDouble(1, reimbursement.getAmount());
            prep.setString(2, reimbursement.getDescription());
            prep.setString(3, reimbursement.getType().getDbValue());
            prep.setString(4, reimbursement.getStatus().getDbValue());
            prep.setInt(5, reimbursement.getResolver_id());
            prep.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Database error", e);
        }
    }

    @Override
    public Reimbursement findById(int id) {
        String sql = "SELECT * FROM reimbursements WHERE id = ?;";
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet result = prep.executeQuery();
            if(result.next()){
                return mapResultSetToReimbursement(result);
            }
        } catch(SQLException e){
            throw new RuntimeException("Database error", e);
        }
        return null;
    }

    @Override
    public List<Reimbursement> findByAuthor(int id, Status status) {
        StringBuilder sql = new StringBuilder("SELECT * FROM reimbursements WHERE author = ?");
        if (status != null) {
            sql.append(" AND status = ?");
        }
        sql.append(";");
        List<Reimbursement> allReimbs = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql.toString());
            prep.setInt(1, id);
            ResultSet result = prep.executeQuery();
            while(result.next()){
                allReimbs.add(mapResultSetToReimbursement(result));
            }
            return allReimbs;
        } catch(SQLException e){
            throw new RuntimeException("Database error", e);
        }
    }

    @Override
    public List<Reimbursement> findAll(Status status, Integer departmentId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM reimbursements WHERE 1 = 1");
        if (status != null) {
            sql.append(" AND status = ?");
        }
        if (departmentId != null) {
            sql.append(" AND department_id = ?");
        }
        sql.append(";");
        List<Reimbursement> allReimbs = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql.toString());
            ResultSet result = prep.executeQuery();
            while(result.next()){
                allReimbs.add(mapResultSetToReimbursement(result));
            }
            return allReimbs;
        } catch(SQLException e){
            throw new RuntimeException("Database error", e);
        }
    }

    private Reimbursement mapResultSetToReimbursement(ResultSet result) throws SQLException {
        int reimbursementID = result.getInt(1);
        double amount = result.getDouble(2);
        String description = result.getString(3);
        Type type = Type.valueOf(result.getString(4));
        Status status = Status.valueOf(result.getString(5));
        int author = result.getInt(6);
        int resolver = result.getInt(7);
        return new Reimbursement(reimbursementID, amount, description, type, status, author, resolver);
    }
}
