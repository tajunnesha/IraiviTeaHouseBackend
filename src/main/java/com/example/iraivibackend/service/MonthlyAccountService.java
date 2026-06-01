package com.example.iraivibackend.service;

import com.example.iraivibackend.model.Account;
import com.example.iraivibackend.DTO.MonthlySummary;
import com.example.iraivibackend.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MonthlyAccountService {

    @Autowired
    private AccountRepository repository;

    public MonthlySummary getMonthlySummary(
            String month) {

        List<Account> accounts =
                repository.findByMonth(month);

        double totalCredit = 0;

        double totalExpense = 0;

        Map<String, List<Account>> grouped =
                accounts.stream()
                        .collect(Collectors.groupingBy(
                                acc -> {

                                    if(acc.getCategory() == null || acc.getCategory().isBlank()) {

                                        return "ORDER";
                                    }

                                    return acc.getCategory();
                                }
                        ));

        List<MonthlySummary.CategorySummary>
                categoryList = new ArrayList<>();

        for (String category : grouped.keySet()) {

            List<Account> categoryAccounts =
                    grouped.get(category);

            double credit =
                    categoryAccounts.stream()

                            .filter(a ->
                                    "CREDIT".equalsIgnoreCase(
                                            a.getType()))

                            .mapToDouble(Account::getAmount)

                            .sum();

            double debit =
                    categoryAccounts.stream()

                            .filter(a ->
                                    "DEBIT".equalsIgnoreCase(
                                            a.getType()))

                            .mapToDouble(Account::getAmount)

                            .sum();

            totalCredit += credit;

            totalExpense += debit;

            categoryList.add(

                    new MonthlySummary.CategorySummary(

                            category,

                            credit,

                            debit
                    )
            );
        }

        return new MonthlySummary(

                totalCredit,

                totalExpense,

                totalCredit - totalExpense,

                categoryList
        );
    }
}