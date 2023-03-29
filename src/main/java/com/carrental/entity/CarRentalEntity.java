package com.carrental.entity;

import com.carrental.enums.RentalStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "car_rental")
@Getter
@Setter
public class CarRentalEntity extends BaseEntity {
    private Date startDate;
    private Date endDate;

    @Enumerated(EnumType.ORDINAL)
    private RentalStatus status;

    private BigDecimal rentalPrice;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
