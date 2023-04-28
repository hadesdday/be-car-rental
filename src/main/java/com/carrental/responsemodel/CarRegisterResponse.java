package com.carrental.responsemodel;

import com.carrental.requestmodel.ExtraFeeRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarRegisterResponse {
    private Long id;
    private String username;
    private String plate;
    private Long modelId;
    private Long brandId;
    private Integer seats;
    private String color;
    private String fuel;
    private String engine;
    private Double fuelConsumption;
    private String transmission;
    private String rentalStatus;
    private String description;
    private Integer yearOfManufacture;
    private List<Long> featureList;
    private BigInteger defaultPrice; //unit_price
    private Integer discountByWeek;
    private Integer discountByMonth;
    private Boolean isFastRent;
    private String addressName;//
//    private Long deliveryLimit;//distance limit
    private List<ExtraFeeRequest> extraFees;
    private Long serviceTypeId;//wd-domestic;wd-interstate;selfdrive
    private String policies;
    private List<String> imagesList;
}
