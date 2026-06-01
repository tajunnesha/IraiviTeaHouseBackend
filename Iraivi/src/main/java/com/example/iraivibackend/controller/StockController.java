package com.example.iraivibackend.controller;

import com.example.iraivibackend.model.Stock;
import com.example.iraivibackend.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "${frontend.url}")
@RequestMapping("/api")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stocks")
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @PostMapping("/stocks/opening")
    public Stock addOpeningStock(
            @RequestParam String itemName,
            @RequestParam double openingStock) {

        return stockService.initializeDailyStock(itemName, openingStock);
    }

    @PostMapping("/stocks/deduct")
    public Stock deductStock(
            @RequestParam String itemName,
            @RequestParam double amountUsed) {

        return stockService.deductStockForOrder(itemName, amountUsed);
    }

    @GetMapping("/stocks/alert/{itemName}")
    public String checkAlert(@PathVariable String itemName) {
        return stockService.checkStockAlert(itemName);
    }
}