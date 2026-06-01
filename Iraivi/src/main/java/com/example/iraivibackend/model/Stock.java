package com.example.iraivibackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stocks")
@Data
@NoArgsConstructor
public class Stock {

    @Id
    @Column(unique = true)
    private String itemName;

    private double openingStock;

    private double currentStock;

    private double minThreshold;

    public Stock(String itemName,
                 double openingStock,
                 double minThreshold) {

        this.itemName = itemName;
        this.openingStock = openingStock;
        this.currentStock = openingStock;
        this.minThreshold = minThreshold;
    }
}