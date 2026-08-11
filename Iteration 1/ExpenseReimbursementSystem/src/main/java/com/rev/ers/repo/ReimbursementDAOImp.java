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

    // Create

    @Override
    public void createReimbursement(Reimbursement reimbursement) {
        String sql = "INSERT INTO reimbursement(amount, description, type, author_id) VALUES(?, ?, ?, ?)";
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setDouble(1, reimbursement.getAmount());
            prep.setString(2, reimbursement.getDescription());
            prep.setString(3, reimbursement.getType().getDbValue());
            prep.setInt(4, reimbursement.getAuthor_id());
            prep.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Database error", e);
        }
    }

    // Read

    @Override
    public Reimbursement queryReimbursementByReimbursementId(int id) {
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
    public List<Reimbursement> queryReimbursementsByAuthorId(int id, Status status) {
        StringBuilder sql = new StringBuilder("SELECT * FROM reimbursements WHERE author = ?");
        if (status != null) {
            sql.append(" AND status = ?");
        }
        sql.append(";");
        List<Reimbursement> allReimbs = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql.toString());
            int paramIndex = 1;
            prep.setInt(paramIndex++, id);
            if (status != null) {
                prep.setString(paramIndex++, status.getDbValue());
            }
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
    public List<Reimbursement> queryReimbursements(Status status, Integer departmentId) {
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
            int paramIndex = 1;
            if (status != null) {
                prep.setString(paramIndex++, status.getDbValue());
            }
            if (departmentId != null) {
                prep.setInt(paramIndex++, departmentId);
            }
            ResultSet result = prep.executeQuery();
            while(result.next()){
                allReimbs.add(mapResultSetToReimbursement(result));
            }
            return allReimbs;
        } catch(SQLException e){
            throw new RuntimeException("Database error", e);
        }
    }

    // Update

    @Override
    public Reimbursement updateReimbursement(Reimbursement reimbursement) {
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
        return reimbursement;
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
