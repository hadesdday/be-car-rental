package com.carrental.responsemodel;

import com.carrental.enums.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarAdminResponse {
    private Long id;
    private Date createdDate;
    private String color;
    private String plate;
    private BigInteger price;
    private IdNameResponse brand;
    private IdNameResponse model;
    private IdNameResponse serviceType;
    private CarStatus status;
}
