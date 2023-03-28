package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "service_fee")
@Getter
@Setter
public class ServiceFeeEntity extends BaseEntity{
    private String name;
}
