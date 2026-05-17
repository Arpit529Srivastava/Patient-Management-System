package com.bank.service;

import com.bank.entity.Account;
import com.bank.entity.AtmCard;
import com.bank.repository.AccountRepository;
import com.bank.repository.AtmCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtmCardService {

    private static final Logger logger = LoggerFactory.getLogger(AtmCardService.class);

    @Autowired
    private AtmCardRepository atmCardRepository;

    @Autowired
    private AccountRepository accountRepository;

    public AtmCard issueAtmCard(Long accountNumber, AtmCard atmCard) {
        Account account = accountRepository.findById(accountNumber).orElse(null);

        if (account == null) {
            logger.error("Account not found with number: {}", accountNumber);
            throw new RuntimeException("Account not found with number: " + accountNumber);
        }

        if (atmCardRepository.existsByAccount(account)) {
            logger.error("ATM card already issued for account number: {}", accountNumber);
            throw new RuntimeException("ATM card already issued for account number: " + accountNumber);
        }

        atmCard.setAccount(account);
        AtmCard savedCard = atmCardRepository.save(atmCard);
        logger.info("ATM card issued successfully. Card number: {} for account: {}", savedCard.getCardNumber(), accountNumber);
        return savedCard;
    }

    public List<Account> getAccountsByCardType(String cardType) {
        logger.info("Retrieving accounts with ATM card type: {}", cardType);
        return atmCardRepository.findAccountsByCardType(cardType);
    }

    public List<AtmCard> getAllAtmCards() {
        logger.info("Retrieving all ATM cards");
        return atmCardRepository.findAll();
    }
}
