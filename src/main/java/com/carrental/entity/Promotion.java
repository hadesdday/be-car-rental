package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "promotion")
@Getter
@Setter
public class Promotion extends BaseEntity {
    private String title;
    private String content;
    private int quantity;
    private int discountPercent;

    @Column
    private Date startDate;
    @Column
    private Date endDate;
    
}
