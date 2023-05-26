package com.carrental.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "extra_fee")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtraFeeEntity extends BaseEntity {
    private String name;
    private Long limitValue;
    private String unit;
    private BigInteger fee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_fee_id")
    private ServiceFeeEntity serviceFee;
}
