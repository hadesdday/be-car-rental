package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "service_type")
@Getter
@Setter
public class ServiceTypeEntity extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "service")
    private Collection<TravelTypeEntity> travel;

    @OneToMany(mappedBy = "serviceType")
    private Collection<ServiceFeeEntity> serviceFees;
}
