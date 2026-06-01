// AccountService.java

package com.example.iraivibackend.service;

import com.example.iraivibackend.DTO.DailySummary;
import com.example.iraivibackend.model.Account;
import com.example.iraivibackend.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service

public class AccountService {

    @Autowired
    private AccountRepository repository;



    // SAVE ORDER

    public void saveOrder(String description,
                          double amount) {

        Account account = new Account();

        account.setDescription(description);

        account.setAmount(amount);

        account.setType("CREDIT");

        // IMPORTANT
        account.setCategory("ORDER");

        account.setDate(LocalDateTime.now());

        repository.save(account);
    }



    // DAILY SUMMARY

    public DailySummary getDailySummary(
            LocalDate date) {

        LocalDateTime start =
                date.atStartOfDay();

        LocalDateTime end =
                date.atTime(23, 59, 59);

        List<Account> accounts =
                repository.findByDateRange(start, end);

        double totalCredit =
                accounts.stream()

                        .filter(a -> a.getType()
                                .equalsIgnoreCase("CREDIT"))

                        .mapToDouble(Account::getAmount)

                        .sum();

        double totalDebit =
                accounts.stream()

                        .filter(a -> a.getType()
                                .equalsIgnoreCase("DEBIT"))

                        .mapToDouble(Account::getAmount)

                        .sum();

        return new DailySummary(
                totalCredit,
                totalDebit
        );
    }



    // TODAY ORDERS

    public List<Account> getTodayOrders() {

        LocalDate today = LocalDate.now();

        LocalDateTime start =
                today.atStartOfDay();

        LocalDateTime end =
                today.atTime(23, 59, 59);

        return repository.findByDateRange(start, end);
    }
}