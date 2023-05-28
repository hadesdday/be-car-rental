package com.carrental.responsemodel;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ExtraFeeResponse {
    private String name;
    private Long limitValue;
    private String unit;
    private BigInteger fee;
    private String code;
}
