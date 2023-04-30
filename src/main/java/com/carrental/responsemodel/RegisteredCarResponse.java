package com.carrental.responsemodel;

import com.carrental.entity.CarImagesEntity;
import com.carrental.enums.CarStatus;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Data
public class RegisteredCarResponse {
    private Long id;
    private String name;
    private BigInteger defaultPrice;
    private CarStatus status;
    private Long totalRental;
    private Double avgRating;
    private String imageUrl;

    public RegisteredCarResponse(Long id, String name, BigInteger defaultPrice, CarStatus status, Long totalRental, Double avgRating, String imageUrl) {
        this.id = id;
        this.name = name;
        this.defaultPrice = defaultPrice;
        this.status = status;
        this.totalRental = totalRental;
        this.avgRating = avgRating;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigInteger defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public Long getTotalRental() {
        return totalRental;
    }

    public void setTotalRental(Long totalRental) {
        this.totalRental = totalRental;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
