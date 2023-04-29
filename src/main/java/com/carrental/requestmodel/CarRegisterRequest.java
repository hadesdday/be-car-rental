package com.carrental.requestmodel;

import com.carrental.dto.ExtraFeeDto;
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
public class CarRegisterRequest {
    private String username;
    private String plate;
    private Long modelId;
    private Long brandId;
    private Integer seats;
    private String color;
    private String fuel;
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
    private List<ExtraFeeRequest> extraFees; //over distance limit fee,delivery fee,....
    private Long serviceTypeId;//wd;self-drive
    private String policies;
    private List<String> imagesList;
}
