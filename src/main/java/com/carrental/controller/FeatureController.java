package com.carrental.controller;

import com.carrental.service.IFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/features")
public class FeatureController {
    @Autowired
    private IFeatureService service;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/findAllByIdIn")
    public ResponseEntity<?> findAllByIdIn(@RequestParam List<Long> ids) {
        return ResponseEntity.ok().body(service.findAllByIdIn(ids));
    }
}
