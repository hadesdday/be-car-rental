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
    private String name;
    private BigInteger unitPrice;

    @OneToOne(mappedBy = "service")
    private CarEntity car;

    @OneToMany(mappedBy = "serviceFee")
    private List<ExtraFeeEntity> extraFeeList;

    @ManyToOne
    @JoinColumn(name = "service_type_id")
    private ServiceTypeEntity serviceType;
}
