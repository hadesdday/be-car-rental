package com.carrental.responsemodel;

import com.carrental.enums.RentalStatus;

import java.util.Date;

public class RentalDetailsResponse {
    private Long id;
    private String model;
    private Date startDate;
    private Date endDate;
    private Double avgRating;
    private String customerName;
    private String customerPhone;
    private RentalStatus status;
    private Long distanceLimit;
    private Double price;
    private String bannerUrl;
    private Date createdDate;

    public RentalDetailsResponse(Long id, String model, Date startDate, Date endDate, Double avgRating, String customerName, String customerPhone, RentalStatus status, Long distanceLimit, Double price, String bannerUrl, Date createdDate) {
        this.id = id;
        this.model = model;
        this.startDate = startDate;
        this.endDate = endDate;
        this.avgRating = avgRating;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.status = status;
        this.distanceLimit = distanceLimit;
        this.price = price;
        this.bannerUrl = bannerUrl;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    public Long getDistanceLimit() {
        return distanceLimit;
    }

    public void setDistanceLimit(Long distanceLimit) {
        this.distanceLimit = distanceLimit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
