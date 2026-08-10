package com.rev.ers.controller;

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
        int id = Integer.parseInt(ctx.pathParam("id"));
        Reimbursement reimbursement = reimbursementService.findByAuthor(id);
        if (reimbursement != null) {
            ctx.status(200).json(reimbursement);
        } else {
            ctx.status(404).result("Reimbursement not found.");
        }
    }

    @Override
    public void findAll(Context ctx) {
        List<Reimbursement> reimbursements = reimbursementService.findAll();
        if (!reimbursements.isEmpty()) {
            ctx.status(200).json(reimbursements);
        } else {
            ctx.status(404).result("No reimbursements found.");
        }
    }
}
