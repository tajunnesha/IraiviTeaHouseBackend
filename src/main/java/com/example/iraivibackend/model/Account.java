package com.example.iraivibackend.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "daily_accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private double amount;

    private String type; // CREDIT / DEBIT

    private String category;

    private LocalDateTime date;

    // AUTO SAVE CURRENT DATE & TIME
    @PrePersist
    public void prePersist() {

        this.date = LocalDateTime.now();
    }
}