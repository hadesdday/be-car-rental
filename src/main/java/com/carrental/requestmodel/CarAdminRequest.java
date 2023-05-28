package com.carrental.requestmodel;

import com.carrental.enums.CarStatus;
import lombok.Data;

import java.math.BigInteger;

@Data
public class CarAdminRequest {
    private Long id;
    private String color;
    private Double price;
    private Long brand;
    private Long model;
    private Long serviceType;
    private CarStatus status;
}
