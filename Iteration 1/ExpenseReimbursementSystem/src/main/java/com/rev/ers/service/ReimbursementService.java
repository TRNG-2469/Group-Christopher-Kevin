package com.rev.ers.service;

import com.rev.ers.model.Reimbursement;
import java.util.List;

public interface ReimbursementService {
    void create(Reimbursement reimbursement);
    void update(Reimbursement reimbursement);
    Reimbursement findByAuthor(int id);
    List<Reimbursement> findAll();
}
