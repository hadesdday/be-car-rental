package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ward")
@Getter
@Setter
public class WardEntity extends BaseEntity {
    private String name;
    private String prefix;
    @ManyToOne
    @JoinColumn(name = "district_id")
    private DistrictEntity district;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private ProvinceEntity provinceWard;
}
