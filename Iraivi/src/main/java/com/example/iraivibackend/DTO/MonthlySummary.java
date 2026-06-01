package com.example.iraivibackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlySummary {

    private double totalCredit;
    private double totalExpense;
    private double netBalance;

    private List<CategorySummary> categoryBreakdown;



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategorySummary {

        private String category;
        private double credit;
        private double debit;
    }
}