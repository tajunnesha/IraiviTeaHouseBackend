package com.example.iraivibackend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.iraivibackend.model.Review;
import com.example.iraivibackend.service.ReviewService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "${frontend.url}")
public class ReviewController 
{
    
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@RequestBody @NonNull Review review) 
    {
        Review savedReview = reviewService.saveReview(review);
        return ResponseEntity.ok(savedReview);
    }

    @GetMapping("/all")
    public List<Review> getAllReviews() 
    {
        return reviewService.getAllReviews();
    }
}