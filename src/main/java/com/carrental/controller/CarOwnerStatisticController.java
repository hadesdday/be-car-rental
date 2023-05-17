package com.carrental.controller;

import com.carrental.requestmodel.CarOwnerChartStatRequest;
import com.carrental.service.ICarOwnerStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statistics")
public class CarOwnerStatisticController {
    @Autowired
    private ICarOwnerStatService statService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam("username") String username) {
        return ResponseEntity.ok(statService.getStatByOwner(username));
    }

    @PostMapping("/getChartData")
    public ResponseEntity<?> getChartDate(@RequestBody CarOwnerChartStatRequest req) {
        return ResponseEntity.ok(statService.getChartStats(req.getCategory(), req.getUsername(),
                req.getStartDate(), req.getEndDate()));
    }
}
