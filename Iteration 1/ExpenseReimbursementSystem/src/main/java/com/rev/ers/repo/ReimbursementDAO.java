package com.rev.ers.repo;

import com.rev.ers.enums.Status;
import com.rev.ers.model.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {
    void create(Reimbursement reimbursement);
    void update(Reimbursement reimbursement);
    List<Reimbursement> findByAuthor(int id, Status status);
    List<Reimbursement> findAll(Status status, Integer departmentId);
}
