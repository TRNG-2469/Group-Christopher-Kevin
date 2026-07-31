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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    @Override
    public Reimbursement findByAuthor(int id) {
        String sql = "SELECT * FROM reimbursements WHERE author = ?;";

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, id);

            ResultSet result = prep.executeQuery();

            while(result.next()){
                int reimbursementID = result.getInt(1);
                double amount = result.getDouble(2);
                String description = result.getString(3);
                Type type = Type.valueOf(result.getString(4));
                Status status = Status.valueOf(result.getString(5));
                int author = result.getInt(6);
                int resolver = result.getInt(7);

                return new Reimbursement(reimbursementID, amount, description, type, status, author, resolver);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reimbursement> findAll() {
        String sql = "SELECT * FROM reimbursements;";
        List<Reimbursement> allReimbs = new ArrayList<>(10);

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);

            ResultSet result = prep.executeQuery();

            while(result.next()){
                int reimbursementID = result.getInt(1);
                double amount = result.getDouble(2);
                String description = result.getString(3);
                Type type = Type.valueOf(result.getString(4));
                Status status = Status.valueOf(result.getString(5));
                int author = result.getInt(6);
                int resolver = result.getInt(7);

                Reimbursement reimbursement = new Reimbursement(reimbursementID, amount, description, type, status, author, resolver);
                allReimbs.add(reimbursement);
            }

            return allReimbs;

        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
