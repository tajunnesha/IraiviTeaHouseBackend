package com.example.iraivibackend.service;

import com.example.iraivibackend.model.Stock;
import com.example.iraivibackend.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;



    // GET ALL STOCKS

    public List<Stock> getAllStocks() {

        return stockRepository.findAll();
    }



    // INITIALIZE DAILY STOCK

    @Transactional
    public Stock initializeDailyStock(
            String itemName,
            double openingStock) {

        Stock stock = stockRepository
                .findByItemName(itemName)

                .orElse(new Stock(
                        itemName,
                        openingStock,
                        1.0
                ));

        stock.setOpeningStock(openingStock);

        stock.setCurrentStock(openingStock);

        return stockRepository.save(stock);
    }



    // DEDUCT STOCK WHEN ORDER HAPPENS

    @Transactional
    public Stock deductStockForOrder(
            String itemName,
            double amountUsed) {

        System.out.println(
                "Updating stock for: " + itemName);

        System.out.println(
                "Used Quantity: " + amountUsed);

        Optional<Stock> optionalStock =
                stockRepository.findByItemName(itemName);

        if (optionalStock.isEmpty()) {

            System.out.println(
                    "Item NOT FOUND in DB: " + itemName);

            return null;
        }

        Stock stock = optionalStock.get();

        double currentStock =
                stock.getCurrentStock();

        double newStock =
                currentStock - amountUsed;

        // Prevent negative stock

        if (newStock < 0) {

            newStock = 0;
        }

        stock.setCurrentStock(newStock);

        Stock updatedStock =
                stockRepository.save(stock);

        System.out.println(
                "New Stock: "
                        + updatedStock.getCurrentStock());

        return updatedStock;
    }



    // TEA ORDER PROCESS

    @Transactional
    public void processTeaOrder(int qty) {

        deductStockForOrder(
                "Tea Powder",
                qty * 0.01
        );

        deductStockForOrder(
                "Milk",
                qty * 0.1
        );

        deductStockForOrder(
                "Sugar",
                qty * 0.005
        );
    }



    // COFFEE ORDER PROCESS

    @Transactional
    public void processCoffeeOrder(int qty) {

        deductStockForOrder(
                "Coffee Powder",
                qty * 0.008
        );

        deductStockForOrder(
                "Milk",
                qty * 0.1
        );

        deductStockForOrder(
                "Sugar",
                qty * 0.005
        );
    }



    // BISCUIT ORDER PROCESS

    @Transactional
    public void processBiscuitOrder(
            String variety,
            int qty) {

        double usagePerPiece = 0.025;

        deductStockForOrder(
                variety + " Biscuit",
                qty * usagePerPiece
        );
    }



    // PUFF ORDER PROCESS

    @Transactional
    public void processPuffOrder(int qty) {

        deductStockForOrder(
                "Wheat",
                qty * 0.05
        );
    }



    // CHECK STOCK ALERT

    public String checkStockAlert(
            String itemName) {

        Optional<Stock> optionalStock =
                stockRepository.findByItemName(itemName);

        if (optionalStock.isEmpty()) {

            return "Item Not Found";
        }

        Stock stock = optionalStock.get();

        if (stock.getCurrentStock()
                <= stock.getMinThreshold()) {

            return "⚠️ Refill Required! ("
                    + itemName + ")";
        }

        return "Sufficient";
    }
}