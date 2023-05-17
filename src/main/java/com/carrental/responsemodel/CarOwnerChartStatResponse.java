package com.carrental.responsemodel;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarOwnerChartStatResponse {
    private Integer month;
    private Integer year;
    private BigDecimal value;

    public CarOwnerChartStatResponse(Integer month, Integer year, BigDecimal value) {
        this.month = month;
        this.year = year;
        this.value = value;
    }
}