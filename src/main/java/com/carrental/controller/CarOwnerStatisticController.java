package com.carrental.controller;

import com.carrental.service.ICarOwnerStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class CarOwnerStatisticController {
    @Autowired
    private ICarOwnerStatService statService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam("username") String username) {
        return ResponseEntity.ok(statService.getStatByOwner(username));
    }
}
