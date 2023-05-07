package com.carrental.controller;

import com.carrental.service.ICarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rentals")
public class CarRentalController {
    @Autowired
    private ICarRentalService carRentalService;

    @GetMapping("/findByOwner")
    public ResponseEntity<?> findByOwner(@RequestParam("username") String username) {
        try {
            return ResponseEntity.ok().body(carRentalService.findByOwner(username));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam("id") Long id) {
        try {
            return ResponseEntity.ok().body(carRentalService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
