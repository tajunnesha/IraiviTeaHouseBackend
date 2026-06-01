// AccountController.java

package com.example.iraivibackend.controller;

import com.example.iraivibackend.DTO.DailySummary;
import com.example.iraivibackend.model.Account;
import com.example.iraivibackend.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "${frontend.url}")
@RequestMapping("/api/accounts")

public class AccountController {

    @Autowired
    private AccountService service;



    @GetMapping("/daily-summary")

    public DailySummary getDailySummary(
            @RequestParam String date) {

        LocalDate localDate =
                LocalDate.parse(date);

        return service.getDailySummary(localDate);
    }



    @PostMapping("/add-sale")

    public String addSale(
            @RequestParam String description,
            @RequestParam double amount) {

        service.saveOrder(description, amount);

        return "Sale Added Successfully";
    }



    @GetMapping("/today-orders")

    public List<Account> getTodayOrders() {

        return service.getTodayOrders();
    }
}