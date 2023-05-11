package com.carrental.responsemodel;

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
public class SearchCarResponse {
    private Long id;
    private String modelName;
    private Integer yearOfManufacture;
    private String location;
    private BigInteger price;
    private Double avgRating;
    private Long totalCompletedRental;
    private List<FeatureResponse> features;
    private String bannerUrl;
}
