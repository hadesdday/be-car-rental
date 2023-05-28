package com.carrental.controller;

import com.carrental.entity.CarRentalEntity;
import com.carrental.enums.RentalStatus;
import com.carrental.requestmodel.UpdateRentalStatusRequest;
import com.carrental.responsemodel.RentalCarResponse;
import com.carrental.responsemodel.UserTripResponse;
import com.carrental.service.ICarRentalService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/findAllCalendar")
    public ResponseEntity<?> findAllCalendar(@RequestParam("username") String username) {
        return ResponseEntity.ok(carRentalService.findCalendarByOwner(username));
    }

    @GetMapping("/findAllCalendarByCarOwner")
    public ResponseEntity<?> findAllCalendarByCarOwner(@RequestParam("username") String username, @RequestParam("carId") Long carId) {
        return ResponseEntity.ok(carRentalService.findCalendarByOwnerAndCarId(username, carId));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserTripResponse> findUserTrip(@PathVariable Long userId){
        List<RentalCarResponse> rentalCars = this.carRentalService.findAllByUserIdAndStatus(userId, RentalStatus.PENDING);
        List<RentalCarResponse> rentedCars = this.carRentalService.findAllByUserIdAndStatus(userId, RentalStatus.COMPLETED);
        UserTripResponse result = new UserTripResponse(rentalCars, rentedCars);
        return ResponseEntity.ok(result);
    }
}