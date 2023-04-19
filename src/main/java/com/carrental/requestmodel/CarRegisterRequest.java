package com.carrental.requestmodel;

import com.carrental.dto.ExtraFeeDto;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class CarRegisterRequest {
    private String username;
    private String plate;
    private Long modelId;
    private Long brandId;
    private Integer seats;
    private String color;
    private String engine;
    private Double fuelConsumption;
    private String transmission;
    private String rentalStatus;
    private String description;
    private Integer yearOfManufacture;
    private List<Integer> featureList;
    private BigInteger defaultPrice; //unit_price
    private Long deliveryLimit;//distance limit
    private Long discountByWeek;
    private Long discountByMonth;
    private List<ExtraFeeRequest> extraFees;
    private Long serviceTypeId;//wd-domestic;wd-interstate;selfdrive
    private String policies;
    private List<String> imagesList;
}
