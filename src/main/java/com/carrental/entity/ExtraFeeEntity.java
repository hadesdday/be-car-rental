package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "model")
@Getter
@Setter
public class ModelEntity extends BaseEntity{
    private String name;
    private String type;
}
