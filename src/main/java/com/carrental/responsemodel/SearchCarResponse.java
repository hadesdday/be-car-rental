package com.carrental.responsemodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCarResponse {
    private Long id;
    private String modelName;
    private Integer yearOfManufacture;
    private String district;
    private BigInteger price;
    private Double avgRating;
}
