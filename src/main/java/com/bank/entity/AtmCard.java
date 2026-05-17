package com.bank.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "atm_cards")
public class AtmCard {

    @Id
    private Long cardNumber;

    private String cardType;
    private String expiryDate;
    private int cvvNumber;

    @OneToOne
    @JoinColumn(name = "account_number")
    @JsonBackReference
    private Account account;

    public AtmCard() {
    }

    public AtmCard(Long cardNumber, String cardType, String expiryDate, int cvvNumber) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        this.cvvNumber = cvvNumber;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
