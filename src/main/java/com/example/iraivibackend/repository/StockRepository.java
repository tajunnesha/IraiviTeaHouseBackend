package com.example.iraivibackend.repository;

import com.example.iraivibackend.model.Stock;

import java.lang.String;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
    // Basic CRUD operations already available through JpaRepository
    Optional<Stock> findByItemName(String itemName);
}