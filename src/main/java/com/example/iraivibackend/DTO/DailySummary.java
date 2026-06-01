package com.example.iraivibackend.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DailySummary {

    private double totalCredit;

    private double totalDebit;

    public DailySummary(double totalCredit, double totalDebit) {

        this.totalCredit = totalCredit;
        this.totalDebit = totalDebit;
    }
}