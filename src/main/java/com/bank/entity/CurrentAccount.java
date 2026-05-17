package com.bank.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CURRENT")
public class CurrentAccount extends Account {

    private double overdraftLimit;

    public CurrentAccount() {
    }

    public CurrentAccount(Long accountNumber, String accountHolderName, double accountBalance, double overdraftLimit) {
        super(accountNumber, accountHolderName, accountBalance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}
