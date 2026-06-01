package com.example.iraivibackend.repository;

import com.example.iraivibackend.model.HiringModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HiringRepository extends JpaRepository<HiringModel, Long> 
{
}