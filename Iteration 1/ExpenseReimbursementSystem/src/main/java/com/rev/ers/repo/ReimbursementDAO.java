package com.rev.ers.repo;

import com.rev.ers.model.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {
    // CRUD operations
    void create(Reimbursement reimbursement);
    void update(Reimbursement reimbursement);

    Reimbursement findByAuthor(int id);
    List<Reimbursement> findAll();
}
