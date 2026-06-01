package com.example.iraivibackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.example.iraivibackend.model.Review;
import com.example.iraivibackend.repository.ReviewRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService 
{
    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public Review saveReview(@NonNull Review review) 
    {
        return reviewRepository.saveAndFlush(review);    
    }

    public List<Review> getAllReviews() 
    {
        return reviewRepository.findAll();
    }
}