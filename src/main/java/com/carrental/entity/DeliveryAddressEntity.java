package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery_address")
@Getter
@Setter
public class DeliveryAddressEntity extends BaseEntity {
    private String addressName;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private ProvinceEntity province;
    @ManyToOne
    @JoinColumn(name = "district_id")
    private DistrictEntity district;
    @ManyToOne
    @JoinColumn(name = "ward_id")
    private WardEntity ward;

    @OneToMany(mappedBy = "address")
    private List<CarEntity> cars;
}
