package com.rev.ers.service;

import com.rev.ers.model.Reimbursement;
import com.rev.ers.repo.ReimbursementDAO;
import java.util.List;

public class ReimbursementServiceImp implements ReimbursementService{
    private final ReimbursementDAO reimbursementDAO;

    public ReimbursementServiceImp(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    @Override
    public void create(Reimbursement reimbursement) {
        validation(reimbursement);
        reimbursementDAO.create(reimbursement);
    }

    @Override
    public void update(Reimbursement reimbursement) {
        validation(reimbursement);
        reimbursementDAO.update(reimbursement);
    }

    @Override
    public Reimbursement findByAuthor(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("ID cannot be negative or zero.");
        }
        return reimbursementDAO.findByAuthor(id);
    }

    @Override
    public List<Reimbursement> findAll() {
        return reimbursementDAO.findAll();
    }

    private void validation(Reimbursement reimbursement) {
        if(reimbursement.getReimbursement_id() <= 0){
            throw new IllegalArgumentException("Reimbursement ID cannot be negative or zero.");
        } else if(reimbursement.getAmount() <= 0.0){
            throw new IllegalArgumentException("Reimbursement amount cannot be negative or zero.");
        } else if(reimbursement.getDescription() == null || reimbursement.getDescription().isBlank()){
            throw new IllegalArgumentException("Description cannot be null or blank.");
        } else if(reimbursement.getType() == null || reimbursement.getStatus() == null){
            throw new IllegalArgumentException("Type and status cannot be null.");
        } else if(reimbursement.getAuthor_id() <= 0 || reimbursement.getResolver_id() <= 0){
            throw new IllegalArgumentException("Author or Resolver ID cannot be negative or zero.");
        }
    }
}
