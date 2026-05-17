package com.bank.repository;

import com.bank.entity.Account;
import com.bank.entity.AtmCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtmCardRepository extends JpaRepository<AtmCard, Long> {

    Optional<AtmCard> findByAccount(Account account);

    @Query("SELECT a FROM Account a JOIN a.atmCard c WHERE c.cardType = :cardType")
    List<Account> findAccountsByCardType(@Param("cardType") String cardType);

    boolean existsByAccount(Account account);
}
