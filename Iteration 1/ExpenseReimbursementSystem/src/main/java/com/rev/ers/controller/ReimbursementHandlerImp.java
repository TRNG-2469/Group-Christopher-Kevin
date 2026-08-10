package com.rev.ers.controller;

import com.rev.ers.enums.Status;
import com.rev.ers.model.Reimbursement;
import com.rev.ers.service.ReimbursementService;
import io.javalin.http.Context;

import java.util.List;

public class ReimbursementHandlerImp implements ReimbursementHandler {
    private final ReimbursementService reimbursementService;

    public ReimbursementHandlerImp(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    @Override
    public void create(Context ctx) {
        Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
        reimbursementService.create(reimbursement);
        ctx.status(201).result("Reimbursement created successfully.");
    }

    @Override
    public void update(Context ctx) {
        Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
        reimbursementService.update(reimbursement);
        ctx.status(200).result("Reimbursement updated successfully.");
    }

    @Override
    public void findByAuthor(Context ctx) {
        int authorId = 0;
        Status status = null;
        String statusParam = ctx.queryParam("status");
        if (statusParam != null) {
            try {
                status = Status.valueOf(statusParam.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid status.");
            }
        }
        try {
            authorId = Integer.parseInt(ctx.pathParam("id"));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid user ID.");
        }
        List<Reimbursement> reimbursements = reimbursementService.findByAuthor(authorId, status);
        if (!reimbursements.isEmpty()) {
            ctx.status(200).json(reimbursements);
        } else {
            ctx.status(404).result("No reimbursements found.");
        }
    }

    @Override
    public void findAll(Context ctx) {
        int departmentId = 0;
        Status status = null;
        String statusParam = ctx.queryParam("status");
        if (statusParam != null) {
            try {
                status = Status.valueOf(statusParam.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid status.");
            }
        }
        try {
            departmentId = Integer.parseInt(ctx.pathParam("department_id"));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid department ID.");
        }
        List<Reimbursement> reimbursements = reimbursementService.findAll(status, departmentId);
        if (!reimbursements.isEmpty()) {
            ctx.status(200).json(reimbursements);
        } else {
            ctx.status(404).result("No reimbursements found.");
        }
    }
}
