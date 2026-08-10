package com.rev.ers.service;

import com.rev.ers.enums.Status;
import com.rev.ers.model.Reimbursement;
import java.util.List;

public interface ReimbursementService {
    void create(Reimbursement reimbursement);
    void update(Reimbursement reimbursement);
    Reimbursement findById(int id);
    List<Reimbursement> findByAuthor(int id, Status status);
    List<Reimbursement> findAll(Status status, Integer departmentId);
}
