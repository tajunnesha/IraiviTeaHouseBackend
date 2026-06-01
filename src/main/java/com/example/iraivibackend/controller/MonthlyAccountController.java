package com.example.iraivibackend.controller;

import com.example.iraivibackend.DTO.MonthlySummary;
import com.example.iraivibackend.service.MonthlyAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${frontend.url}")
@RequestMapping("/api")
public class MonthlyAccountController {

    @Autowired
    private MonthlyAccountService service;

    @GetMapping("/monthly-summary")
    public MonthlySummary getMonthlySummary(
            @RequestParam String month) {

        return service.getMonthlySummary(month);
    }
}