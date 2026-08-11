package com.rev.ers.service;

import com.rev.ers.enums.Status;
import com.rev.ers.model.Reimbursement;
import com.rev.ers.model.User;

import java.util.List;

public interface ReimbursementService {
    // Create
    Reimbursement createReimbursement(Reimbursement reimbursement, User author);
    // Read
    List<Reimbursement> queryReimbursements(Status status, Integer departmentId);
    List<Reimbursement> queryReimbursementsByAuthorId(int authorId, Status status);
    Reimbursement queryReimbursementByReimbursementId(int reimbursementId);
    // Update
    Reimbursement updateReimbursement(Reimbursement reimbursement);
    Reimbursement resolveReimbursement(int reimbursementId, User manager, Status status);
}
