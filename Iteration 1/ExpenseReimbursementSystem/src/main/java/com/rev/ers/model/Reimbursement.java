package com.rev.ers.model;

import com.rev.ers.enums.Status;
import com.rev.ers.enums.Type;

import java.util.Objects;

public class Reimbursement {
    private int reimbursement_id;
    private double amount;
    private String description;
    private Type type;
    private Status status;
    private int author_id;
    private int resolver_id;

    private Reimbursement() {}

    public Reimbursement(int reimbursement_id, double amount, String description, Type type, Status status,
                         int author_id, int resolver_id) {
        this.reimbursement_id = reimbursement_id;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.status = status;
        this.author_id = author_id;
        this.resolver_id = resolver_id;
    }

    public int getReimbursement_id() {
        return reimbursement_id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public int getResolver_id() {
        return resolver_id;
    }

    public void setResolver_id(int resolver_id) {
        this.resolver_id = resolver_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setReimbursement_id(int reimbursement_id) {
        this.reimbursement_id = reimbursement_id;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursement_id=" + reimbursement_id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", author_id=" + author_id +
                ", resolver_id=" + resolver_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Reimbursement that)) return false;
        return getReimbursement_id() == that.getReimbursement_id() &&
                Double.compare(getAmount(), that.getAmount()) == 0 &&
                getAuthor_id() == that.getAuthor_id() &&
                getResolver_id() == that.getResolver_id() &&
                Objects.equals(getDescription(), that.getDescription()) &&
                getType() == that.getType() &&
                getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReimbursement_id(), getAmount(), getDescription(),
                getType(), getStatus(), getAuthor_id(), getResolver_id());
    }
}
