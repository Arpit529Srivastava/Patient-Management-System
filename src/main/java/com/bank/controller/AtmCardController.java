package com.bank.controller;

import com.bank.entity.Account;
import com.bank.entity.AtmCard;
import com.bank.service.AtmCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atmcards")
public class AtmCardController {

    @Autowired
    private AtmCardService atmCardService;

    @PostMapping("/{accountNumber}")
    public ResponseEntity<?> issueAtmCard(@PathVariable Long accountNumber, @RequestBody AtmCard atmCard) {
        try {
            AtmCard savedCard = atmCardService.issueAtmCard(accountNumber, atmCard);
            return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cardtype/{cardType}")
    public ResponseEntity<List<Account>> getAccountsByCardType(@PathVariable String cardType) {
        List<Account> accounts = atmCardService.getAccountsByCardType(cardType);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AtmCard>> getAllAtmCards() {
        List<AtmCard> cards = atmCardService.getAllAtmCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }
}
