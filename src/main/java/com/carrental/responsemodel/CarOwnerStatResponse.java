package com.carrental.responsemodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
public class CarOwnerStatResponse {
    private Double avgRating;
    private Number totalRevenue;
    private Long totalRental;
    private Long totalCar;
    private Long acceptedRentalRating;
    private Long cancelRentalRating;

    public CarOwnerStatResponse(Double avgRating, Number totalRevenue, Long totalRental, Long totalCar, Long acceptedRentalRating, Long cancelRentalRating) {
        this.avgRating = avgRating;
        this.totalRevenue = totalRevenue;
        this.totalRental = totalRental;
        this.totalCar = totalCar;
        this.acceptedRentalRating = acceptedRentalRating;
        this.cancelRentalRating = cancelRentalRating;
    }
}
