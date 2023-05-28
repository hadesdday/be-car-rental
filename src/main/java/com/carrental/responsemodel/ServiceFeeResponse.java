package com.carrental.responsemodel;

import com.carrental.entity.ExtraFeeEntity;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class ServiceFeeResponse {
    private Double defaultPrice;
    private Integer discountByWeek;
    private Integer discountByMonth;
    private List<ExtraFeeResponse> extraFeeList;
}
