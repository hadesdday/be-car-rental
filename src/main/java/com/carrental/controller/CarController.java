package com.carrental.controller;

import com.carrental.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private ICarService service;

    @GetMapping("/findByPlate/{plate}")
    public ResponseEntity<?> get(@PathVariable("plate") String plate) {
        try {
            return ResponseEntity.ok().body(service.findByPlate(plate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
