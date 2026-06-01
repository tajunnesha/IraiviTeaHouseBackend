package com.example.iraivibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.iraivibackend.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> 
{
}
