package com.carrental.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ExtraFeeDto {
    private Long id;
    private String name;
    private Long limit;
    private String unit;
    private BigInteger fee;
}
