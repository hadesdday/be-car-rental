package com.carrental.controller;

import com.carrental.requestmodel.UpdateRentalStatusRequest;
import com.carrental.service.ICarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/acceptRental")
    public ResponseEntity<?> acceptRental(@RequestBody UpdateRentalStatusRequest request) {
        try {
            return ResponseEntity.ok().body(carRentalService.acceptRental(request));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/rejectRental")
    public ResponseEntity<?> rejectRental(@RequestBody UpdateRentalStatusRequest request) {
        try {
            return ResponseEntity.ok().body(carRentalService.rejectRental(request));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/confirmDeliveredCarToRenter")
    public ResponseEntity<?> confirmDeliveredCarToRenter(@RequestBody UpdateRentalStatusRequest request) {
        try {
            return ResponseEntity.ok().body(carRentalService.confirmDeliveredCarToRenter(request));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/completeRental")
    public ResponseEntity<?> completeRental(@RequestBody UpdateRentalStatusRequest request) {
        try {
            return ResponseEntity.ok().body(carRentalService.completeRental(request));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
