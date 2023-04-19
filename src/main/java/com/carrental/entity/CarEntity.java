package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "car")
@Getter
@Setter
public class CarEntity extends BaseEntity implements Serializable {
    //    @EmbeddedId
//    private CarId ids;
    private String plate;
    private String description;
    private Integer yearOfManufacture;
    private Integer seats;
    private String color;
    private String engine;
    private Double fuelConsumption;
    private String transmission;
    private String rentalStatus;
    private Boolean isFastRent;

    @OneToOne
    @JoinColumn(name = "service_id")
    private ServiceFeeEntity service;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private ModelEntity model;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @OneToMany(mappedBy = "cars")
    private Collection<FeatureEntity> features;

    @OneToMany(mappedBy = "car")
    private Collection<FavoriteCar> favorites;
    @OneToMany(mappedBy = "car")
    private Collection<CarRentalEntity> rentals;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private DeliveryAddressEntity address;

}
