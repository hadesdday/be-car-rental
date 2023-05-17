package com.carrental.responsemodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarOwnerStatResponse {
    private Double avgRating;
    private BigInteger totalRevenue;
    private Long totalRental;
    private Long totalCar;
    private Number acceptedRentalRating;
    private Number cancelRentalRating;
}
