package com.carrental.requestmodel;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ExtraFeeRequest {
    private String name;
    private Long limit;//limit distance,...
    private String unit;//km,vnd,..
    private BigInteger fee;
}
