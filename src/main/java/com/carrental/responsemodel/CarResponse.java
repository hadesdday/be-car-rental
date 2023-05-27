package com.carrental.responsemodel;

import com.carrental.dto.CarRatingDTO;
import com.carrental.dto.DeliveryAddressDTO;
import com.carrental.dto.UserDTO;
import com.carrental.entity.CarRatingEntity;
import com.carrental.enums.CarStatus;
import lombok.Data;

import java.util.Collection;

@Data
public class CarResponse {
    private String plate;
    private String description;
    private Integer yearOfManufacture;
    private Integer seats;
    private String color;
    private String fuel;
    private Double fuelConsumption;
    private String transmission;
    private String policies;
    private Boolean isFastRent;
    private CarStatus status;
    private ModelDTO model;
    private Collection<FeatureDTO> features;;
    private UserDTO user;
    private DeliveryAddressDTO address;
    private Collection<CarRatingDTO> ratings;
    private Boolean isFav;
    private String serviceType;
    private ServiceFeeResponse service;
}
