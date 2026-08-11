package com.rev.ers.service;

import com.rev.ers.enums.Status;
import com.rev.ers.model.Reimbursement;
import com.rev.ers.model.User;
import com.rev.ers.repo.ReimbursementDAO;
import java.util.List;

public class ReimbursementServiceImp implements ReimbursementService{
    private final ReimbursementDAO reimbursementDAO;

    public ReimbursementServiceImp(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    // Create

    @Override
    public void createReimbursement(Reimbursement reimbursement, User author) {
        validation(reimbursement);
        reimbursement.setAuthor_id(author.getUser_id());
        reimbursementDAO.createReimbursement(reimbursement);
    }

    // Read

    @Override
    public List<Reimbursement> queryReimbursements(Status status, Integer departmentId) {
        return reimbursementDAO.queryReimbursements(status, departmentId);
    }

    @Override
    public Reimbursement queryReimbursementByReimbursementId(int reimbursementId) {
        if(reimbursementId <= 0){
            throw new IllegalArgumentException("Reimbursement ID cannot be negative or zero.");
        }
        return reimbursementDAO.queryReimbursementByReimbursementId(reimbursementId);
    }

    @Override
    public List<Reimbursement> queryReimbursementsByAuthorId(int authorId, Status status) {
        if(authorId <= 0){
            throw new IllegalArgumentException("User ID cannot be negative or zero.");
        }
        return reimbursementDAO.queryReimbursementsByAuthorId(authorId, status);
    }

    // Update

    @Override
    public Reimbursement updateReimbursement(Reimbursement reimbursement) {
        validation(reimbursement);
        if(reimbursement.getReimbursement_id() <= 0){
            throw new IllegalArgumentException("Reimbursement ID cannot be negative or zero.");
        } else if(reimbursement.getType() == null || reimbursement.getStatus() == null){
            throw new IllegalArgumentException("Type and status cannot be null.");
        }
        Reimbursement original = queryReimbursementByReimbursementId(reimbursement.getReimbursement_id());
        if(original == null) {
            throw new IllegalArgumentException("Reimbursement ID not found.");
        }
        if(original.getStatus() == Status.APPROVED || original.getStatus() == Status.DENIED) {
            throw new IllegalArgumentException("Cannot update a reimbursement that has been approved or denied.");
        }
        return reimbursementDAO.updateReimbursement(reimbursement);
    }

    @Override
    public void resolveReimbursement(int id, User manager, Status status) {
        if(status == Status.PENDING) {
            throw new IllegalArgumentException("Cannot set status to PENDING when resolving a reimbursement.");
        }
        Reimbursement original = queryReimbursementByReimbursementId(id);
        if(original == null) {
            throw new IllegalArgumentException("Reimbursement ID not found.");
        }
        if(original.getStatus() == Status.APPROVED || original.getStatus() == Status.DENIED) {
            throw new IllegalArgumentException("Cannot update a reimbursement that has been approved or denied.");
        }
        original.setStatus(status);
        original.setResolver_id(manager.getUser_id());
        reimbursementDAO.updateReimbursement(original);
    }

    private void validation(Reimbursement reimbursement) {
        if(reimbursement.getAmount() <= 0.0){
            throw new IllegalArgumentException("Reimbursement amount cannot be negative or zero.");
        } else if(reimbursement.getDescription() == null || reimbursement.getDescription().isBlank()){
            throw new IllegalArgumentException("Description cannot be null or blank.");
        } else if(reimbursement.getAuthor_id() <= 0){
            throw new IllegalArgumentException("Author ID cannot be negative or zero.");
        }
    }
}
