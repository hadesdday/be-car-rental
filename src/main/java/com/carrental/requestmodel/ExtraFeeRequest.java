package com.carrental.requestmodel;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ExtraFeeRequest {
    private String name;
    private Long limit;
    private String unit;
    private BigInteger fee;
}
