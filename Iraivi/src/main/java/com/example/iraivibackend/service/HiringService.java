package com.example.iraivibackend.service;

import com.example.iraivibackend.model.HiringModel;
import com.example.iraivibackend.repository.HiringRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;


@Service
public class HiringService {
    @Autowired
    private HiringRepository repository;

    @Transactional
    public HiringModel saveApplication(@NonNull HiringModel application) {
        return repository.save(application);
    }
}