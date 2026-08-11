package com.rev.ers.repo;

import com.rev.ers.enums.Status;
import com.rev.ers.model.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {
    // Create
    Reimbursement createReimbursement(Reimbursement reimbursement);
    // Read
    List<Reimbursement> queryReimbursements(Status status, Integer departmentId);
    List<Reimbursement> queryReimbursementsByAuthorId(int authorId, Status status);
    Reimbursement queryReimbursementByReimbursementId(int reimbursementId);
    // Update
    Reimbursement updateReimbursement(Reimbursement reimbursement);

}
