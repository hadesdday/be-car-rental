package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "car_images")
@Getter
@Setter
public class CarImagesEntity extends BaseEntity{
    private String imageUrl;
    private String status;
    private Boolean isThumbnail;
}
