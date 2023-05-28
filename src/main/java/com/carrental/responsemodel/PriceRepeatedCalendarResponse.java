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
public class PriceRepeatedCalendarResponse {
    private Long carId;
    private BigInteger defaultPrice;
    private List<DateValueResponse> priceByDays;
}