package com.carrental.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {
    private String plate;
    private String description;
    private Integer yearOfManufacture;
    private Integer seats;
    private String color;
    private String engine;
    private Double fuelConsumption;
    private String transmission;
    private String rentalStatus;
    private String policies;
    private Boolean isFastRent;

    public static void main(String[] args) {

    }
}
