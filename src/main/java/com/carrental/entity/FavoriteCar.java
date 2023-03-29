package com.carrental.entity;

import com.carrental.entity.BaseEntity;
import com.carrental.entity.CarEntity;
import com.carrental.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favorite_car")
@Getter
@Setter
public class FavoriteCar extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
