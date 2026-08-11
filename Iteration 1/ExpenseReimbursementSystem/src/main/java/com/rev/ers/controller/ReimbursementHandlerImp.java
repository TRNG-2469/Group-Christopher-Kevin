package com.rev.ers.controller;

import com.rev.ers.enums.Status;
import com.rev.ers.model.Reimbursement;
import com.rev.ers.model.User;
import com.rev.ers.service.ReimbursementService;
import io.javalin.http.Context;

import java.util.List;

public class ReimbursementHandlerImp implements ReimbursementHandler {
    private final ReimbursementService reimbursementService;

    public ReimbursementHandlerImp(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    // Create

    @Override
    public void createReimbursement(Context ctx) {
        User author = ctx.sessionAttribute("user");
        Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
        reimbursementService.createReimbursement(reimbursement, author);
        ctx.status(201).result("Reimbursement created successfully.");
    }

    // Read

    @Override
    public void queryReimbursements(Context ctx) {
        Integer departmentId = ctx.queryParam("departmentId") != null ? Integer.valueOf(ctx.queryParam("departmentId")) : null;
        Status status = null;
        if (ctx.queryParam("status") != null) {
            try {
                status = Status.valueOf(ctx.queryParam("status").toUpperCase());
            } catch (IllegalArgumentException e) {
                ctx.status(400).result("Invalid status: " + ctx.queryParam("status"));
                return;
            }
        }
        List<Reimbursement> reimbursements = reimbursementService.queryReimbursements(status, departmentId);
        if (!reimbursements.isEmpty()) {
            ctx.status(200).json(reimbursements);
        } else {
            ctx.status(404).result("No reimbursements found.");
        }
    }

    @Override
    public void queryReimbursementByAuthorId(Context ctx) {
        int authorId = Integer.parseInt(ctx.pathParam("authorId"));
        Status status = null;
        if (ctx.queryParam("status") != null) {
            try {
                status = Status.valueOf(ctx.queryParam("status").toUpperCase());
            } catch (IllegalArgumentException e) {
                ctx.status(400).result("Invalid status: " + ctx.queryParam("status"));
                return;
            }
        }
        List<Reimbursement> reimbursements = reimbursementService.queryReimbursementsByAuthorId(authorId, status);
        if (!reimbursements.isEmpty()) {
            ctx.status(200).json(reimbursements);
        } else {
            ctx.status(404).result("No reimbursements found.");
        }
    }

    // Update

    @Override
    public void updateReimbursement(Context ctx) {
        Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
        Reimbursement updatedReimbursement = reimbursementService.updateReimbursement(reimbursement);
        ctx.status(200).json(updatedReimbursement);
    }

    @Override
    public void resolveReimbursement(Context ctx) {
        int reimbursementId = Integer.parseInt(ctx.pathParam("reimbursementId"));
        Status status = null;
        if (ctx.queryParam("status") != null) {
            try {
                status = Status.valueOf(ctx.queryParam("status").toUpperCase());
            } catch (IllegalArgumentException e) {
                ctx.status(400).result("Invalid status: " + ctx.queryParam("status"));
                return;
            }
        }
        User manager = ctx.sessionAttribute("user");
        reimbursementService.resolveReimbursement(reimbursementId, manager, status);
        ctx.status(200).result("Reimbursement updated successfully.");
    }
}
