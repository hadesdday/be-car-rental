package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "service_fee")
@Getter
@Setter
public class ServiceFeeEntity extends BaseEntity {
    private BigInteger unitPrice;
    private Integer discountByWeek;
    private Integer discountByMonth;

    @OneToOne(mappedBy = "service")
    private CarEntity car;

    @OneToMany(mappedBy = "serviceFee")
    private List<ExtraFeeEntity> extraFeeList;

    @ManyToOne
    @JoinColumn(name = "service_type_id")
    private ServiceTypeEntity serviceType;
}
