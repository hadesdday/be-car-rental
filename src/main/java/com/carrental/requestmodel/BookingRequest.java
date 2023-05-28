package com.carrental.requestmodel;

import com.carrental.entity.CarEntity;
import com.carrental.entity.UserEntity;
import com.carrental.enums.RentalStatus;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class BookingRequest {
    private Long startTime;
    private Long endTime;
    private Long userId;
    private Long carId;
    private Long promoId;
}
