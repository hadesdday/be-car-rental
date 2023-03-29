package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "travel_type")
@Getter
@Setter
public class TravelTypeEntity extends BaseEntity {
    private String name;
    private String code;

    @ManyToOne
    @JoinColumn(name = "service_type_id")
    private ServiceTypeEntity service;
}
