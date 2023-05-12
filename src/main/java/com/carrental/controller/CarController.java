package com.carrental.controller;

import com.carrental.constance.SystemConstance;
import com.carrental.entity.CarEntity;
import com.carrental.repository.IExtraFeeRepository;
import com.carrental.requestmodel.CarAdminRequest;
import com.carrental.requestmodel.CarRegisterRequest;
import com.carrental.requestmodel.SearchCarRequest;
import com.carrental.service.ICarImageService;
import com.carrental.service.ICarService;
import com.carrental.service.IExtraFeeService;
import com.carrental.specification.builder.SearchCarBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @PutMapping("/updateCar")
    public ResponseEntity<?> updateCar(@RequestBody CarAdminRequest request) {
        try {
            return ResponseEntity.ok().body(service.updateCar(request));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/searchCar")
    public ResponseEntity<?> searchCar(@RequestBody SearchCarRequest request) {
        SearchCarBuilder builder = new SearchCarBuilder();
        builder
                .with("", request.getAddress(), "address")
                .with("", "", "active")
        ;
        if (request.getSeats() != null) {
            String value = request.getSeats();//already in between format (x-x)
            if (value.contains(">")) {
                value = value.split(">")[1] + "-" + SystemConstance.MAX_SEAT_ALLOWED;
            } else if (value.contains("<")) {
                value = "0-" + value.split("<")[1];
            }
            builder.with("seats", value, "between");
        }

        if (request.getYearOfManufacture() != null) {
            String value = request.getYearOfManufacture();//already in between format (x-x)
            if (value.contains(">")) {
                value = value.split(">")[1] + "-" + SystemConstance.MAX_YEAR_ALLOWED;
            } else if (value.contains("<")) {
                value = "0-" + value.split("<")[1];
            }
            builder.with("yearOfManufacture", value, "between");
        }

        if (request.getIsDiscount() != null) {
            builder.with("", "", "discount");
        }

        String priceRange = request.getPrice();
        if (priceRange.contains("MAX")) {
            priceRange = priceRange.split("-")[0] + "-" + SystemConstance.MAX_PRICE_ALLOWED;
        }
        builder.with("", priceRange, "price");

        if (!ObjectUtils.isEmpty(request.getBrand())) {
            builder.with("", request.getBrand(), "brand");
        }

        if (!ObjectUtils.isEmpty(request.getDistanceLimit())) {
            if (request.getDistanceLimit().equals("noDistanceLimit"))
                builder.with("", "", "noDistanceLimit");
            else
                builder.with("", request.getDistanceLimit(), "distanceLimit");
        }

        if (!ObjectUtils.isEmpty(request.getFeatures())) {
            StringBuilder featureString = new StringBuilder();
            featureString.append(request.getFeatures().stream().map(Object::toString).reduce((i, j) -> i + "-" + j).get());
            builder.with("", featureString, "features");
        }

        if (!ObjectUtils.isEmpty(request.getIsFastRent())) {
            builder.with("isFastRent", request.getIsFastRent(), "equals");
        }

        Date[] dateRange = {request.getStartDate(), request.getEndDate()};
        builder.with("", dateRange, "available");

        if (!ObjectUtils.isEmpty(request.getType())) {
            builder.with("", request.getType(), "type");
        }

        if (!ObjectUtils.isEmpty(request.getTransmission())) {
            builder.with("transmission", request.getTransmission(), "equals");
        }

        if (!ObjectUtils.isEmpty(request.getFuel())) {
            builder.with("fuel", request.getFuel(), "equals");
        }

        if (!ObjectUtils.isEmpty(request.getFuelConsumption())) {
            builder.with("fuelConsumption", request.getFuelConsumption(), "lessThan");
        }

        Specification<CarEntity> spec = builder.build();

        Sort sortBy;
        switch (request.getSortBy()) {
            case 1:
                sortBy = Sort.by("service.defaultPrice").ascending();
                break;
            case 2:
                sortBy = Sort.by("service.defaultPrice").descending();
                break;
            case 3:
                sortBy = Sort.by("avgRating").descending();
                break;
            default:
                sortBy = Sort.unsorted();
                break;
        }

        Pageable pageable = PageRequest.of(request.getPageNo(), 10, sortBy);
        return ResponseEntity.ok(service.searchCar(spec, pageable));
    }
}
