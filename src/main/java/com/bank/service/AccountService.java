package com.bank.service;

import com.bank.entity.Account;
import com.bank.entity.CurrentAccount;
import com.bank.entity.SavingsAccount;
import com.bank.repository.AccountRepository;
import com.bank.repository.CurrentAccountRepository;
import com.bank.repository.SavingsAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    private CurrentAccountRepository currentAccountRepository;

    public SavingsAccount openSavingsAccount(SavingsAccount savingsAccount) {
        SavingsAccount saved = savingsAccountRepository.save(savingsAccount);
        logger.info("Savings account created successfully with account number: {}", saved.getAccountNumber());
        return saved;
    }

    public CurrentAccount openCurrentAccount(CurrentAccount currentAccount) {
        CurrentAccount saved = currentAccountRepository.save(currentAccount);
        logger.info("Current account created successfully with account number: {}", saved.getAccountNumber());
        return saved;
    }

    public List<Account> getAllAccounts() {
        logger.info("Retrieving all accounts");
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountByNumber(Long accountNumber) {
        logger.info("Retrieving account with number: {}", accountNumber);
        return accountRepository.findById(accountNumber);
    }
}
