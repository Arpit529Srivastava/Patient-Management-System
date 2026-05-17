package com.bank.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SAVINGS")
public class SavingsAccount extends Account {

    private double minimumBalance;

    public SavingsAccount() {
    }

    public SavingsAccount(Long accountNumber, String accountHolderName, double accountBalance, double minimumBalance) {
        super(accountNumber, accountHolderName, accountBalance);
        this.minimumBalance = minimumBalance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
}
