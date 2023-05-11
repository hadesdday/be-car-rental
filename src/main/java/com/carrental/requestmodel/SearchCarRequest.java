package com.carrental.requestmodel;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class SearchCarRequest {
    private Integer pageNo;
    private Integer sortBy;
    private String address;
    private String seats;
    private String yearOfManufacture;
    private Boolean isDiscount;
    private String price;
    private Long brand;
    private String distanceLimit;
    private List<Long> features;
    private Boolean isFastRent;
}
