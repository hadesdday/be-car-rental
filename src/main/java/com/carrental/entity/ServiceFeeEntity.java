package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "extra_fee")
@Getter
@Setter
public class ExtraFeeEntity extends BaseEntity{
    private String name;
    private Integer type;
    private String unit;
    private Integer fee;
}
