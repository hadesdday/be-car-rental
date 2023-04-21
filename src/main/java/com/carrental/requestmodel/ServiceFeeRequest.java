package com.carrental.requestmodel;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ServiceFeeRequest {
    private Long serviceTypeId;
    private BigInteger defaultPrice;
    private Integer discountByWeek;
    private Integer discountByMonth;
}
