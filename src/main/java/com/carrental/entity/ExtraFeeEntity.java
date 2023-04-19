package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "extra_fee")
@Getter
@Setter
public class ExtraFeeEntity extends BaseEntity {
    private String name;
    private Long limitValue;
    private String unit;
    private BigInteger fee;

    @ManyToOne
    @JoinColumn(name = "service_fee_id")
    private ServiceFeeEntity serviceFee;
}
