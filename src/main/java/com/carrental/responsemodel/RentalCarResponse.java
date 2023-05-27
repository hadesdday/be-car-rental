package com.carrental.responsemodel;

import com.carrental.dto.UserDTO;
import com.carrental.enums.RentalStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RentalCarResponse {
    private Date startDate;
    private Date endDate;

    private RentalStatus status;

    private BigDecimal rentalPrice;

    private CarResponse car;

    private UserDTO user;
}
