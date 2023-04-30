package com.carrental.entity;

import com.carrental.enums.CarStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "car")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity extends BaseEntity implements Serializable {
    //    @EmbeddedId
//    private CarId ids;
    private String plate;
    private String description;
    private Integer yearOfManufacture;
    private Integer seats;
    private String color;
    private String fuel;
    private Double fuelConsumption;
    private String transmission;
    private String policies;
    private Boolean isFastRent;
    @Enumerated(EnumType.ORDINAL)
    private CarStatus status;

    @OneToOne(targetEntity = ServiceFeeEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    private ServiceFeeEntity service;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private ModelEntity model;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @OneToMany(mappedBy = "cars", cascade = CascadeType.ALL)
    private Collection<FeatureEntity> features;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Collection<FavoriteCar> favorites;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Collection<CarRatingEntity> ratings;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Collection<CarRentalEntity> rentals;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private DeliveryAddressEntity address;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity user;

    @OneToMany(mappedBy = "car", targetEntity = CarImagesEntity.class, cascade = CascadeType.ALL)
    private List<CarImagesEntity> images;

}
