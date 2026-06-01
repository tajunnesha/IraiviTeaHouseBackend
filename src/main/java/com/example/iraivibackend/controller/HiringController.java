package com.example.iraivibackend.controller;

import com.example.iraivibackend.model.HiringModel;
import com.example.iraivibackend.service.HiringService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${frontend.url}")

public class HiringController {

    @Autowired
    private HiringService service;

    @PostMapping("/hiring")
    public ResponseEntity<String> submitHiring(@RequestBody HiringModel hiringData) 
    {
        System.out.println("Received data: " + hiringData.getName());
        service.saveApplication(hiringData);
        return ResponseEntity.ok("Application Success!");
    }
}