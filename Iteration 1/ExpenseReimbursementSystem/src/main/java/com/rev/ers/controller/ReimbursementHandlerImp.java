package com.rev.ers.controller;

import com.rev.ers.service.ReimbursementService;

public class ReimbursementHandlerImp implements ReimbursementHandler {
    private final ReimbursementService reimbursementService;

    public ReimbursementHandlerImp(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }
}
