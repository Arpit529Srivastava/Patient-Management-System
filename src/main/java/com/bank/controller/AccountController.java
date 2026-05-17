package com.bank.controller;

import com.bank.entity.Account;
import com.bank.entity.CurrentAccount;
import com.bank.entity.SavingsAccount;
import com.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/savings")
    public ResponseEntity<SavingsAccount> openSavingsAccount(@RequestBody SavingsAccount savingsAccount) {
        SavingsAccount saved = accountService.openSavingsAccount(savingsAccount);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/current")
    public ResponseEntity<CurrentAccount> openCurrentAccount(@RequestBody CurrentAccount currentAccount) {
        CurrentAccount saved = accountService.openCurrentAccount(currentAccount);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccountByNumber(@PathVariable Long accountNumber) {
        Optional<Account> account = accountService.getAccountByNumber(accountNumber);
        return account.map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
