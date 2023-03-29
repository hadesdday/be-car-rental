package com.carrental.controller;

import com.carrental.entity.CarEntity;
import com.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    @Autowired
    private CarRepository repository;

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @PostMapping("/post")
    public ResponseEntity<?> post(@RequestBody CarEntity car) {
        return ResponseEntity.ok().build();
    }
}
