package com.rev.ers.repo;

import com.rev.ers.model.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {
    // CRUD operations
    void create(Reimbursement employee);
    void update(Reimbursement employee);
    void delete(int id);

    // Read All, Read One
    Reimbursement findByID(int id);
    List<Reimbursement> findAll();
}
