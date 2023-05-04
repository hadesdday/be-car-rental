package com.carrental.controller;

import com.carrental.requestmodel.CarRegisterRequest;
import com.carrental.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private ICarService service;

    @PostMapping("/registerNewCar")
    public ResponseEntity<?> registerNewCar(@RequestBody CarRegisterRequest request) {
        try {
            return ResponseEntity.ok().body(service.registerNewCar(request));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findRegisteredCar")
    public ResponseEntity<?> findRegisteredCar(@RequestParam("username") String username) {
        try {
            return ResponseEntity.ok().body(service.findAllRegisteredCar(username));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok().body(service.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
